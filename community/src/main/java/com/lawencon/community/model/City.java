package com.lawencon.community.model;

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
@Table(uniqueConstraints = {
	@UniqueConstraint(
		name = "city_bk",
		columnNames = {"cityCode"}
	),
	@UniqueConstraint(
		name = "city_ck",
		columnNames = {"cityCode", "cityName", "province_id"}
	)
})

@Data
@EqualsAndHashCode(callSuper = false)
@Indexed
public class City extends BaseEntity{

	private static final long serialVersionUID = -367065163396635966L;
	
	@OneToOne
	@JoinColumn(name = "province_id")
	private Province province;
	
	@FullTextField
	private String cityCode;
	
	@FullTextField
	private String cityName; 
	
}
