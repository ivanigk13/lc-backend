package com.lawencon.community.dto.activity;

import java.util.List;

import lombok.Data;

@Data
public class GetLastTwoActivityDtoRes {

	private String msg;
	private List<GetActivityDtoDataRes> data;
}
