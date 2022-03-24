package com.lawencon.community.dto.orderdetail;

import lombok.Data;

@Data
public class InsertOrderDetailDtoReq {

	private String orderId;
	private String subscribeId;
	private String activityId;
}
