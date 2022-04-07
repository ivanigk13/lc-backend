package com.lawencon.community.dto.thread;

import lombok.Data;

@Data
public class GetThreadDtoDataRes {

	private String id;
	private String profilePictureId;
	private String fullName;
	private String threadTypeId;	
	private String threadTypeName;
	private String fileId;
	private String title;
	private String content;
	private Integer version;
	private Boolean isActive;
}
