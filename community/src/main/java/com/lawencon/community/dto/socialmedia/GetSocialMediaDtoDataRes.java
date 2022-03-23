package com.lawencon.community.dto.socialmedia;

import lombok.Data;

@Data
public class GetSocialMediaDtoDataRes {

	private String id;
	private Integer version;
	private Boolean isActive;
	private String instagram;
	private String facebook;
	private String twitter;
}
