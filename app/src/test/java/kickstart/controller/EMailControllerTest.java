package kickstart.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;

import kickstart.model.ArticleRepo;
import kickstart.model.CategoryRepo;
import kickstart.model.User;
import kickstart.model.UserRepository;

public class EMailControllerTest {

    //private final UserAccountManager userAccountManager;
   // private final UserRepository userRepository;

    final Role refugee = new Role("ROLE_REFUGEE");
    final Role volunteer = new Role("ROLE_VOLUNTEER");
    final Role admin = new Role("ROLE_ADMIN");
	
	@Test
	public void test() {
		//fail("Not yet implemented");
	}

	public void testValidation(UserAccountManager userAccountManager, UserRepository userRepository){
        UserAccount admin1 = userAccountManager.create("admin1", "admin1PW", admin);
        userAccountManager.save(admin1);
        User user = new User (1, admin1, "Admin", "Erster", "DataInitializer", "admin@test.de", "Stadt", "01234", "Straße", "1", "", "german", "english", "arab");

        //EMailController.validation(Integer.toString(user.getHashcode()));
		
        assertEquals("Error",true,user.isValidated());
	}
	
	
	
}
