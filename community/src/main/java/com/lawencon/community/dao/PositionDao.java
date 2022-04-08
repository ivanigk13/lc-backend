package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Position;

@Repository
public class PositionDao extends AbstractJpaDao<Position>{

	public Integer isPositionCodeExist(String code) {
		String sql = "SELECT COUNT(id) FROM position where position_code = :code";
		Object result = createNativeQuery(sql).setParameter("code", code).getSingleResult();
		Integer flag = Integer.valueOf(result.toString());
		return flag;
	}
	
	public Integer isPositionNameExist(String name) {
		String sql = "SELECT COUNT(id) FROM position where position_name = :name";
		Object result = createNativeQuery(sql).setParameter("name", name).getSingleResult();
		Integer flag = Integer.valueOf(result.toString());
		return flag;
	}
}
