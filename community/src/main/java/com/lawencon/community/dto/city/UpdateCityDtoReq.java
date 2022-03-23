package com.lawencon.community.dto.city;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateCityDtoReq {
		
	@NotNull(message = "Id must be filled")
	private String id;			
	
	@NotEmpty(message = "City Name must be filled")
	private String cityName;				
	private Integer version;
}
