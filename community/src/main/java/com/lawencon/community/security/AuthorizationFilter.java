package com.lawencon.community.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.community.dto.user.AuthorizationDtoRes;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

public class AuthorizationFilter extends BasicAuthenticationFilter{

	private JwtBuilderComponent builderComponent;

	public AuthorizationFilter(AuthenticationManager authenticationManager, JwtBuilderComponent builderComponent) {
		super(authenticationManager);
		this.builderComponent = builderComponent;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		String token = header.substring(7, header.length());
		String id = null;
		AuthorizationDtoRes authorizationDtoRes = new AuthorizationDtoRes();
		try {
			id = builderComponent.getClaimId(token);
		} catch (ExpiredJwtException e) {
			authorizationDtoRes.setMsg("token expired");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			e.printStackTrace();
		} catch (ClaimJwtException e) {
			authorizationDtoRes.setMsg("token invalid or token is wrong");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			e.printStackTrace();
		} catch (SignatureException e) {
			authorizationDtoRes.setMsg("token invalid");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			e.printStackTrace();
		}
		if(authorizationDtoRes.getMsg()!=null) {
			String loginStr = new ObjectMapper().writeValueAsString(authorizationDtoRes);
			response.getWriter().append(loginStr);
			return;
		}
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(id, null, null);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
	
}
