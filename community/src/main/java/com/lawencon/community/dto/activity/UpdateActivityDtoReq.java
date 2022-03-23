package com.lawencon.community.dto.activity;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateActivityDtoReq {
	
	@NotNull(message = "Id must be filled")
	private String id;
	private String file;
	
	@NotEmpty(message = "Activity Name must be filled")
	private String activityName;
	
	@NotNull(message = "Date Start must be filled")
	private Date dateStart;
	
	@NotNull(message = "Date End must be filled")
	private Date dateEnd;
	
	@NotNull(message = "Time Start must be filled")
	private LocalTime timeStart;
	
	@NotNull(message = "Time End must be filled")
	private LocalTime timeEnd;
	private BigDecimal price;
	private String location;
	private Integer version;
}
