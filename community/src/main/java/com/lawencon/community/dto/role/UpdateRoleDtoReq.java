package com.lawencon.community.dto.role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateRoleDtoReq {

	@NotNull(message = "Role Id must be filled")
	private String id;
	
	@NotEmpty(message = "Role Name must be filled")
	@Size(min = 1, max = 30, message = "Role Node min length is 1 and max length is 30")
	private String roleName;
	
	@NotNull(message = "Version must be filled")
	private Integer version;
	
	@NotNull(message = "Is Active active must be filled")
	private Boolean isActive;
}
