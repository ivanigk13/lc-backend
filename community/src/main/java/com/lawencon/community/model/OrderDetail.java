package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "order_detail")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderDetail extends BaseEntity{

	private static final long serialVersionUID = -3876509063482717635L;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@OneToOne
	@JoinColumn(name = "subscribe_id")
	private Subscribe subscribe;
	
	@OneToOne
	@JoinColumn(name = "activity_id")
	private Activity activity;
}
