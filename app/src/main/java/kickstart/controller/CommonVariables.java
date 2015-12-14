package kickstart.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import kickstart.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class CommonVariables {
    @Autowired
    protected UserRepository userRepository;
    
	@Autowired
	protected CategoryRepo categories;
	
	@Autowired
	protected ArticleRepo articleRepo;

	@Autowired
	protected SettingsRepo settingsRepo;

	
	
	protected long current_cat=0;
	

	protected LinkedList<CategoryFirstTierObject> processedCategories; 
	
	protected LinkedList<CategoryFirstTierObject> getProcessedCategories(){
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
	
	public Map<String, List<Category>> getCategoryMap(){
		Map<String, List<Category>> toReturn = new HashMap<String, List<Category>>();
		Iterable<Category> allCategories = this.categories.findAll();
		
		for(Category s : this.categories.findAll()){
			long currCat = s.getId();
			LinkedList<Category> putList = getChildren(currCat);
			toReturn.put(s.getName(), putList);
		}
		
		return toReturn;
	}
	
	public LinkedList<Category> getChildren(long id){
		LinkedList<Category> toReturn = new LinkedList<Category>();
		for(Category c : this.categories.findAll()){
			if(c.getPredecessor() == id) toReturn.add(c);
		}
		
		return toReturn;
		
	}

	public Model getCurrent_cat(Model model) {
		if (current_cat==0) model.addAttribute("current_category",new Category("Alle Kategorien", 0));
		else model.addAttribute("current_category",this.categories.findOne(current_cat).get());
		
		return model;
	}

	public void setCurrent_cat(long current_cat) {
		if (this.categories.findOne(current_cat).get().getId()==current_cat) this.current_cat = current_cat; 
		else throw new IllegalArgumentException("Kategorie ID existiert nicht!");
	}
	
	public Model cat_exchange(Model model){
		if (current_cat==0) model.addAttribute("current_category",new Category("Alle Kategorien", 0));
		else model.addAttribute("current_category",this.categories.findOne(current_cat).get());
		
		return model;
		
	}
	
	public Long getCurrent_cat(){
			return current_cat;	
			}
	
	
}
