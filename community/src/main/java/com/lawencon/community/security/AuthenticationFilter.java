package com.lawencon.community.security;

import java.io.IOException;
import java.time.Duration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DataBindingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.user.AuthenticationDtoRes;
import com.lawencon.community.dto.user.LoginDtoReq;
import com.lawencon.community.dto.user.LoginDtoRes;
import com.lawencon.community.model.User;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;
	private JwtBuilderComponent builderComponent;
	private UserDao userDao;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager, @Autowired JwtBuilderComponent builderComponent,
			@Autowired UserDao userDao) {
		this.authenticationManager = authenticationManager;
		this.builderComponent = builderComponent;
		this.userDao = userDao;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		LoginDtoReq loginDtoReq = new LoginDtoReq();
		try {
			loginDtoReq = new ObjectMapper().readValue(request.getInputStream(), LoginDtoReq.class);
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DataBindingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDtoReq.getEmail(), loginDtoReq.getPassword()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = userDao.getByEmail(authResult.getName());
		if(user!=null) {
			try {
				String token = builderComponent.generateToken(Duration.ofHours(5), user.getId());
				LoginDtoRes loginDtoRes = new LoginDtoRes();
				loginDtoRes.setToken(token);
				loginDtoRes.setId(user.getId());
				loginDtoRes.setRoleCode(user.getRole().getRoleCode());
				String loginStr = new ObjectMapper().writeValueAsString(loginDtoRes);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				response.getWriter().append(loginStr);	
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}		
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		AuthenticationDtoRes authenticationDtoRes = new AuthenticationDtoRes();
		authenticationDtoRes.setMsg("gagal login");
		String loginStr = new ObjectMapper().writeValueAsString(authenticationDtoRes);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().append(loginStr);
	}
	
}
