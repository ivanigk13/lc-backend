package com.lawencon.community.dto.profile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateProfileDtoReq {

	@NotNull(message = "Profile Id must be filled")
	private String id;
	
	@NotNull(message = "Industry Id must be filled")
	private String industryId;
	
	@NotNull(message = "Position Id must be filled")
	private String positionId;
	
	private String cityId;
	private String socialMediaId;
	private String fileId;
	
	@NotEmpty(message = "Full Name must be filled")
	@Size(min = 1, max = 50, message = "full name min length is 1 and max length is 50")
	private String fullName;
	
	@NotEmpty(message = "Company Name must be filed")
	@Size(min = 1, max = 50, message = "Company Name min lenght is 1 and max lenght is 50")
	private String companyName;
	
	private String phoneNumber;
	private String postalCode;
	
	@NotNull(message = "Version must be filled")
	private Integer version;
	
	@NotNull(message = "Is Active active must be filled")
	private Boolean isActive;
}
