package com.lawencon.community.dto.pollingvoter;

import java.util.List;

import lombok.Data;

@Data
public class GetAllPollingVoterDtoRes {

	private String msg;
	private List<GetPollingVoterDtoData> data;
}
