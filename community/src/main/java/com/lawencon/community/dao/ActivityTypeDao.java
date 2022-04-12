package com.lawencon.community.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.ActivityType;
import com.lawencon.model.SearchQuery;

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
	
	public SearchQuery<ActivityType> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<ActivityType> sq = new SearchQuery<>();
		List<ActivityType> data = null;

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
				return getAll(query, startPage, maxPage, "activityTypeCode", "activityTypeName");
			}
		}

		return sq;
	}
}
