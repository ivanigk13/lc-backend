package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "thread_bookmark")
@Data
@EqualsAndHashCode(callSuper = false)
public class ThreadBookmark extends BaseEntity{

	private static final long serialVersionUID = -1836488594501447246L;

	@ManyToOne
	@JoinColumn(name = "thread_id")
	private Thread thread;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
}
