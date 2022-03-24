package com.lawencon.community.dto.city;

import lombok.Data;

@Data
public class GetCityDtoDataRes {
	
	private String id;
	private String provinceId;
	private String cityCode;
	private String cityName;
	private Integer version;
	private Boolean isActive;

}
