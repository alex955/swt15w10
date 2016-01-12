package refugeeApp.model;
//package kickstart.model;
//
//import static org.junit.Assert.*;
//
//import java.time.LocalDateTime;
//
//import org.junit.Test;
//import org.salespointframework.useraccount.Role;
//import org.salespointframework.useraccount.UserAccount;
//import org.salespointframework.useraccount.UserAccountManager;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import kickstart.AbstractIntegrationTests;
//import kickstart.model.ArticleRepo;
//import kickstart.model.CategoryRepo;
//import kickstart.model.User;
//import kickstart.model.UserRepository;
//
//public class UserTest extends AbstractIntegrationTests {
//
//	//@Autowired UserAccountManager userAccountManager;
//	@Autowired UserRepository userRepository;
//	
//    final Role admin = new Role("ROLE_ADMIN");
//
//   	final User user = new User();
//   	//final UserAccount userAcc = userAccountManager.create("testUserName", "pw", admin);
//   	final User userExplicit = new User(123123, null, "Lastname", "firstname", "country", "mail", "city", "123123", "streetname", "addition", "l1", "l2", "l3");
//    
//    @Test
//	public void constructorTest() {
//		assertFalse("Error", user == null);
//		assertFalse("Error", userExplicit == null);
//	}
//    
//	@Test
//	public void initialisationTest() {
//		assertEquals("Error", null, user.getEmail());
//		assertEquals("Error", "mail", userExplicit.getEmail());
//	}
//	
//	@Test
//	public void userRepoTest() {
//		userRepository.save(userExplicit);
//		
//		//User result = userRepository.findByUserAccount(userAcc);
//		User result2 = userRepository.findByUsername("testUserName");
//		
//		//assertEquals("Error", userExplicit.getId(), result.getId());
//		assertEquals("Error", userExplicit.getId(), result2.getId());
//		
//		userRepository.delete(userExplicit);
//	}
//	
//	
//	
//}
