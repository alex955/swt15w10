package kickstart.controller;

import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kickstart.model.Article;
import kickstart.model.Category;
import kickstart.model.CategoryRepo;
import kickstart.model.NewArticleForm;
import kickstart.model.RegistrationForm;
import kickstart.model.User;
import kickstart.model.ArticleRepo;

@Controller
public class ArticleController extends CommonVariables {
	@Autowired
	public ArticleController(CategoryRepo categories, ArticleRepo articleRepo){
		this.categories = categories;
		this.articleRepo=articleRepo;
	}
	
	
	
	@RequestMapping(value = "/showArticle/{id}")
	public String showArticle(@PathVariable("id") long id,Model model) {
		//initiate categories
				this.processedCategories = this.getProcessedCategories();
				model.addAttribute("categories", this.processedCategories);
				model.addAttribute("categoriesForm", this.categories.findAll());
	    model.addAttribute("Article", articleRepo.findOne(id));
	    
	    model=this.getCurrent_cat(model);
	    return "article";
	}
	
	@RequestMapping(value = "/editArticle/{id}")
	public String editArtile(@PathVariable("id") long id,Model model) {
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
		model.addAttribute("editArticle", articleRepo.findOne(id));
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
	@RequestMapping(value = "/newArticle", method = RequestMethod.POST)
    public String newArticle(@ModelAttribute("NewArticleForm") NewArticleForm newArticleForm) {
		if (!((newArticleForm.getFile()).isEmpty())) {
            try {
                byte[] bytes = (newArticleForm.getFile()).getBytes();
 
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath() + File.separator + newArticleForm.getTitle()+ ".jpg"); 
                BufferedOutputStream stream = new BufferedOutputStream( new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
                System.out.println("Server File Location="
                        + serverFile.getAbsolutePath());
 
                // aktuelles Datum in String umgewandelt
                LocalDate date = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                String current_datum = date.format(formatter);
                
                Article article = new Article(newArticleForm.getTitle(), newArticleForm.getDescription(), serverFile.getAbsolutePath(), "dresden", "eilenburger", newArticleForm.getCategoryId(), "16", newArticleForm.getPlz(), current_datum);
        		articleRepo.save(article);
        		System.out.println(article);
        		
        		System.out.println("You successfully uploaded file=" + newArticleForm.getTitle());
            	return ("redirect:/search");
            } catch (Exception e) {
                return "You failed to upload " + newArticleForm.getTitle() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + newArticleForm.getTitle()
                    + " because the file was empty.";
            
        }
	}
	
	
	
}

	
//    public @ResponseBody
//    String uploadFileHandler(@RequestParam("name") String name,
//            @RequestParam("file") MultipartFile file) {
 
      
