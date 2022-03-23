package com.lawencon.community.dto.role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateRoleDtoReq {

	@NotNull(message = "role id must be filled")
	private String id;
	
	@NotEmpty(message = "role name must be filled")
	@Size(min = 1, max = 30, message = "role code min length is 1 and max length is 30")
	private String roleName;
	
	private Integer version;
}
