package com.lawencon.community.dto.threadlike;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InsertThreadLikeDtoReq {

	@NotNull(message = "thread id must be filled")
	private String threadId;
	
	@NotNull(message = "user id must be filled")
	private String userId;
	
	@NotNull(message = "like counter must be filled")
	private Integer likeCounter;
}
