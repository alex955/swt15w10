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

import kickstart.model.Anzeige;
import kickstart.model.Category;
import kickstart.model.CategoryRepo;
import kickstart.model.Good;
import kickstart.model.NewArticleForm;
import kickstart.model.RegistrationForm;
import kickstart.model.User;
import kickstart.model.activityREPO;
import kickstart.model.goodREPO;

@Controller
public class ArticleController extends CommonVariables {
	@Autowired
	public ArticleController(CategoryRepo categories, goodREPO grepo, activityREPO arepo){
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
	
	@RequestMapping(value = "/inspectcategory/{categoryId}")
	public String showSubcategories(@PathVariable Long categoryId, Model model, @ModelAttribute Category category) {
		
		//List<Good> catGoods = this.goodREPO.findByCategory(categoryId);
		List<Good> catGoods = getAllSubcategoryItems(categoryId);
		
		//System.out.println("Length of list: " + catGoods.size());
		

		
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("anzeigen", catGoods);
		
		return "anzeigen";
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
	
	
	//@RequestMapping(value = "/search?{name}?{plz}?{distance}?{search}?{category}")
	
		@RequestMapping(value = "/search/{text}/{category}")
		public String suche_via_name_category(@PathVariable("text") String text, @PathVariable("category") Long catID, Model model)
		{  System.out.println("Es wird nach "+text+" in der Kategorie "+catID+" gesucht");
			
			List<Good> catGoods = goodREPO.findByCategory(catID);
			System.out.println("Length of list: " + catGoods.size());
		 
		   List<Good> übergabe = new LinkedList<Good>();
 		   
		   int count = 1;
		   for (Good good : catGoods) 
		     { System.out.println(count); count++;
		     System.out.println(good.getName()+"    "+good.getCategory());
		  
		     
		     	if (good.getName().toLowerCase().contains(text.toLowerCase())) { System.out.println("match"); übergabe.add(good);} else System.out.println("dismatch"); 
		     	if(good.getDescription().toLowerCase().contains(text.toLowerCase()) && !übergabe.contains(good)) übergabe.add(good);
		     
		     }
		 
		  
		   
		   model.addAttribute("anzeigen", übergabe);

		    return "anzeigen";
		}
}

	
//    public @ResponseBody
//    String uploadFileHandler(@RequestParam("name") String name,
//            @RequestParam("file") MultipartFile file) {
 
      
