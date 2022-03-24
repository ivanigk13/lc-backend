package com.lawencon.community.dto.socialmedia;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateSocialMediaDtoReq {

	@NotNull(message = "Id must be filled")
	private String id;
	
	private String instagram;
	private String facebook;
	private String twitter;
	
	@NotNull(message = "Version must be filled")
	private Integer version;
	
	@NotNull(message = "Is Active active must be filled")
	private Boolean isActive;
}
