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
}
