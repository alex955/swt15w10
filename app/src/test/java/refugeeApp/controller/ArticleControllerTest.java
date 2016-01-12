package refugeeApp.controller;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import refugeeApp.AbstractWebIntegrationTests;

/**
 * The Class ArticleControllerTest.
 */
public class ArticleControllerTest extends AbstractWebIntegrationTests{

	
	/**
	 * Returns model for admin edit article.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void returnsModelForAdminEditArticle() throws Exception {

		mvc.perform(get("/editArticle/1").with(user("admin1").roles("ADMIN"))).//
				andExpect(status().isOk()).//
				andExpect(view().name("editArticle")).//
				andExpect(model().attributeExists("categories", "categoriesForm", "Article", "currentUserId", "user", "Creator", "isAdminLoggedIn"));
	}
	
	/**
	 * Returns model for admin new article.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void returnsModelForAdminNewArticle() throws Exception {

		mvc.perform(get("/newArticle").with(user("admin1").roles("ADMIN"))).//
				andExpect(status().isOk()).//
				andExpect(view().name("newArticle")).//
				andExpect(model().attributeExists("categories", "categoriesForm", "article", "creator"));
	}
	
	/**
	 * Returns model for admin edit attributes.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void returnsModelForAdminEditAttributes() throws Exception {

		mvc.perform(get("/editAttributes/1").with(user("admin1").roles("ADMIN"))).//
				andExpect(status().isOk()).//
				andExpect(view().name("editAttributes")).//
				andExpect(model().attributeExists("categories", "categoriesForm", "editArticle", "userId", "user", "Creator", "FormAttributes", "NewAttributes", "isAdminLoggedIn"));
	}

}
