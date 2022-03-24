package com.lawencon.community.dto.orderdetail;

import lombok.Data;

@Data
public class GetOrderDetailDtoDataRes {
	
	private String id;
	private String orderId;
	private String subscribeId;
	private String activityId;
	private Integer version;
	private Boolean isActive;

}
