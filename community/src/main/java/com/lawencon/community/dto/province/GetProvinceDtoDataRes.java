package com.lawencon.community.dto.province;

import lombok.Data;

@Data
public class GetProvinceDtoDataRes {

	private String id;
	private String provinceCode;
	private String provinceName;
	private Integer version;
	private Boolean isActive;
}
