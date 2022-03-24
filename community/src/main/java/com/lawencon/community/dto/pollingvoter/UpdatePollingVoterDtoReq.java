package com.lawencon.community.dto.pollingvoter;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdatePollingVoterDtoReq {

	@NotNull(message = "Polling Id must be filled")
	private String id;
	
	@NotNull(message = "Polling Detail Id must be filled")
	private String pollingDetailId;
	
	@NotNull(message = "Version must be filled")
	private Integer version;
	
	@NotNull(message = "Is Active active must be filled")
	private Boolean isActive;
}
