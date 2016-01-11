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
import refugeeApp.model.CategoryRepo;
import refugeeApp.model.User;
import refugeeApp.model.UserRepository;

/**
 * The Class AttributeTest.
 */
public class AttributeTest extends AbstractIntegrationTests {

	/** The good repo. */
	@Autowired ArticleRepo goodREPO;

    /** The att1. */
    final Attribute att1 = new Attribute();
    
    /** The list. */
    LinkedList<String> list = new LinkedList<String>();
    
    /** The att2. */
    final Attribute att2 = new Attribute("att2", list);
   
    
    /**
     * Constructor test.
     */
    @Test
	public void constructorTest() {
		assertFalse("Error", att1 == null);
		assertFalse("Error", att2 == null);
	}
    
	/**
	 * Initialisation test.
	 */
	@Test
	public void initialisationTest() {
		assertEquals("Error", new LinkedList<String>(), att1.getTags());
		assertEquals("Error", new LinkedList<String>(), att2.getTags());
	}
	
	/**
	 * Equals test.
	 */
	@Test
	public void equalsTest() {
		List<String> newList1 = att1.getTags();
		List<String> newList2 = att2.getTags();
		
		att1.setName("name");
		att2.setName("name");
		
		newList1.add("1");
		newList2.add("1");
		
		att1.setTags(newList1);
		att2.setTags(newList2);
		
		assertEquals("Error", true, att1.equals(att2));
		
		att1.setName("name2");
		assertEquals("Error", false, att1.equals(att2));
		
		att1.setName(att2.getName());
		newList2 = att2.getTags();
		newList2.add("2");
		att2.setTags(newList2);
		
		assertEquals("Error", false, att1.equals(att2));
	}
	
	
	
}
