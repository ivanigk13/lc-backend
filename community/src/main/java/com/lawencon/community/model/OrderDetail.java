package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderDetail extends BaseEntity{

	private static final long serialVersionUID = -3876509063482717635L;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders order;
	
	@OneToOne
	@JoinColumn(name = "subscribe_id")
	private Subscribe subscribe;
	
	@OneToOne
	@JoinColumn(name = "activity_id")
	private Activity activity;
}
