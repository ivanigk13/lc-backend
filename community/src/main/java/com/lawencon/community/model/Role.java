package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

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
@Indexed
public class Role extends BaseEntity{
	
	private static final long serialVersionUID = -6023381543594489514L;

	@FullTextField
	private String roleCode;
	
	@FullTextField
	private String roleName;
	
}
