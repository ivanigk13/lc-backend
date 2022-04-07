package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.PollingHeader;
import com.lawencon.community.model.Thread;

@Repository
public class PollingHeaderDao extends AbstractJpaDao<PollingHeader>{

	public PollingHeader getByThreadId(String threadId) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ph.id,ph.thread_id,ph.title ");
		builder.append("FROM polling_header ph ");
		builder.append("WHERE ph.thread_id = :id");
		Object result = createNativeQuery(builder.toString())
				.setParameter("id", threadId)
				.getSingleResult();
		Object[] obj = (Object[]) result;
		
		PollingHeader pollingHeader = new PollingHeader();
		if(obj != null) {
		pollingHeader.setId(obj[0].toString());
		
		Thread thread = new Thread();
		thread.setId(obj[1].toString());
		pollingHeader.setThread(thread);
		pollingHeader.setTitle(obj[2].toString());
		}
		
		return pollingHeader;
	}
	
}
