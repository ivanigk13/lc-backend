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
@Table
@Data
@EqualsAndHashCode(callSuper = false)
public class Thread extends BaseEntity{

	private static final long serialVersionUID = 8106823297265145443L;

	@ManyToOne
	@JoinColumn(name = "thread_type_id")
	private ThreadType threadType;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	private String title;
	private String content;
	
}
