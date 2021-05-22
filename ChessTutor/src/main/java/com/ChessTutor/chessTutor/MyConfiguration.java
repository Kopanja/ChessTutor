package com.ChessTutor.chessTutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;




@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	     http
	             // komunikacija izmedju klijenta i servera je stateless posto je u pitanju REST aplikacija
	             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

	             // svim korisnicima dopusti da pristupe putanji /auth/**
	             .authorizeRequests().antMatchers("/api/**").permitAll()

	             // umesto anotacija iynad svake metode, moze i ovde da se proveravaju prava pristupa ya odredjeni URL
	             //.antMatchers(HttpMethod.GET, "/api/users").hasRole("CONSUMER")

	             // za svaki drugi zahtev korisnik mora biti autentifikovan
	             .anyRequest().authenticated();
	             // za development svrhe ukljuci konfiguraciju za CORS iz WebConfig klase
	             
	 }

	 @SuppressWarnings("deprecation")
	@Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**")
	                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
	            }
	        };
	    }
	 // Generalna bezbednost aplikacije
	 @Override
	 public void configure(WebSecurity web) throws Exception {
	     // TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
	     web.ignoring().antMatchers(HttpMethod.POST, "/api/**");
	     web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
	             "/**/*.css", "/**/*.js");
	 }
	 
	

}
