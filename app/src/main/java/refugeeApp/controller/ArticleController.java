package refugeeApp.controller;


import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import refugeeApp.model.*;
import refugeeApp.utilities.CategoryMethods;

import javax.validation.Valid;

@Controller
public class ArticleController {
	private PictureRepo pictureRepo;
    
	@Autowired private final CategoryRepo categories;	
	@Autowired private final ArticleRepo articleRepo;
	@Autowired private final CategoryMethods categoryMethods;	
    @Autowired private final UserRepository userRepository;    
    @Autowired private final SettingsRepository settingsRepo;
    protected LinkedList<CategoryFirstTierObject> processedCategories;
	@Autowired private final LanguageRepository languageRepository;
    
	@Autowired 
	public ArticleController(CategoryRepo categories, ArticleRepo articleRepo, PictureRepo pictureRepo, CategoryMethods categoryMethods, UserRepository userRepository, SettingsRepository settingsRepo, LanguageRepository languageRepository){
		this.categories = categories;
		this.articleRepo=articleRepo;
		this.pictureRepo = pictureRepo;
		this.categoryMethods = categoryMethods;
		this.userRepository = userRepository;
		this.settingsRepo = settingsRepo;
		this.languageRepository = languageRepository;
	}

	 @Scheduled(fixedRate = 3600000)
	 public void deleteArticlesAfterExpiration() {
		List<Article> articles = this.articleRepo.findAll();
		LocalDateTime now =LocalDateTime.now();
		
		 for(Article article:articles){
			 if(article.getKind().equals("article")){
				 LocalDateTime expiration = article.getCreationdate().plusDays(30);
				if(now.isAfter(expiration) == true) this.articleRepo.delete(article); 
			 }
			 if(article.getKind().equals("activity")){
				 LocalDateTime activityDate = article.getActivitydate();
				 if(now.isAfter(activityDate) == true) this.articleRepo.delete(article); 
			 }
			 
		 }
		 
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
	    model.addAttribute("tags", articleRepo.findOne(id).getAttributes());
	    model.addAttribute("current_category",new Category("AlleKategorien",1));
		model.addAttribute("current_ort",new Location(""));
		model.addAttribute("category", categories.findOne(articleRepo.findOne(id).getCategory()).get());
	   
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
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/editArticle/{id}")
	public String editArticle(@ModelAttribute("NewArticleForm") NewArticleForm newArticleForm, @PathVariable("id") long id, @LoggedIn Optional<UserAccount> userAccount, Model model) {
		this.processedCategories = categoryMethods.getProcessedCategories();
		long userId = this.userRepository.findByUserAccount(userAccount.get()).getId();
		Article originalArticle = this.articleRepo.findOne(id);

		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
		model.addAttribute("Article", originalArticle);
		model.addAttribute("userId", userId);
		model.addAttribute("user", this.userRepository.findOne(userId));
		model.addAttribute("Creator", articleRepo.findOne(id).getCreator());
		model.addAttribute("current_category",new Category("AlleKategorien",1));
		model.addAttribute("current_ort",new Location(""));
		
		boolean isAdminLoggedIn = false;
		if(userAccount.get().hasRole(new Role("ROLE_ADMIN"))) isAdminLoggedIn = true;
		
		model.addAttribute("isAdminLoggedIn", isAdminLoggedIn);
		
	    return "editArticle";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/editArticle/{id}", method = RequestMethod.POST)
	public String processEditedArticle(@ModelAttribute("NewArticleForm") @Valid NewArticleForm newArticleForm, BindingResult result, ModelMap modelMap, @PathVariable("id")long id, @LoggedIn Optional<UserAccount> userAccount, Model model){

		Article originalArticle = this.articleRepo.findOne(id);		
		long currentUserId = this.userRepository.findByUserAccount(userAccount.get()).getId();

		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
		model.addAttribute("currentUserId", currentUserId);
		model.addAttribute("userId", currentUserId);
		model.addAttribute("user", this.userRepository.findOne(currentUserId));
		model.addAttribute("Creator", articleRepo.findOne(id).getCreator());
		model.addAttribute("Article", originalArticle);
		model.addAttribute("tags",articleRepo.findOne(id).getAttributes());
		model.addAttribute("current_category",new Category("AlleKategorien",1));
		model.addAttribute("current_ort",new Location(""));

		Locale locale = LocaleContextHolder.getLocale();
		String browserLanguage = locale.toString().substring(0, 2);
		if(languageRepository.findByBrowserLanguage(browserLanguage) == null){
			browserLanguage = "en";
		}
		Language language = languageRepository.findByBrowserLanguage(browserLanguage);

		if(result.hasErrors()) {

			if (result.hasErrors()) {
				if (result.hasFieldErrors("kind")) {
					final String kindError = language.getKindError();
					modelMap.addAttribute("kindError", kindError);
				}
				if (result.hasFieldErrors("title")) {
					final String titleError = language.getTitleError();
					modelMap.addAttribute("titleError", titleError);
					return "editArticle";
				}
			}
			//kind errors cannot happen, because an hidden argument (initialised with prior kind) is passed, kind cannot be changed
			//return "editArticle";
		}

		//case: current user didnt create article && logged in user no admin -> end
		if(originalArticle.getCreator().getId() != currentUserId && !userAccount.get().hasRole(new Role("ROLE_ADMIN"))){
			return null;
		}
		
		originalArticle.setCategory(newArticleForm.getCategoryId());
		originalArticle.setTitle(newArticleForm.getTitle());
		originalArticle.setDescription(newArticleForm.getDescription());
		originalArticle.setZip(newArticleForm.getZip());
		originalArticle.setLocation(newArticleForm.getCity());
		originalArticle.setStreet(newArticleForm.getStreetName());
		originalArticle.setAddressAddition(newArticleForm.getAdressAddition());
		originalArticle.setKind(newArticleForm.getKind());
	 
		
		if (!((newArticleForm.getFile()).isEmpty())) {
            try {
                int existingPicture = 0;
                Picture oldPicture = null;
                byte[] bytes = (newArticleForm.getFile()).getBytes();
                
                // Creating the directory to store file               
                String rootPath;
                if(settingsRepo.findOne("UploadedPicturesPath") == null){
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

                //get the logged in user
                User creator = userRepository.findByUserAccount(userAccount.get());
                
                //check for an existing picture
                if(originalArticle.getPicture() != null){
                	existingPicture = 1;
                	oldPicture = originalArticle.getPicture();
                }
                
                Picture picture = new Picture(serverFile.getAbsolutePath(), newArticleForm.getFile().getOriginalFilename(), creator);
				pictureRepo.save(picture);
				originalArticle.setPicture(picture);
				
				//delete the old picture from the repo
				if(existingPicture == 1){
				pictureRepo.delete(oldPicture);
				}
				
            } catch (Exception e) {
                return "You failed to upload " + newArticleForm.getTitle() + " => " + e.getMessage();
            }
        } 
		
		//Breitengrad und Längengradberechnung 
		Location ort = new Location(newArticleForm.getStreetName()+" "+newArticleForm.getZip()+" "+newArticleForm.getCity());
		ort = ort.GetCoordinates(ort);
		originalArticle.setLatitude(ort.getLatitude()); 
		originalArticle.setLongitude(ort.getLongitude());
		
		this.articleRepo.save(originalArticle);
		model.addAttribute("Article", articleRepo.findOne(id));
		model.addAttribute("Creator", articleRepo.findOne(id).getCreator());
		model.addAttribute("current_category",new Category("AlleKategorien",1));
		model.addAttribute("current_ort",new Location(""));
		
		currentUserId = -1;
		boolean isAdminLoggedIn = false;
	    
		//if any user is logged in, set values for vars
	    if(userAccount.isPresent()){
	    	currentUserId = userRepository.findByUserAccount(userAccount.get()).getId();
	    	
	    	if(userAccount.get().hasRole(new Role("ROLE_ADMIN"))) {
	    		isAdminLoggedIn = true;
	    	}
	    }

		model.addAttribute("isAdminLoggedIn", isAdminLoggedIn);
		model.addAttribute("category", categories.findOne(articleRepo.findOne(id).getCategory()).get());
		
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

	//create a new Article
	
	/**
	 * returns the newArticle template with logged in user
	 * @author Alexander Shulga
	 * @param newArticleForm
	 * @param model
	 * @param userAccount
	 * @return newArticle template
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/newArticle")
	public String createArticle(@ModelAttribute("NewArticleForm") NewArticleForm newArticleForm, Model model, @LoggedIn Optional<UserAccount> userAccount){
		//initiate categories
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
		model.addAttribute("article", new Article());
		model.addAttribute("current_category",new Category("AlleKategorien",1));
		model.addAttribute("current_ort",new Location(""));

		User creator = userRepository.findByUserAccount(userAccount.get());
		model.addAttribute("creator", creator);

		return "newArticle";
	}

	/**
	 * creates and saves the new offer/article with or without a picture
	 * @author Alexander Shulga
	 * @param newArticleForm
	 * @param userAccount
	 * @return editAttributes template
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/newArticle", method = RequestMethod.POST)
    public String saveArticle(@ModelAttribute("NewArticleForm") @Valid NewArticleForm newArticleForm, BindingResult result, Model model, ModelMap modelMap, @LoggedIn Optional<UserAccount> userAccount) {
		User creator = userRepository.findByUserAccount(userAccount.get());
		model.addAttribute("creator", creator);
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
		model.addAttribute("current_category",new Category("AlleKategorien",1));
		model.addAttribute("current_ort",new Location(""));
		
		Locale locale = LocaleContextHolder.getLocale();
		String browserLanguage = locale.toString().substring(0, 2);

		if(languageRepository.findByBrowserLanguage(browserLanguage) == null){
			browserLanguage = "de";
		}

		Language language = languageRepository.findByBrowserLanguage(browserLanguage);
		
		if(result.hasErrors()){

			if(result.hasFieldErrors("kind")){
				final String kindError = language.getKindError();
				modelMap.addAttribute("kindError", kindError);
			}
			if(result.hasFieldErrors("title")){
				final String titleError = language.getTitleError();
				modelMap.addAttribute("titleError",titleError);
			}
			return "newArticle";
		}
		
		//parse activity date
		LocalDateTime activityDate = null;
		if(newArticleForm.getKind().equals("article")){
			activityDate = LocalDateTime.now();
		}
		
		if(newArticleForm.getKind().equals("activity")){
			//check validity of form of date string
			if(newArticleForm.getActivityDate().equals("____/__/__ __:__")){
				String dateError = language.getDateError();
				modelMap.addAttribute("dateError", dateError);
				return "newArticle";
			}
			String activityDateString = newArticleForm.getActivityDate();
			String parsedDate = activityDateString.substring(0, 4) + "-" + activityDateString.substring(5, 7) + "-" + activityDateString.substring(8,10);
			String parsedTime = activityDateString.substring(11, 13) + ":" +  activityDateString.substring(14, 16);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			activityDate = LocalDateTime.parse(parsedDate + " " + parsedTime, formatter);
		}
		//end parsing

		if (!((newArticleForm.getFile()).isEmpty())) {
            try {
                byte[] bytes = (newArticleForm.getFile()).getBytes();
 
                // Creating the directory to store file
                String rootPath;
                if(settingsRepo.findOne("UploadedPicturesPath") == null){
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
                
				Picture picture = new Picture(serverFile.getAbsolutePath(), newArticleForm.getFile().getOriginalFilename(), creator);
				pictureRepo.save(picture);
				Article article = new Article(newArticleForm.getTitle(), newArticleForm.getDescription(), picture, newArticleForm.getCity(), newArticleForm.getStreetName(), newArticleForm.getCategoryId(), newArticleForm.getZip(), creator, newArticleForm.getKind());
				//set activity date
				article.setActivitydate(activityDate);
				
				//Breitengrad und Längengradberechnung 
				Location ort = new Location(newArticleForm.getStreetName()+" "+newArticleForm.getZip()+" "+newArticleForm.getCity());
				ort = ort.GetCoordinates(ort);
				article.setLatitude(ort.getLatitude()); 
	    		article.setLongitude(ort.getLongitude());				
	    		articleRepo.save(article);
	    		
	    		System.out.println(article.getCategory());
        		return ("redirect:/editAttributes/"+article.getId());
            } catch (Exception e) {
                return "You failed to upload " + newArticleForm.getTitle() + " => " + e.getMessage();
            }
        } else {
			//save article without Picture
			Article article = new Article(newArticleForm.getTitle(), newArticleForm.getDescription(), newArticleForm.getCity(), newArticleForm.getStreetName(), newArticleForm.getCategoryId(),  newArticleForm.getZip(),creator, newArticleForm.getKind());
			//set activity date
			article.setActivitydate(activityDate);
			
			Location ort = new Location(newArticleForm.getStreetName()+" "+newArticleForm.getZip()+" "+newArticleForm.getCity());
			ort = ort.GetCoordinates(ort);
			article.setLatitude(ort.getLatitude()); 
    		article.setLongitude(ort.getLongitude());
			articleRepo.save(article);
			System.out.println(article.getCategory());
            return ("redirect:/editAttributes/"+article.getId());
        }
	}
	
	
	
	/**
	 * Provide article, tags, user for editAttributes template.
	 * @author herzoga
	 * @param id
	 * @param userAccount
	 * @param model
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/editAttributes/{id}")
	public String editTags(@PathVariable("id") long id, @LoggedIn Optional<UserAccount> userAccount, Model model) {
		this.processedCategories = categoryMethods.getProcessedCategories();
		long userId = this.userRepository.findByUserAccount(userAccount.get()).getId();
		
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
		model.addAttribute("editArticle", articleRepo.findOne(id));
		model.addAttribute("userId", userId);
		model.addAttribute("user", this.userRepository.findOne(userId));
		model.addAttribute("Creator", articleRepo.findOne(id).getCreator());
		model.addAttribute("FormAttributes", this.categories.findOne(articleRepo.findOne(id).getCategory()).get().getAttributes());
		model.addAttribute("NewAttributes", new NewAttributes());
		model.addAttribute("current_category", new Category("AlleKategorien",1));
		model.addAttribute("current_ort", new Location(""));
	
		boolean isAdminLoggedIn = false;
		if(userAccount.get().hasRole(new Role("ROLE_ADMIN"))) isAdminLoggedIn = true;
		
		model.addAttribute("isAdminLoggedIn", isAdminLoggedIn);
		
	    return "editAttributes";
	}
	
	/**
	 * Change Tags of an article
	 * @author herzoga
	 * @param newAttributes
	 * @param userAccount
	 * @param artikelid
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/editAttributes/{id}", method = RequestMethod.POST)
	public String processEditTags(@ModelAttribute("NewAttributes") NewAttributes newAttributes, @PathVariable("id") long id , @LoggedIn Optional<UserAccount> userAccount, Model model) {
		Article originalArticle = this.articleRepo.findOne(id);
		long currentUserId = this.userRepository.findByUserAccount(userAccount.get()).getId();
		
		//case: current user didnt create article && logged in user no admin -> end
		if(originalArticle.getCreator().getId() != currentUserId && !userAccount.get().hasRole(new Role("ROLE_ADMIN"))){
			return null;
		}
		originalArticle.setAttributes(new LinkedList<Attribute>());
		LinkedList<String> providedAttributes = newAttributes.getChoosenTags();
		int count=0;
		for(String e:providedAttributes){
			if (e.isEmpty()==true) {
			count++;	
			} 
			else
			{
				LinkedList<String> tag = new LinkedList<String>();
				tag.add(e);
				// fügt das Attribut der Attribute Liste hinzu
				Attribute att = new Attribute();
				att.setName(this.categories.findOne(articleRepo.findOne(id).getCategory()).get().getAttributes().get(count).getName());
				att.setTags(tag);
				originalArticle.addAttribute(att);
				count++;
			}
		}
		
		for(Attribute a:originalArticle.getAttributes()){
			for(String s:a.getTags()){
			}
			
		}
		
		articleRepo.save(originalArticle);
		
		return ("redirect:/showArticle/"+id);
	}



	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/deleteArticle/{id}")
	public String deleteArticle(@PathVariable("id") long id, @LoggedIn Optional<UserAccount> userAccount){
		long userId = userRepository.findByUserAccount(userAccount.get()).getId();
		Article toDelete = articleRepo.findOne(id);

		if(toDelete.getCreator().getId() != userId && !userAccount.get().hasRole(new Role("ROLE_ADMIN"))) {
			return "redirect:/frontpage";
		}

		articleRepo.delete(toDelete);

		return "redirect:/search/myArticles";

	}
}