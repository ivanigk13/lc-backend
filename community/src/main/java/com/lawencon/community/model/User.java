package com.lawencon.community.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

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
@Indexed
public class User extends BaseEntity{

	private static final long serialVersionUID = 2365608740022216271L;

	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	@FullTextField
	private String email;
	
	@FullTextField
	private String password; 
	
	
	private LocalDateTime subcriptionEnd;
	
}
