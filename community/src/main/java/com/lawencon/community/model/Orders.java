package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Orders extends BaseEntity{

	private static final long serialVersionUID = 254974765201098981L;

	@OneToOne
	@JoinColumn(name = "transaction_status_id")
	private TransactionStatus transactionStatus;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	private String invoice;
}
