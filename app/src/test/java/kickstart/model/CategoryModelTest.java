package kickstart.model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;

import kickstart.AbstractIntegrationTests;
import refugeeApp.model.ArticleRepo;
import refugeeApp.model.Attribute;
import refugeeApp.model.Category;
import refugeeApp.model.CategoryRepo;
import refugeeApp.model.User;
import refugeeApp.model.UserRepository;

/**
 * The Class CategoryModelTest.
 */
public class CategoryModelTest extends AbstractIntegrationTests {

	/** The category repo. */
	@Autowired CategoryRepo categoryRepo;

    /** The cat. */
    final Category cat = new Category();
    
    /** The cat2. */
    final Category cat2 = new Category("newCat", -1);

	/** The attributes. */
	private LinkedList<Attribute> attributes = new LinkedList<Attribute>();
    
    /** The cat3. */
    final Category cat3 = new Category("newCat2", -1, attributes);
    
    /**
     * Constructor test.
     */
    @Test
	public void constructorTest() {
		assertFalse("Error", cat == null);
		assertFalse("Error", cat2 == null);
		assertFalse("Error", cat3 == null);
	}
    
	/**
	 * Initialisation test.
	 */
	@Test
	public void initialisationTest() {
		assertEquals("Error", new LinkedList<Attribute>(), cat.getAttributes());
		assertEquals("Error", "newCat", cat2.getName());
		assertEquals("Error", new LinkedList<Attribute>(), cat3.getAttributes());
	}
	
	/**
	 * Adds the attribute to category.
	 */
	@Test
	public void addAttributeToCategory() {
		Attribute attr = new Attribute("Name", new LinkedList<String>());
		cat3.addAttribute(attr);
		assertEquals("Error", true, cat3.getAttributes().contains(attr));
	}
	
	/**
	 * Adds the and delete category.
	 */
	@Test
	public void addAndDeleteCategory() {
		long count = categoryRepo.count();
		categoryRepo.save(cat);
		assertEquals("Error", categoryRepo.count(), count+1);
		
		categoryRepo.delete(cat.getId());
		assertEquals("Error", categoryRepo.count(), count);
	}
	
	/**
	 * Find category.
	 */
	@Test
	public void findCategory() {
		categoryRepo.save(cat3);
		Category result = categoryRepo.findOne(cat3.getId()).get();
		assertEquals("Error", cat3.getId(), result.getId());
		categoryRepo.delete(cat3.getId());
	}
	
	/**
	 * Find all categories.
	 */
	@Test
	public void findAllCategories() {
		long count = categoryRepo.count();
	
		categoryRepo.save(cat);
		categoryRepo.save(cat2);
		categoryRepo.save(cat3);
		
		Iterable<Category> result = categoryRepo.findAll();
		long resultCount = 0;
		for(Category s : result){
			resultCount++;
		}
		assertEquals("Error", count + 3, resultCount);
		
		categoryRepo.delete(cat.getId());
		categoryRepo.delete(cat2.getId());
		categoryRepo.delete(cat3.getId());
	}
	
	
	
}
