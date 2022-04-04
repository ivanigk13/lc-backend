package com.lawencon.community.dto.activity;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class GetActivityDtoDataRes {
	
	private String id;
	private String fileId;
	private String categoryName;
	private String activityTypeName;
	private String transactionStatusId;
	private String transactionStatusName;
	private String paymentFileId;
	private String activityName;
	
	@JsonFormat(pattern = "E, dd MMM yyyy")
	private Date dateStart;
	
	@JsonFormat(pattern = "E, dd MMM yyyy")
	private Date dateEnd;
	
	@JsonFormat(pattern = "HH:mm")
	private Time timeStart;
	
	@JsonFormat(pattern = "HH:mm")
	private Time timeEnd;
	private BigDecimal price;
	private String location;
	private Integer version;

}
