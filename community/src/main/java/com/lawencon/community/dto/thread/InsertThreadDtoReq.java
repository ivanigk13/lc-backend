package com.lawencon.community.dto.thread;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertThreadDtoReq {

	@NotNull(message = "thread type id must be filled")
	private String threadTypeId;
	
	@NotEmpty(message = "title must be filled")
	@Size(min = 1, message = "title length must be at least 1")
	private String title;
	
	@NotEmpty(message = "content must be filled")
	@Size(min = 1, message = "content length must be at least 1")
	private String content;
}
