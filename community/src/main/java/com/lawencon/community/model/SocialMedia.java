package com.lawencon.community.model;

import javax.persistence.Entity;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class SocialMedia extends BaseEntity{

	private static final long serialVersionUID = -6170317550297912768L;
	private String instagram;
	private String facebook;
	private String twitter;
}
