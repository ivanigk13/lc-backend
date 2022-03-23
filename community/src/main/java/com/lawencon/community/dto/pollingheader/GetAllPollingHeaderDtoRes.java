package com.lawencon.community.dto.pollingheader;

import java.util.List;

import lombok.Data;

@Data
public class GetAllPollingHeaderDtoRes {
	
	private String msg;
	private List<GetPollingHeaderDtoDataRes> data;

}
