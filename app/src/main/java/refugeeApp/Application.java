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
package refugeeApp;

import java.util.Locale;

import org.salespointframework.EnableSalespoint;
import org.salespointframework.SalespointSecurityConfiguration;
import org.salespointframework.SalespointWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

/**
 * The Class Application.
 */
@EnableScheduling
@EnableSalespoint
@EntityScan(basePackageClasses = { Application.class, Jsr310JpaConverters.class })
public class Application extends SpringBootServletInitializer  {

	/** The Constant LOGIN_ROUTE. */
	private static final String LOGIN_ROUTE = "/";
	
	/**
	 * Java8 time dialect.
	 *
	 * @return the java8 time dialect
	 */
	// Java 8 type formatting
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	} 
	
    /**
     * Sets the location of the user, to change language default settings.
     *
     * @param application the application
     * @return the spring application builder
     */
			
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * The Class RefugeeWebConfiguration.
	 *
	 */
	@Configuration
	static class RefugeeWebConfiguration extends SalespointWebConfiguration {
		
		/* (non-Javadoc)
		 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)
		 */
		@Override
		public void addViewControllers(ViewControllerRegistry registry){
			registry.addViewController(LOGIN_ROUTE).setViewName("");
		}
		
		/*
		 * (non-Javadoc)
		 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
		 */
		@Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(localeChangeInterceptor());
	    }

		/**
		 * initializes locale change interceptor which changes the language after calling url?language=LOCALE, e.g. ?language=DE
		 * @return locale change interceptor
		 */
	    @Bean
	    public LocaleChangeInterceptor localeChangeInterceptor(){
	        LocaleChangeInterceptor localeChangeInterceptor=new LocaleChangeInterceptor();
	        localeChangeInterceptor.setParamName("language");
	        return localeChangeInterceptor;
	    }

	    /**
	     * defines cookies locale resolver as locale resolver
	     * @return Cookie Locale Resolver, cookies live 1 month
	     */
	    @Bean(name = "localeResolver")
	    public LocaleResolver getLocaleResolver(){
	    	CookieLocaleResolver resolver = new CookieLocaleResolver();
	    	//one month
	    	resolver.setCookieMaxAge(2678400);
	        return resolver;
	    }
	}
	
	/**
	 * The Class WebSecurityConfiguration.
	 *
	 */
	@Configuration
	static class WebSecurityConfiguration extends SalespointSecurityConfiguration {

		/* (non-Javadoc)
		 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
		 */
		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.csrf().disable();

			http.authorizeRequests().antMatchers("/resources/**").permitAll().and().formLogin().loginPage(LOGIN_ROUTE).loginProcessingUrl(LOGIN_ROUTE).defaultSuccessUrl("/search").and()
					.logout().logoutUrl("/logout").logoutSuccessUrl("/");
		}
	}

		


}
