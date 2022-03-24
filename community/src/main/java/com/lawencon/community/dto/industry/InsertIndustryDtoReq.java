package com.lawencon.community.dto.industry;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertIndustryDtoReq {
	
	@NotEmpty(message = "Industry Code must be filled")
	@Size(min = 1, max = 5, message = "Industry Code min length is 1 and max length is 5")
	private String industryCode;
		
	@NotEmpty(message = "Industry Name must be filled")
	@Size(min = 1, max = 50, message = "Industry Name min length is 1 and max length is 50")
	private String industryName;
			
}
