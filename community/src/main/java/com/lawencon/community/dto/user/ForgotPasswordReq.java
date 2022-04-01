package com.lawencon.community.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ForgotPasswordReq {

	@NotEmpty(message = "Email must be filled")
	@Size(max = 100, message = "Email length maximum = 100")
	private String email;
}
