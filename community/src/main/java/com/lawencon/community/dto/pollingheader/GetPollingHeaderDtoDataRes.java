package com.lawencon.community.dto.pollingheader;

import lombok.Data;

@Data
public class GetPollingHeaderDtoDataRes {

	private String id;
	private String threadId;
	private String title;
	private Integer version;
	private Boolean isActive;
}
