package com.lawencon.community.constant;

public enum RoleConstant {
	
	ADMIN("R01"), MEMBER("R02"), PREMIUM("R03");
	
	private String roleCode;
	
	RoleConstant(String roleCode){
		this.roleCode = roleCode;
	}

	public String getRoleCode() {
		return roleCode;
	}
	
	
}
