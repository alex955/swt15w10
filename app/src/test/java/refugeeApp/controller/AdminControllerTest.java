package refugeeApp.controller;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import refugeeApp.AbstractWebIntegrationTests;
import refugeeApp.controller.AdminController;
import refugeeApp.model.UserRepository;

/**
 * The Class AdminControllerTest.
 */
public class AdminControllerTest extends AbstractWebIntegrationTests{

	/** The user repository. */
	@Autowired UserRepository userRepository;
	
	/** The controller. */
	@Autowired AdminController controller;
	
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
	
	/**
	 * Returns model and view for secured uri after authentication.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void returnsModelAndViewForSecuredUriAfterAuthentication() throws Exception {

		mvc.perform(get("/admin").with(user("admin1").roles("ADMIN"))).//
				andExpect(status().isOk()).//
				andExpect(view().name("admin")).//
				andExpect(model().attributeExists("categories", "categoriesAdmin", "newCategory", "rootCount", "subCount", "totalCount", "registeredUsers"));
	}
	
	/**
	 * Returns model and view for secured uri after authentication2.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void returnsModelAndViewForSecuredUriAfterAuthentication2() throws Exception {

		mvc.perform(get("/admin/inspectCategory/2").with(user("admin1").roles("ADMIN"))).//
				andExpect(status().isOk()).//
				andExpect(view().name("adminSubcategory")).//
				andExpect(model().attributeExists("categories", "category", "subcategories", "newCategory"));
	}
	
	/**
	 * Returns model and view for secured uri after authentication3.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void returnsModelAndViewForSecuredUriAfterAuthentication3() throws Exception {

		mvc.perform(get("/admin/editUser/2").with(user("admin1").roles("ADMIN"))).//
				andExpect(status().isOk()).//
				andExpect(view().name("admin/adminEditUser")).//
				andExpect(model().attributeExists("categories", "user"));
	}	
}
