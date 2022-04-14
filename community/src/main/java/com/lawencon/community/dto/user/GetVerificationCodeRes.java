package com.lawencon.community.dto.user;

import lombok.Data;

@Data
public class GetVerificationCodeRes {

	private String verificationCode;
	private String msg;
}
