package com.lawencon.community.dto.position;

import java.util.List;

import lombok.Data;

@Data
public class GetAllPositionDtoRes {

	private String msg;
	private List<GetPositionDtoDataRes> data;
}
