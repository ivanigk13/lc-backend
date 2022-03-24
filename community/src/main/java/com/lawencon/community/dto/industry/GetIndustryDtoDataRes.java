package com.lawencon.community.dto.industry;

import lombok.Data;

@Data
public class GetIndustryDtoDataRes {
	
	private String id;
	private String industryCode;
	private String industryName;
	private Integer version;
	private Boolean isActive;

}
