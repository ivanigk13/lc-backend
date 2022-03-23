package com.lawencon.community.dto.user;

import lombok.Data;

@Data
public class GetUserDtoDataRes {

	private String id;
	private String roleId;
	private String roleName;
	private String email;
	private String password;
	private Integer version;
	private Boolean isActive;
}
