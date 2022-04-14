package com.lawencon.community.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.dto.orderdetail.GetIncomeActivityDtoDataRes;
import com.lawencon.community.dto.orderdetail.GetParticipantDtoDataRes;
import com.lawencon.community.model.Activity;
import com.lawencon.community.model.OrderDetail;
import com.lawencon.community.model.Orders;
import com.lawencon.community.model.Subscribe;

@Repository
public class OrderDetailDao extends AbstractJpaDao<OrderDetail>{

	public OrderDetail getOrderDetailByOrderId(String orderId) {
		String sql = "SELECT id, subscribe_id, activity_id, order_id FROM order_detail WHERE order_id = :id";
		Object result = createNativeQuery(sql).setParameter("id", orderId).getSingleResult();
		Object[] obj = (Object[]) result;
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setId(obj[0].toString());
		
		Subscribe subscribe = new Subscribe();
		if(obj[1] != null) {
			subscribe.setId(obj[1].toString());
			orderDetail.setSubscribe(subscribe);
		}
		
		Activity activity = new Activity();
		if(obj[2] != null) {
			activity.setId(obj[2].toString());
			orderDetail.setActivity(activity);
		}
		
		Orders order = new Orders();
		order.setId(obj[3].toString());
		orderDetail.setOrder(order);
		
		return orderDetail;
	}
	
	public List<GetParticipantDtoDataRes> getParticipant(String id) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT p.full_name, u.email, p.phone_number, o.invoice, a.activity_name, at2.activity_type_name, a.date_start, a.date_end, a.time_start, a.time_end, a.location, ts.status_name, o.id, o.file_id, a.price ");
		builder.append("FROM order_detail od ");
		builder.append("INNER JOIN orders o ON o.id = od.order_id ");
		builder.append("INNER JOIN transaction_status ts ON ts.id = o.transaction_status_id ");
		builder.append("INNER JOIN users u ON u.id = o.user_id ");
		builder.append("INNER JOIN profile p ON p.user_id = u.id ");
		builder.append("INNER JOIN activity a ON a.id = od.activity_id ");
		builder.append("INNER JOIN activity_type at2 ON at2.id = a.activity_type_id ");
		builder.append("WHERE a.id=:id");		
		List<?> results = createNativeQuery(builder.toString())
						.setParameter("id", id)
						.getResultList();
		
		List<GetParticipantDtoDataRes> details = new ArrayList<>();
		for(Object result: results) {
			Object[] obj = (Object[]) result;
			GetParticipantDtoDataRes participant = new GetParticipantDtoDataRes();
			
			participant.setFullName(obj[0].toString());
			participant.setEmail(obj[1].toString());
			participant.setPhoneNumber(obj[2].toString());
			participant.setInvoice(obj[3].toString());
			participant.setActivityName(obj[4].toString());
			participant.setActivityTypeName(obj[5].toString());
			participant.setDateStart((Date)obj[6]);
			participant.setDateEnd((Date)obj[7]);
			participant.setTimeStart((Time)obj[8]);
			participant.setTimeEnd((Time)obj[9]);
			participant.setLocation(obj[10].toString());
			participant.setStatusName(obj[11].toString());
			participant.setOrderId(obj[12].toString());
			participant.setFileId(obj[13].toString());
			participant.setPrice(BigDecimal.valueOf(Long.valueOf(obj[14].toString())));
			
			details.add(participant);
		}
		
