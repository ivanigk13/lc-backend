package com.lawencon.community.dto.orderstatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateOrderStatusDtoReq {
	
	
	@NotNull(message = "Id is required")
	private String id;
	
	@NotEmpty(message = "Status Code must be filled")
	private String statusCode;
	
	@NotEmpty(message = "Status Name must be filled")
	private String statusName;
	private Integer version;
}
