package com.lawencon.community.dto.order;

import lombok.Data;

@Data
public class GetOrderDtoDataRes {

	private String id;
	private String orderStatus;
	private String user;
	private String file;
	private String invoice;
	private String createdBy;
	private Integer version;

}
