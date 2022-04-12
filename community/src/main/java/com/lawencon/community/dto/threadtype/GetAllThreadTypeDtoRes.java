package com.lawencon.community.dto.threadtype;

import java.util.List;

import lombok.Data;

@Data
public class GetAllThreadTypeDtoRes {

	private String msg;
	private Long rows;
	private List<GetThreadTypeDtoDataRes> data;
}
