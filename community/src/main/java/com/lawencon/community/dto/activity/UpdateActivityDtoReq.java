package com.lawencon.community.dto.activity;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UpdateActivityDtoReq {
	
	@NotNull(message = "Id must be filled")
	private String id;
	
	@NotNull(message = "File must be filled")
	private String fileId;
	
	@NotNull(message = "Payment File Id must be filled")
	private String paymentFileId;
	
	@NotEmpty(message = "Activity Name must be filled")
	private String activityName;		
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Date Start must be filled")
	private Date dateStart;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Date End must be filled")
	private Date dateEnd;
	
	@JsonFormat(pattern = "HH:mm")
	@NotNull(message = "Time Start must be filled")
	private LocalTime timeStart;
	
	@JsonFormat(pattern = "HH:mm")
	@NotNull(message = "Time End must be filled")
	private LocalTime timeEnd;
	
	@NotNull(message = "price must be filled" )
	private BigDecimal price;
	
	@NotEmpty(message = "Location must be filled")
	private String location;
	
	@NotNull(message = "Version must be filled")
	private Integer version;
	
	@NotNull(message = "Is Active must be filled")
	private Boolean isActive;
}
