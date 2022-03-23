package com.lawencon.community.dto.pollingvoter;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdatePollingVoterDtoReq {

	@NotNull(message = "polling id must be filled")
	private String id;
	
	@NotNull(message = "polling detail id must be filled")
	private String pollingDetailId;
	
	private Integer version;
}
