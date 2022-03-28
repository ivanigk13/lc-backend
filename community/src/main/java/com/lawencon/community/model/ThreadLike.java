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
public class ThreadLike extends BaseEntity{
	
	private static final long serialVersionUID = -2255592248427233360L;

	@OneToOne
	@JoinColumn(name = "thread_id")
	private Thread thread;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private int likeCounter;

}
