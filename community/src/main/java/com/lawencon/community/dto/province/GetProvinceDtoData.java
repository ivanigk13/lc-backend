package com.lawencon.community.dto.province;

import lombok.Data;

@Data
public class GetProvinceDtoData {

	private String id;
	private String provinceCode;
	private String provinceName;
	private Integer version;
	private Boolean isActive;
}
