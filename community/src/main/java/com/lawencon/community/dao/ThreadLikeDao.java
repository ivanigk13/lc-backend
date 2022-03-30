package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.ThreadLike;

@Repository
public class ThreadLikeDao extends AbstractJpaDao<ThreadLike>{

	public Integer getLikeCounterByThreadId(String threadId) {
		String sql = "SELECT COUNT(like_counter) FROM thread_like WHERE thread_id = :id";
		Object result = createNativeQuery(sql).setParameter("id", threadId).getSingleResult();
		Object obj = (Object) result;
		Integer counter = Integer.valueOf(obj.toString());
		return counter;
	}
}
