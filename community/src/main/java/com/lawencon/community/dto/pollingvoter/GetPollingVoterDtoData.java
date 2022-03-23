package com.lawencon.community.dto.pollingvoter;

import lombok.Data;

@Data
public class GetPollingVoterDtoData {

	private String id;
	private String pollingDetailId;
	private Integer version;
	private Boolean isActive;
}
