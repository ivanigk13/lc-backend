package com.lawencon.community.model;

import javax.persistence.Entity;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class File extends BaseEntity{

	private static final long serialVersionUID = -5186693406748562228L;
	private String extensionName;
	private byte[] content;
}
