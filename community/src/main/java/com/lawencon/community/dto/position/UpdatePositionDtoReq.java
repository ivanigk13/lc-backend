package com.lawencon.community.dto.position;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdatePositionDtoReq {

	@NotNull(message = "Position id must be filled")
	private String id;
	
	@NotNull(message = "Position Name must be filled")
	@Size(min = 1, max = 50, message = "Position Name min length is 1 and max length is 50")
	private String positionName;
	
	@NotNull(message = "Version must be filled")
	private Integer version;
	
	@NotNull(message = "Is Active active must be filled")
	private Boolean isActive;
}
