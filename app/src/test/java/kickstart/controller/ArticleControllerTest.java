package kickstart.controller;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kickstart.AbstractWebIntegrationTests;

public class ArticleControllerTest extends AbstractWebIntegrationTests{

	
	@Test
	public void returnsModelForAdminEditArticle() throws Exception {

		mvc.perform(get("/editArticle/1").with(user("admin1").roles("ADMIN"))).//
				andExpect(status().isOk()).//
				andExpect(view().name("editArticle")).//
				andExpect(model().attributeExists("categories", "categoriesForm", "Article", "userId", "user", "Creator", "isAdminLoggedIn"));
	}
	
	@Test
	public void returnsModelForAdminNewArticle() throws Exception {

		mvc.perform(get("/newArticle").with(user("admin1").roles("ADMIN"))).//
				andExpect(status().isOk()).//
				andExpect(view().name("newArticle")).//
				andExpect(model().attributeExists("categories", "categoriesForm", "article", "creator"));
	}
	
	@Test
	public void returnsModelForAdminEditAttributes() throws Exception {

		mvc.perform(get("/editAttributes/1").with(user("admin1").roles("ADMIN"))).//
				andExpect(status().isOk()).//
				andExpect(view().name("editAttributes")).//
				andExpect(model().attributeExists("categories", "categoriesForm", "editArticle", "userId", "user", "Creator", "FormAttributes", "NewAttributes", "isAdminLoggedIn"));
	}

}
