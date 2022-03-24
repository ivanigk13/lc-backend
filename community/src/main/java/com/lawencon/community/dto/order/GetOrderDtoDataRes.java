package com.lawencon.community.dto.order;

import lombok.Data;

@Data
public class GetOrderDtoDataRes {

	private String id;
	private String transactionStatusId;
	private String userId;
	private String fileId;
	private String invoice;
	private Integer version;
	private Boolean isActive;

}
