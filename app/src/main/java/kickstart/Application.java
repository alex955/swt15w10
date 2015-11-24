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

import org.joda.time.DateTime;
import org.salespointframework.EnableSalespoint;
import org.salespointframework.SalespointSecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import kickstart.model.CategoryRepo;
import kickstart.model.Good;
import kickstart.model.User;
import kickstart.model.UserRepository;
import kickstart.model.activityREPO;
import kickstart.model.goodREPO;
import kickstart.model.Category;

@EnableSalespoint
public class Application {
	
	@Autowired CategoryRepo categories;
	@Autowired goodREPO goodREPO;
	@Autowired activityREPO activityREPO;
    @Autowired UserRepository userRepository;

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
    	
    	Good g1 = new Good("in Möbel1", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 1);
    	Good g2 = new Good("in Möbel2", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 1);
    	Good g3 = new Good("in Badmöbel1", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 2);
    	Good g4 = new Good("in Badmöbel2", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 2);
    	Good g5 = new Good("in Küchenmöbel", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 3);
    	Good g6 = new Good("in Bücher", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 4);
    	Good g7 = new Good("in gute Bücher", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 5);
    	Good g8 = new Good("in schlechte Bücher", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 6);
    	Good g9 = new Good("in Blablub", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 7);
    	Good g10 = new Good("in weapons", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 8);
    	Good g11 = new Good("in gruppenter", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 9);
    	Good g12 = new Good("in nochmal möbel", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 11);
    	
    	
    	goodREPO.save(g1);
    	goodREPO.save(g2);
    	goodREPO.save(g3);
    	goodREPO.save(g4);
    	goodREPO.save(g5);
    	goodREPO.save(g6);
    	goodREPO.save(g7);
    	goodREPO.save(g8);
    	goodREPO.save(g9);
    	goodREPO.save(g10);
    	goodREPO.save(g11);
    	goodREPO.save(g12);
    	
//    	goodREPO.save(new Good("Hackepeter","Lorem ipsum","img/hacke.jpg","An der Jever",01217,"Pirna","11"));
//    	goodREPO.save(new Good("Jeans","Lorem ipsum","img/jeans.jpg","An der hasseröder",01217,"Dresden","11"));
//    	goodREPO.save(new Good("Pommes","Lorem ipsum","img/pommes.jpg","An der Jever",01217,"Pirna","11"));
    	
    	this.userRepository.save(new User(1,"rolle","nachname","vorname","username", "mail", "pwd", "pwd", "stadt", "01067", "straße", 123, "sprache","sprache2","sprache3"));
    	this.userRepository.save(new User(1,"rolle","nachname","vorname","username", "mail", "pwd", "pwd", "stadt", "01067", "straße", 123, "sprache","sprache2","sprache3"));
    	this.userRepository.save(new User(1,"rolle","nachname","vorname","username", "mail", "pwd", "pwd", "stadt", "01067", "straße", 123, "sprache","sprache2","sprache3"));
    	this.userRepository.save(new User(1,"rolle","nachname","vorname","username", "mail", "pwd", "pwd", "stadt", "01067", "straße", 123, "sprache","sprache2","sprache3"));
    	this.userRepository.save(new User(1,"rolle","nachname","vorname","username", "mail", "pwd", "pwd", "stadt", "01067", "straße", 123, "sprache","sprache2","sprache3"));
    	this.userRepository.save(new User(1,"rolle","nachname","vorname","username", "mail", "pwd", "pwd", "stadt", "01067", "straße", 123, "sprache","sprache2","sprache3"));
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
