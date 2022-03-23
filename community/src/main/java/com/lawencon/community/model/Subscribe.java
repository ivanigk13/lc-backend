package com.lawencon.community.model;

import java.math.BigDecimal;

import javax.persistence.Entity;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Subscribe extends BaseEntity{

	private static final long serialVersionUID = 8935545143374766189L;
	private Integer duration;
	private BigDecimal price;
}
