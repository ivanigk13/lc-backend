package com.lawencon.community.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.constant.TransactionStatus;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Order;
import com.lawencon.community.model.User;

@Repository
public class OrderDao extends AbstractJpaDao<Order>{

	public List<Order> getPendingOrder(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT o.id, o.transaction_status_id, ts.status_name, o.user_id, o.file, o.invoice ");
		sb.append("FROM order as o ");
		sb.append("INNER JOIN transaction_status ts ON o.transaction_status_id = ts.id ");
		sb.append("WHERE ts.status_code = :code ");
		sb.append("ORDER BY o.id ASC");
		List<?> results = createNativeQuery(sb.toString())
								.setParameter("code", TransactionStatus.PENDING.getStatusCode())
								.getResultList();
		List<Order> orders = new ArrayList<Order>();
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			Order order = new Order();
			order.setId(obj[0].toString());
			
			com.lawencon.community.model.TransactionStatus transactionStatus = new com.lawencon.community.model.TransactionStatus();
			transactionStatus.setId(obj[1].toString());
			transactionStatus.setStatusName(obj[2].toString());
			order.setTransactionStatus(transactionStatus);
			
			User user = new User();
			user.setId(obj[3].toString());
			order.setUser(user);
			
			File file = new File();
			file.setId(obj[4].toString());
			order.setFile(file);
			
			order.setInvoice(obj[5].toString());
			
			orders.add(order);
		}
		return orders;
	}
	
	public List<Order> getOrderByUserId(String id){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT o.id, o.transaction_status_id, ts.status_name, o.user_id, o.file, o.invoice ");
		sb.append("FROM order as o ");
		sb.append("INNER JOIN transaction_status ts ON o.transaction_status_id = ts.id ");
		sb.append("WHERE o.user_id = :id ");
		sb.append("ORDER BY o.id ASC");
		List<?> results = createNativeQuery(sb.toString())
								.setParameter("id", id)
								.getResultList();
		List<Order> orders = new ArrayList<Order>();
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			Order order = new Order();
			order.setId(obj[0].toString());
			
			com.lawencon.community.model.TransactionStatus transactionStatus = new com.lawencon.community.model.TransactionStatus();
			transactionStatus.setId(obj[1].toString());
			transactionStatus.setStatusName(obj[2].toString());
			order.setTransactionStatus(transactionStatus);
			
			User user = new User();
			user.setId(obj[3].toString());
			order.setUser(user);
			
			File file = new File();
			file.setId(obj[4].toString());
			order.setFile(file);
			
			order.setInvoice(obj[5].toString());
			
			orders.add(order);
		}
		return orders;
	}
	
	public List<Order> getApprovedOrderByUserId(String id){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT o.id, o.transaction_status_id, ts.status_name, o.user_id, o.file, o.invoice ");
		sb.append("FROM order as o ");
		sb.append("INNER JOIN transaction_status ts ON o.transaction_status_id = ts.id ");
		sb.append("WHERE o.user_id = :id AND ts.status_code = :code");
		sb.append("ORDER BY o.id ASC");
		List<?> results = createNativeQuery(sb.toString())
								.setParameter("id", id)
								.setParameter("code", TransactionStatus.APPROVED.getStatusCode())
								.getResultList();
		List<Order> orders = new ArrayList<Order>();
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			Order order = new Order();
			order.setId(obj[0].toString());
			
			com.lawencon.community.model.TransactionStatus transactionStatus = new com.lawencon.community.model.TransactionStatus();
			transactionStatus.setId(obj[1].toString());
			transactionStatus.setStatusName(obj[2].toString());
			order.setTransactionStatus(transactionStatus);
			
			User user = new User();
			user.setId(obj[3].toString());
			order.setUser(user);
			
			File file = new File();
			file.setId(obj[4].toString());
			order.setFile(file);
			
			order.setInvoice(obj[5].toString());
			
			orders.add(order);
		}
		return orders;
	}
}