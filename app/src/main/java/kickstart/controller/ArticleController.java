package kickstart.controller;

import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import kickstart.model.Good;
import kickstart.model.NewArticleForm;
import kickstart.model.RegistrationForm;
import kickstart.model.User;
import kickstart.model.ActivityRepo;
import kickstart.model.GoodRepo;

@Controller
public class ArticleController extends CommonVariables {
	@Autowired
	public ArticleController(CategoryRepo categories, GoodRepo grepo, ActivityRepo arepo){
		this.categories = categories;
		this.activityREPO=arepo;
		this.goodREPO=grepo;
	}
	
	public List<Good> getAllSubcategoryItems(long subcatId){
		List<Good> toReturn = getAllCategoryItems(subcatId);
		
		List<Long> subcategories = new LinkedList<Long>();
		for(Category s : categories.findAll()){
			if(s.getPredecessor() == subcatId){
				subcategories.add(s.getId());
				//System.out.println("\tsubcategory zum durchschauen: "+s.getName());
			}
		}
		
		for(Long l : subcategories){
			toReturn.addAll(getAllSubcategoryItems(l));
		}
		
		return toReturn;
	}
	
	public List<Good> getAllCategoryItems(long subcatID){
		return this.goodREPO.findByCategory(subcatID);
	}
	
	@RequestMapping(value = "/showArticle/{id}")
	public String anzeige_anzeigen(@PathVariable("id") long id,Model model) {
	    model.addAttribute("Good", goodREPO.findOne(id));	 
	    return "article";
	}
	
	@RequestMapping(value = "/inspectcategory/{categoryId}")
	public String showSubcategories(@PathVariable Long categoryId, Model model, @ModelAttribute Category category) {
		
		//List<Good> catGoods = this.goodREPO.findByCategory(categoryId);
		List<Good> catGoods = getAllSubcategoryItems(categoryId);
		
		//System.out.println("Length of list: " + catGoods.size());
		

		
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("anzeigen", catGoods);
		
		return "search";
	}
	
	@RequestMapping("/newArticle")
	public String newArticle(Model model){
		//initiate categories
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
		
		return "newArticle";
	}
	
	
	//create a new Good
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
 
                Good good = new Good(newArticleForm.getTitle(), newArticleForm.getDescription(), serverFile.getAbsolutePath(), "01309", null, 0, null, null, newArticleForm.getId());
        		goodREPO.save(good);
        		System.out.println(good);
        		
        		System.out.println("You successfully uploaded file=" + newArticleForm.getTitle());
            	return ("redirect:/");
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
 
      
