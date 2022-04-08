package com.lawencon.community.dto.order;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateOrderDtoReq {

	@NotNull(message = "Order Id must be filled")
	private String id;
}
