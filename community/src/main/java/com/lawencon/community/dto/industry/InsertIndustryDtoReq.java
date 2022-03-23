package com.lawencon.community.dto.industry;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class InsertIndustryDtoReq {
		
	@NotEmpty(message = "Industry Name must be filled")
	private String industryName;
	
	@NotEmpty(message = "Industry Code must be filled")
	private String industryCode;		
}
