package com.lawencon.community.dto.industry;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateIndustryDtoReq {
	
	
	@NotNull(message = "Id must be filled")
	private String id;
	
	@NotEmpty(message = "Industry Name must be filled")
	@Size(min = 1, max = 50, message = "Industry Name min length is 1 and max length is 50")
	private String industryName;
	
	@NotNull(message = "Version must be filled")
	private Integer version;
	
	@NotNull(message = "Is Active active must be filled")
	private Boolean isActive;
}
