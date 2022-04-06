package com.lawencon.community.dto.orderdetail;

import lombok.Data;

@Data
public class GetByOrderIdDtoRes {
	private String msg;
	private GetOrderDetailDtoDataRes data;
}
