/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kickstart.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import kickstart.model.CategoryRepo;
import kickstart.utilities.CategoryMethods;
import kickstart.model.ArticleRepo;
import kickstart.model.Category;
import kickstart.model.CategoryFirstTierObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WelcomeController {
	
	@Autowired private final CategoryRepo categories;
	@Autowired private final CategoryMethods categoryMethods;
	@Autowired private final ArticleRepo articleRepo;

	//Classvars
	protected LinkedList<CategoryFirstTierObject> processedCategories; 
	
	
	//Constructor, insert autowired variables here (?)
	@Autowired
	public WelcomeController(CategoryRepo categories, CategoryMethods categoryMethods, ArticleRepo articleRepo){
		this.categories = categories;
		this.categoryMethods = categoryMethods;
		this.articleRepo = articleRepo;
	}
	
	//help functions

	
	//Mappings
	@RequestMapping({"/", "/frontpage"})
    public String frontPage(Model model, ModelMap modelMap) {
        this.processedCategories = categoryMethods.getProcessedCategories();
        model.addAttribute("categories", this.processedCategories);

        return "frontpage";
    }

	@RequestMapping("/search")
	public String search(Model model) {
		this.processedCategories = categoryMethods.getProcessedCategories();
		//System.out.println("size of root categories: " + this.processedCategories.size());
		model.addAttribute("categories", this.processedCategories);
		
		 model.addAttribute("anzeigen", articleRepo.findAll());
		 
		return "search";
	}
	
	@RequestMapping("/article")
	public String article(Model model){
		//initiate categories
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		
		return "article";
	}
	
	@RequestMapping("/initiatecategory")
	public String registration(Model model){
		//initiate categories
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		
		return "initiatecategory";
	}
	
	
	@RequestMapping("/chat")
	public String chat(Model model){
		//initiate categories
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		
		return "chat";
	}
	
	@RequestMapping("/test")
	public String testShit(Model model){
		//initiate categories
		Map<String, List<Category>> test = categoryMethods.getCategoryMap();
		
		model.addAttribute("map", test);
		model.addAttribute("testVar", new String("test123"));
		
		return "utilities/testShit";
	}

	
	//Mappings end
}
