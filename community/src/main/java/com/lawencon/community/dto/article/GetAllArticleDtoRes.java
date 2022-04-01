package com.lawencon.community.dto.article;

import java.util.List;

import lombok.Data;

@Data
public class GetAllArticleDtoRes {

	private String msg;
	private List<GetArticleDtoDataRes> data;
}
