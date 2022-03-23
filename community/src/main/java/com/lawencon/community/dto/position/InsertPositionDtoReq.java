package com.lawencon.community.dto.position;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertPositionDtoReq {

	@NotNull(message = "position code must be filled")
	@Size(min = 1, max = 5, message = "position code min length is 1 and max length is 5")
	private String positionCode;
	
	@NotNull(message = "position name must be filled")
	@Size(min = 1, max = 50, message = "position name min length is 1 and max length is 50")
	private String positionName;
	
	
}
