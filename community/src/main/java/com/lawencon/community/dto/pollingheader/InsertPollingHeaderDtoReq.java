package com.lawencon.community.dto.pollingheader;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InsertPollingHeaderDtoReq {

	@NotNull(message = "Polling Id must be filled")
	private String threadId;
}
