package com.lawencon.community.dto.orderdetail;

import java.util.List;

import lombok.Data;

@Data
public class GetAllOrderDetailDtoRes {

	private String msg;
	private List<GetOrderDetailDtoDataRes> data;
}
