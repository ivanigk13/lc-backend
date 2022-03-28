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
		name = "province_bk",
		columnNames = {"provinceCode"}
	),
	@UniqueConstraint(
		name = "province_ck",
		columnNames = {"provinceCode", "provinceName"}
	)
})

@Data
@EqualsAndHashCode(callSuper = false)
public class Province extends BaseEntity{

	private static final long serialVersionUID = -8710407403332050955L;
	private String provinceCode;
	private String provinceName;
}

