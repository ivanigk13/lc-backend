package com.lawencon.community.dto.transactionstatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertTransactionStatusDtoReq {

	@NotEmpty(message = "Status Code must be filled")
	@Size(min = 1, max = 15, message = "Status Code min length is 1 and max length is 5")
	private String statusCode;
	
	@NotEmpty(message = "Status Name must be filled")
	@Size(min = 1, max = 30, message = "Status Name min lenght is 1 adn max length is 30")
	private String statusName;
}
