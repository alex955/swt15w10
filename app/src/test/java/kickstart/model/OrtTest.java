package kickstart.model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

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

public class OrtTest extends AbstractIntegrationTests {

    final Ort ort = new Ort();
    
    @Test
	public void constructorTest() {
		assertFalse("Error", ort == null);
	}
    
	@Test
	public void initialisationTest() {
		assertEquals("Error", null, ort.getAddress());
	}
	
	
	
}
