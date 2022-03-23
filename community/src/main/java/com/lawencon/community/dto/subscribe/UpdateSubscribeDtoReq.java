package com.lawencon.community.dto.subscribe;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateSubscribeDtoReq {

	@NotNull(message = "id must be filled")
	private String id;
	
	@NotNull(message = "version must be filled")
	private Integer version;
	
	@NotNull(message = "duration must be filled")
	@Size(min = 1, message = "duration must be at least 1")
	private Integer duration;
	
	@NotNull(message = "price must be filled")
	@Size(min = 30000, message = "price must be at least 30000")
	private BigDecimal price;
	
	@NotNull(message = "is active must be filled")
	private Boolean isActive;
}
