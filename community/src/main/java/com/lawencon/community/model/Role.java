package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "roles", uniqueConstraints = {
	@UniqueConstraint(
		name = "role_bk",
		columnNames = {"roleCode"}
	),
	@UniqueConstraint(
		name = "role_ck",
		columnNames = {"roleCode", "roleName"}
	)
})

@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntity{
	
	private static final long serialVersionUID = 1019583607543236514L;
	private String roleCode;
	private String roleName;
	
}
