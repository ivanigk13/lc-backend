package com.lawencon.community.dto.transactionstatus;

import lombok.Data;

@Data
public class GetTransactionStatusDtoDataRes {
	
	private String id;
	private String statusCode;
	private String statusName;
	private String createdBy;
	private Integer version;

}
