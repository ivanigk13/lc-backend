package com.lawencon.community.dto.threadlike;

import lombok.Data;

@Data
public class GetThreadLikeDtoDataRes {

	private String id;
	private String threadId;
	private String userId;
	private Integer likeCounter;
	private Integer version;
	private Boolean isActive;
}
