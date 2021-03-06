package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class PollingDetail extends BaseEntity{

	private static final long serialVersionUID = -4733757363807473129L;

	@ManyToOne
	@JoinColumn(name = "polling_header_id")
	private PollingHeader pollingHeader;
	
	private String pollingName;
	private int pollingCounter;
}
