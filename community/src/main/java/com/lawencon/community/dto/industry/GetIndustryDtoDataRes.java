package com.lawencon.community.dto.industry;

import lombok.Data;

@Data
public class GetIndustryDtoDataRes {
	
	private String id;
	private String industryCode;
	private String industryName;
	private String createdBy;
	private Integer version;

}
