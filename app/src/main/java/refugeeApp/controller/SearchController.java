package refugeeApp.controller;

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
import refugeeApp.model.*;
import refugeeApp.utilities.CategoryMethods;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The Class SearchController.
 */
@Controller
@Scope("session")
public class SearchController {
	
	/** The category methods. */
	private final CategoryMethods categoryMethods;
	
	/** The user repository. */
	private final UserRepository userRepository;
	
	/** The categories. */
	private final CategoryRepo categories;
	
	/** The article repo. */
	private final ArticleRepo articleRepo;
	/**
	 * The processed categories.
	 */
	protected LinkedList<CategoryFirstTierObject> processedCategories;
	
	/** The current_cat. */
	private long current_cat=0;
	/** The location */
	private Location ort = new Location();
	
	
	/**
	 * autowired constructor.
	 *
	 * @param categoryMethods the category methods
	 * @param userRepository the user repository
	 * @param categories the categories
	 * @param articleRepo the article repo
	 */
	@Autowired
	public SearchController(CategoryMethods categoryMethods, UserRepository userRepository, CategoryRepo categories,
			ArticleRepo articleRepo) {
		super();
		this.categoryMethods = categoryMethods;
		this.userRepository = userRepository;
		this.categories = categories;
		this.articleRepo = articleRepo;
		}

	/**
	 * caluclates distance between two locations given by lat/long.
	 *
	 * @param lat1 the lat1
	 * @param lon1 the lon1
	 * @param lat2 the lat2
	 * @param lon2 the lon2
	 * @return distance between two locations
	 */
	private static double distance(double lat1, double lon1, double lat2, double lon2) {
		double delta = lon1 - lon2;
		double dist = Math.sin(lat1 * Math.PI / 180.0) * Math.sin(lat2 * Math.PI / 180.0) + Math.cos(lat1 * Math.PI / 180.0) * Math.cos(lat2 * Math.PI / 180.0) * Math.cos(delta * Math.PI / 180.0);
		dist = Math.acos(dist);
		dist = dist * 180 / Math.PI;
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;

		return dist;
	}

	/**
	 * sorts out articles with the distance
	 *
	 * @param articles list of articles
	 * @return list without sorted out articles
	 */
	public LinkedList<Article> sortOutArticlesWithDistance(List<Article> articles) {
		LinkedList<Article> output = new LinkedList<Article>();


		for (Article article : articles) {
			if ((int)this.ort.getDistance() == 0 || this.ort.getAddress().isEmpty() || (int)this.ort.getLatitude() == 0 || (int)this.ort.getLongitude() == 0)
				output.add(article);
			else {
				if (distance(article.getLatitude(), article.getLongitude(), this.ort.getLatitude(), this.ort.getLongitude()) <= this.ort.getDistance()) {
					output.add(article);
				}
			}
		}
		return output;
	}
		
	/**
	 * Gets the category map.
	 *
	 * @return Category Map containing name of category and children
	 */
	public Map<String, List<Category>> getCategoryMap(){
		Map<String, List<Category>> toReturn = new HashMap<String, List<Category>>();
		
		for(Category s : this.categories.findAll()){
			long currCat = s.getId();
			LinkedList<Category> putList = getChildren(currCat);
			toReturn.put(s.getName(), putList);
		}
		return toReturn;
	}
		
	/**
	 * Gets the children.
	 *
	 * @param id ID of category
	 * @return children of certain category in list
	 */
	public LinkedList<Category> getChildren(long id){
		LinkedList<Category> toReturn = new LinkedList<Category>();
		for(Category c : this.categories.findAll()){
			if(c.getPredecessor() == id) toReturn.add(c);
		}
		return toReturn;
	}

	/**
	 * Gets the current_cat.
	 *
	 * @param model mvc model to which information is added
	 * @return adds current location or category to model
	 */
	public Model getCurrent_cat(Model model) {
		if (current_cat==0) model.addAttribute("current_category",new Category("AlleKategorien", 0));
		else model.addAttribute("current_category",this.categories.findOne(current_cat).get());
		return model;
	}

	/**
	 * exchanges current category.
	 *
	 * @param model mvc model
	 * @return new mvc model
	 */
	public Model cat_exchange(Model model) {
		if (current_cat == 0) model.addAttribute("current_category", new Category("AlleKategorien", 0));
		else model.addAttribute("current_category", this.categories.findOne(current_cat).get());
		return model;
	}

	/**
	 * Gets the current_cat.
	 *
	 * @return current category id
	 */
	public Long getCurrent_cat() {
		return current_cat;
	}

	/**
	 * sets current catgegory.
	 *
	 * @param current_cat the new current_cat
	 */
	public void setCurrent_cat(long current_cat) {
		if (this.categories.findOne(current_cat).get().getId() == current_cat) this.current_cat = current_cat;
		else throw new IllegalArgumentException("Kategorie ID existiert nicht!");
	}
	
	/**
	 * Gets the all subcategory items.
	 *
	 * @param subcatId id of category
	 * @return List of all items of a certain category and its subcategories
	 */
	public List<Article> getAllSubcategoryItems(long subcatId){
		List<Article> toReturn = getAllCategoryItems(subcatId);
		
		List<Long> subcategories = new LinkedList<Long>();
		for(Category s : categories.findAll()){
			if(s.getPredecessor() == subcatId){
				subcategories.add(s.getId());
			}
		}
		
		for(Long l : subcategories){
			toReturn.addAll(getAllSubcategoryItems(l));
		}
		
		return toReturn;
	}
	
