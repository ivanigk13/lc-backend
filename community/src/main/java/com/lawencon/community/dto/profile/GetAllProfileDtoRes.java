package com.lawencon.community.dto.profile;

import java.util.List;

import lombok.Data;

@Data
public class GetAllProfileDtoRes {

	private String msg;
	private List<GetProfileDtoDataRes> data;
}
