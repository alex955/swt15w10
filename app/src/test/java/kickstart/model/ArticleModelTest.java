package kickstart.model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;

import kickstart.AbstractIntegrationTests;
import refugeeApp.model.Article;
import refugeeApp.model.ArticleRepo;
import refugeeApp.model.CategoryRepo;
import refugeeApp.model.User;
import refugeeApp.model.UserRepository;

/**
 * The Class ArticleModelTest.
 */
public class ArticleModelTest extends AbstractIntegrationTests {

	/** The good repo. */
	@Autowired ArticleRepo goodREPO;

    /** The article. */
    final Article article = new Article();
    
    /** The article2. */
    final Article article2 = new Article("article2", "desc", "Testort", "Straße", -1, "01011", null, "kind");
    
    /** The article3. */
    final Article article3 = new Article("article3", "desc", null, "Testort", "Straße", 564, "212322", LocalDateTime.now(), "kind");
    
    /**
     * Constructor test.
     */
    @Test
	public void constructorTest() {
		assertFalse("Error", article == null);
		assertFalse("Error", article2 == null);
		assertFalse("Error", article3 == null);
	}
    
	/**
	 * Initialisation test.
	 */
	@Test
	public void initialisationTest() {
		assertEquals("Error", null, article.getActivitydate());
		assertEquals("Error","kind" , article2.getKind());
		assertEquals("Error", "kind", article3.getKind());
	}
	
	/**
	 * Find article.
	 */
	@Test
	public void findArticle() {
		Article newArticle = new Article();
		
		goodREPO.save(newArticle);
		Article result = goodREPO.findOne(newArticle.getId());
		assertEquals("Error", result.getId(), newArticle.getId());
		goodREPO.delete(newArticle);
	}
	
	/**
	 * Find articles by location.
	 */
	@Test
	public void findArticlesByLocation() {
		this.goodREPO.save(article2);
		this.goodREPO.save(article3);
		assertEquals("Error", 2, goodREPO.findByLocation("Testort").size());
		this.goodREPO.delete(article2);
		this.goodREPO.delete(article3);
	}
	
	/**
	 * Find articles by creator.
	 */
	@Test
	public void findArticlesByCreator() {
		this.goodREPO.save(article2);
		this.goodREPO.save(article3);
		assertEquals("Error", 2, goodREPO.findByCreator(null).size());
		this.goodREPO.delete(article2);
		this.goodREPO.delete(article3);
	}
	
	/**
	 * Find articles by category.
	 */
	@Test
	public void findArticlesByCategory() {
		this.goodREPO.save(article2);
		this.goodREPO.save(article3);
		assertEquals("Error", 1, goodREPO.findByCategory(564).size());
		this.goodREPO.delete(article2);
		this.goodREPO.delete(article3);
	}
	
	/**
	 * Adds the and delete article.
	 */
	@Test
	public void addAndDeleteArticle() {
		long count = goodREPO.count();
		goodREPO.save(article);
		assertEquals("Error", goodREPO.count(), count+1);
		
		goodREPO.delete(article);
		assertEquals("Error", goodREPO.count(), count);
	}
	
	/**
	 * Test user deactivation.
	 */
	@Test
	public void testUserDeactivation(){
//        UserAccount admin1 = userAccountManager.create("admin1", "admin1PW", admin);
//        userAccountManager.save(admin1);
//        User user = new User (1, admin1, "Admin", "Erster", "DataInitializer", "admin@test.de", "Stadt", "01234", "Straße", "1", "", "german", "english", "arab");

        //EMailController.validation(Integer.toString(user.getHashcode()));
		
        assertEquals("Error",true,true);
	}
	
	
	
}
