package com.lawencon.community.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateUserDtoReq {

	@NotNull(message = "id must be filled")
	private String id;
	
	@NotNull(message = "version must be filled")
	private Integer version;
	
	@NotNull(message = "role id must be filled")
	private String roleId;
	
	@NotEmpty(message = "password must be filled")
	@Size(min = 8, max = 100, message = "password length minimal = 8")
	private String password;
	
	@NotNull(message = "is active can't be null")
	private Boolean isActive;
}
