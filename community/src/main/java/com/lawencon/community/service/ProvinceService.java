package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ProvinceDao;
import com.lawencon.community.dto.province.GetAllProvinceDtoRes;
import com.lawencon.community.dto.province.GetByIdProvinceDtoRes;
import com.lawencon.community.dto.province.GetProvinceDtoDataRes;
import com.lawencon.community.model.Province;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProvinceService extends BaseCommunityService {

	private final ProvinceDao provinceDao;

	public GetAllProvinceDtoRes getAll(Integer start, Integer max) throws Exception {
		List<Province> provinces;
		if(start == null) provinces = provinceDao.getAll();
		else provinces = provinceDao.getAll(start, max);
		
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
	
	public GetByIdProvinceDtoRes getById(String id) throws Exception {
		Province province = provinceDao.getById(id);
		if(province!=null) {
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
		
		throw new RuntimeException("Province Id doesn't exist");
	}
}
