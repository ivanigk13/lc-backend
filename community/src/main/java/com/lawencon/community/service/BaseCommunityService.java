package com.lawencon.community.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseService;
import com.lawencon.community.security.AuthPrincipal;

public class BaseCommunityService extends BaseService {
	
	private AuthPrincipal authPrincipal;
	
	@Autowired
	public void setAuthPrincipal(AuthPrincipal authPrincipal) {
		this.authPrincipal = authPrincipal;
	}

	public String getId() {	
		
		if(authPrincipal.getAuthentication().getPrincipal() != null) {
			return authPrincipal.getAuthentication().getPrincipal().toString();
		}
		
		throw new RuntimeException("Invalid Login");
	}
	
	public String generateCode(int input) {
		String codeString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0987654321";
		StringBuilder sb = new StringBuilder(input);
		
		for (int i = 0; i < input; i++) {
			int index = (int) (codeString.length() * Math.random());
			sb.append(codeString.charAt(index));
		}
		return sb.toString();
	}
	
	public String generateVerificationCode(int input) {
		String codeString = "1234567890";
		StringBuilder sb = new StringBuilder(input);
		
		for (int i = 0; i < input; i++) {
			int index = (int) (codeString.length() * Math.random());
			sb.append(codeString.charAt(index));
		}
		return sb.toString();
	}
}
