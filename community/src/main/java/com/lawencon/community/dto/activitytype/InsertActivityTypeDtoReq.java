package com.lawencon.community.dto.activitytype;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class InsertActivityTypeDtoReq {
	
	@NotEmpty(message = "Activity Type Name must be filled")
	private String activityTypeCode;
	
	@NotEmpty(message = "Activity Type Name must be filled")
	private String activityTypeName;
	
}
