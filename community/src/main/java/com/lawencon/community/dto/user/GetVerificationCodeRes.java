package com.lawencon.community.dto.user;

import lombok.Data;

@Data
public class GetVerificationCodeRes {

	private Integer verificationCode;
	private String msg;
}
