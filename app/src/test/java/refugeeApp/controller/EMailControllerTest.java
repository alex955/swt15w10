package refugeeApp.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;

import refugeeApp.model.ArticleRepo;
import refugeeApp.model.CategoryRepo;
import refugeeApp.model.User;
import refugeeApp.model.UserRepository;

/**
 * The Class EMailControllerTest.
 */
public class EMailControllerTest {

    //private final UserAccountManager userAccountManager;
   // private final UserRepository userRepository;

    /** The refugee. */
    final Role refugee = new Role("ROLE_REFUGEE");
    
    /** The volunteer. */
    final Role volunteer = new Role("ROLE_VOLUNTEER");
    
    /** The admin. */
    final Role admin = new Role("ROLE_ADMIN");
	
	/**
	 * Test.
	 */
	@Test
	public void test() {
		//fail("Not yet implemented");
	}

	/**
	 * Test validation.
	 *
	 * @param userAccountManager the user account manager
	 * @param userRepository the user repository
	 */
	public void testValidation(UserAccountManager userAccountManager, UserRepository userRepository){
        UserAccount admin1 = userAccountManager.create("admin1", "admin1PW", admin);
        userAccountManager.save(admin1);
        User user = new User (1, admin1, "Admin", "Erster", "DataInitializer", "admin@test.de", "Stadt", "01234", "Straße 1", "", "german", "english", "arab");

        //EMailController.validation(Integer.toString(user.getHashcode()));
		
        assertEquals("Error",true,user.isValidated());
	}
	
	
	
}
