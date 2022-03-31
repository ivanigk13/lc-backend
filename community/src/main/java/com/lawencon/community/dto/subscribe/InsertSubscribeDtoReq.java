package com.lawencon.community.dto.subscribe;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InsertSubscribeDtoReq {

	@NotNull(message = "Duration must be filled")
	private Integer duration;
	
	@NotNull(message = "Price must be filled")
	private BigDecimal price;
}
