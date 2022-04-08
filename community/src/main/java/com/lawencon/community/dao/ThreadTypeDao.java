package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.ThreadType;

@Repository
public class ThreadTypeDao extends AbstractJpaDao<ThreadType>{
	
	public Integer isThreadTypeCodeExist(String code) {
		String sql = "SELECT COUNT(id) FROM thread_type where thread_type_code = :code";
		Object result = createNativeQuery(sql).setParameter("code", code).getSingleResult();
		Integer flag = Integer.valueOf(result.toString());
		return flag;
	}
	
	public Integer isThreadTypeNameExist(String name) {
		String sql = "SELECT COUNT(id) FROM thread_type where thread_type_name = :name";
		Object result = createNativeQuery(sql).setParameter("name", name).getSingleResult();
		Integer flag = Integer.valueOf(result.toString());
		return flag;
	}
}
