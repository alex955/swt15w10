package kickstart.controller;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kickstart.model.ArticleRepo;
import kickstart.model.CategoryFirstTierObject;
import kickstart.model.CategoryRepo;
import kickstart.utilities.CategoryMethods;

@Controller
public class ChatController {
	
	@Autowired private final CategoryRepo categories;
	@Autowired private final CategoryMethods categoryMethods;
	@Autowired private final ArticleRepo articleRepo;

	//Classvars
	protected LinkedList<CategoryFirstTierObject> processedCategories;

	@Autowired
	public ChatController(CategoryRepo categories, CategoryMethods categoryMethods, ArticleRepo articleRepo) {
		this.categories = categories;
		this.categoryMethods = categoryMethods;
		this.articleRepo = articleRepo;
	} 
	
	
	@RequestMapping("/blablabla")
	public String initialChat(Model model) {
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		
		return "frontpage";
	}
	
	

}
