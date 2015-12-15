package kickstart.controller;

import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.Optional;

import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kickstart.model.Article;
import kickstart.model.CategoryRepo;
import kickstart.model.NewArticleForm;
import kickstart.model.Picture;
import kickstart.model.PictureRepo;
import kickstart.model.User;
import kickstart.model.UserRepository;
import kickstart.utilities.CategoryMethods;
import kickstart.utilities.SettingsRepository;
import kickstart.model.ArticleRepo;
import kickstart.model.CategoryFirstTierObject;

@Controller
public class ArticleController {
	private PictureRepo pictureRepo;
    
	@Autowired
	private final CategoryRepo categories;
	
	@Autowired
	private final ArticleRepo articleRepo;

	@Autowired 
	private final CategoryMethods categoryMethods;
	
    @Autowired
    private final UserRepository userRepository;
    
    @Autowired 
    private final SettingsRepository settingsRepo;

	protected LinkedList<CategoryFirstTierObject> processedCategories; 
	
	@Autowired
	public ArticleController(CategoryRepo categories, ArticleRepo articleRepo, PictureRepo pictureRepo, CategoryMethods categoryMethods, UserRepository userRepository, SettingsRepository settingsRepo){
		this.categories = categories;
		this.articleRepo=articleRepo;
		this.pictureRepo = pictureRepo;
		this.categoryMethods = categoryMethods;
		this.userRepository = userRepository;
		this.settingsRepo = settingsRepo;
	}
	
	
	
	@RequestMapping(value = "/showArticle/{id}")
	public String showArticle(@PathVariable("id") long id,Model model, @LoggedIn Optional<UserAccount> userAccount) {
		//initiate categories
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
	    model.addAttribute("Article", articleRepo.findOne(id));
	    model.addAttribute("Creator", articleRepo.findOne(id).getCreator());
	    model.addAttribute("Useraccount", articleRepo.findOne(id).getCreator().getUserAccount());
	    
	    long currentUserId = -1;
		boolean isAdminLoggedIn = false;
	    
		//if any user is logged in, set values for vars
	    if(userAccount.isPresent()){
	    	currentUserId = userRepository.findByUserAccount(userAccount.get()).getId();
	    	
	    	if(userAccount.get().hasRole(new Role("ROLE_ADMIN"))) {
	    		isAdminLoggedIn = true;
	    	}
	    }
	    
	    model.addAttribute("currentUserId", currentUserId);
	    

		
		model.addAttribute("isAdminLoggedIn", isAdminLoggedIn);
	    return "article";
	}
	
