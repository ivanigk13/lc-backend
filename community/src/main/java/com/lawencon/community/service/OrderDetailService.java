package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.base.BaseService;
import com.lawencon.community.dao.ActivityDao;
import com.lawencon.community.dao.OrderDao;
import com.lawencon.community.dao.OrderDetailDao;
import com.lawencon.community.dao.SubscribeDao;
import com.lawencon.community.dto.orderdetail.GetAllOrderDetailDtoRes;
import com.lawencon.community.dto.orderdetail.GetByIdOrderDetailDtoRes;
import com.lawencon.community.dto.orderdetail.GetOrderDetailDtoDataRes;
import com.lawencon.community.dto.orderdetail.InsertOrderDetailDtoDataRes;
import com.lawencon.community.dto.orderdetail.InsertOrderDetailDtoReq;
import com.lawencon.community.dto.orderdetail.InsertOrderDetailDtoRes;
import com.lawencon.community.model.Activity;
import com.lawencon.community.model.Order;
import com.lawencon.community.model.OrderDetail;
import com.lawencon.community.model.Subscribe;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailService extends BaseService{

	private final OrderDetailDao orderDetailDao;
	private final OrderDao orderDao;
	private final SubscribeDao subscribeDao;
	private final ActivityDao activityDao;
	
	public InsertOrderDetailDtoRes insert(InsertOrderDetailDtoReq data) throws Exception {
		OrderDetail orderDetail = new OrderDetail();
		Order order = orderDao.getById(data.getOrderId());
		orderDetail.setOrder(order);
		
		Subscribe subscribe = subscribeDao.getById(data.getSubscribeId());
		orderDetail.setSubscribe(subscribe);
		
		Activity activity = activityDao.getById(data.getActivityId());
		orderDetail.setActivity(activity);
		
		begin();
		OrderDetail orderDetailInsert = orderDetailDao.save(orderDetail);
		InsertOrderDetailDtoDataRes orderDetailId = new InsertOrderDetailDtoDataRes();
		orderDetailId.setId(orderDetailInsert.getId());
		
		InsertOrderDetailDtoRes result = new InsertOrderDetailDtoRes();
		result.setData(orderDetailId);
		result.setMsg("Insert Successfully");
		commit();
		return result;
	}
	
	public GetAllOrderDetailDtoRes getAll() throws Exception{
		List<OrderDetail> OrderDetails = orderDetailDao.getAll();
		List<GetOrderDetailDtoDataRes> data = new ArrayList<>();
		
		OrderDetails.forEach(list -> {
			GetOrderDetailDtoDataRes orderDetail = new GetOrderDetailDtoDataRes();
			orderDetail.setId(list.getId());
			orderDetail.setActivityId(list.getActivity().getId());
			orderDetail.setSubscribeId(list.getSubscribe().getId());
			orderDetail.setActivityId(list.getActivity().getId());
			orderDetail.setVersion(list.getVersion());
			orderDetail.setIsActive(list.getIsActive());
			data.add(orderDetail);
		});
		
		GetAllOrderDetailDtoRes result = new GetAllOrderDetailDtoRes();
		result.setData(data);
		
		return result;
	}
	
	public GetByIdOrderDetailDtoRes getById(String id) throws Exception{
		OrderDetail orderDetail = orderDetailDao.getById(id);
		
		GetOrderDetailDtoDataRes orderDetailData = new GetOrderDetailDtoDataRes();
		orderDetailData.setId(orderDetail.getId());
		orderDetailData.setActivityId(orderDetail.getActivity().getId());
		orderDetailData.setSubscribeId(orderDetail.getSubscribe().getId());
		orderDetailData.setActivityId(orderDetail.getActivity().getId());
		orderDetailData.setVersion(orderDetail.getVersion());
		orderDetailData.setIsActive(orderDetail.getIsActive());
		
		GetByIdOrderDetailDtoRes result = new GetByIdOrderDetailDtoRes();
		result.setData(orderDetailData);		
		
		return result;		
	}
}
