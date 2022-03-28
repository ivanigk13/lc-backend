package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(
		name = "position_bk",
		columnNames = {"positionCode"}
	),
	@UniqueConstraint(
		name = "position_ck",
		columnNames = {"positionCode", "positionName"}
	)
})

@Data
@EqualsAndHashCode(callSuper = false)
public class Position extends BaseEntity{

	private static final long serialVersionUID = 3735559242985006384L;
	private String positionCode;
	private String positionName;
}
