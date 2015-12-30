package kickstart.controller;



import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kickstart.model.Category;
import kickstart.model.SearchQuery;
import kickstart.model.User;
import kickstart.model.Article;


@Controller
public class SearchController extends CommonVariables {
			
	
	public List<Article> getAllSubcategoryItems(long subcatId){
		List<Article> toReturn = getAllCategoryItems(subcatId);
		
		List<Long> subcategories = new LinkedList<Long>();
		for(Category s : categories.findAll()){
			if(s.getPredecessor() == subcatId){
				subcategories.add(s.getId());
				//System.out.println("\tsubcategory zum durchschauen: "+s.getName());
			}
		}
		
		for(Long l : subcategories){
			toReturn.addAll(getAllSubcategoryItems(l));
		}
		
		return toReturn;
	}
	

	public List<Article> getAllCategoryItems(long subcatID){
		return this.articleRepo.findByCategory(subcatID);
	}
	
   //@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searcharticle(@ModelAttribute("SearchQuery") SearchQuery SearchQuery, Model model ) {
	
		
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
		model=this.getCurrent_cat(model);
		SearchQuery.setCategory(this.getCurrent_cat());
		
		System.out.println("Gesucht "+ SearchQuery.getQuery() +" in "+SearchQuery.getCategory());
		List<Article> catGoods = new LinkedList<Article>();
		
		if (SearchQuery.getCategory()==0) { catGoods = this.articleRepo.findAll() ;}
				else { catGoods = articleRepo.findByCategory(SearchQuery.getCategory()); }
		System.out.println("Length of list: " + catGoods.size());
		 
		List<Article> output = new LinkedList<Article>();
 		   
		   int count = 1;
		   for (Article good : catGoods) 
		     { System.out.println(count); count++;
		     System.out.println(good.getTitle()+"    "+good.getCategory());
		  
		     	// Überprüfung ob Name gleich
		     	if (good.getTitle().toLowerCase().contains(SearchQuery.getQuery().toLowerCase())) { System.out.println("match"); output.add(good);} else System.out.println("dismatch"); 
		     	// Überprüfung ob Suchtext in Description
		     	if (good.getDescription().toLowerCase().contains(SearchQuery.getQuery().toLowerCase()) && !output.contains(good)) output.add(good);
		     }
		 
		  
		   
		   model.addAttribute("anzeigen", output);	
		   
		   return "search";
		}
			
			
	@RequestMapping(value = "/search/{category}/{page}")
	public String searchByCategory(@PathVariable("page") int page, @PathVariable("category") Long catID, Model model){  
		System.out.println("Es wird in der Kategorie "+catID+" gesucht");
	
		//setzt aktuelle kategorie auf catID
		this.setCurrent_cat(catID);
		
		List<Article> catGoods = this.getAllSubcategoryItems(catID);
	
		
		System.out.println("Anzahl an Artikeln: " + catGoods.size());
		
		
		//whereAmI is part of the page mapping to distinguish whether the url looks like
		//search/0
		//or search/myOffers/0
		//it builds the url for the mapping + page number
		String whereAmI = "search/"+catID;
		model.addAttribute("currentPage", page);
		model.addAttribute("whereAmI", whereAmI);
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("anzeigen", catGoods);
		
		model=this.getCurrent_cat(model);
		
		return "search";
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/search/myOffers/{page}")
	public String searchByUser(@PathVariable("page") int page, Model model, @LoggedIn Optional<UserAccount> userAccount){  
	
		User creator = userRepository.findByUserAccount(userAccount.get());
		Pageable pageRequest = new PageRequest(page, 5);
		Page<List<Article>> articles = articleRepo.findByCreator(creator, pageRequest);
				
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("anzeigen", articles);
		model.addAttribute("currentPage", page);
		//whereAmI is part of the page mapping to distinguish whether the url looks like
		//search/0
		//or search/myOffers/0
		//it builds the url for the mapping + page number
		String whereAmI = "search/myOffers";
		model.addAttribute("whereAmI", whereAmI);
		model=this.getCurrent_cat(model);

		return "search";
	}
}
