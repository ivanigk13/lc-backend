package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.ActivityType;

@Repository
public class ActivityTypeDao extends AbstractJpaDao<ActivityType>{

	public Integer isActivityTypeCodeExist (String code) {
		String sql = "SELECT COUNT(id) FROM activity_type where activity_type_code = :code";
		Object result = createNativeQuery(sql).setParameter("code", code).getSingleResult();
		Integer flag = Integer.valueOf(result.toString());
		return flag;
	}
	
	public Integer isActivityTypeNameExist(String name) {
		String sql = "SELECT COUNT(id) FROM activity_type where activity_type_name = :name";
		Object result = createNativeQuery(sql).setParameter("name", name).getSingleResult();
		Integer flag = Integer.valueOf(result.toString());
		return flag;
	}
}
