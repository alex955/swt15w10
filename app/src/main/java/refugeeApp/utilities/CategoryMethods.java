package refugeeApp.utilities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import refugeeApp.model.ArticleRepo;
import refugeeApp.model.Category;
import refugeeApp.model.CategoryFirstTierObject;
import refugeeApp.model.CategoryRepo;
import refugeeApp.model.UserRepository;

@Component
public class CategoryMethods {
	
	@Autowired private final CategoryRepo categories;
	
	/**
	 * autowired constructor
	 * @param userAccountManager
	 * @param userRepository
	 * @param categories
	 * @param goodREPO
	 */
	@Autowired
	public CategoryMethods(UserAccountManager userAccountManager, UserRepository userRepository,
			CategoryRepo categories, ArticleRepo goodREPO) {
		this.categories = categories;
	}
	

	protected LinkedList<CategoryFirstTierObject> processedCategories; 
	

	/**
	 * processes categories into hierarchical CategoryFirstTierObject
	 * @return List of Hierarchical objects
	 */
	public LinkedList<CategoryFirstTierObject> getProcessedCategories(){
		LinkedList<CategoryFirstTierObject> toReturn = new LinkedList<CategoryFirstTierObject>();
		Iterable<Category> foundCategories = categories.findAll();
		
		for(Category s : foundCategories){
			if(s.getRoot()) {
				LinkedList<Category> subcats = new LinkedList<Category>();
				
				
				for(Category t : foundCategories){
					if(t.getPredecessor() == s.getId()){
						subcats.add(t);
					}
				}
				
				CategoryFirstTierObject toAdd = new CategoryFirstTierObject(s, subcats);
				toReturn.add(toAdd);
			}
			else{
			}
		}
		return toReturn;
	}
	
	/**
	 * 
	 * @return Mapping of subcategories by name of rootcategory
	 */
	public Map<String, List<Category>> getCategoryMap(){
		Map<String, List<Category>> toReturn = new HashMap<String, List<Category>>();
		
		for(Category s : this.categories.findAll()){
			long currCat = s.getId();
			LinkedList<Category> putList = getChildren(currCat);
			toReturn.put(s.getName(), putList);
		}
		
		return toReturn;
	}
	
	/**
	 * 
	 * @param id category id
	 * @return List of children of certain category
	 */
	public LinkedList<Category> getChildren(long id){
		LinkedList<Category> toReturn = new LinkedList<Category>();
		for(Category c : this.categories.findAll()){
			if(c.getPredecessor() == id) toReturn.add(c);
		}
		
		return toReturn;
		
	}	
}
