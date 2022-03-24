package com.lawencon.community.dto.order;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertOrderDtoReq {

	@NotNull(message = "Order Status Id must be filled")
	private String orderStatusId;
	
	@NotNull(message = "User Id must be filled")
	private String userId;
	
	@NotNull(message = "File Id must be filled")
	private String fileId;
	
	@NotEmpty(message = "Invoice must be filled")
	@Size(min = 1, max = 10, message = "Invoice min length is 1 and max length is 10")
	private String invoice;
}
