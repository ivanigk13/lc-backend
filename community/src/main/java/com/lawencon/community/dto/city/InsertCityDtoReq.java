package com.lawencon.community.dto.city;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class InsertCityDtoReq {
	
	@NotEmpty(message = "Province Name must be filled")
	private String province;
	
	@NotEmpty(message = "City Code Name must be filled")
	private String cityCode;
	
	@NotEmpty(message = "City Name Name must be filled")
	private String cityName;
}
