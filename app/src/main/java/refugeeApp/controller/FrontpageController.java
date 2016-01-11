package refugeeApp.controller;

import java.util.LinkedList;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import refugeeApp.model.ArticleRepo;
import refugeeApp.model.Category;
import refugeeApp.model.CategoryFirstTierObject;
import refugeeApp.model.Location;
import refugeeApp.utilities.CategoryMethods;

/**
 * The Class FrontpageController.
 */
@Controller
public class FrontpageController {
	
	/** The category methods. */
	@Autowired private final CategoryMethods categoryMethods;
	
	/** The processed categories. */
	protected LinkedList<CategoryFirstTierObject> processedCategories; 
	
	/**
	 * Instantiates a new frontpage controller.
	 *
	 * @param categoryMethods the category methods
	 * @param articleRepo the article repo
	 */
	@Autowired
	public FrontpageController(CategoryMethods categoryMethods, ArticleRepo articleRepo){
		this.categoryMethods = categoryMethods;
	}
	
	/**
	 * returns the frontpage with categories.
	 *
	 * @param model the model
	 * @return frontpage template
	 */
	@RequestMapping({"/", "/frontpage"})
	public String frontPage(Model model) {
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("current_category",new Category("AlleKategorien", 0));
		model.addAttribute("current_ort",new Location());
		return "frontpage";
	}
	
//	@RequestMapping({"/testVars"})
//	public String testVars(Model model) {
//		Locale currentLocale = LocaleContextHolder.getLocale();
//		model.addAttribute("currentLocale", currentLocale);
//		
//		Locale[] allLocales = Locale.getAvailableLocales();
//		model.addAttribute("allLocales", allLocales);
//		
//		return "testVars";
//	}
	
	
}
