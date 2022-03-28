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
		name = "thread_type_bk",
		columnNames = {"threadTypeCode"}
	),
	@UniqueConstraint(
		name = "thread_type_ck",
		columnNames = {"threadTypeCode", "threadTypeName"}
	)
})

@Data
@EqualsAndHashCode(callSuper = false)
public class ThreadType extends BaseEntity{
	
	private static final long serialVersionUID = 8823545507880654221L;
	private String threadTypeCode;
	private String threadTypeName;
}