		return details;
	}
	
	public List<GetIncomeActivityDtoDataRes> getReportIncome(String id) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT o.created_at, a.price, o.invoice, a.activity_name, at2.activity_type_name, ");
		builder.append("a.date_start, a.date_end,a.time_start, a.time_end, a.location ");
		builder.append("FROM order_detail od ");
		builder.append("INNER JOIN orders o ON o.id = od.order_id ");
		builder.append("INNER JOIN users u ON u.id = o.user_id ");
		builder.append("INNER JOIN activity a ON a.id = od.activity_id ");
		builder.append("INNER JOIN activity_type at2 ON at2.id = a.activity_type_id ");
		builder.append("WHERE od.activity_id =:id");		
		List<?> results = createNativeQuery(builder.toString())
						.setParameter("id", id)
						.getResultList();
		
		List<GetIncomeActivityDtoDataRes> details = new ArrayList<>();
		for(Object result: results) {
			Object[] obj = (Object[]) result;
			GetIncomeActivityDtoDataRes data = new GetIncomeActivityDtoDataRes();
			
			data.setCreatedAt((Timestamp)obj[0]);
			data.setPrice(BigDecimal.valueOf(Long.valueOf(obj[1].toString())));
			data.setInvoice(obj[2].toString());
			data.setActivityName(obj[3].toString());
			data.setActivityTypeName(obj[4].toString());
			data.setDateStart((Date)obj[5]);
			data.setDateEnd((Date)obj[6]);
			data.setTimeStart((Time)obj[7]);
			data.setTimeEnd((Time)obj[8]);
			data.setLocation(obj[9].toString());
			
			details.add(data);
		}
		
		return details;
	}
	
	public List<GetParticipantDtoDataRes> getAllParticipant() {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT p.full_name, u.email, p.phone_number, o.invoice, a.activity_name, at2.activity_type_name, a.date_start, a.date_end, a.time_start, a.time_end, a.location, od.activity_id ");
		builder.append("FROM order_detail od ");
		builder.append("INNER JOIN orders o ON o.id = od.order_id ");
		builder.append("INNER JOIN transaction_status ts ON ts.id = o.transaction_status_id ");
		builder.append("INNER JOIN users u ON u.id = o.user_id ");
		builder.append("INNER JOIN profile p ON p.user_id = u.id ");
		builder.append("INNER JOIN activity a ON a.id = od.activity_id ");
		builder.append("INNER JOIN activity_type at2 ON at2.id = a.activity_type_id");		
		List<?> results = createNativeQuery(builder.toString()).getResultList();
		
		List<GetParticipantDtoDataRes> details = new ArrayList<>();
		for(Object result: results) {
			Object[] obj = (Object[]) result;
			GetParticipantDtoDataRes participant = new GetParticipantDtoDataRes();
			
			participant.setFullName(obj[0].toString());
			participant.setEmail(obj[1].toString());
			participant.setPhoneNumber(obj[2].toString());
			participant.setInvoice(obj[3].toString());
			participant.setActivityName(obj[4].toString());
			participant.setActivityTypeName(obj[5].toString());
			participant.setDateStart((Date)obj[6]);
			participant.setDateEnd((Date)obj[7]);
			participant.setTimeStart((Time)obj[8]);
			participant.setTimeEnd((Time)obj[9]);
			participant.setLocation(obj[10].toString());
			participant.setActivityId(obj[11].toString());
			
			details.add(participant);
		}
		
		return details;
	}
	
	public List<GetIncomeActivityDtoDataRes> getAllIncome() {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT o.created_at, a.price, o.invoice, a.activity_name, at2.activity_type_name, ");
		builder.append("a.date_start, a.date_end,a.time_start, a.time_end, a.location, od.activity_id ");
		builder.append("FROM order_detail od ");
		builder.append("INNER JOIN orders o ON o.id = od.order_id ");
		builder.append("INNER JOIN users u ON u.id = o.user_id ");
		builder.append("INNER JOIN activity a ON a.id = od.activity_id ");
		builder.append("INNER JOIN activity_type at2 ON at2.id = a.activity_type_id ");
		List<?> results = createNativeQuery(builder.toString()).getResultList();
		
		List<GetIncomeActivityDtoDataRes> details = new ArrayList<>();
		for(Object result: results) {
			Object[] obj = (Object[]) result;
			GetIncomeActivityDtoDataRes data = new GetIncomeActivityDtoDataRes();
			
			data.setCreatedAt((Timestamp)obj[0]);
			data.setPrice(BigDecimal.valueOf(Long.valueOf(obj[1].toString())));
			data.setInvoice(obj[2].toString());
			data.setActivityName(obj[3].toString());
			data.setActivityTypeName(obj[4].toString());
			data.setDateStart((Date)obj[5]);
			data.setDateEnd((Date)obj[6]);
			data.setTimeStart((Time)obj[7]);
			data.setTimeEnd((Time)obj[8]);
			data.setLocation(obj[9].toString());
			data.setActivityId(obj[10].toString());
			
			details.add(data);
		}
		
		return details;
	}
}
