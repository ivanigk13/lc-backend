package com.lawencon.community.dto.threadbookmark;

import lombok.Data;

@Data
public class GetThreadBookmarkDtoDataRes {

	private String id;
	private String threadId;
	private String userId;
	private Integer version;
	private Boolean isActive;
	
}
