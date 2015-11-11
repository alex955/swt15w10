/*
 * Copyright 2014.2015 the original author or authors.
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
package kickstart;

import javax.annotation.PostConstruct;

import org.salespointframework.EnableSalespoint;
import org.salespointframework.SalespointSecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import kickstart.model.CategoryRepo;
import kickstart.model.Category;

@EnableSalespoint
public class Application {
	
	@Autowired CategoryRepo categories;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@PostConstruct
    void initialize(){
    	categories.save(new Category("Möbel", -1));
    	categories.save(new Category("Badmöbel", 1));
    	categories.save(new Category("Küchenmöbel", 1));
    	categories.save(new Category("Bücher", -1));
    	categories.save(new Category("gute Bücher", 4));
    	categories.save(new Category("schlechte Bücher", 4));
    	categories.save(new Category("Blablub", 4));
    	categories.save(new Category("Weapons of math instruction", -1));
    	categories.save(new Category("Gruppenterrorie", 8));
    	categories.save(new Category("Sisisnus und Cosisisnus", 8));
    	categories.save(new Category("nochmal Möbel", -1));
    	categories.save(new Category("nochmal nochmal Möbel", -1));
    }

	@Configuration
	static class WebSecurityConfiguration extends SalespointSecurityConfiguration {

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.csrf().disable();

			http.authorizeRequests().antMatchers("/**").permitAll().and().formLogin().loginProcessingUrl("/login").and()
					.logout().logoutUrl("/logout").logoutSuccessUrl("/");
		}
	}
}
