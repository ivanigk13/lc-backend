package com.lawencon.community.dto.city;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateCityDtoReq {
		
	@NotNull(message = "Id must be filled")
	private String id;			
	
	@NotEmpty(message = "City Name must be filled")
	@Size(min = 1, max = 100, message = "City Name min lenght is 1 and max length is 100")
	private String cityName;
	
	@NotNull(message = "Version must be filled")
	private Integer version;
	
	@NotNull(message = "Is Active active must be filled")
	private Boolean isActive;
}
