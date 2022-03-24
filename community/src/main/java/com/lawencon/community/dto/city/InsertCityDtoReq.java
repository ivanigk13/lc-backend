package com.lawencon.community.dto.city;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertCityDtoReq {
	
	@NotNull(message = "Province Id must be filled")
	private String provinceId;
	
	@NotEmpty(message = "City Code must be filled")
	@Size(min = 1, max = 5, message = "City Code min length is 1 and max lenght is 5")
	private String cityCode;
	
	@NotEmpty(message = "City Name must be filled")
	@Size(min = 1, max = 100, message = "City Name min lenght is 1 and max length is 100")
	private String cityName;
}
