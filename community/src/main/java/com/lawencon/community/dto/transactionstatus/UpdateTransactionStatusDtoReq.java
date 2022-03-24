package com.lawencon.community.dto.transactionstatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateTransactionStatusDtoReq {
	
	
	@NotNull(message = "Id is required")
	private String id;		
	
	@NotEmpty(message = "Status Name must be filled")
	@Size(min = 1, max = 30, message = "Status Name min lenght is 1 adn max length is 30")
	private String statusName;
	
	@NotNull(message = "Version must be filled")
	private Integer version;

	@NotNull(message = "Is Active must be filled")
	private Boolean isActive;
}
