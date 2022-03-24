package com.lawencon.community.dto.threaddetail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertThreadDetailDtoReq {

	@NotNull(message = "Thread Id must be filled")
	private String threadId;
	
	@NotEmpty(message = "Comment must be filled")
	@Size(min = 1, message = "Comment length must be at least 1")
	private String comment;
}
