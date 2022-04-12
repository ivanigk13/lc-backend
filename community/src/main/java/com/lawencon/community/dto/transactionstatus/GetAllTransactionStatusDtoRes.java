package com.lawencon.community.dto.transactionstatus;

import java.util.List;

import lombok.Data;

@Data
public class GetAllTransactionStatusDtoRes {

	private String msg;
	private Long rows;
	private List<GetTransactionStatusDtoDataRes> data;
}
