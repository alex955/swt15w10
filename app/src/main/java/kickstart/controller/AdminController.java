package kickstart.controller;

import java.util.LinkedList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kickstart.model.Category;
import kickstart.model.CategoryFirstTierObject;
import kickstart.model.CategoryRepo;
import kickstart.model.activityREPO;
import kickstart.model.goodREPO;

@Controller
public class AdminController extends CommonVariables {
	@Autowired
	public AdminController(CategoryRepo categories, goodREPO grepo, activityREPO arepo){
		this.categories = categories;
		this.activityREPO=arepo;
		this.goodREPO=grepo;
	}
	
	@RequestMapping(value = "/admin")
    public String initialView(Model model) {
		
		LinkedList<CategoryFirstTierObject> testList = new LinkedList<CategoryFirstTierObject>();
		Iterable<Category> testSet = categories.findAll();
		
        int rootCount = 0;
        int subCount = 0;
		
		for(Category s : testSet){
			if(s.getRoot()) {
				LinkedList<Category> subcats = new LinkedList<Category>();
				
				//check subcats
				for(Category t : testSet){
					if(t.getPredecessor() == s.getId()){
						subcats.add(t);
					}
				}
				
				CategoryFirstTierObject toAdd = new CategoryFirstTierObject(s, subcats);
				testList.add(toAdd);
				rootCount++;
			}
			else{
				subCount++;
			}
		}
		
		int totalCount = rootCount + subCount;
		
        model.addAttribute("categories", testList);
        model.addAttribute("newCategory", new Category());
        model.addAttribute("rootCount", rootCount);
        model.addAttribute("subCount", subCount);
        model.addAttribute("totalCount", totalCount);

        return "admin";
    }
    @RequestMapping(value="/admin/addSubcategory/{id}", method=RequestMethod.POST)
    public String addSubcategory(@ModelAttribute Category newCategory, Model model, @PathVariable Long id) {
    	Category toSave = new Category();
    	toSave.setName(newCategory.getName());
    	toSave.setPredecessor(id);
    	toSave.setRoot(false);
    	
    	this.categories.save(toSave);
    	
        return "redirect:/admin/inspectCategory/{id}";
    }
	
	
    @RequestMapping(value="/admin/addRootCat", method=RequestMethod.POST)
    public String addRootCategory(@ModelAttribute Category category, Model model) {
    	category.setPredecessor(-1);
    	category.setRoot(true);
        this.categories.save(category);
        System.out.println("added category");
        //model.addAttribute("categories", categories.findAll());
        return "redirect:/admin";
    }
    

    
	@RequestMapping(value = "/admin/deleteCategory/{id}")
	public String deleteCategory(@PathVariable Long id) {
		System.out.println("delete category with id " + id);
		categories.delete(id);
		deleteSubcategories(id);
		return "redirect:/admin";
	}
	
	public void deleteSubcategories(long id){
		for(Category s : categories.findAll()){
			if(s.getPredecessor() == id){
				long idToDelete = s.getId();
				System.out.println("delete subcategory with id " + idToDelete);
				categories.delete(idToDelete);
				deleteSubcategories(idToDelete);
			}
		}
	}
	
	@RequestMapping(value = "/admin/inspectCategory/{id}")
	public String showSubcategories(@PathVariable Long id, Model model, @ModelAttribute Category category) {
		
		Optional<Category> currOpt = categories.findOne(id);
		Category currCat = currOpt.get();
		
		LinkedList<Category> subcategories = new LinkedList<Category>();
		
		for(Category f : categories.findAll()){
			if(f.getPredecessor() == id) subcategories.add(f);
		}
		
		model.addAttribute("category", currCat);
		model.addAttribute("subcategories", subcategories);

        model.addAttribute("newCategory", new Category());
		
		return "adminSubcategory";
	}
}
