package com.lawencon.community.dto.transactionstatus;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class InsertTransactionStatusDtoReq {

	@NotEmpty(message = "Status Code must be filled")
	private String statusCode;
	
	@NotEmpty(message = "Status Name must be filled")
	private String statusName;
}
