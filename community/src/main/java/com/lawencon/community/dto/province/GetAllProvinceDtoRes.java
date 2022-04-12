package com.lawencon.community.dto.province;

import java.util.List;

import lombok.Data;

@Data
public class GetAllProvinceDtoRes {

	private String msg;
	private Long rows;
	private List<GetProvinceDtoDataRes> data;
}
