package com.lawencon.community.dto.activitytype;

import java.util.List;

import lombok.Data;

@Data
public class GetAllActivityTypeDtoRes {

	private String msg;
	private Long rows;
	private List<GetActivityTypeDtoDataRes> data;
}
