package com.lawencon.community.dto.subscribe;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class GetSubscribeDtoDataRes {

	private String id;
	private Integer duration;
	private BigDecimal price;
	private Integer version;
	private Boolean isActive;
}
