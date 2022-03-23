package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.community.model.ThreadLike;

public class ThreadLikeDao extends BaseDaoImpl<ThreadLike>{

	public Integer getLikeCounterByThreadId(String threadId) {
		String sql = "SELECT like_counter FROM thread_like WHERE thread_id = :id";
		List<?> results = createNativeQuery(sql).setParameter("id", threadId).getResultList();
		Integer counter = 0;
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			counter += Integer.valueOf(obj[0].toString());
		}
		return counter;
	}
}
