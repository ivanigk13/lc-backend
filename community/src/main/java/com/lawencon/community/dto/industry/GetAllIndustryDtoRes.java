package com.lawencon.community.dto.industry;

import java.util.List;

import lombok.Data;

@Data
public class GetAllIndustryDtoRes {

	private String msg;
	private List<GetIndustryDtoDataRes> data;
}
