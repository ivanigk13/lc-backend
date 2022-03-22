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
public class ThreadLike extends BaseEntity{
	
	private static final long serialVersionUID = -2255592248427233360L;

	@ManyToOne
	@JoinColumn(name = "thread_id")
	private Thread thread;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private int likeCounter;

}
