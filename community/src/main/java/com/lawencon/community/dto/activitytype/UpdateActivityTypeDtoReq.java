package com.lawencon.community.dto.activitytype;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateActivityTypeDtoReq {	
	
	@NotNull(message = "Id must be filled")
	private String id;
	
	@NotEmpty(message = "Activity Type Code must be filled")
	private String activityTypeCode;
	
	@NotEmpty(message = "Activity Type Name must be filled")
	private String activityTypeName;
	
	@NotNull(message = "Version must be filled")
	private Integer version;
	
	@NotNull(message = "Is Active active must be filled")
	private Boolean isActive;
	
}
