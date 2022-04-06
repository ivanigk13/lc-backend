package com.lawencon.community.dto.activitytype;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateActivityTypeDtoReq {	
	
	@NotNull(message = "Id must be filled")
	private String id;	
	
	@NotEmpty(message = "Activity Type Name must be filled")
	private String activityTypeName;
	
	@NotNull(message = "Price must be filled")
	private BigDecimal price;
	
	@NotNull(message = "Version must be filled")
	private Integer version;
	
	@NotNull(message = "Is Active active must be filled")
	private Boolean isActive;
	
}
