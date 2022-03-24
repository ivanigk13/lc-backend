package com.lawencon.community.constant;

public enum TransactionStatus {
	PENDING("ST01"), APPROVED("ST02"), REJECTED("ST03");
	
	private String statusCode;
	
	TransactionStatus(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusCode() {
		return statusCode;
	}
	
}
