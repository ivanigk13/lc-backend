package com.lawencon.community.dto.threadtype;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertThreadTypeDtoReq {

	@NotEmpty(message = "Thread Type Code must be filled")
	@Size(min = 1, max = 5, message = "Thread Type Code min length is 1 and max length is 5")
	private String threadTypeCode;
	
	@NotEmpty(message = "Thread Type Name must be filled")
	@Size(min = 1, max = 15, message = "Thread Type Name min length is 1 and max length is 15")
	private String threadTypeName;
}
