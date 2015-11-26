package kickstart.controller;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kickstart.model.Category;
import kickstart.model.CategoryFirstTierObject;
import kickstart.model.CategoryRepo;
import kickstart.model.UserRepository;
import kickstart.model.ActivityRepo;
import kickstart.model.GoodRepo;

@Controller
public class CommonVariables {

    @Autowired
    protected UserRepository userRepository;
    
	@Autowired
	protected CategoryRepo categories;
	
	@Autowired
	protected GoodRepo goodREPO;
	@Autowired
	protected ActivityRepo activityREPO;
	

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
}
