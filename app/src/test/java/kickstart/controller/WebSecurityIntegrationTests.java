/*
 * Copyright 2013-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kickstart.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;

import kickstart.AbstractWebIntegrationTests;

/**
 * The Class WebSecurityIntegrationTests.
 */
public class WebSecurityIntegrationTests extends AbstractWebIntegrationTests {


	/**
	 * Redirects to login page for secured resource.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void redirectsToLoginPageForSecuredResource() throws Exception {

		mvc.perform(get("/admin")).//
		andExpect(status().isFound()).//
		andExpect(header().string("Location", endsWith("/")));
		
		mvc.perform(get("/admin/addRootCat")).//
		andExpect(status().isFound()).//
		andExpect(header().string("Location", endsWith("/")));
		
		mvc.perform(get("/admin/addSubcategory/0")).//
		andExpect(status().isFound()).//
		andExpect(header().string("Location", endsWith("/")));
		
		mvc.perform(get("/admin/inspectCategory/0")).//
		andExpect(status().isFound()).//
		andExpect(header().string("Location", endsWith("/")));
		
		mvc.perform(get("/admin/deactivateUser/0")).//
		andExpect(status().isFound()).//
		andExpect(header().string("Location", endsWith("/")));
		
		mvc.perform(get("/admin/activateUser/0")).//
		andExpect(status().isFound()).//
		andExpect(header().string("Location", endsWith("/")));
		
		mvc.perform(get("/admin/editUser/0")).//
		andExpect(status().isFound()).//
		andExpect(header().string("Location", endsWith("/")));
	}
}
