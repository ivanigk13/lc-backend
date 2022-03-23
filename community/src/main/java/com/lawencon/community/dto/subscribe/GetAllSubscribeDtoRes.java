package com.lawencon.community.dto.subscribe;

import java.util.List;

import lombok.Data;

@Data
public class GetAllSubscribeDtoRes {

	private String msg;
	private List<GetSubscribeDtoDataRes> data;
}
