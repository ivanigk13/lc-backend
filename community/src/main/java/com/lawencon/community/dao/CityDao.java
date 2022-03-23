package com.lawencon.community.dao;

import java.util.ArrayList;
import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.community.model.City;

public class CityDao extends BaseDaoImpl<City>{

	public List<City> getAllByProvinceId(String provinceId) {
		String sql = "SELECT id, city_code, city_name WHERE province_id = :id ORDER BY id ASC";
		List<?> results = createNativeQuery(sql).setParameter("id", provinceId).getResultList();
		List<City> cities = new ArrayList<>();
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			City city = new City();
			city.setId(obj[0].toString());
			city.setCityCode(obj[1].toString());
			city.setCityName(obj[2].toString());
			
			cities.add(city);
		});
		return cities;
	}

}
