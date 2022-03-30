package com.lawencon.community.dto.pollingheader;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdatePollingHeaderDtoReq {
	
	@NotNull(message = "Id Polling Header must be filled")
	private String id;

	@NotEmpty(message = "Title must be filled")
	@Size(min = 1, max = 100, message = "Title must length min is 1 and max length is 100")
	private String title;
}
