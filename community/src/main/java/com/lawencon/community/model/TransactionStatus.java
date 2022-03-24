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
		name = "transaction_status_code_bk",
		columnNames = {"status_code"}
	),
	@UniqueConstraint(
		name = "transaction_status_ck",
		columnNames = {"status_code", "status_name"}
	)
})

@Data
@EqualsAndHashCode(callSuper = false)
public class TransactionStatus extends BaseEntity{
	
	private static final long serialVersionUID = 8059651552254369419L;
	private String statusCode;
	private String statusName;
}
