package kickstart.controller;



import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kickstart.model.Good;


@Controller
public class SearchController extends CommonVariables {

	
	//@RequestMapping(value = "/search?{name}?{plz}?{distance}?{search}?{category}")
	
			@RequestMapping(value = "/search/{text}/{category}")
			public String suche_via_name_category(@PathVariable("text") String text, @PathVariable("category") Long catID, Model model)
			{  System.out.println("Es wird nach "+text+" in der Kategorie "+catID+" gesucht");
				
				List<Good> catGoods = goodREPO.findByCategory(catID);
				System.out.println("Length of list: " + catGoods.size());
			 
			   List<Good> übergabe = new LinkedList<Good>();
	 		   
			   int count = 1;
			   for (Good good : catGoods) 
			     { System.out.println(count); count++;
			     System.out.println(good.getName()+"    "+good.getCategory());
			  
			     	// Überprüfung ob Name gleich
			     	if (good.getName().toLowerCase().contains(text.toLowerCase())) { System.out.println("match"); übergabe.add(good);} else System.out.println("dismatch"); 
			     	// Überprüfung ob Suchtext in Description
			     	if (good.getDescription().toLowerCase().contains(text.toLowerCase()) && !übergabe.contains(good)) übergabe.add(good);
			     
			     }
			 
			  
			   
			   model.addAttribute("anzeigen", übergabe);

			    return "search";
			}
	
}
