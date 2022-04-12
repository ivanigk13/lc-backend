package com.lawencon.community.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Category;
import com.lawencon.model.SearchQuery;

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
	
	public SearchQuery<Category> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Category> sq = new SearchQuery<>();
		List<Category> data = null;

		if (startPage == null || maxPage == null) {
			data = getAll();
			sq.setData(data);
		} else {
			if (query == null) {
				data = getAll(startPage, maxPage);
				int count = countAll().intValue();

				sq.setData(data);
				sq.setCount(count);
			} else {
				return getAll(query, startPage, maxPage, "categoryCode", "categoryName");
			}
		}

		return sq;
	}
}
