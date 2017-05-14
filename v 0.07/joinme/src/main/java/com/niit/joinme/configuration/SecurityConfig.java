package com.niit.joinme.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@EnableWebSecurity
@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

	  auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"select userName,password, enabled from DSUser where userName=?")
		.authoritiesByUsernameQuery(
			"select userName, userRole from DSUser where userName=?");
	}
	

    @Bean(name="authenticationManager")
    public AuthenticationManager authenticationManagerBean() throws Exception {
		
	       return super.authenticationManagerBean();
	   }
	



    @Override
    protected void configure(HttpSecurity http) throws Exception {
   
      http.authorizeRequests()
      .antMatchers("/admin").access("hasRole('ADMIN')")
		.and()
		  .formLogin().loginPage("http://localhost:8081/joinme_frontend/#/").loginProcessingUrl("/perform_login").failureUrl("/loginerror")
		  .usernameParameter("userName").passwordParameter("password").defaultSuccessUrl("http://localhost:8081/joinme_frontend/#/")
		.and()
		  .logout().logoutSuccessUrl("/perform_logout")
		.and()
		  .exceptionHandling().accessDeniedPage("/403")
		.and()
		  .csrf().disable();
    }   

}
