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
@Indexed
public class Province extends BaseEntity{

	private static final long serialVersionUID = -8710407403332050955L;
	
	@FullTextField
	private String provinceCode;
	
	@FullTextField
	private String provinceName;
}

