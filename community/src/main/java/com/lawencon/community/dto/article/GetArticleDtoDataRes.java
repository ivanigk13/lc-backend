package com.lawencon.community.dto.article;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class GetArticleDtoDataRes {

	private String id;
	private String publisherName;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime publishedTime;
	private String fileId;
	private String title;
	private String content;
	private Integer version;
	private Boolean isActive;
}
