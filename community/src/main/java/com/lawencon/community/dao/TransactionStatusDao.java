package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.constant.TransactionStatusConstant;
import com.lawencon.community.model.TransactionStatus;

@Repository
public class TransactionStatusDao extends AbstractJpaDao<TransactionStatus>{

	public String getStatusPendingId() {
		String sql = "SELECT id FROM transaction_status WHERE status_code = :code";
		Object result = createNativeQuery(sql).setParameter("code", TransactionStatusConstant.PENDING.getStatusCode()).getSingleResult();
		Object obj = (Object) result;
		String id = obj.toString();
		return id;
	}
	
	public String getStatusApproveId() {
		String sql = "SELECT id FROM transaction_status WHERE status_code = :code";
		Object result = createNativeQuery(sql).setParameter("code", TransactionStatusConstant.APPROVED.getStatusCode()).getSingleResult();
		Object obj = (Object) result;
		String id = obj.toString();
		return id;
	}
	
	public String getStatusRejectId() {
		String sql = "SELECT id FROM transaction_status WHERE status_code = :code";
		Object result = createNativeQuery(sql).setParameter("code", TransactionStatusConstant.REJECTED.getStatusCode()).getSingleResult();
		Object obj = (Object) result;
		String id = obj.toString();
		return id;
	}
	
	public Integer isStatusCodeExist(String code) {
		String sql = "SELECT COUNT(id) FROM transaction_status where status_code = :code";
		Object result = createNativeQuery(sql).setParameter("code", code).getSingleResult();
		Integer flag = Integer.valueOf(result.toString());
		return flag;
	}
	
	public Integer isStatusNameExist(String name) {
		String sql = "SELECT COUNT(id) FROM transaction_status where status_name = :name";
		Object result = createNativeQuery(sql).setParameter("name", name).getSingleResult();
		Integer flag = Integer.valueOf(result.toString());
		return flag;
	}
}
