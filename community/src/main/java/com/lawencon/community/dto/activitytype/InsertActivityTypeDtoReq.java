package com.lawencon.community.dto.activitytype;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertActivityTypeDtoReq {
	
	@NotEmpty(message = "Activity Type Code must be filled")
	@Size(min = 1, max = 5, message = "Activity Type Code min length is 1 and max length is 5")
	private String activityTypeCode;
	
	@NotEmpty(message = "Activity Type Name must be filled")
	@Size(min = 1, max = 10, message = "Activity Type Name min length is 1 and max length is 10")
	private String activityTypeName;
	
}
