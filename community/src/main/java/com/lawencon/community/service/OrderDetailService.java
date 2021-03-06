package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ActivityDao;
import com.lawencon.community.dao.OrderDao;
import com.lawencon.community.dao.OrderDetailDao;
import com.lawencon.community.dao.SubscribeDao;
import com.lawencon.community.dto.orderdetail.DeleteOrderDetailDtoRes;
import com.lawencon.community.dto.orderdetail.GetAllOrderDetailDtoRes;
import com.lawencon.community.dto.orderdetail.GetByIdOrderDetailDtoRes;
import com.lawencon.community.dto.orderdetail.GetByOrderIdDtoRes;
import com.lawencon.community.dto.orderdetail.GetIncomeActivityDtoDataRes;
import com.lawencon.community.dto.orderdetail.GetIncomeActivityDtoRes;
import com.lawencon.community.dto.orderdetail.GetOrderDetailDtoDataRes;
import com.lawencon.community.dto.orderdetail.GetParticipantDtoDataRes;
import com.lawencon.community.dto.orderdetail.GetParticipantDtoRes;
import com.lawencon.community.dto.orderdetail.InsertOrderDetailDtoDataRes;
import com.lawencon.community.dto.orderdetail.InsertOrderDetailDtoReq;
import com.lawencon.community.dto.orderdetail.InsertOrderDetailDtoRes;
import com.lawencon.community.model.Activity;
import com.lawencon.community.model.OrderDetail;
import com.lawencon.community.model.Orders;
import com.lawencon.community.model.Subscribe;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailService extends BaseCommunityService {

	private final OrderDetailDao orderDetailDao;
	private final OrderDao orderDao;
	private final SubscribeDao subscribeDao;
	private final ActivityDao activityDao;

	public InsertOrderDetailDtoRes insert(InsertOrderDetailDtoReq data) throws Exception {
		OrderDetail orderDetail = new OrderDetail();
		Orders order = orderDao.getById(data.getOrderId());
		orderDetail.setOrder(order);
		
		if(data.getSubscribeId() != null) {
		Subscribe subscribe = subscribeDao.getById(data.getSubscribeId());
		orderDetail.setSubscribe(subscribe);
		}
		
		if(data.getActivityId() != null) {
		Activity activity = activityDao.getById(data.getActivityId());
		orderDetail.setActivity(activity);
		}
		
		orderDetail.setCreatedBy(getId());

		begin();
		OrderDetail orderDetailInsert = orderDetailDao.save(orderDetail);
		commit();
		
		InsertOrderDetailDtoDataRes orderDetailId = new InsertOrderDetailDtoDataRes();
		orderDetailId.setId(orderDetailInsert.getId());

		InsertOrderDetailDtoRes result = new InsertOrderDetailDtoRes();
		result.setData(orderDetailId);
		result.setMsg("Insert Successfully");
		
		return result;
	}

	public GetAllOrderDetailDtoRes getAll(Integer start, Integer max) throws Exception {
		List<OrderDetail> orderDetails;		
		if(start == null) orderDetails = orderDetailDao.getAll();
		else orderDetails = orderDetailDao.getAll(start, max);
		
		List<GetOrderDetailDtoDataRes> data = new ArrayList<>();

		orderDetails.forEach(list -> {
			GetOrderDetailDtoDataRes orderDetail = new GetOrderDetailDtoDataRes();
			orderDetail.setId(list.getId());
			orderDetail.setOrderId(list.getOrder().getId());
			if(list.getActivity() != null) {
				orderDetail.setActivityId(list.getActivity().getId());			
			}		
			if(list.getSubscribe() != null) {
				orderDetail.setSubscribeId(list.getSubscribe().getId());			
			}
			orderDetail.setActivityId(list.getActivity().getId());
			orderDetail.setVersion(list.getVersion());
			orderDetail.setIsActive(list.getIsActive());
			data.add(orderDetail);
		});

		GetAllOrderDetailDtoRes result = new GetAllOrderDetailDtoRes();
		result.setData(data);

		return result;
	}

	public GetByIdOrderDetailDtoRes getById(String id) throws Exception {
		OrderDetail orderDetail = orderDetailDao.getById(id);

		GetOrderDetailDtoDataRes orderDetailData = new GetOrderDetailDtoDataRes();
		orderDetailData.setId(orderDetail.getId());
		orderDetailData.setOrderId(orderDetail.getOrder().getId());
		
		if(orderDetail.getActivity() != null) {
			orderDetailData.setActivityId(orderDetail.getActivity().getId());			
		}		
		if(orderDetail.getSubscribe() != null) {
			orderDetailData.setSubscribeId(orderDetail.getSubscribe().getId());			
		}		
		
		orderDetailData.setVersion(orderDetail.getVersion());
		orderDetailData.setIsActive(orderDetail.getIsActive());

		GetByIdOrderDetailDtoRes result = new GetByIdOrderDetailDtoRes();
		result.setData(orderDetailData);

		return result;
	}
	
	public GetByOrderIdDtoRes getByOrderId(String id) throws Exception {
		OrderDetail orderDetail = orderDetailDao.getOrderDetailByOrderId(id);
		
		GetOrderDetailDtoDataRes orderDetailData = new GetOrderDetailDtoDataRes();
		orderDetailData.setId(orderDetail.getId());
		orderDetailData.setOrderId(orderDetail.getOrder().getId());
		if(orderDetail.getActivity() != null) {
			orderDetailData.setActivityId(orderDetail.getActivity().getId());			
		}		
		if(orderDetail.getSubscribe() != null) {
			orderDetailData.setSubscribeId(orderDetail.getSubscribe().getId());			
		}	
		orderDetailData.setVersion(orderDetail.getVersion());
		orderDetailData.setIsActive(orderDetail.getIsActive());

		GetByOrderIdDtoRes result = new GetByOrderIdDtoRes();
		result.setData(orderDetailData);

		return result;
	}
	
	public DeleteOrderDetailDtoRes deleteById(String id) throws Exception {
		try {			
			begin();
			boolean isDeleted = orderDetailDao.deleteById(id);
			commit();
			
			DeleteOrderDetailDtoRes orderDetailRes = new DeleteOrderDetailDtoRes();
			if(isDeleted) {
				orderDetailRes.setMsg("Delete Successfully");
			} else {
				orderDetailRes.setMsg("Delete Unsuccessfully");
			}
			
			return orderDetailRes;
		}
		catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public GetParticipantDtoRes getParticipant(String id) throws Exception {
		List<GetParticipantDtoDataRes> details = orderDetailDao.getParticipant(id);
		
		GetParticipantDtoRes dataRes = new GetParticipantDtoRes();
		dataRes.setData(details);
		
		return dataRes;
	}
	
	public List<GetIncomeActivityDtoDataRes> getIncome(String id) throws Exception {
		List<GetIncomeActivityDtoDataRes> details = orderDetailDao.getReportIncome(id);
		
		return details;
	}
	
	public GetParticipantDtoRes getAllParticipant() throws Exception {
		List<GetParticipantDtoDataRes> details = orderDetailDao.getAllParticipant();
		
		GetParticipantDtoRes dataRes = new GetParticipantDtoRes();
		dataRes.setData(details);
		
		return dataRes;
	}
	
	public GetIncomeActivityDtoRes getAllIncome() throws Exception {
		List<GetIncomeActivityDtoDataRes> details = orderDetailDao.getAllIncome();
		
		GetIncomeActivityDtoRes dataRes = new GetIncomeActivityDtoRes();
		dataRes.setData(details);
		
		return dataRes;
	}
}