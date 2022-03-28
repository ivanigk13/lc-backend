package com.lawencon.community.constant;

public enum TransactionStatusConstant {
	PENDING("ST01"), APPROVED("ST02"), REJECTED("ST03");
	
	private String statusCode;
	
	TransactionStatusConstant(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusCode() {
		return statusCode;
	}
	
}
