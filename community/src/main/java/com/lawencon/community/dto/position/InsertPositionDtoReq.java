package com.lawencon.community.dto.position;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertPositionDtoReq {

	@NotNull(message = "Position Code must be filled")
	@Size(min = 1, max = 5, message = "Position Code min length is 1 and max length is 5")
	private String positionCode;
	
	@NotNull(message = "Position Name must be filled")
	@Size(min = 1, max = 50, message = "Position Name min length is 1 and max length is 50")
	private String positionName;
	
	
}
