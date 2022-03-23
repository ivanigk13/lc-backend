package com.lawencon.community.dto.threadbookmark;

import java.util.List;

import lombok.Data;

@Data
public class GetAllThreadBookmarkDtoRes {

	private String msg;
	private List<GetThreadBookmarkDtoDataRes> data;
}
