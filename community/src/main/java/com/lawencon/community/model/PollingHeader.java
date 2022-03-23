package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "polling_header")
@Data
@EqualsAndHashCode(callSuper = false)
public class PollingHeader extends BaseEntity{

	private static final long serialVersionUID = 2707815275377358262L;
	
	@ManyToOne
	@JoinColumn(name = "thread_id")
	private Thread thread;
}
