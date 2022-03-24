package com.lawencon.community.dto.threadtype;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateThreadTypeDtoReq {

	@NotNull(message = "id must be filled")
	private String id;
	
	@NotEmpty(message = "Thread Type Name must be filled")
	@Size(min = 1, max = 15, message = "Thread Type Name min length is 1 and max length is 15")
	private String threadTypeName;
	
	@NotNull(message = "Version must be filled")
	private Integer version;

	@NotNull(message = "Is Active must be filled")
	private Boolean isActive;
}
