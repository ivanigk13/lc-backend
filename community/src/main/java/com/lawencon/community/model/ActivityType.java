package com.lawencon.community.model;

import java.math.BigDecimal;

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
		name = "activity_type_bk",
		columnNames = {"activityTypeCode"}
	),
	@UniqueConstraint(
		name = "activity_type_ck",
		columnNames = {"activityTypeCode", "activityTypeName"}
	)
})

@Data
@EqualsAndHashCode(callSuper = false)
@Indexed
public class ActivityType extends BaseEntity{
	
	private static final long serialVersionUID = -5152097572762796072L;
	
	@FullTextField
	private String activityTypeCode;
	
	@FullTextField
	private String activityTypeName;
	
	private BigDecimal price;
}
