package kickstart.controller;

import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileOutputStream;

import java.util.Optional;

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
import kickstart.model.ArticleRepo;

@Controller
public class ArticleController extends CommonVariables {
	private PictureRepo pictureRepo;
	
	@Autowired
	public ArticleController(CategoryRepo categories, ArticleRepo articleRepo, PictureRepo pictureRepo){
		this.categories = categories;
		this.articleRepo=articleRepo;
		this.pictureRepo = pictureRepo;
	}
	
	
	
	@RequestMapping(value = "/showArticle/{id}")
	public String showArticle(@PathVariable("id") long id,Model model) {
		//initiate categories
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
	    model.addAttribute("Article", articleRepo.findOne(id));
	    model.addAttribute("Creator", articleRepo.findOne(id).getCreator());
	    model.addAttribute("Useraccount", articleRepo.findOne(id).getCreator().getUserAccount());
	    
	    model=this.getCurrent_cat(model);
	    return "article";
	}
	
	@RequestMapping(value = "/editArticle/{id}")
	public String editArticle(@PathVariable("id") long id, @LoggedIn Optional<UserAccount> userAccount, Model model) {
		this.processedCategories = this.getProcessedCategories();
		long userId = this.userRepository.findByUserAccount(userAccount.get()).getId();
		
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
		model.addAttribute("editArticle", articleRepo.findOne(id));
		model.addAttribute("userId", userId);
		model.addAttribute("user", this.userRepository.findOne(userId));

		
		model=this.getCurrent_cat(model);
		
	    return "editArticle";
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
//		this.processedCategories = this.getProcessedCategories();
//		model.addAttribute("categories", this.processedCategories);
//		model.addAttribute("anzeigen", catGoods);
//		
//		return "search";
//	}
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/newArticle")
	public String newArticle(Model model){
		//initiate categories
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
		
		model=this.getCurrent_cat(model);
		return "newArticle";
	}
	
	
	//create a new Article
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/newArticle", method = RequestMethod.POST)
    public String newArticle(@ModelAttribute("NewArticleForm") NewArticleForm newArticleForm, @LoggedIn Optional<UserAccount> userAccount) {
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
 
                // aktuelles Datum in String umgewandelt
               
                
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

	
//    public @ResponseBody
//    String uploadFileHandler(@RequestParam("name") String name,
//            @RequestParam("file") MultipartFile file) {
 
      
