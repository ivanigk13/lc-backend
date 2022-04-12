package com.lawencon.community.dto.role;

import java.util.List;
import lombok.Data;

@Data
public class GetAllRoleDtoRes {

	private String msg;
	private Long rows;
	private List<GetRoleDtoDataRes> data;
}
