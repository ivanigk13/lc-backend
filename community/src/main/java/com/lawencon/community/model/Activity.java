package com.lawencon.community.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
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
	
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToOne
	@JoinColumn(name = "transaction_status_id")
	private TransactionStatus transactionStatus;
	
	@OneToOne
	@JoinColumn(name = "payment_file_id")
	private File paymentFile;
	
	private String activityName;
	private Date dateStart;
	private Date dateEnd;
	private LocalTime timeStart;
	private LocalTime timeEnd;
	private BigDecimal price;
	private String location;
}
