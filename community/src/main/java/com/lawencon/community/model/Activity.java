package com.lawencon.community.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = false)
public class Activity extends BaseEntity{

	private static final long serialVersionUID = 7378832516126888635L;

	@OneToOne
	@JoinColumn(name = "activity_type_id")
	private ActivityType activityType;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	private String activityName;
	private Date dateStart;
	private Date dateEnd;
	private LocalTime timeStart;
	private LocalTime timeEnd;
	private BigDecimal price;
	private String location;
}
