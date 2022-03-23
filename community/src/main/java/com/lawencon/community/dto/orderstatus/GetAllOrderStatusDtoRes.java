package com.lawencon.community.dto.orderstatus;

import java.util.List;

import lombok.Data;

@Data
public class GetAllOrderStatusDtoRes {

	private String msg;
	private List<GetOrderStatusDtoDataRes> data;
}
