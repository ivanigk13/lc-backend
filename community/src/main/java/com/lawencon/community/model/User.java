package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "users", uniqueConstraints = {
	@UniqueConstraint(
		name = "users_bk",
		columnNames = {"email"}
	),
	@UniqueConstraint(
		name = "users_ck",
		columnNames = {"role_id", "email"}
	)
})

@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity{

	private static final long serialVersionUID = 2365608740022216271L;

	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	private String email;
	private String password; 
	
}
