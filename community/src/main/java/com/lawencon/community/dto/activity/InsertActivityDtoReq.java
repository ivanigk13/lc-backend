package com.lawencon.community.dto.activity;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class InsertActivityDtoReq {

	@NotNull(message = "Activity Type Id must be filled")
	private String activityTypeId;
	
	@NotNull(message = "File Id must be filled")
	private String fileId;
	
	@NotEmpty(message = "Activity Name must be filled")
	@Size(max = 100)
	private String activityName;
	
	@NotNull(message = "Category Id must be filled")
	private String categoryId;
	
	@NotNull(message = "Transaction Status Id must be filled")
	private String transactionStatusId;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Date Start must be filled")
	private Date dateStart;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Date End must be filled")
	private Date dateEnd;
	
	@NotNull(message = "Time Start must be filled")
	private Time timeStart;
	
	@NotNull(message = "Time End must be filled")
	private Time timeEnd;
	
	@NotNull(message = "Price must be filled")
	private BigDecimal price;
	
	@NotEmpty(message = "Location must be filled")
	@Size(max = 50)
	private String location;
}
