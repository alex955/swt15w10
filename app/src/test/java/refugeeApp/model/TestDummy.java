package refugeeApp.model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;

import refugeeApp.AbstractIntegrationTests;
import refugeeApp.model.Article;
import refugeeApp.model.ArticleRepo;
import refugeeApp.model.CategoryRepo;
import refugeeApp.model.User;
import refugeeApp.model.UserRepository;

/**
 * The Class TestDummy.
 */
public class TestDummy extends AbstractIntegrationTests {

	/** The good repo. */
	@Autowired ArticleRepo goodREPO;

    /** The article. */
    final Article article = new Article();
    
    /**
     * Constructor test.
     */
    @Test
	public void constructorTest() {
		assertFalse("Error", article == null);
	}
    
	/**
	 * Initialisation test.
	 */
	@Test
	public void initialisationTest() {
		assertEquals("Error", null, article.getActivitydate());
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
	
	
	
}
