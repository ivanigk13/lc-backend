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
@Indexed
public class Industry extends BaseEntity{

	private static final long serialVersionUID = 5070956208128726910L;
	
	@FullTextField
	private String industryCode;
	
	@FullTextField
	private String industryName;
}
