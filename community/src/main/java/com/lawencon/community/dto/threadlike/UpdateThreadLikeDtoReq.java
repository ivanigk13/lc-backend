package com.lawencon.community.dto.threadlike;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateThreadLikeDtoReq {

	@NotNull(message = "id must be filled")
	private String id;
	
	@NotNull(message = "version must be filled")
	private Integer version;

	@NotNull(message = "is active must be filled")
	private Boolean isActive;
	
	@NotNull(message = "thread id must be filled")
	private String threadId;
	
	@NotNull(message = "user id must be filled")
	private String userId;
	
	@NotNull(message = "like counter must be filled")
	private Integer likeCounter;
}
