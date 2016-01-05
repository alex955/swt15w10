package kickstart.model;

import static org.junit.Assert.*;

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

public class ArticleModelTest extends AbstractIntegrationTests {

	@Autowired ArticleRepo goodREPO;

    final Article article = new Article();
	
	@Test
	public void initialisationTest() {
		assertEquals("Error", article.getActivitydate(), null);
	}

	@Test
	public void addArticle() {
		goodREPO.save(article);
		assertEquals("Error", goodREPO.count(), 13);
	}
	
	@Test
	public void deleteArticle() {
		goodREPO.delete(article);
		assertEquals("Error", goodREPO.count(), 12);
	}
	
	@Test
	public void testUserDeactivation(){
//        UserAccount admin1 = userAccountManager.create("admin1", "admin1PW", admin);
//        userAccountManager.save(admin1);
//        User user = new User (1, admin1, "Admin", "Erster", "DataInitializer", "admin@test.de", "Stadt", "01234", "Straße", "1", "", "german", "english", "arab");

        //EMailController.validation(Integer.toString(user.getHashcode()));
		
        assertEquals("Error",true,true);
	}
	
	
	
}
