package com.lawencon.community.dto.orderstatus;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class InsertOrderStatusDtoReq {

	@NotEmpty(message = "Status Code must be filled")
	private String statusCode;
	
	@NotEmpty(message = "Status Name must be filled")
	private String statusName;
}
