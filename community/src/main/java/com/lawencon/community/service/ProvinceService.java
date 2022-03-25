package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.base.BaseService;
import com.lawencon.community.dao.ProvinceDao;
import com.lawencon.community.dto.province.GetAllProvinceDtoRes;
import com.lawencon.community.dto.province.GetByIdProvinceDtoRes;
import com.lawencon.community.dto.province.GetProvinceDtoDataRes;
import com.lawencon.community.model.Province;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProvinceService extends BaseService {

	private final ProvinceDao provinceDao;
	
	public GetByIdProvinceDtoRes getById(String id) throws Exception {
		Province province = provinceDao.getById(id);
		GetProvinceDtoDataRes provinceDataRes = new GetProvinceDtoDataRes();
		provinceDataRes.setId(province.getId());
		provinceDataRes.setProvinceCode(province.getProvinceCode());
		provinceDataRes.setProvinceName(province.getProvinceName());
		provinceDataRes.setVersion(province.getVersion());
		provinceDataRes.setIsActive(province.getIsActive());
		
		GetByIdProvinceDtoRes provinceRes = new GetByIdProvinceDtoRes();
		provinceRes.setData(provinceDataRes);
		
		return provinceRes;
	}

	public GetAllProvinceDtoRes getAll() throws Exception {
		List<Province> provinces = provinceDao.getAll();
		
		List<GetProvinceDtoDataRes> data = new ArrayList<GetProvinceDtoDataRes>();
		
		provinces.forEach(province -> {
			GetProvinceDtoDataRes provinceDataRes = new GetProvinceDtoDataRes();
			provinceDataRes.setId(province.getId());
			provinceDataRes.setProvinceCode(province.getProvinceCode());
			provinceDataRes.setProvinceName(province.getProvinceName());
			provinceDataRes.setVersion(province.getVersion());
			provinceDataRes.setIsActive(province.getIsActive());
			
			data.add(provinceDataRes);
		});
		
		GetAllProvinceDtoRes provinceRes = new GetAllProvinceDtoRes();
		provinceRes.setData(data);
		
		return provinceRes;
	}
}
