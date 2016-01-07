package refugeeApp.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import refugeeApp.model.Article;
import refugeeApp.model.ArticleRepo;
import refugeeApp.model.Attribute;
import refugeeApp.model.Category;
import refugeeApp.model.CategoryFirstTierObject;
import refugeeApp.model.CategoryRepo;
import refugeeApp.model.Location;
import refugeeApp.model.NewAttributes;
import refugeeApp.model.SearchQuery;
import refugeeApp.model.User;
import refugeeApp.model.UserRepository;
import refugeeApp.utilities.CategoryMethods;

@Controller
@Scope("session")
public class SearchController {
	
	@Autowired private final CategoryMethods categoryMethods;
	@Autowired private final UserRepository userRepository;
	@Autowired private final CategoryRepo categories;	
	@Autowired private final ArticleRepo articleRepo;	
	private long current_cat=0;
	private Location ort=new Location();
	protected LinkedList<CategoryFirstTierObject> processedCategories; 
	
	@Autowired
	public SearchController(CategoryMethods categoryMethods, UserRepository userRepository, CategoryRepo categories,
			ArticleRepo articleRepo) {
		super();
		this.categoryMethods = categoryMethods;
		this.userRepository = userRepository;
		this.categories = categories;
		this.articleRepo = articleRepo;
	}
	
	public LinkedList<Article> sortOutArticlesWithDistance(List<Article> articles){
		LinkedList<Article> output= new LinkedList<Article>();
		
		
		
		for(Article article:articles){
			if (this.ort.getDistance()==0 || this.ort.getAddress().isEmpty() || this.ort.getLatitude()==0 || this.ort.getLongitude()==0) output.add(article); 
			else {
				if (distance(article.getLatitude(),article.getLongitude(),this.ort.getLatitude(),this.ort.getLongitude()) <= this.ort.getDistance()) 
					{output.add(article); 
					}		
			}
		}
		return output;
	}
	
	private static double distance(double lat1, double lon1, double lat2, double lon2) {
		double delta = lon1 - lon2;
		double dist = Math.sin(lat1* Math.PI / 180.0) * Math.sin(lat2* Math.PI / 180.0) + Math.cos(lat1* Math.PI / 180.0) * Math.cos(lat2* Math.PI / 180.0) * Math.cos(delta * Math.PI / 180.0);
		dist = Math.acos(dist);
		dist = dist * 180 / Math.PI;
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;
		
		return dist;
	}

	protected LinkedList<CategoryFirstTierObject> getProcessedCategories(){
		LinkedList<CategoryFirstTierObject> toReturn = new LinkedList<CategoryFirstTierObject>();
		Iterable<Category> foundCategories = categories.findAll();
		
		for(Category s : foundCategories){
			if(s.getRoot()) {
				LinkedList<Category> subcats = new LinkedList<Category>();
				
				
				for(Category t : foundCategories){
					if(t.getPredecessor() == s.getId()){
						subcats.add(t);
					}
				}	
				CategoryFirstTierObject toAdd = new CategoryFirstTierObject(s, subcats);
				toReturn.add(toAdd);
			}
		}
		return toReturn;
	}
		
	public Map<String, List<Category>> getCategoryMap(){
		Map<String, List<Category>> toReturn = new HashMap<String, List<Category>>();
		
		for(Category s : this.categories.findAll()){
			long currCat = s.getId();
			LinkedList<Category> putList = getChildren(currCat);
			toReturn.put(s.getName(), putList);
		}
		return toReturn;
	}
		
	public LinkedList<Category> getChildren(long id){
		LinkedList<Category> toReturn = new LinkedList<Category>();
		for(Category c : this.categories.findAll()){
			if(c.getPredecessor() == id) toReturn.add(c);
		}
		return toReturn;
	}

	public Model getCurrent_cat(Model model) {
		if (current_cat==0) model.addAttribute("current_category",new Category("AlleKategorien", 0));
		else model.addAttribute("current_category",this.categories.findOne(current_cat).get());
		model.addAttribute("current_ort",this.ort);
		return model;
	}

