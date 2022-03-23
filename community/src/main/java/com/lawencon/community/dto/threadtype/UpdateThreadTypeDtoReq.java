package com.lawencon.community.dto.threadtype;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateThreadTypeDtoReq {

	@NotNull(message = "id must be filled")
	private String id;
	
	@NotNull(message = "version must be filled")
	private Integer version;

	@NotNull(message = "is active must be filled")
	private Boolean isActive;
	
	@NotEmpty(message = "thread type name must be filled")
	@Size(min = 1, max = 15, message = "thread type name min length is 1 and max length is 15")
	private String threadTypeName;
}
