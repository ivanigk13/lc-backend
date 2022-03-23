package com.lawencon.community.dto.category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateCategoryDtoReq {
	
	@NotNull(message = "Id must be filled")
	private String id;
	
	@NotEmpty(message = "Category Name must be filled")
	private String categoryName;
	
	private Integer version;
}
