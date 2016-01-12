package refugeeApp.utilities;

import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import refugeeApp.model.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The Class CategoryMethods.
 */
@Component
public class CategoryMethods {
	
	/** The categories. */
	private final CategoryRepo categories;
	
	/**
	 * autowired constructor.
	 *
	 * @param userAccountManager the user account manager
	 * @param userRepository the user repository
	 * @param categories the categories
	 * @param goodREPO the good repo
	 */
	@Autowired
	public CategoryMethods(UserAccountManager userAccountManager, UserRepository userRepository,
			CategoryRepo categories, ArticleRepo goodREPO) {
		this.categories = categories;
	}

	/**
	 * processes categories into hierarchical CategoryFirstTierObject.
	 *
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
		}
		return toReturn;
	}
	
	/**
	 * Gets the category map.
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
	 * Gets the children.
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
