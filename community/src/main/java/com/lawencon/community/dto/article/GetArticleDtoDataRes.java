package com.lawencon.community.dto.article;

import lombok.Data;

@Data
public class GetArticleDtoDataRes {

	private String id;
	private String fileId;
	private String title;
	private String content;
	private Integer version;
	private Boolean isActive;
}
