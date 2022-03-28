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
public class ActivityType extends BaseEntity{
	
	private static final long serialVersionUID = -5152097572762796072L;
	private String activityTypeCode;
	private String activityTypeName;
}
