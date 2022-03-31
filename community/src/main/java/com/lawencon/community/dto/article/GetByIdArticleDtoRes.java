package com.lawencon.community.dto.article;

import lombok.Data;

@Data
public class GetByIdArticleDtoRes {

	private String msg;
	private GetArticleDtoDataRes data;
}
