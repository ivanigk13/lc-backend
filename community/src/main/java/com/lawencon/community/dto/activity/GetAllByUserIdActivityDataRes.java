package com.lawencon.community.dto.activity;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import lombok.Data;

@Data
public class GetAllByUserIdActivityDataRes {

	private String id;
	private String fileId;
	private String categoryName;
	private String activityTypeName;
	private String transactionStatusId;
	private String transactionStatusName;
	private String paymentFileId;
	private String activityName;
	private Date dateStart;
	private Date dateEnd;
	private Time timeStart;
	private Time timeEnd;
	private BigDecimal price;
	private String location;
	private Integer version;
}
