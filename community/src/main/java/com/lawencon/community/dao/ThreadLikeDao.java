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
	
	public Integer isUserLikeByThreadId(String threadId, String userId) {
		String sql = "SELECT COUNT(like_counter) FROM thread_like WHERE thread_id = :id AND user_id = :user_id";
		Object result = createNativeQuery(sql)
							.setParameter("id", threadId)
							.setParameter("user_id", userId)
							.getSingleResult();
		Object obj = (Object) result;
		Integer counter = Integer.valueOf(obj.toString());
		return counter;
	}
	
	public ThreadLike getIdByThreadId(String threadId, String userId) {
		String sql = "SELECT tl.id FROM thread_like as tl WHERE tl.thread_id = :id AND tl.user_id = :user_id";
		Object result = createNativeQuery(sql)
							.setParameter("id", threadId)
							.setParameter("user_id", userId)
							.getSingleResult();
		Object obj = (Object) result;
		ThreadLike threadLike = new ThreadLike();
		threadLike.setId(obj.toString());
		return threadLike;
	}
	
	
}
