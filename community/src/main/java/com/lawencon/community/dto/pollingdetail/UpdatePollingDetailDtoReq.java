package com.lawencon.community.dto.pollingdetail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdatePollingDetailDtoReq {

	@NotNull(message = "polling id must be filled")
	private String id;
	
	@NotEmpty(message = "polling name must be filled")
	@Size(min = 1, max = 100, message = "polling name min length is 1 and max length is 100")
	private String pollingName;
	
	private int pollingCounter; 
	private Integer version;
}
