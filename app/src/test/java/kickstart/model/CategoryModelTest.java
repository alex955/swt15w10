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
import kickstart.model.ArticleRepo;
import kickstart.model.CategoryRepo;
import kickstart.model.User;
import kickstart.model.UserRepository;

public class CategoryModelTest extends AbstractIntegrationTests {

	@Autowired CategoryRepo categoryRepo;

    final Category cat = new Category();
    final Category cat2 = new Category("newCat", -1);

	private LinkedList<Attribute> attributes = new LinkedList<Attribute>();
    final Category cat3 = new Category("newCat2", -1, attributes);
    
    @Test
	public void constructorTest() {
		assertFalse("Error", cat == null);
		assertFalse("Error", cat2 == null);
		assertFalse("Error", cat3 == null);
	}
    
	@Test
	public void initialisationTest() {
		assertEquals("Error", new LinkedList<Attribute>(), cat.getAttributes());
		assertEquals("Error", "newCat", cat2.getName());
		assertEquals("Error", new LinkedList<Attribute>(), cat3.getAttributes());
	}
	
	@Test
	public void addAttributeToCategory() {
		Attribute attr = new Attribute("Name", new LinkedList<String>());
		cat3.addAttribute(attr);
		assertEquals("Error", true, cat3.getAttributes().contains(attr));
	}
	
	@Test
	public void addAndDeleteCategory() {
		long count = categoryRepo.count();
		categoryRepo.save(cat);
		assertEquals("Error", categoryRepo.count(), count+1);
		
		categoryRepo.delete(cat.getId());
		assertEquals("Error", categoryRepo.count(), count);
	}
	
	@Test
	public void findCategory() {
		categoryRepo.save(cat3);
		Category result = categoryRepo.findOne(cat3.getId()).get();
		assertEquals("Error", cat3.getId(), result.getId());
		categoryRepo.delete(cat3.getId());
	}
	
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
