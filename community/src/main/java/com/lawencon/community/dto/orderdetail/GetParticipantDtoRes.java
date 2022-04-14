package com.lawencon.community.dto.orderdetail;

import java.util.List;

import lombok.Data;

@Data
public class GetParticipantDtoRes {

	private String msg;
	private List<GetParticipantDtoDataRes> data;
}
