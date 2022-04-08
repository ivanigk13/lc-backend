package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Industry;

@Repository
public class IndustryDao extends AbstractJpaDao<Industry>{
	
	public Integer isIndustryCodeExist(String code) {
		String sql = "SELECT COUNT(id) FROM industry where industry_code = :code";
		Object result = createNativeQuery(sql).setParameter("code", code).getSingleResult();
		Integer flag = Integer.valueOf(result.toString());
		return flag;
	}
	
	public Integer isIndustryNameExist(String name) {
		String sql = "SELECT COUNT(id) FROM industry where industry_name = :name";
		Object result = createNativeQuery(sql).setParameter("name", name).getSingleResult();
		Integer flag = Integer.valueOf(result.toString());
		return flag;
	}
}
