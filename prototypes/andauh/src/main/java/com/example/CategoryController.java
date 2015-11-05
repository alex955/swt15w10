package com.example;

import java.util.LinkedList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {
	
	@Autowired
	private final Categories categories;
	
	@RequestMapping("/")
	public String firstCall() {
		return "redirect:/categories";
	}
	
	@Autowired
	public CategoryController(Categories categories){
		this.categories = categories;
    	System.out.println("wird this.categories zugewiesen");

	}

	@RequestMapping(value = "/categories")
    public String firstView(Model model) {
		
		LinkedList<Category> testList = new LinkedList<Category>();
		Iterable<Category> testSet = categories.findAll();
		
        int rootCount = 0;
        int subCount = 0;
		
		for(Category s : testSet){
			if(s.getRoot()) {
				testList.add(s);
				rootCount++;
			}
			else{
				subCount++;
			}
		}
		
        model.addAttribute("categories", testList);
        model.addAttribute("newCategory", new Category());
        model.addAttribute("rootCount", rootCount);
        model.addAttribute("subCount", subCount);

        return "categories";
    }
	
    @RequestMapping(value="/categories", method=RequestMethod.POST)
    public String secondView(@ModelAttribute Category category, Model model) {
    	category.setPredecessor(-1);
    	category.setRoot(true);
        this.categories.save(category);
        System.out.println("added category");
        //model.addAttribute("categories", categories.findAll());
        return "redirect:/categories";
    }
    
	@RequestMapping(value = "/categories/delete/{id}")
	public String deleteCategory(@PathVariable Long id) {
		System.out.println("delete category with id " + id);
		categories.delete(id);
		deleteSubcategories(id);
		return "redirect:/categories";
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
	
	@RequestMapping(value = "/inspectcategory/{id}")
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
		
		return "subcategory";
	}
	
    @RequestMapping(value="/inspectcategory/add/{id}", method=RequestMethod.POST)
    public String addSubcategory(@ModelAttribute Category newCategory, Model model, @PathVariable Long id) {
    	Category toSave = new Category();
    	toSave.setName(newCategory.getName());
    	toSave.setPredecessor(id);
    	toSave.setRoot(false);
    	
    	this.categories.save(toSave);
    	
        return "redirect:/inspectcategory/{id}";
    }


}
