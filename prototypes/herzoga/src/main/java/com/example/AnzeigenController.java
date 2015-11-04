package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AnzeigenController {
	
	@Autowired
	private final Anzeigen Anzeigen;
	
	@Autowired
	public AnzeigenController(Anzeigen Anzeigen){
		this.Anzeigen = Anzeigen;
    	System.out.println("wird this.anzeigen zugewiesen");

	}
	
	@RequestMapping("/")
	public String index() {
		return "alle";		// alle Artikel werden angezeigt

		
	}
	
	
	
	@RequestMapping(value = "/articles/delete/{id}", method = RequestMethod.GET)
	public String deleteArticle(@PathVariable Long id) {
		System.out.println("delete article with id " + id);
		Anzeigen.delete(id);
		return "alle";
	}
	
	@RequestMapping(value="/article", method=RequestMethod.POST)
    public String add(@ModelAttribute Article article, Model model) {
        this.Anzeigen.save(article);
        model.addAttribute("anzeigen", Anzeigen.findAll());
        return "alle";
    }
	
	
}
