package com.lawencon.community.dto.user;

import lombok.Data;

@Data
public class GetByIdUserDtoRes {
	
	private String msg;
	private GetUserDtoDataRes data;
}
