package com.lawencon.community.dto.order;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InsertOrderDtoReq {

	@NotNull(message = "Order Status must be filled")
	private String orderStatus;
	
	@NotNull(message = "User must be filled")
	private String user;
		
	private String file;
	
	@NotEmpty(message = "Invoice must be filled")
	private String invoice;
}
