package com.lawencon.community.dto.activity;

import java.util.List;

import lombok.Data;

@Data
public class GetAllActivityDtoRes {

	private String msg;
	private List<GetActivityDtoDataRes> data;
}
