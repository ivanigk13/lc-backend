package com.lawencon.community.dto.category;

import java.util.List;

import lombok.Data;

@Data
public class GetAllCategoryDtoRes {

	private String msg;
	private List<GetCategoryDtoDataRes> data;
}
