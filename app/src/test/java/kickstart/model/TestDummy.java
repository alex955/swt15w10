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

public class TestDummy extends AbstractIntegrationTests {

	@Autowired ArticleRepo goodREPO;

    final Article article = new Article();
    
    @Test
	public void constructorTest() {
		assertFalse("Error", article == null);
	}
    
	@Test
	public void initialisationTest() {
		assertEquals("Error", null, article.getActivitydate());
	}
	
	@Test
	public void findArticle() {
		Article newArticle = new Article();
		
		goodREPO.save(newArticle);
		Article result = goodREPO.findOne(newArticle.getId());
		assertEquals("Error", result.getId(), newArticle.getId());
		goodREPO.delete(newArticle);
	}
	
	@Test
	public void addAndDeleteArticle() {
		long count = goodREPO.count();
		goodREPO.save(article);
		assertEquals("Error", goodREPO.count(), count+1);
		
		goodREPO.delete(article);
		assertEquals("Error", goodREPO.count(), count);
	}
	
	
	
}
