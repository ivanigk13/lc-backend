package com.lawencon.community.dto.category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateCategoryDtoReq {
	
	@NotNull(message = "Id must be filled")
	private String id;
	
	@NotEmpty(message = "Category Name must be filled")
	@Size(min = 1, max = 50, message = "Category Name min length is 1 and max length is 50")
	private String categoryName;
	
	@NotNull(message = "Version must be filled")
	private Integer version;
	
	@NotNull(message = "Is Active active must be filled")
	private Boolean isActive;
}
