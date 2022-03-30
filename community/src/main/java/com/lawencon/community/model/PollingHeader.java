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
public class PollingHeader extends BaseEntity{

	private static final long serialVersionUID = 2707815275377358262L;
	
	@OneToOne
	@JoinColumn(name = "thread_id")
	private Thread thread;
	
	private String title;
}
