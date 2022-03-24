package com.lawencon.community.dto.profile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertProfileDtoReq {

	@NotNull(message = "User Id must be filled")
	private String userId;
	private String industryId;
	private String positionId;
	private String cityId;
	private String fileId;
	private String socialMediaId;
	
	@NotEmpty(message = "Full Name must be filled")
	@Size(min = 1, max = 50, message = "Full Name min length is 1 and max length is 50")
	private String fullName;
	
	private String phoneNumber;
	private String postalCode;
}
