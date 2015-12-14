package kickstart.controller;

import java.util.LinkedList;
import java.util.Optional;

import kickstart.model.*;

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
import kickstart.utilities.CategoryMethods;
import kickstart.model.ArticleRepo;
import javax.validation.Valid;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
	
	
    @Autowired
    private final UserRepository userRepository;
    
	@Autowired
	private final CategoryRepo categories;
	
	@Autowired
	private final ArticleRepo articleRepo;

	@Autowired private final CategoryMethods categoryMethods;
	
	protected LinkedList<CategoryFirstTierObject> processedCategories; 
	
	@Autowired
	private UserAccountManager userAccountManager;
    
	@Autowired
	public AdminController(CategoryRepo categories, ArticleRepo articleRepo, UserRepository userRepository, UserAccountManager userAccountManager, CategoryMethods categoryMethods){
		this.categories = categories;
		this.articleRepo=articleRepo;
		this.userRepository = userRepository;
		this.userAccountManager = userAccountManager;
		this.categoryMethods = categoryMethods;
	}


	
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
		

		this.processedCategories = categoryMethods.getProcessedCategories();
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
        
        return "admin";
    }
    @RequestMapping(value="/admin/addSubcategory/{id}", method=RequestMethod.POST)
    public String addSubcategory(@ModelAttribute Category newCategory, Model model, @PathVariable Long id) {
    	Category toSave = new Category();
    	toSave.setName(newCategory.getName());
    	toSave.setPredecessor(id);
    	toSave.setRoot(false);
    	
    	this.categories.save(toSave);
    	
        return "redirect:/admin/inspectCategory/{id}";
    }
	
	
    @RequestMapping(value="/admin/addRootCat", method=RequestMethod.POST)
    public String addRootCategory(@ModelAttribute Category category, Model model) {
    	category.setPredecessor(-1);
    	category.setRoot(true);
        this.categories.save(category);
        System.out.println("added category");
        //model.addAttribute("categories", categories.findAll());
        
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
		

		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		
		
		model.addAttribute("category", currCat);
		model.addAttribute("subcategories", subcategories);

        model.addAttribute("newCategory", new Category());
		
		return "adminSubcategory";
	}
	
	@RequestMapping(value = "/admin/deactivateUser/{id}")
	public String deactivateUser(@PathVariable Long id) {
		this.userAccountManager.disable(this.userRepository.findOne(id).getUserAccount().getIdentifier());
		return "redirect:/admin";
	}
	
	@RequestMapping(value = "/admin/activateUser/{id}")
	public String activateUser(@PathVariable Long id) {
		this.userAccountManager.enable(this.userRepository.findOne(id).getUserAccount().getIdentifier());
		return "redirect:/admin";
	}
	
	@RequestMapping(value = "/admin/editUser/{id}")
	public String editUser(@PathVariable Long id, Model model) {
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		
		User user = this.userRepository.findOne(id);
        model.addAttribute("user", user);
		
		return "admin/adminEditUser";
	}

	@RequestMapping(value="/admin/editUser/{id}",method=RequestMethod.POST)
	public String processEditUser(@PathVariable Long id, @Valid UserSettingsForm userSettingsForm, BindingResult result) {
		User user = this.userRepository.findOne(id);

		System.out.println(user.toString());
		
		
		if(!userSettingsForm.getNewFirstName().isEmpty())
			user.setFirstName(userSettingsForm.getNewFirstName());
		
		if(!userSettingsForm.getNewLastName().isEmpty())
			user.setLastName(userSettingsForm.getNewLastName());

		if(!userSettingsForm.getNewCity().isEmpty())
			user.setCity(userSettingsForm.getNewCity());

		if(!userSettingsForm.getNewZip().isEmpty())
			user.setZip(userSettingsForm.getNewZip());

		if(!userSettingsForm.getNewStreetName().isEmpty())
			user.setStreetName(userSettingsForm.getNewStreetName());

		if(!userSettingsForm.getNewHouseNumber().isEmpty())
			user.setHouseNumber(userSettingsForm.getNewHouseNumber());

		if(!userSettingsForm.getNewAddressAddition().isEmpty())
			user.setAddressAddition(userSettingsForm.getNewAddressAddition());

		//Email-Änderung

		if(!userSettingsForm.getNewEmail().isEmpty())
			user.setEmail(userSettingsForm.getNewEmail());

		//Passwort-Änderung
		if(!userSettingsForm.getNewPassword().isEmpty() && userSettingsForm.getNewPassword().equals(userSettingsForm.getConfirmPW()) ){
			userAccountManager.changePassword(user.getUserAccount(), userSettingsForm.getNewPassword());
		}

		//Sprachenänderung
		if(userSettingsForm.getNewLanguage1().equals("null")){
			user.setLanguage1(null);
		} else {
			user.setLanguage1(userSettingsForm.getNewLanguage1());
		}

		if(userSettingsForm.getNewLanguage2().equals("null")){
			user.setLanguage2(null);
		} else {
			user.setLanguage2(userSettingsForm.getNewLanguage2());
		}

		if(userSettingsForm.getNewLanguage3().equals("null")){
			user.setLanguage3(null);
		} else {
			user.setLanguage3(userSettingsForm.getNewLanguage3());
		}

		userAccountManager.save(user.getUserAccount());
		userRepository.save(user);


		return "redirect:/admin";
	}
	
}
