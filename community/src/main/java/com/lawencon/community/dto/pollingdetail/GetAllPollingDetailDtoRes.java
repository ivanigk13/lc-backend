package com.lawencon.community.dto.pollingdetail;

import java.util.List;

import lombok.Data;

@Data
public class GetAllPollingDetailDtoRes {

	private String msg;
	private List<GetPollingDetailDtoData> data;
}
