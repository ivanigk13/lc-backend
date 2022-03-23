package com.lawencon.community.dto.activity;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;

import lombok.Data;

@Data
public class GetActivityDtoDataRes {
	
	private String id;
	private String activityType;
	private String file;
	private String activityName;
	private Date dateStart;
	private Date dateEnd;
	private LocalTime timeStart;
	private LocalTime timeEnd;
	private BigDecimal price;
	private String location;
	private String createdBy;
	private Integer version;

}
