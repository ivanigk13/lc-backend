package com.lawencon.community.dto.role;

import lombok.Data;

@Data
public class GetRoleDtoDataRes {

	private String id;
	private String roleCode;
	private String roleName;
	private Integer version;
	private Boolean isActive;
}
