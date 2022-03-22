package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.City;

public interface CityDao {

	List<City> getAllByProvinceId(Long provinceId);
}
