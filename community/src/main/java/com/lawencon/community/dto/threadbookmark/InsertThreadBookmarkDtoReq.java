package com.lawencon.community.dto.threadbookmark;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InsertThreadBookmarkDtoReq {

	@NotNull(message = "thread type id must be filled")
	private String threadTypeId;
	
	@NotNull(message = "user id must be filled")
	private String userId;
}
