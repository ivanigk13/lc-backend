package com.lawencon.community.dto.activitytype;

import lombok.Data;

@Data
public class GetActivityTypeDtoDataRes {
	
	private String id;
	private String activityTypeCode;
	private String activityTypeName;	
	private Integer version;
	private Boolean isActive;

}
