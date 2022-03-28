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
		name = "industry_bk",
		columnNames = {"industryCode"}
	),
	@UniqueConstraint(
		name = "industry_ck",
		columnNames = {"industryCode", "industryName"}
	)
})

@Data
@EqualsAndHashCode(callSuper = false)
public class Industry extends BaseEntity{

	private static final long serialVersionUID = 5070956208128726910L;
	private String industryCode;
	private String industryName;
}
