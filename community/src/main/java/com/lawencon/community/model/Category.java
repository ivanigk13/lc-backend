package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(
		name = "category_bk",
		columnNames = {"categoryCode"}
	),
	@UniqueConstraint(
		name = "category_ck",
		columnNames = {"categoryCode", "categoryName"}
	)
})

@Data
@EqualsAndHashCode(callSuper = false)
public class Category extends BaseEntity{
	
	private static final long serialVersionUID = -5449659961400155560L;
	
	@FullTextField
	private String categoryCode;
	
	@FullTextField
	private String categoryName;

}
