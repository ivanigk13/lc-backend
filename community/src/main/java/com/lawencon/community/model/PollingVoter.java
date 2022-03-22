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
public class PollingVoter extends BaseEntity{

	private static final long serialVersionUID = -2971146709151601659L;
	
	@ManyToOne
	@JoinColumn(name = "polling_detail_id")
	private PollingDetail pollingDetail;
	
}