	@RequestMapping(value = "/editArticle/{id}")
	public String editArticle(@PathVariable("id") long id, @LoggedIn Optional<UserAccount> userAccount, Model model) {
		this.processedCategories = categoryMethods.getProcessedCategories();
		long userId = this.userRepository.findByUserAccount(userAccount.get()).getId();
		
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
		model.addAttribute("editArticle", articleRepo.findOne(id));
		model.addAttribute("userId", userId);
		model.addAttribute("user", this.userRepository.findOne(userId));
		model.addAttribute("Creator", articleRepo.findOne(id).getCreator());
		
		boolean isAdminLoggedIn = false;
		if(userAccount.get().hasRole(new Role("ROLE_ADMIN"))) isAdminLoggedIn = true;
		
		model.addAttribute("isAdminLoggedIn", isAdminLoggedIn);
		
	    return "editArticle";
	}

	
	@RequestMapping(value = "/editArticle/{id}", method = RequestMethod.POST)
	public String processEditedArticle(@ModelAttribute("NewArticleForm") NewArticleForm newArticleForm, @PathVariable("id") long id, @LoggedIn Optional<UserAccount> userAccount, Model model){
		Article originalArticle = this.articleRepo.findOne(id);
		long currentUserId = this.userRepository.findByUserAccount(userAccount.get()).getId();
		
		//case: current user didnt create article && logged in user no admin -> end
		if(originalArticle.getCreator().getId() != currentUserId && !userAccount.get().hasRole(new Role("ROLE_ADMIN"))){
			return null;
		}
		
		//todo: validation
		
		
		originalArticle.setCategory(newArticleForm.getCategoryId());
		originalArticle.setTitle(newArticleForm.getTitle());
		originalArticle.setDescription(newArticleForm.getDescription());
		
		originalArticle.setZip(newArticleForm.getZip());
		originalArticle.setLocation(newArticleForm.getCity());
		originalArticle.setStreet(newArticleForm.getStreetName());
		originalArticle.setNumber(newArticleForm.getHouseNumber());
		originalArticle.setAddressAddition(newArticleForm.getAdressAddition());
		
		if (!((newArticleForm.getFile()).isEmpty())) {
            try {
                byte[] bytes = (newArticleForm.getFile()).getBytes();
 
                // Creating the directory to store file
                String rootPath = System.getProperty("user.home");
                File dir = new File(rootPath + "/" + "Pics");
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file local
                File serverFile = new File(dir.getAbsolutePath() + "/" + newArticleForm.getFile().getOriginalFilename()); 
                BufferedOutputStream stream = new BufferedOutputStream( new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                System.out.println("Server File Location="
                        + serverFile.getAbsolutePath());         
                
                //get the logged in user
                User creator = userRepository.findByUserAccount(userAccount.get());
//                if(originalArticle.getPicture() != null){
//                	pictureRepo.delete(originalArticle.getPicture());
//                }
                Picture picture = new Picture(serverFile.getAbsolutePath(), newArticleForm.getFile().getOriginalFilename(), creator);
				pictureRepo.save(picture);
				originalArticle.setPicture(picture);
        		
        		System.out.println("You successfully uploaded file=" + newArticleForm.getTitle());
            } catch (Exception e) {
                return "You failed to upload " + newArticleForm.getTitle() + " => " + e.getMessage();
            }
        } 
		
		System.out.println("debug3");
		
		this.articleRepo.save(originalArticle);
		model.addAttribute("Article", articleRepo.findOne(id));
		model.addAttribute("Creator", articleRepo.findOne(id).getCreator());
		
		currentUserId = -1;
		boolean isAdminLoggedIn = false;
	    
		//if any user is logged in, set values for vars
	    if(userAccount.isPresent()){
	    	currentUserId = userRepository.findByUserAccount(userAccount.get()).getId();
	    	
	    	if(userAccount.get().hasRole(new Role("ROLE_ADMIN"))) {
	    		isAdminLoggedIn = true;
	    	}
	    }
	    
	    model.addAttribute("currentUserId", currentUserId);
	    
		model.addAttribute("isAdminLoggedIn", isAdminLoggedIn);
		
		return "article";
	}
	
//	@RequestMapping(value = "/inspectcategory/{categoryId}")
//	public String showSubcategories(@PathVariable Long categoryId, Model model, @ModelAttribute Category category) {
//		
//		//List<Good> catGoods = this.goodREPO.findByCategory(categoryId);
//		List<Good> catGoods = getAllSubcategoryItems(categoryId);
//		
//		//System.out.println("Length of list: " + catGoods.size());
//		
//
//		
//		this.processedCategories = categoryMethods.getProcessedCategories();
//		model.addAttribute("categories", this.processedCategories);
//		model.addAttribute("anzeigen", catGoods);
//		
//		return "search";
//	}
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/newArticle")
	public String newArticle(Model model, @LoggedIn Optional<UserAccount> userAccount){
		//initiate categories
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
		model.addAttribute("creator", userRepository.findByUserAccount(userAccount.get()));
		return "newArticle";
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/deleteArticle/{id}")
	public String deleteArticle(@PathVariable("id") long id, @LoggedIn Optional<UserAccount> userAccount){
		long userId = userRepository.findByUserAccount(userAccount.get()).getId();
		Article toDelete = articleRepo.findOne(id);
		
		if(toDelete.getCreator().getId() != userId && !userAccount.get().hasRole(new Role("ROLE_ADMIN"))) {
			System.out.println("ids: " + userId + "," + toDelete.getCreator().getId());
			return "redirect:/frontpage";
		}
		
		articleRepo.delete(toDelete);
		
		
		return "redirect:/search/myArticles";
	}
	
	
	//create a new Article
	/**
	 * @param newArticleForm
	 * @param userAccount
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/newArticle", method = RequestMethod.POST)
    public String newArticle(@ModelAttribute("NewArticleForm") NewArticleForm newArticleForm, @LoggedIn Optional<UserAccount> userAccount) {
		if (!((newArticleForm.getFile()).isEmpty())) {
            try {
                byte[] bytes = (newArticleForm.getFile()).getBytes();
 
                // Creating the directory to store file
                String rootPath;
                if(settingsRepo.findOne("UploadedPicturesPath") == null){
                	System.out.println("null");
                	rootPath = System.getProperty("user.home");
                }
                else rootPath = settingsRepo.findOne("UploadedPicturesPath").getValue();
                
                File dir = new File(rootPath + "/" + "Pics");
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file local
                File serverFile = new File(dir.getAbsolutePath() + "/" + newArticleForm.getFile().getOriginalFilename()); 
                BufferedOutputStream stream = new BufferedOutputStream( new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                System.out.println("Server File Location="
                        + serverFile.getAbsolutePath());
                               
                //get the logged in user
                User creator = userRepository.findByUserAccount(userAccount.get());
                Picture picture = new Picture(serverFile.getAbsolutePath(), newArticleForm.getFile().getOriginalFilename(), creator);
				pictureRepo.save(picture);
                Article article = new Article(newArticleForm.getTitle(), newArticleForm.getDescription(), picture, newArticleForm.getCity(), newArticleForm.getStreetName(), newArticleForm.getCategoryId(), newArticleForm.getHouseNumber(), newArticleForm.getZip(), creator);
        		articleRepo.save(article);
        		System.out.println(article);
        		
        		System.out.println("You successfully uploaded file=" + newArticleForm.getTitle());
            	return ("redirect:/search");
            } catch (Exception e) {
                return "You failed to upload " + newArticleForm.getTitle() + " => " + e.getMessage();
            }
        } else {
        	
            //get the logged in user
            User creator = userRepository.findByUserAccount(userAccount.get());
            //save article without Picture
        	Article article = new Article(newArticleForm.getTitle(), newArticleForm.getDescription(), newArticleForm.getCity(), newArticleForm.getStreetName(), newArticleForm.getCategoryId(), newArticleForm.getHouseNumber(), newArticleForm.getZip(),creator);
    		articleRepo.save(article);
    		System.out.println(article);
            return ("redirect:/search");
        }
	}
}