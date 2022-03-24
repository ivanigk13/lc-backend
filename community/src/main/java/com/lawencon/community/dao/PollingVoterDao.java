package com.lawencon.community.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.PollingDetail;
import com.lawencon.community.model.PollingVoter;

@Repository
public class PollingVoterDao extends AbstractJpaDao<PollingVoter>{

	public List<PollingVoter> getPollingVoterByUserId(String userId) {
		String sql = "SELECT id, polling_detail_id FROM polling_voter WHERE created_by = :id ORDER BY id ASC";
		List<?> results = createNativeQuery(sql).setParameter("id", userId).getResultList();
		List<PollingVoter> pollingVoters = new ArrayList<PollingVoter>();
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			PollingVoter pollingVoter = new PollingVoter();
			pollingVoter.setId(obj[0].toString());
			
			PollingDetail pollingDetail = new PollingDetail();
			pollingDetail.setId(obj[1].toString());
			pollingVoter.setPollingDetail(pollingDetail);
			
			pollingVoters.add(pollingVoter);
		}
		return pollingVoters;
	}
}
