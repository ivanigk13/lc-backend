package com.lawencon.community.dto.city;

import lombok.Data;

@Data
public class GetCityDtoDataRes {
	
	private String id;
	private String province;
	private String cityCode;
	private String cityName;
	private String createdBy;
	private Integer version;

}
