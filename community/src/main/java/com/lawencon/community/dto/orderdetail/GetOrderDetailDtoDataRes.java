package com.lawencon.community.dto.orderdetail;

import lombok.Data;

@Data
public class GetOrderDetailDtoDataRes {
	
	private String id;
	private String order;
	private String subscribe;
	private String activity;
	private String createdBy;
	private Integer version;

}
