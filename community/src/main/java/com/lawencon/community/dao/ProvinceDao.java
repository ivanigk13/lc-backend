package com.lawencon.community.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Province;
import com.lawencon.model.SearchQuery;

@Repository
public class ProvinceDao extends AbstractJpaDao<Province>{

	public SearchQuery<Province> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Province> sq = new SearchQuery<>();
		List<Province> data = null;

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
				return getAll(query, startPage, maxPage, "provinceCode", "provinceName");
			}
		}

		return sq;
	}
}
