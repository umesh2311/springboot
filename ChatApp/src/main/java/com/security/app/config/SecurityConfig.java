package com.security.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	//Core interface which loads user-specific data. 
	//It is used throughout the framework as a user DAO and is the strategy used by the DaoAuthenticationProvider. 
	//Flow looks like this,  Controller --> Service --> DAO
	
	//FLow is --> SecurityConfig --> AuthenticationProvider(DaoAuthenticationPovider) --> UserDetailsService(Interface) 
	// --> UserDetails(Interface)
	//Need an implementation of UserDetailsService which will further connect to DAO get user details
	
	/*@Autowired
	private UserDetailsService userDetailsService;
	@Bean
	public AuthenticationProvider authProvider() {
		
		// An AuthenticationProvider implementation that retrieves user details from a UserDetailsService.
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}*/
	//Any request should be authenticated
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests().antMatchers("/login").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic();
		/*.formLogin()
		.loginPage("/login").permitAll()
		.and()
		.logout().invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/logout-success").permitAll();*/
		
	}
	
	
	/*@Bean
	@Override
	protected UserDetailsService userDetailsService() {

		List<UserDetails> users = new ArrayList<>();
		users.add(User.withDefaultPasswordEncoder().username("Umesh").password("12345").roles("USER").build());
		users.add(User.withDefaultPasswordEncoder().username("Rashmi").password("12345").roles("ADMIN").build());
		
		return new InMemoryUserDetailsManager(users);
	}*/

}
