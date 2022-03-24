package com.lawencon.community.dto.pollingdetail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdatePollingDetailDtoReq {

	@NotNull(message = "Polling Id must be filled")
	private String id;
	
	@NotEmpty(message = "Polling Name must be filled")
	@Size(min = 1, max = 100, message = "Polling Name min length is 1 and max length is 100")
	private String pollingName;
	
	private int pollingCounter; 
	
	@NotNull(message = "Version must be filled")
	private Integer version;
	
	@NotNull(message = "Is Active active must be filled")
	private Boolean isActive;
}
