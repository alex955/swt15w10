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

public class AttributeTest extends AbstractIntegrationTests {

	@Autowired ArticleRepo goodREPO;

    final Attribute att1 = new Attribute();
    
    LinkedList<String> list = new LinkedList<String>();
    final Attribute att2 = new Attribute("att2", list);
   
    
    @Test
	public void constructorTest() {
		assertFalse("Error", att1 == null);
		assertFalse("Error", att2 == null);
	}
    
	@Test
	public void initialisationTest() {
		assertEquals("Error", new LinkedList<String>(), att1.getTags());
		assertEquals("Error", new LinkedList<String>(), att2.getTags());
	}
	
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
