package com.lawencon.community.dto.transactionstatus;

import lombok.Data;

@Data
public class GetByIdTransactionStatusDtoRes {

	private String msg;
	private GetTransactionStatusDtoDataRes data;
}
