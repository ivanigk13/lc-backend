package com.lawencon.community.dto.city;

import java.util.List;

import lombok.Data;

@Data
public class GetAllCityDtoRes {

	private String msg;
	private Long rows;
	private List<GetCityDtoDataRes> data;
}
