package com.lawencon.community.dto.article;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertArticleDtoReq {

	@NotEmpty(message = "Title must be filled")
	@Size(min = 1, message = "Title length must be at least 1")
	private String title;
	
	@NotEmpty(message = "Content must be filled")
	@Size(min = 1, message = "Content length must be at least 1")
	private String content;
}
