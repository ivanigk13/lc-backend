package com.lawencon.community.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.ThreadType;
import com.lawencon.model.SearchQuery;

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
	
	public SearchQuery<ThreadType> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<ThreadType> sq = new SearchQuery<>();
		List<ThreadType> data = null;

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
				return getAll(query, startPage, maxPage, "threadTypeCode", "threadTypeName");
			}
		}

		return sq;
	}
}
