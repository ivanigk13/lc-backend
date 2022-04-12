package com.lawencon.community.dto.industry;

import java.util.List;

import lombok.Data;

@Data
public class GetAllIndustryDtoRes {

	private String msg;
	private Long rows;
	private List<GetIndustryDtoDataRes> data;
}
