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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kickstart.model.CategoryRepo;
import kickstart.model.Category;
import kickstart.model.CategoryFirstTierObject;

@Controller
public class WelcomeController extends CommonVariables {
	
	//Classvars

	
	//Constructor, insert autowired variables here (?)
	@Autowired
	public WelcomeController(CategoryRepo categories){
		this.categories = categories;
	}
	
	//help functions

	
	//Mappings
	@RequestMapping({"/", "/frontpage"})
	public String frontPage(Model model) {
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		
		model=this.getCurrent_cat(model);
		return "frontpage";
	}
	
	@RequestMapping("/search")
	public String search(Model model) {
		this.processedCategories = this.getProcessedCategories();
		//System.out.println("size of root categories: " + this.processedCategories.size());
		model.addAttribute("categories", this.processedCategories);
		
		 model.addAttribute("anzeigen", articleRepo.findAll());

		   // System.out.println(s"GOOD REPO WURDE AN html übergeben");
		 
		 model=this.getCurrent_cat(model);
		 
		return "search";
	}
	
	@RequestMapping("/article")
	public String article(Model model){
		//initiate categories
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		
		model=this.getCurrent_cat(model);
		
		return "article";
	}
	
	@RequestMapping("/initiatecategory")
	public String registration(Model model){
		//initiate categories
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		
		model=this.getCurrent_cat(model);
		
		return "initiatecategory";
	}
	
	
	@RequestMapping("/chat")
	public String chat(Model model){
		//initiate categories
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		
		model=this.getCurrent_cat(model);
		
		return "chat";
	}
	
	//Mappings end
}
