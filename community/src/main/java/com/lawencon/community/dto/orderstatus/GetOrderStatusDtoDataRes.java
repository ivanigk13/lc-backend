package com.lawencon.community.dto.orderstatus;

import lombok.Data;

@Data
public class GetOrderStatusDtoDataRes {
	
	private String id;
	private String statusCode;
	private String statusName;
	private String createdBy;
	private Integer version;

}
