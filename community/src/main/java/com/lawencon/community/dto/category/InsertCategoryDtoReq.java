package com.lawencon.community.dto.category;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class InsertCategoryDtoReq {

	@NotEmpty(message = "Category Code must be filled")
	private String categoryCode;	
	
	@NotEmpty(message = "Category Name must be filled")
	private String categoryName;
}
