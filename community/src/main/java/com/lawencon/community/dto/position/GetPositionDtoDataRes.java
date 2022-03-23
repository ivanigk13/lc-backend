package com.lawencon.community.dto.position;

import lombok.Data;

@Data
public class GetPositionDtoDataRes {

	private String id;
	private String positionCode;
	private String positionName;
	private Integer version;
	private Boolean isActive;
}
