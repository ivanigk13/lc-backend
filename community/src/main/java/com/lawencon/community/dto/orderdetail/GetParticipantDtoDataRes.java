package com.lawencon.community.dto.orderdetail;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

import lombok.Data;

@Data
public class GetParticipantDtoDataRes {

	private String activityId;
	private String fullName;
	private String email;
	private String phoneNumber;
	private String invoice;
	private String activityName;
	private String activityTypeName;
	private Date dateStart;
	private Date dateEnd;
	private Time timeStart;
	private Time timeEnd;
	private String location;
	private String statusName;
	private String orderId;
	private String fileId;
	private BigDecimal price; 
}
