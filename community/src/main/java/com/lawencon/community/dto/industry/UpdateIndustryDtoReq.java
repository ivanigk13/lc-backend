package com.lawencon.community.dto.industry;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateIndustryDtoReq {
	
	
	@NotNull(message = "Id is required")
	private String id;
	
	@NotEmpty(message = "Industry Name must be filled")
	private String industryName;
	
	private Integer version;
}
