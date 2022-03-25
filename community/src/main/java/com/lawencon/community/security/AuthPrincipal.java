package com.lawencon.community.security;

import org.springframework.security.core.Authentication;

public interface AuthPrincipal {
	Authentication getAuthentication();
}
