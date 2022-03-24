package com.lawencon.community.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.PollingDetail;
import com.lawencon.community.model.PollingHeader;

@Repository
public class PollingDetailDao extends AbstractJpaDao<PollingDetail>{

	public List<PollingDetail> getAllByPollingHeaderId(String pollingHeaderId) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT id, polling_header_id, polling_name, polling_counter FROM polling_detail ");
		builder.append("WHERE polling_header_id = :id ");
		builder.append("ORDER BY id ASC");
		List<?> results = createNativeQuery(builder.toString()).setParameter("id", pollingHeaderId).getResultList();
		List<PollingDetail> pollingDetails = new ArrayList<PollingDetail>();
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			PollingDetail pollingDetail = new PollingDetail();
			pollingDetail.setId(obj[0].toString());
			
			PollingHeader pollingHeader = new PollingHeader();
			pollingHeader.setId(obj[1].toString());
			pollingDetail.setPollingHeader(pollingHeader);
			
			pollingDetail.setPollingName(obj[2].toString());
			pollingDetail.setPollingCounter(Integer.valueOf(obj[3].toString()));
			
			pollingDetails.add(pollingDetail);
		}
		return pollingDetails;
	}
}
