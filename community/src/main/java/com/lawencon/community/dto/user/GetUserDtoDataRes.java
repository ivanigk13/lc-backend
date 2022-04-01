package com.lawencon.community.dto.user;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GetUserDtoDataRes {

	private String id;
	private String roleId;
	private String roleName;
	private String email;
	private String password;
	private LocalDateTime createdAt;
	private Integer version;
	private Boolean isActive;
}
