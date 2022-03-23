package com.lawencon.community.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadDetail;

@Repository
public class ThreadDetailDao extends AbstractJpaDao<ThreadDetail>{

	public List<ThreadDetail> getAllByThreadId(String threadId) {
		String sql = "SELECT id, thread_id, comment FROM thread_detail WHERE thread_id = :id ORDER BY id ASC";
		List<?> results = createNativeQuery(sql).setParameter("id", threadId).getResultList();
		List<ThreadDetail> threadDetails = new ArrayList<ThreadDetail>();
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			ThreadDetail threadDetail = new ThreadDetail();
			threadDetail.setId(obj[0].toString());
			
			Thread thread = new Thread();
			thread.setId(obj[1].toString());
			threadDetail.setThread(thread);
			threadDetail.setComment(obj[2].toString());
			
			threadDetails.add(threadDetail);
		}
		return threadDetails;
	}
}
