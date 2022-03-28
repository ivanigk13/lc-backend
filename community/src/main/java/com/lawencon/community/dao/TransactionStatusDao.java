package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.constant.TransactionStatusConstant;
import com.lawencon.community.model.TransactionStatus;

@Repository
public class TransactionStatusDao extends AbstractJpaDao<TransactionStatus>{

	public String getStatusPendingId() {
		String sql = "SELECT id FROM transaction_status WHERE status_code = :code";
		Object result = createNativeQuery(sql).setParameter("code", TransactionStatusConstant.PENDING.getStatusCode());
		Object[] obj = (Object[]) result;
		String id = obj[0].toString();
		return id;
	}
}
