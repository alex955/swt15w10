package kickstart.controller;

import java.util.LinkedList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kickstart.utilities.CategoryMethods;
import kickstart.model.ArticleRepo;
import kickstart.model.CategoryFirstTierObject;

@Controller
public class Frontpage {
	
	@Autowired private final CategoryMethods categoryMethods;
	protected LinkedList<CategoryFirstTierObject> processedCategories; 
	
	@Autowired
	public Frontpage(CategoryMethods categoryMethods, ArticleRepo articleRepo){
		this.categoryMethods = categoryMethods;
	}
		
	@RequestMapping({"/", "/frontpage"})
	public String frontPage(Model model) {
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		return "frontpage";
	}
}
