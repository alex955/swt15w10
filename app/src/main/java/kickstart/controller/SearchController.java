package kickstart.controller;



import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kickstart.model.Category;
import kickstart.model.Good;


@Controller
public class SearchController extends CommonVariables {
			
	
	public List<Good> getAllSubcategoryItems(long subcatId){
		List<Good> toReturn = getAllCategoryItems(subcatId);
		
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
	

	public List<Good> getAllCategoryItems(long subcatID){
		return this.goodREPO.findByCategory(subcatID);
	}
	
	//@RequestMapping(value = "/search?{name}?{plz}?{distance}?{search}?{category}")
	
			@RequestMapping(value = "/search/{text}/{category}")
			public String suche_via_name_category(@PathVariable("text") String text, @PathVariable("category") Long catID, Model model)
			{  System.out.println("Es wird nach "+text+" in der Kategorie "+catID+" gesucht");
			//initiate categories
			this.processedCategories = this.getProcessedCategories();
			model.addAttribute("categories", this.processedCategories);
			model.addAttribute("categoriesForm", this.categories.findAll());
				
				List<Good> catGoods = goodREPO.findByCategory(catID);
				System.out.println("Length of list: " + catGoods.size());
			 
			   List<Good> output = new LinkedList<Good>();
	 		   
			   int count = 1;
			   for (Good good : catGoods) 
			     { System.out.println(count); count++;
			     System.out.println(good.getName()+"    "+good.getCategory());
			  
			     	// Überprüfung ob Name gleich
			     	if (good.getName().toLowerCase().contains(text.toLowerCase())) { System.out.println("match"); output.add(good);} else System.out.println("dismatch"); 
			     	// Überprüfung ob Suchtext in Description
			     	if (good.getDescription().toLowerCase().contains(text.toLowerCase()) && !output.contains(good)) output.add(good);
			     
			     }
			 
			  
			   
			   model.addAttribute("anzeigen", output);

			    return "search";
			}
			
			
			
			@RequestMapping(value = "/search/{category}")
			public String suche_via_categorie(@PathVariable("category") Long catID, Model model)
			{  System.out.println("Es wird in der Kategorie "+catID+" gesucht");
			
			//setzt aktuelle kategorie auf catID
			this.setCurrent_cat(catID);
			
			List<Good> catGoods = this.getAllSubcategoryItems(catID);
		
			
			System.out.println("Anzahl an Artikeln: " + catGoods.size());
			
			
			
			this.processedCategories = this.getProcessedCategories();
			model.addAttribute("categories", this.processedCategories);
			model.addAttribute("anzeigen", catGoods);
			
			model=this.getCurrent_cat(model);
			
			
			return "search";
			}
			 
			 
			
	
}
