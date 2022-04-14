package com.lawencon.community.dto.orderdetail;

import java.util.List;

import lombok.Data;

@Data
public class GetIncomeActivityDtoRes {

	private String msg;
	private List<GetIncomeActivityDtoDataRes> data;
}
