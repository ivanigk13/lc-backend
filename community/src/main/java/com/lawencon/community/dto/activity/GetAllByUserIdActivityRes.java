package com.lawencon.community.dto.activity;

import java.util.List;

import lombok.Data;

@Data
public class GetAllByUserIdActivityRes {

	private String msg;
	private List<GetAllByUserIdActivityDataRes> data;
}
