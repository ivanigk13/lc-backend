package com.lawencon.community.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = false)
public class Subscribe extends BaseEntity{

	private static final long serialVersionUID = 8935545143374766189L;
	private Date duration;
	private BigDecimal price;
}
