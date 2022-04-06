package com.lawencon.community.dto.activitytype;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class GetActivityTypeDtoDataRes {
	
	private String id;
	private String activityTypeCode;
	private String activityTypeName;	
	private BigDecimal price;
	private Integer version;
	private Boolean isActive;

}
