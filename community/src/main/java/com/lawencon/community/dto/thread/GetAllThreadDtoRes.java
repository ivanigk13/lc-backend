package com.lawencon.community.dto.thread;

import java.util.List;

import lombok.Data;

@Data
public class GetAllThreadDtoRes {

	private String msg;
	private List<GetThreadDtoDataRes> data;
}
