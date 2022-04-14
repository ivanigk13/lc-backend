package com.lawencon.community.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.UserService;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class ApiSecurity extends WebSecurityConfigurerAdapter{
	
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final JwtBuilderComponent builderComponent;
	private final UserDao userDao;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().anyRequest().authenticated();
		http.addFilter(new AuthenticationFilter(super.authenticationManager(), builderComponent, userDao));
		http.addFilter(new AuthorizationFilter(super.authenticationManager(), builderComponent));
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.POST, "/users").antMatchers(HttpMethod.GET, "/files/**").antMatchers(HttpMethod.PUT, "/users/forgot-password").antMatchers(HttpMethod.GET, "/report/**");
	}
	
}
