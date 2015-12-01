package kickstart.controller;

import java.util.LinkedList;
import java.util.Optional;

import kickstart.model.*;

import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import kickstart.model.Category;
import kickstart.model.CategoryFirstTierObject;
import kickstart.model.CategoryRepo;
import kickstart.model.UserRepository;
import kickstart.model.ArticleRepo;
import javax.validation.Valid;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController extends CommonVariables {
    
	@Autowired
	public AdminController(CategoryRepo categories, ArticleRepo articleRepo, UserRepository userRepository, UserAccountManager userAccountManager){
		this.categories = categories;
		this.articleRepo=articleRepo;
		this.userRepository = userRepository;
		this.userAccountManager = userAccountManager;
	}

	@Autowired
	private UserAccountManager userAccountManager;
	@RequestMapping(value = "/admin")
    public String initialView(Model model) {
		
		LinkedList<CategoryFirstTierObject> testList = new LinkedList<CategoryFirstTierObject>();
		Iterable<Category> testSet = categories.findAll();
		
        int rootCount = 0;
        int subCount = 0;
		
		for(Category s : testSet){
			if(s.getRoot()) {
				LinkedList<Category> subcats = new LinkedList<Category>();
				
				//check subcats
				for(Category t : testSet){
					if(t.getPredecessor() == s.getId()){
						subcats.add(t);
					}
				}
				
				CategoryFirstTierObject toAdd = new CategoryFirstTierObject(s, subcats);
				testList.add(toAdd);
				rootCount++;
			}
			else{
				subCount++;
			}
		}
		
		int totalCount = rootCount + subCount;
		

		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		
        model.addAttribute("categoriesAdmin", testList);
        model.addAttribute("newCategory", new Category());
        model.addAttribute("rootCount", rootCount);
        model.addAttribute("subCount", subCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("registeredUsers", this.userRepository.findAll());
        
//        for(User b : this.userRepository.findAll()){
//        	b.getCity();
//        }
        
        
        model=this.getCurrent_cat(model);
        return "admin";
    }
    @RequestMapping(value="/admin/addSubcategory/{id}", method=RequestMethod.POST)
    public String addSubcategory(@ModelAttribute Category newCategory, Model model, @PathVariable Long id) {
    	Category toSave = new Category();
    	toSave.setName(newCategory.getName());
    	toSave.setPredecessor(id);
    	toSave.setRoot(false);
    	
    	this.categories.save(toSave);
    	
    	model=this.getCurrent_cat(model);
        return "redirect:/admin/inspectCategory/{id}";
    }
	
	
    @RequestMapping(value="/admin/addRootCat", method=RequestMethod.POST)
    public String addRootCategory(@ModelAttribute Category category, Model model) {
    	category.setPredecessor(-1);
    	category.setRoot(true);
        this.categories.save(category);
        System.out.println("added category");
        //model.addAttribute("categories", categories.findAll());
        
        model=this.getCurrent_cat(model);
        return "redirect:/admin";
    }
    

    
	@RequestMapping(value = "/admin/deleteCategory/{id}")
	public String deleteCategory(@PathVariable Long id) {
		System.out.println("delete category with id " + id);
		categories.delete(id);
		deleteSubcategories(id);
		return "redirect:/admin";
	}
	
	public void deleteSubcategories(long id){
		for(Category s : categories.findAll()){
			if(s.getPredecessor() == id){
				long idToDelete = s.getId();
				System.out.println("delete subcategory with id " + idToDelete);
				categories.delete(idToDelete);
				deleteSubcategories(idToDelete);
			}
		}
	}
	
	@RequestMapping(value = "/admin/inspectCategory/{id}")
	public String showSubcategories(@PathVariable Long id, Model model, @ModelAttribute Category category) {
		
		Optional<Category> currOpt = categories.findOne(id);
		Category currCat = currOpt.get();
		
		LinkedList<Category> subcategories = new LinkedList<Category>();
		
		for(Category f : categories.findAll()){
			if(f.getPredecessor() == id) subcategories.add(f);
		}
		

		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		
		
		model.addAttribute("category", currCat);
		model.addAttribute("subcategories", subcategories);

        model.addAttribute("newCategory", new Category());
		
        model=this.getCurrent_cat(model);
		return "adminSubcategory";
	}
	
	@RequestMapping(value = "/admin/deleteUser/{id}")
	public String deleteUser(@PathVariable Long id) {
		this.userRepository.delete(id);
		return "redirect:/admin";
	}
	
	@RequestMapping(value = "/admin/editUser/{id}")
	public String editUser(@PathVariable Long id, Model model) {
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model=this.getCurrent_cat(model);
		
		User user = this.userRepository.findOne(id);
        model.addAttribute("user", user);
		
		return "admin/adminEditUser";
	}

	@RequestMapping(value="/admin/editUser/{id}",method=RequestMethod.POST)
	public String processEditUser(@PathVariable Long id, @Valid UserSettings userSettings, BindingResult result) {


		User user = this.userRepository.findOne(id);

		System.out.println(user.toString());

		if(!userSettings.getNewCity().isEmpty())
			user.setCity(userSettings.getNewCity());

		if(!userSettings.getNewZip().isEmpty())
			user.setZip(userSettings.getNewZip());

		if(!userSettings.getNewStreetName().isEmpty())
			user.setStreetName(userSettings.getNewStreetName());

		if(!userSettings.getNewHouseNumber().isEmpty())
			user.setHouseNumber(userSettings.getNewHouseNumber());

		if(!userSettings.getNewAddressAddition().isEmpty())
			user.setAddressAddition(userSettings.getNewAddressAddition());

		//Email-Änderung

		if(!userSettings.getNewEmail().isEmpty())
			user.setEmail(userSettings.getNewEmail());

		//Passwort-Änderung
		if(!userSettings.getNewPassword().isEmpty()){
			userAccountManager.changePassword(user.getUserAccount(), userSettings.getNewPassword());

		}
			

		//Sprachenänderung
		if(!userSettings.getNewLanguage1().isEmpty())
			user.setLanguage1(userSettings.getNewLanguage1());

		if(!userSettings.getNewLanguage2().isEmpty())
			user.setLanguage2(userSettings.getNewLanguage2());

		if(!userSettings.getNewLanguage3().isEmpty())
			user.setLanguage3(userSettings.getNewLanguage3());

		userAccountManager.save(user.getUserAccount());
		userRepository.save(user);


		return "redirect:/admin";
	}
	
}
