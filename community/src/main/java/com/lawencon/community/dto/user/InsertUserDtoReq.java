package com.lawencon.community.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertUserDtoReq {

	@NotNull(message = "role id must be filled")
	private Long roleId;

	@NotEmpty(message = "email must be filled")
	@Size(max = 50, message = "email length maximum = 50")
	private String email;
	
	@NotEmpty(message = "password must be filled")
	@Size(min = 5, max = 100, message = "password length minimal = 5")
	private String password;
}
