package com.lawencon.community.dto.pollingheader;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InsertPollingHeaderDtoReq {

	@NotNull(message = "Polling Id must be filled")
	private String threadId;
	
	@NotEmpty(message = "Title must be filled")
	@Size(min = 1, max = 100, message = "Title must length min is 1 and max length is 100")
	private String title;
	
	private List<String> data;
}
