package com.lawencon.community.dto.pollingheader;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InsertPollingHeaderDtoReq {

	@NotNull(message = "polling id must be filled")
	private String threadId;
}
