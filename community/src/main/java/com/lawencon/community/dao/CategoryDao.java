package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Category;

@Repository
public class CategoryDao extends AbstractJpaDao<Category>{

	public Integer isCategoryCodeExist(String code) {
		String sql = "SELECT COUNT(id) FROM category where category_code = :code";
		Object result = createNativeQuery(sql).setParameter("code", code).getSingleResult();
		Integer flag = Integer.valueOf(result.toString());
		return flag;
	}
	
	public Integer isCategoryNameExist(String name) {
		String sql = "SELECT COUNT(id) FROM category where category_name = :name";
		Object result = createNativeQuery(sql).setParameter("name", name).getSingleResult();
		Integer flag = Integer.valueOf(result.toString());
		return flag;
	}
}
