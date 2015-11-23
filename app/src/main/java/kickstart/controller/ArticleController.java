package kickstart.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kickstart.model.Anzeige;
import kickstart.model.Category;
import kickstart.model.CategoryRepo;
import kickstart.model.Good;
import kickstart.model.NewArticleForm;
import kickstart.model.RegistrationForm;
import kickstart.model.User;
import kickstart.model.activityREPO;
import kickstart.model.goodREPO;

@Controller
public class ArticleController extends CommonVariables {
	@Autowired
	public ArticleController(CategoryRepo categories, goodREPO grepo, activityREPO arepo){
		this.categories = categories;
		this.activityREPO=arepo;
		this.goodREPO=grepo;
	}
	
	
	@RequestMapping(value = "/inspectcategory/{categoryId}")
	public String showSubcategories(@PathVariable Long categoryId, Model model, @ModelAttribute Category category) {
		List<Good> catGoods = this.goodREPO.findByCategory(categoryId);
		
		//todo - ändern! nur Testinitialisierung
		catGoods.add(new Good("a", "b", "c", "d", 0, null, null, 1));
		catGoods.add(new Good("a", "b", "c", "d", 0, null, null, 1));
		catGoods.add(new Good("a", "b", "c", "d", 0, null, null, 1));
		catGoods.add(new Good("a", "b", "c", "d", 0, null, null, 1));
		
		System.out.println("Länge: "+catGoods.size());
		

		
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("anzeigen", catGoods);
		
		return "anzeigen";
	}
	
	@RequestMapping("/newArticle")
	public String newArticle(Model model){
		//initiate categories
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
		
		return "newArticle";
	}
	
	
	//create a new Good
	@RequestMapping(value = "/newArticle", method = RequestMethod.POST)
    public String newArticle(@ModelAttribute("NewArticleForm") NewArticleForm newArticleForm) {
    Good good = new Good(newArticleForm.getTitle(), newArticleForm.getDescription(), "01309", null, 0, null, null, newArticleForm.getId());
    goodREPO.save(good);
    System.out.println(good);
    return ("redirect:/");
}
}
