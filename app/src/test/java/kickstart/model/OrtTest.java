package kickstart.model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;

import kickstart.AbstractIntegrationTests;
import refugeeApp.model.ArticleRepo;
import refugeeApp.model.CategoryRepo;
import refugeeApp.model.Location;
import refugeeApp.model.User;
import refugeeApp.model.UserRepository;

/**
 * The Class OrtTest.
 */
public class OrtTest extends AbstractIntegrationTests {

    /** The ort. */
    final Location ort = new Location();
    
    /**
     * Constructor test.
     */
    @Test
	public void constructorTest() {
		assertFalse("Error", ort == null);
	}
    
	/**
	 * Initialisation test.
	 */
	@Test
	public void initialisationTest() {
		assertEquals("Error", null, ort.getAddress());
	}
	
	
	
}
