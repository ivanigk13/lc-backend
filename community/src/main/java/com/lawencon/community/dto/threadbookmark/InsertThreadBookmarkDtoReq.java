package com.lawencon.community.dto.threadbookmark;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InsertThreadBookmarkDtoReq {

	@NotNull(message = "Thread Id must be filled")
	private String threadId;
	
	@NotNull(message = "User Id must be filled")
	private String userId;
}
