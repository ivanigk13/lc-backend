package com.lawencon.community.dto.threadtype;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertThreadTypeDtoReq {

	@NotEmpty(message = "thread type code must be filled")
	@Size(min = 1, max = 5, message = "thread type code min length is 1 and max length is 5")
	private String threadTypeCode;
	
	@NotEmpty(message = "thread type name must be filled")
	@Size(min = 1, max = 15, message = "thread type name min length is 1 and max length is 15")
	private String threadTypeName;
}
