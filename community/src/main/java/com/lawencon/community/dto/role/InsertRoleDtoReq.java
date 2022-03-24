package com.lawencon.community.dto.role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertRoleDtoReq {

	@NotEmpty(message = "Role Code must be filled")
	@Size(min = 1, max = 5, message = "Role Code min length is 1 and max length is 5")
	private String roleCode;
	
	@NotEmpty(message = "Role Name must be filled")
	@Size(min = 1, max = 30, message = "Role Name min length is 1 and max length is 30")
	private String roleName;
}
