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
public class PollingVoter extends BaseEntity{

	private static final long serialVersionUID = -2971146709151601659L;
	
	@OneToOne
	@JoinColumn(name = "polling_detail_id")
	private PollingDetail pollingDetail;
	
}
