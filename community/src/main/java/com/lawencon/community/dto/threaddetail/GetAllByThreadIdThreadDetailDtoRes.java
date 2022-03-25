package com.lawencon.community.dto.threaddetail;

import java.util.List;

import lombok.Data;

@Data
public class GetAllByThreadIdThreadDetailDtoRes {

	private String msg;
	private List<GetThreadDetailDtoDataRes> data;
}