	/**
	 * Gets the all category items.
	 *
	 * @param subcatID category id
	 * @return list of all articles in certain category
	 */
	public List<Article> getAllCategoryItems(long subcatID){
		return this.articleRepo.findByCategory(subcatID);
	}
		
	//Controller
	
	/**
	 * returns the search template with all existing offers.
	 *
	 * @param model the model
	 * @return search template
	 */
	@RequestMapping("/search")
	public String search(Model model) {
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("anzeigen", this.sortOutArticlesWithDistance(articleRepo.findAll()));
		this.current_cat=0;
		this.ort=new Location("");
		model.addAttribute("current_ort",this.ort);
		model=this.getCurrent_cat(model);
		return "search";
	}
	
	
			
	/**
	 * searches through articles by category.
	 *
	 * @param catID category id
	 * @param model mvc model
	 * @return search template
	 */
	@RequestMapping(value = "/search/{category}")
	public String searchByCategory(@PathVariable("category") Long catID, Model model)
	{ 	
		//setzt aktuelle kategorie auf catID
		this.setCurrent_cat(catID);
		
		List<Article> catGoods = this.getAllSubcategoryItems(catID);

		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("anzeigen",this.sortOutArticlesWithDistance(catGoods));
		model.addAttribute("NewAttributes",new NewAttributes());
		model.addAttribute("FormAttributes",this.categories.findOne(catID).get().getAttributes());
		model=this.getCurrent_cat(model);
		model.addAttribute("current_ort",this.ort);
		
		return "search";
	}
			
	/**
	 * searches for the offers created by the logged in user and sends them to the template		.
	 *
	 * @param model the model
	 * @param userAccount the user account
	 * @return search template
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/search/myArticles")
	public String searchByUser(Model model, @LoggedIn Optional<UserAccount> userAccount){  
		User creator = userRepository.findByUserAccount(userAccount.get());
		List<Article> articles = articleRepo.findByCreator(creator);

		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("anzeigen", (articles));
		model=this.getCurrent_cat(model);
		this.ort.setAddress("");
		model.addAttribute("current_ort",this.ort);
		
		return "search";
	}
			
	/**
	 * Frontpage.
	 *
	 * @return frontpage template
	 */
	@RequestMapping(value = "/searchbytags")
    public String frontpage() {return "frontpage";}
		
	/**
	 * processes search by tags .
	 *
	 * @param SearchTag the search tag
	 * @param model the model
	 * @return search template
	 */
	@RequestMapping(value = "/searchbytags", method = RequestMethod.POST)
	public String searchbytags(@ModelAttribute("searchattributes") NewAttributes SearchTag, Model model) {
		SearchQuery SearchQuery = new SearchQuery();

		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
		
		model=this.getCurrent_cat(model);
		model.addAttribute("current_ort",this.ort);
		SearchQuery.setCategory(this.getCurrent_cat());
		
		List<Article> catGoods = new LinkedList<Article>();
		
		if (SearchQuery.getCategory()==0) { catGoods = this.articleRepo.findAll() ;}
				else { catGoods = articleRepo.findByCategory(SearchQuery.getCategory()); }
		 
		List<Article> output = new LinkedList<Article>();
		List<String> providedtags =  SearchTag.getChoosenTags();
		List<Attribute> searchAttributes = new LinkedList<Attribute>();
		
		// Umwandlung übergebene Strings in LinkedList<Attributes>
		int count=0;
		for(Attribute att:categories.findOne(SearchQuery.getCategory()).get().getAttributes()){
			LinkedList<String> tags=new LinkedList<String>();
			tags.add(providedtags.get(count));
			searchAttributes.add(new Attribute(att.getName(),tags));
			count++;
		}
	
		
		//Artikel mit einzelnen Attributen vergleichen
		for(Article art:catGoods)	
		{ 
			List<Boolean> flag= new LinkedList<Boolean>();
			for(Attribute att:art.getAttributes()){
			if (searchAttributes.contains(att)) flag.add(true); else flag.add(false);
		
			}
			if (!flag.contains(false)) output.add(art);
		
		}	
		
		
		model.addAttribute("NewAttributes",new NewAttributes(SearchTag.getChoosenTags()));
		model.addAttribute("FormAttributes",this.categories.findOne(SearchQuery.getCategory()).get().getAttributes());
		model.addAttribute("anzeigen", this.sortOutArticlesWithDistance(output));			   
		return "search";
	}
	
	/**
	 * Frontpage2.
	 *
	 * @return frontpage template
	 */
	@RequestMapping(value = "/setsearchaddress")
    public String frontpage2() {return "frontpage";}
	
	/**
	 * sets search address.
	 *
	 * @param ort the ort
	 * @param model the model
	 * @return redirect to search
	 */
	@RequestMapping(value = "/setsearchaddress", method = RequestMethod.POST)
	public String setsearchaddress(@ModelAttribute("Ort") Location ort, Model model) {
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("categoriesForm", this.categories.findAll());
		
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
		
		model=this.getCurrent_cat(model);
		model.addAttribute("current_ort",this.ort);
		model.addAttribute("anzeigen",this.sortOutArticlesWithDistance(catGoods2));
		model.addAttribute("Ort",new Location());
		if (this.getCurrent_cat()==0) return "search"; else return "redirect:search/"+this.getCurrent_cat(); 
		
    }
}