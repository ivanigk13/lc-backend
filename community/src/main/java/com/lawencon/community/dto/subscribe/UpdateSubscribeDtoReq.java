package com.lawencon.community.dto.subscribe;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateSubscribeDtoReq {

	@NotNull(message = "Id must be filled")
	private String id;
	
	@NotNull(message = "Duration must be filled")
	private Integer duration;
	
	@NotNull(message = "Price must be filled")
	private BigDecimal price;
	
	@NotNull(message = "Version must be filled")
	private Integer version;
	
	@NotNull(message = "Is Active must be filled")
	private Boolean isActive;
}
