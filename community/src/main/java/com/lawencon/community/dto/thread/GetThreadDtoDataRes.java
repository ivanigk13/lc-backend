package com.lawencon.community.dto.thread;

import lombok.Data;

@Data
public class GetThreadDtoDataRes {

	private String id;
	private Integer version;
	private String threadTypeId;
	private String threadTypeName;
	private String fileId;
	private String title;
	private String content;
	private Boolean isActive;
}
