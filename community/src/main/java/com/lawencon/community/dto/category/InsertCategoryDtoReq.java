package com.lawencon.community.dto.category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertCategoryDtoReq {

	@NotEmpty(message = "Category Code must be filled")
	@Size(min = 1, max = 5, message = "Category Code min length is 1 and max length is 5")
	private String categoryCode;	
	
	@NotEmpty(message = "Category Name must be filled")
	@Size(min = 1, max = 50, message = "Category Name min length is 1 and max length is 50")
	private String categoryName;
}
