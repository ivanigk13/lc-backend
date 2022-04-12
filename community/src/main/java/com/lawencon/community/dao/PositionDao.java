package com.lawencon.community.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Position;
import com.lawencon.model.SearchQuery;

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
	
	public SearchQuery<Position> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Position> sq = new SearchQuery<>();
		List<Position> data = null;

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
				return getAll(query, startPage, maxPage, "positionCode", "positionName");
			}
		}

		return sq;
	}
}
