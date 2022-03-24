package com.lawencon.community.dto.pollingdetail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertPollingDetailDtoReq {

	@NotNull(message = "Polling Header Id must be filled")
	private String pollingHeaderId;
	
	@NotEmpty(message = "Polling Name must be filled")
	@Size(min = 1, max = 100, message = "polling name min length is 1 and max length is 100")
	private String pollingName;
	
	private int pollingCounter;
}
