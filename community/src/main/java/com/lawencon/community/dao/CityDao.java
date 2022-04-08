package com.lawencon.community.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.City;

@Repository
public class CityDao extends AbstractJpaDao<City>{

	public List<City> getAllByProvinceId(String provinceId) {
		String sql = "SELECT id, city_code, city_name FROM city WHERE province_id = :id ORDER BY id ASC";
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
	
	public Integer isCityCodeExist(String code) {
		String sql = "SELECT COUNT(id) FROM city where city_code = :code";
		Object result = createNativeQuery(sql).setParameter("code", code).getSingleResult();
		Integer flag = Integer.valueOf(result.toString());
		return flag;
	}
	
	public Integer isCityNameExist(String name) {
		String sql = "SELECT COUNT(id) FROM city where city_name = :name";
		Object result = createNativeQuery(sql).setParameter("name", name).getSingleResult();
		Integer flag = Integer.valueOf(result.toString());
		return flag;
	}

}
