package com.lawencon.community.dto.order;

import java.util.List;

import lombok.Data;

@Data
public class GetAllOrderDtoRes {

	private String msg;
	private List<GetOrderDtoDataRes> data;
}
