package com.lawencon.community.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateUserDtoReq {

	@NotNull(message = "Id must be filled")
	private String id;
	
	@NotNull(message = "Role id must be filled")
	private String roleId;
	
	@NotEmpty(message = "Password must be filled")
	@Size(min = 8, max = 100, message = "Password length minimal = 8")
	private String password;
	
	@NotNull(message = "Version must be filled")
	private Integer version;

	@NotNull(message = "Is Active must be filled")
	private Boolean isActive;
}
