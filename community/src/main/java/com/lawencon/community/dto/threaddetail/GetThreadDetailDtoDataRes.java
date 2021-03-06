package com.lawencon.community.dto.threaddetail;

import lombok.Data;

@Data
public class GetThreadDetailDtoDataRes {

	private String id;
	private String profilePictureId;
	private String fullName;
	private String threadId;
	private String comment;
	private Integer version;
	private Boolean isActive;
}
