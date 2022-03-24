package com.lawencon.community.dto.threadlike;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateThreadLikeDtoReq {

	@NotNull(message = "Id must be filled")
	private String id;
	
	@NotNull(message = "Thread Id must be filled")
	private String threadId;
	
	@NotNull(message = "User Id must be filled")
	private String userId;
	
	@NotNull(message = "Like Counter must be filled")
	private Integer likeCounter;
	
	@NotNull(message = "Version must be filled")
	private Integer version;

	@NotNull(message = "Is Active must be filled")
	private Boolean isActive;
}
