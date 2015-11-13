package kickstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kickstart.model.goodREPO;
import kickstart.model.Good;
import kickstart.model.activityREPO;

@Controller
public class AnzeigenController extends CommonVariables {


	
	@Autowired
	public AnzeigenController(goodREPO grepo, activityREPO arepo){
		this.activityREPO=arepo;
		this.goodREPO=grepo;
		System.out.println("GOOD REPO IST ANGELEGT UND CONTROLLER ZUGEWIESEN");
    	System.out.println(goodREPO.findAll().size());
    	System.out.println("ACT REPO IST ANGELEGT UND CONTROLLER ZUGEWIESEN");
    	System.out.println(activityREPO.findAll().size());
	}
	
	@RequestMapping(value = "/anzeigen")
	public String anzeigen_anzeigen(Model model) {
	    model.addAttribute("anzeigen", goodREPO.findAll());
	    model.addAttribute("newGood",new Good());
	    System.out.println("GOOD REPO WURDE AN search.html übergeben");
	 
	    return "anzeigen";
	}
	
	@RequestMapping(value = "/article?{id}")
	public String anzeige_anzeigen(@PathVariable("id") long id,Model model) {
	    model.addAttribute("Good", goodREPO.findOne(id));
	    System.out.println("GOOD"+id+ "WURDE AN article.html übergeben");
	 
	    return "article_anzeigen";
	}
	


	
	
	
}