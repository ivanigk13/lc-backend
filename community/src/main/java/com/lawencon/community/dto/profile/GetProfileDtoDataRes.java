package com.lawencon.community.dto.profile;

import lombok.Data;

@Data
public class GetProfileDtoDataRes {

	private String id;
	private String userId;
	private String industryId;
	private String positionId;
	private String cityId;
	private String provinceId;
	private String fileId;
	private String socialMediaId;
	private String fullName;
	private String companyName;
	private String phoneNumber;
	private String postalCode;
	private Integer version;
	private Boolean isActive;
}
