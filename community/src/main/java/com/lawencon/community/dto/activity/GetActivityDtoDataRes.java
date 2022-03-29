package com.lawencon.community.dto.activity;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import lombok.Data;

@Data
public class GetActivityDtoDataRes {
	
	private String id;
	private String activityTypeId;
	private String fileId;
	private String paymentFileId;
	private String activityName;
	private String categoryId;
	private Date dateStart;
	private Date dateEnd;
	private Time timeStart;
	private Time timeEnd;
	private BigDecimal price;
	private String location;
	private String createdBy;
	private Integer version;

}
