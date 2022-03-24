package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.base.BaseService;
import com.lawencon.community.dao.CityDao;
import com.lawencon.community.dao.ProvinceDao;
import com.lawencon.community.dto.city.GetAllCityDtoRes;
import com.lawencon.community.dto.city.GetByIdCityDtoRes;
import com.lawencon.community.dto.city.GetCityDtoDataRes;
import com.lawencon.community.dto.city.InsertCityDtoDataRes;
import com.lawencon.community.dto.city.InsertCityDtoReq;
import com.lawencon.community.dto.city.InsertCityDtoRes;
import com.lawencon.community.dto.city.UpdateCityDtoDataRes;
import com.lawencon.community.dto.city.UpdateCityDtoReq;
import com.lawencon.community.dto.city.UpdateCityDtoRes;
import com.lawencon.community.model.City;
import com.lawencon.community.model.Province;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService extends BaseService {

	private final CityDao cityDao;
	private final ProvinceDao provinceDao;

	public InsertCityDtoRes insert(InsertCityDtoReq data) throws Exception {
		City city = new City();
		Province province = provinceDao.getById(data.getProvinceId());
		city.setProvince(province);
		city.setCityName(data.getCityName());
		city.setCityCode(data.getCityCode());

		begin();
		City cityInsert = cityDao.save(city);
		InsertCityDtoDataRes cityId = new InsertCityDtoDataRes();
		cityId.setId(cityInsert.getId());

		InsertCityDtoRes result = new InsertCityDtoRes();
		result.setData(cityId);
		result.setMsg("Insert Successfully");
		commit();
		return result;
	}

	public UpdateCityDtoRes update(UpdateCityDtoReq data) throws Exception {
		City city = cityDao.getById(data.getId());
		city.setCityName(data.getCityName());
		city.setVersion(data.getVersion());
		city.setIsActive(data.getIsActive());

		begin();
		City cityUpdate = cityDao.save(city);

		UpdateCityDtoDataRes cityVersion = new UpdateCityDtoDataRes();
		cityVersion.setVersion(cityUpdate.getVersion());

		UpdateCityDtoRes result = new UpdateCityDtoRes();
		result.setData(cityVersion);
		result.setMsg("Update Successfully");
		return result;
	}

	public GetAllCityDtoRes getAll() throws Exception {
		List<City> cities = cityDao.getAll();
		List<GetCityDtoDataRes> data = new ArrayList<>();

		cities.forEach(list -> {
			GetCityDtoDataRes city = new GetCityDtoDataRes();
			city.setId(list.getId());
			city.setCityName(list.getCityName());
			city.setCityCode(list.getCityCode());
			city.setVersion(list.getVersion());
			city.setIsActive(list.getIsActive());
			data.add(city);
		});

		GetAllCityDtoRes result = new GetAllCityDtoRes();
		result.setData(data);

		return result;
	}

	public GetByIdCityDtoRes getById(String id) throws Exception {
		City city = cityDao.getById(id);

		GetCityDtoDataRes cityData = new GetCityDtoDataRes();
		cityData.setId(city.getId());
		cityData.setCityName(city.getCityName());
		cityData.setCityCode(city.getCityCode());
		cityData.setVersion(city.getVersion());
		cityData.setIsActive(city.getIsActive());

		GetByIdCityDtoRes result = new GetByIdCityDtoRes();
		result.setData(cityData);

		return result;
	}
}
