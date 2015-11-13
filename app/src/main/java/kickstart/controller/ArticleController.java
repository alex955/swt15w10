package kickstart.controller;

import java.util.LinkedList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kickstart.model.Category;
import kickstart.model.CategoryRepo;
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
}
