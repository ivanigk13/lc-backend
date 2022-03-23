package com.lawencon.community.dto.threadlike;

import lombok.Data;

@Data
public class GetThreadLikeDtoDataRes {

	private String id;
	private Integer version;
	private Boolean isActive;
	private String threadId;
	private String userId;
	private Integer likeCounter;
}
