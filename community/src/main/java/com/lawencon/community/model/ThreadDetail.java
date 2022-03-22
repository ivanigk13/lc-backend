package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = false)
public class ThreadDetail extends BaseEntity{

	private static final long serialVersionUID = 5977708460774834498L;

	@ManyToOne
	@JoinColumn(name = "thread_id")
	private Thread thread;
	
	private String comment;
}
