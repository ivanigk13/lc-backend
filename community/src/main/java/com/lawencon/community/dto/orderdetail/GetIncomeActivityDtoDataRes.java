package com.lawencon.community.dto.orderdetail;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class GetIncomeActivityDtoDataRes {

	private String activityId;
	private Timestamp createdAt;
	private String invoice;
	private BigDecimal price;
	private String activityName;
	private String activityTypeName;
	private Date dateStart;
	private Date dateEnd;
	private Time timeStart;
	private Time timeEnd;
	private String location;
	private BigDecimal total;
}
