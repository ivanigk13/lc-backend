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
public class ThreadBookmark extends BaseEntity{

	private static final long serialVersionUID = -1836488594501447246L;

	@OneToOne
	@JoinColumn(name = "thread_id")
	private Thread thread;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
}
