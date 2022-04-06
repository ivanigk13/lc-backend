package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Activity;
import com.lawencon.community.model.Orders;
import com.lawencon.community.model.OrderDetail;
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
}
