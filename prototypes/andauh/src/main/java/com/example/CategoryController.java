package com.example;

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
        model.addAttribute("categories", categories.findAll());
        model.addAttribute("newCategory", new Category());
        return "categories";
    }
	
    @RequestMapping(value="/categories", method=RequestMethod.POST)
    public String secondView(@ModelAttribute Category category, Model model) {
        this.categories.save(category);
        model.addAttribute("categories", categories.findAll());
        return "categories2";
    }
    
	 //delete user
	@RequestMapping(value = "/categories/delete/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable Long id) {
		System.out.println("delete user with id " + id);
		categories.delete(id);
		return "redirect:/categories";
	}

}