	public void setCurrent_cat(long current_cat) {
		if (this.categories.findOne(current_cat).get().getId()==current_cat) this.current_cat = current_cat; 
		else throw new IllegalArgumentException("Kategorie ID existiert nicht!");
	}
	
	public Model cat_exchange(Model model){
		if (current_cat==0) model.addAttribute("current_category",new Category("AlleKategorien", 0));
		else model.addAttribute("current_category",this.categories.findOne(current_cat).get());
		return model;
	}
		
	public Long getCurrent_cat(){
			return current_cat;	
	}
		
	public List<Article> getAllSubcategoryItems(long subcatId){
		List<Article> toReturn = getAllCategoryItems(subcatId);
		
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
	
	public List<Article> getAllCategoryItems(long subcatID){
		return this.articleRepo.findByCategory(subcatID);
	}
		
	//Controller
	
	/**
	 * returns the search template with all existing offers
	 * @param model
	 * @return search template
	 */
	@RequestMapping("/search")
	public String search(Model model) {
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("anzeigen", this.sortOutArticlesWithDistance(articleRepo.findAll()));
		this.current_cat=0;
		model=this.getCurrent_cat(model);
		return "search";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searcharticle(@ModelAttribute("SearchQuery") SearchQuery SearchQuery, Model model ) {
	
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
		model=this.getCurrent_cat(model);
		SearchQuery.setCategory(this.getCurrent_cat());
		
		System.out.println("Gesucht "+ SearchQuery.getQuery() +" in "+SearchQuery.getCategory());
		List<Article> catGoods = new LinkedList<Article>();
		
		if (SearchQuery.getCategory()==0) { catGoods = this.articleRepo.findAll() ;}
				else { catGoods = articleRepo.findByCategory(SearchQuery.getCategory()); }
		System.out.println("Length of list: " + catGoods.size());
		 
		List<Article> output = new LinkedList<Article>();
 		   
		   int count = 1;
		   for (Article good : catGoods) 
		     { System.out.println(count); count++;
		     System.out.println(good.getTitle()+"    "+good.getCategory());
		  
		     	// Überprüfung ob Name gleich
		     	if (good.getTitle().toLowerCase().contains(SearchQuery.getQuery().toLowerCase())) { System.out.println("match"); output.add(good);} else System.out.println("dismatch"); 
		     	// Überprüfung ob Suchtext in Description
		     	if (good.getDescription().toLowerCase().contains(SearchQuery.getQuery().toLowerCase()) && !output.contains(good)) output.add(good);
		     }
		  
		   model.addAttribute("anzeigen", this.sortOutArticlesWithDistance(output));	
		   
		   return "search";
		   
	}
			
			
	@RequestMapping(value = "/search/{category}")
	public String searchByCategory(@PathVariable("category") Long catID, Model model)
	{  System.out.println("Es wird in der Kategorie "+catID+" gesucht");
	
	//setzt aktuelle kategorie auf catID
	this.setCurrent_cat(catID);
	
	List<Article> catGoods = this.getAllSubcategoryItems(catID);

	System.out.println("Anzahl an Artikeln: " + catGoods.size());
	
	this.processedCategories = this.getProcessedCategories();
	model.addAttribute("categories", this.processedCategories);
	model.addAttribute("anzeigen",this.sortOutArticlesWithDistance(catGoods));
	model.addAttribute("NewAttributes",new NewAttributes());
	model.addAttribute("FormAttributes",this.categories.findOne(catID).get().getAttributes());
	model=this.getCurrent_cat(model);
	
	return "search";
	}
			
	/**
	 * searches for the offers created by the logged in user and sends them to the template		
	 * @author Alexander Shulga
	 * @param model
	 * @param userAccount
	 * @return search template
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/search/myArticles")
	public String searchByUser(Model model, @LoggedIn Optional<UserAccount> userAccount){  
		User creator = userRepository.findByUserAccount(userAccount.get());
		List<Article> articles = articleRepo.findByCreator(creator);
				
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("anzeigen", this.sortOutArticlesWithDistance(articles));
		model=this.getCurrent_cat(model);
		
		return "search";
	}
			
	@RequestMapping(value = "/searchbytags")
    public String frontpage() {return "frontpage";}
		
	@RequestMapping(value = "/searchbytags", method = RequestMethod.POST)
    public String searchbytags(@ModelAttribute("searchattributes") NewAttributes SearchTag, Model model ) {
		SearchQuery SearchQuery= new SearchQuery();
		
		this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
		model.addAttribute("FormAttributes",this.categories.findOne(articleRepo.findOne(this.getCurrent_cat()).getCategory()).get().getAttributes());
		model.addAttribute("NewAttributes",new NewAttributes());
		model=this.getCurrent_cat(model);
		SearchQuery.setCategory(this.getCurrent_cat());
		
		System.out.println("Gesucht "+ SearchQuery.getQuery() +" in "+SearchQuery.getCategory());
		List<Article> catGoods = new LinkedList<Article>();
		
		if (SearchQuery.getCategory()==0) { catGoods = this.articleRepo.findAll() ;}
				else { catGoods = articleRepo.findByCategory(SearchQuery.getCategory()); }
		System.out.println("Length of list: " + catGoods.size());
		 
		List<Article> output = new LinkedList<Article>();
		List<String> providedtags =  SearchTag.getChoosenTags();
		List<Attribute> searchattributes = new LinkedList<Attribute>();
		
		// Übermittelte leere Attribute aussortieren
		int count=0;
		for(String e:providedtags){
			if (e.isEmpty()==true) {
			count++;	
			} 
			else
			{ 
			LinkedList<String> tag = new LinkedList<String>();
			tag.add(e);
			// fügt das Attribut der Attribute Liste hinzu
			Attribute att = new Attribute();
			att.setName(this.categories.findOne(this.getCurrent_cat()).get().getAttributes().get(count).getName());
			att.setTags(tag);
			searchattributes.add(att);
			count++;
			}
		 } 
				
		System.out.println(searchattributes);
		
		//Attribute mit Artikeln vergleichen
		for(Article good:catGoods) {
				System.out.println(good.getAttributes());
				System.out.println(good.getAttributes().containsAll(searchattributes));
				if (good.getAttributes().containsAll(searchattributes)) output.add(good);
		} 
		model.addAttribute("anzeigen", this.sortOutArticlesWithDistance(output));			   
		return "search";
	}
	
	@RequestMapping(value = "/setsearchaddress")
    public String frontpage2() {return "frontpage";}
	
	@RequestMapping(value = "/setsearchaddress", method = RequestMethod.POST)
    public String setsearchaddress(@ModelAttribute("Ort") Location ort, Model model ) 
    {	this.processedCategories = this.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
		model=this.getCurrent_cat(model);
	//	model.addAttribute("FormAttributes",this.categories.findOne(articleRepo.findOne(this.getCurrent_cat()).getCategory()).get().getAttributes());
	//	model.addAttribute("NewAttributes",new NewAttributes());
		
		
		
		List<Article> catGoods = this.getAllSubcategoryItems(this.getCurrent_cat());
		LinkedList<Article> catGoods2 = new LinkedList<Article>();
		
		if (ort.getDistance()==0 || ort.getAddress().isEmpty()) {}
		else ort = ort.GetCoordinates(ort);
		this.ort=ort;
		
		//umwandlung in linkedlist
		for(Article a:catGoods) {
			catGoods2.add(a);
		}
		
		
		//System.out.println(ort);
		
		model.addAttribute("anzeigen",this.sortOutArticlesWithDistance(catGoods2));
		model.addAttribute("Ort",new Location());
		return "redirect:/search";
		
    }
}