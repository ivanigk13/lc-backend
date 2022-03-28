package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
public class City extends BaseEntity{

	private static final long serialVersionUID = -367065163396635966L;
	
	@ManyToOne
	@JoinColumn(name = "province_id")
	private Province province;
	private String cityCode; 
	private String cityName; 
	
}
