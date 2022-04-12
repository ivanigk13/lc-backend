package com.lawencon.community.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Industry;
import com.lawencon.model.SearchQuery;

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
	
	public SearchQuery<Industry> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Industry> sq = new SearchQuery<>();
		List<Industry> data = null;

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
				return getAll(query, startPage, maxPage, "industryCode", "industryName");
			}
		}

		return sq;
	}
}
