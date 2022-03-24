package com.lawencon.community.dto.socialmedia;

import lombok.Data;

@Data
public class GetSocialMediaDtoDataRes {

	private String id;
	private String instagram;
	private String facebook;
	private String twitter;
	private Integer version;
	private Boolean isActive;
}
