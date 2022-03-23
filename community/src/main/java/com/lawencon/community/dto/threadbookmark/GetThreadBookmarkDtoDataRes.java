package com.lawencon.community.dto.threadbookmark;

import lombok.Data;

@Data
public class GetThreadBookmarkDtoDataRes {

	private String id;
	private Integer version;
	private Boolean isActive;
	private String threadId;
	private String userId;
	
}
