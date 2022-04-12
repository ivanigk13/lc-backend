package com.lawencon.community.dto.user;

import java.util.List;

import lombok.Data;

@Data
public class GetAllUserDtoRes {

	private String msg;
	private Long rows;
	private List<GetUserDtoDataRes> data;
}
