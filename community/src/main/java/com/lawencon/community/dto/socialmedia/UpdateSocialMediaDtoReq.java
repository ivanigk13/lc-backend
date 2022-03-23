package com.lawencon.community.dto.socialmedia;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateSocialMediaDtoReq {

	@NotNull(message = "id must be filled")
	private String id;
	
	@NotNull(message = "version must be filled")
	private Integer version;
	
	private String instagram;
	private String facebook;
	private String twitter;
	
	@NotNull(message = "is active active must be filled")
	private Boolean isActive;
}
