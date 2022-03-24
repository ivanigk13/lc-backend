package com.lawencon.community.dto.category;

import lombok.Data;

@Data
public class GetCategoryDtoDataRes {
	
	private String id;
	private String categoryCode;
	private String categoryName;
	private Integer version;
	private Boolean isActive;
}
