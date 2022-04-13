package com.lawencon.community.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadBookmark;
import com.lawencon.community.model.User;

@Repository
public class ThreadBookmarkDao extends AbstractJpaDao<ThreadBookmark> {

	public List<ThreadBookmark> getThreadBookmarkByUserId(String id) {
		String sql = "SELECT id, thread_id, user_id FROM thread_bookmark WHERE user_id = :id ORDER BY id";
		List<?> results = createNativeQuery(sql).setParameter("id", id).getResultList();
		List<ThreadBookmark> threadBookmarks = new ArrayList<ThreadBookmark>();
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			ThreadBookmark threadBookmark = new ThreadBookmark();
			threadBookmark.setId(obj[0].toString());

			Thread thread = new Thread();
			thread.setId(obj[1].toString());
			threadBookmark.setThread(thread);

			User user = new User();
			user.setId(obj[2].toString());
			threadBookmark.setUser(user);

			threadBookmarks.add(threadBookmark);
		}
		return threadBookmarks;
	}

	public ThreadBookmark getThreadBookmarkByThreadId(String id) {
		ThreadBookmark threadBookmark = null;
		try {
			String sql = "SELECT id, thread_id, user_id FROM thread_bookmark WHERE thread_id = :id ORDER BY id";
			Object result = createNativeQuery(sql).setParameter("id", id).getSingleResult();
			Object[] obj = (Object[]) result;

			threadBookmark = new ThreadBookmark();
			threadBookmark.setId(obj[0].toString());

			Thread thread = new Thread();
			thread.setId(obj[1].toString());
			threadBookmark.setThread(thread);

			User user = new User();
			user.setId(obj[2].toString());
			threadBookmark.setUser(user);
		} catch (NoResultException | EmptyResultDataAccessException e) {
			e.printStackTrace();
		}

		return threadBookmark;
	}
}
