package com.lawencon.community.dto.activity;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateActivityTransactionStatusDtoReq {

	@NotNull(message = "Id must be filled")
	private String id;
}
