package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Article extends BaseEntity {
	
	private static final long serialVersionUID = 1124866183478177740L;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	@Column(columnDefinition = "text")
	private String title;
	
	@Column(columnDefinition = "text")
	private String content;

}
