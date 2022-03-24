package com.lawencon.community.dto.pollingvoter;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InsertPollingVoterDtoReq {

	@NotNull(message = "Polling Detail id must be filled")
	private String pollingDetailId;
}
