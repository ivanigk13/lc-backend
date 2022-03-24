package com.lawencon.community.dto.subscribe;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertSubscribeDtoReq {

	@NotNull(message = "Duration must be filled")
	@Size(min = 1, message = "Duration must be at least 1")
	private Integer duration;
	
	@NotNull(message = "Price must be filled")
	@Size(min = 30000, message = "Price must be at least 30000")
	private BigDecimal price;
}
