package com.lawencon.community.dto.order;

import java.util.List;

import lombok.Data;

@Data
public class GetByUserIdOrderDtoRes {

	private String msg;
	private List<GetOrderDtoDataRes> data;
}
