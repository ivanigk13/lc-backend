package com.lawencon.community.dto.pollingdetail;

import lombok.Data;

@Data
public class GetPollingDetailDtoDataRes {

	private String id;
	private String pollingHeaderId;
	private String pollingName;
	private int pollingCounter;
	private Integer version;
	private Boolean isActive;
}
