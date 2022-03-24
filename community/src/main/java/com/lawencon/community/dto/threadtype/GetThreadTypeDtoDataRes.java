package com.lawencon.community.dto.threadtype;

import lombok.Data;

@Data
public class GetThreadTypeDtoDataRes {

	private String id;
	private String threadTypeCode;
	private String threadTypeName;
	private Integer version;
	private Boolean isActive;
}
