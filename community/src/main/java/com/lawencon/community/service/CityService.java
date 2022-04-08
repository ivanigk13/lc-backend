package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.CityDao;
import com.lawencon.community.dao.ProvinceDao;
import com.lawencon.community.dto.city.DeleteCityDtoRes;
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
public class CityService extends BaseCommunityService {

	private final CityDao cityDao;
	private final ProvinceDao provinceDao;

	public GetAllCityDtoRes getAll(Integer start, Integer max) throws Exception {
		List<City> cities;		
		if(start == null) cities = cityDao.getAll();
		else cities = cityDao.getAll(start, max);
		
		List<GetCityDtoDataRes> data = new ArrayList<>();

		cities.forEach(list -> {
			GetCityDtoDataRes city = new GetCityDtoDataRes();
			city.setId(list.getId());
			city.setProvinceId(list.getProvince().getId());
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
		if(city!=null) {
			GetCityDtoDataRes cityData = new GetCityDtoDataRes();
			cityData.setId(city.getId());
			cityData.setProvinceId(city.getProvince().getId());
			cityData.setCityName(city.getCityName());
			cityData.setCityCode(city.getCityCode());
			cityData.setVersion(city.getVersion());
			cityData.setIsActive(city.getIsActive());
			
			GetByIdCityDtoRes result = new GetByIdCityDtoRes();
			result.setData(cityData);
			
			return result;
		}
		
		throw new RuntimeException("City Id doesn't exist");
	}

	public InsertCityDtoRes insert(InsertCityDtoReq data) throws Exception {
		City city = new City();
		Province province = provinceDao.getById(data.getProvinceId());
		city.setProvince(province);
		valBkNotExist(data.getCityCode(), data.getCityName());
		city.setCityName(data.getCityName());
		city.setCityCode(data.getCityCode());
		city.setCreatedBy(getId());

		begin();
		City cityInsert = cityDao.save(city);
		commit();
		
		InsertCityDtoDataRes cityId = new InsertCityDtoDataRes();
		cityId.setId(cityInsert.getId());

		InsertCityDtoRes result = new InsertCityDtoRes();
		result.setData(cityId);
		result.setMsg("Insert Successfully");
		
		return result;
	}

	public UpdateCityDtoRes update(UpdateCityDtoReq data) throws Exception {
		City city = cityDao.getById(data.getId());
		valBkNotExist(data.getCityName());
		city.setCityName(data.getCityName());
		city.setUpdatedBy(getId());
		city.setVersion(data.getVersion());
		city.setIsActive(data.getIsActive());

		begin();
		City cityUpdate = cityDao.save(city);
		commit();

		UpdateCityDtoDataRes cityVersion = new UpdateCityDtoDataRes();
		cityVersion.setVersion(cityUpdate.getVersion());

		UpdateCityDtoRes result = new UpdateCityDtoRes();
		result.setData(cityVersion);
		result.setMsg("Update Successfully");
		return result;
	}
	
	public DeleteCityDtoRes deleteById(String id) throws Exception {
		try {			
			begin();
			boolean isDeleted = cityDao.deleteById(id);
			commit();
			
			DeleteCityDtoRes cityRes = new DeleteCityDtoRes();
			if(isDeleted) {
				cityRes.setMsg("Delete Successfully");
			} else {
				cityRes.setMsg("Delete Unsuccessfully");
			}
			
			return cityRes;
		}
		catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	private void valBkNotExist(String code, String name) {
		Integer flagCode = cityDao.isCityCodeExist(code);
		Integer flagName = cityDao.isCityNameExist(name);
		if(flagCode == 1 || flagName == 1) {
			throw new RuntimeException("City Code or City Name has existed");
		}
	}
	
	private void valBkNotExist(String name) {
		Integer flagName = cityDao.isCityNameExist(name);
		if(flagName == 1) {
			throw new RuntimeException("City Name has existed");
		}
	}
}
