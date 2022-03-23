package com.lawencon.community.dto.user;

import lombok.Data;

@Data
public class LoginDtoRes {

	private String id;
	private String roleCode;
	private String email;
	private String token; 
	private String msg;
}
