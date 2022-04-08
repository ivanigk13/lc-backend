package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.community.dao.ActivityDao;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.OrderDao;
import com.lawencon.community.dao.OrderDetailDao;
import com.lawencon.community.dao.SubscribeDao;
import com.lawencon.community.dao.TransactionStatusDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.order.DeleteOrderDtoRes;
import com.lawencon.community.dto.order.GetAllOrderDtoRes;
import com.lawencon.community.dto.order.GetByIdOrderDtoRes;
import com.lawencon.community.dto.order.GetByUserIdOrderDtoRes;
import com.lawencon.community.dto.order.GetOrderDtoDataRes;
import com.lawencon.community.dto.order.InsertOrderDtoDataRes;
import com.lawencon.community.dto.order.InsertOrderDtoReq;
import com.lawencon.community.dto.order.InsertOrderDtoRes;
import com.lawencon.community.dto.order.UpdateOrderDtoDataRes;
import com.lawencon.community.dto.order.UpdateOrderDtoReq;
import com.lawencon.community.dto.order.UpdateOrderDtoRes;
import com.lawencon.community.model.Activity;
import com.lawencon.community.model.File;
import com.lawencon.community.model.OrderDetail;
import com.lawencon.community.model.Orders;
import com.lawencon.community.model.Subscribe;
import com.lawencon.community.model.TransactionStatus;
import com.lawencon.community.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService extends BaseCommunityService {

	private final OrderDao orderDao;
	private final TransactionStatusDao transactionStatusDao;
	private final UserDao userDao;
	private final FileDao fileDao;
	private final OrderDetailDao orderDetailDao;
	private final ActivityDao activityDao;
	private final SubscribeDao subscribeDao;

	public InsertOrderDtoRes insert(String content, MultipartFile file) throws Exception {
		try {
			InsertOrderDtoReq orderReq = new ObjectMapper().readValue(content, InsertOrderDtoReq.class);
			Orders order = new Orders();

			TransactionStatus transactionStatus = transactionStatusDao.getById(transactionStatusDao.getStatusPendingId());
			order.setTransactionStatus(transactionStatus);
			String invoice = "INV-" + generateCode(5);
			order.setInvoice(invoice);

			User user = userDao.getById(orderReq.getUserId());
			order.setUser(user);

			File fileThread = new File();
			String splitter = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, 
					file.getOriginalFilename().length());
			fileThread.setExtensionName(splitter);
			fileThread.setContent(file.getBytes());
			fileThread.setCreatedBy(getId());
			
			begin();
			fileThread = fileDao.save(fileThread);
			order.setFile(fileThread);
			order.setCreatedBy(getId());

			Orders orderInsert = orderDao.save(order);
			commit();
			
			InsertOrderDtoDataRes orderId = new InsertOrderDtoDataRes();
			orderId.setId(orderInsert.getId());

			InsertOrderDtoRes result = new InsertOrderDtoRes();
			result.setData(orderId);
			result.setMsg("Insert Successfully");
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
	}
	
	public UpdateOrderDtoRes updateApprove(UpdateOrderDtoReq data) throws Exception {
		Orders order = orderDao.getById(data.getId());
		
		TransactionStatus transactionStatus = new TransactionStatus();
		transactionStatus.setId(transactionStatusDao.getStatusApproveId());
		order.setTransactionStatus(transactionStatus);
		order.setUpdatedBy(getId());
		
		begin();
		order = orderDao.save(order);
		commit();
		
		UpdateOrderDtoDataRes dataRes = new UpdateOrderDtoDataRes();
		dataRes.setVersion(order.getVersion());
		
		UpdateOrderDtoRes orderRes = new UpdateOrderDtoRes();
		orderRes.setData(dataRes);
		orderRes.setMsg("Update Succesfully");
		
		return orderRes;
	}
	
	public UpdateOrderDtoRes updateReject(UpdateOrderDtoReq data) throws Exception {
		Orders order = orderDao.getById(data.getId());
		
		TransactionStatus transactionStatus = new TransactionStatus();
		transactionStatus.setId(transactionStatusDao.getStatusRejectId());
		order.setTransactionStatus(transactionStatus);
		order.setUpdatedBy(getId());
		
		begin();
		order = orderDao.save(order);
		commit();
		
		UpdateOrderDtoDataRes dataRes = new UpdateOrderDtoDataRes();
		dataRes.setVersion(order.getVersion());
		
		UpdateOrderDtoRes orderRes = new UpdateOrderDtoRes();
		orderRes.setData(dataRes);
		orderRes.setMsg("Update Succesfully");
		
		return orderRes;
	}
	
	public GetAllOrderDtoRes getAll(Integer start, Integer max) throws Exception {
		List<Orders> orders;	
		if(start == null) orders = orderDao.getAll();
		else orders = orderDao.getAll(start, max);
		
		List<GetOrderDtoDataRes> data = new ArrayList<>();

		orders.forEach(list -> {
			GetOrderDtoDataRes order = new GetOrderDtoDataRes();
			order.setId(list.getId());
			order.setTransactionStatusId(list.getTransactionStatus().getId());
			
			TransactionStatus transactionStatus = transactionStatusDao.getById(order.getTransactionStatusId());
			order.setTransactionStatusName(transactionStatus.getStatusName());
			
			order.setFileId(list.getFile().getId());
			order.setUserId(list.getUser().getId());
			
			OrderDetail orderDetail = orderDetailDao.getOrderDetailByOrderId(order.getId());
			
			if(orderDetail.getActivity() != null) {
				Activity activity = activityDao.getById(orderDetail.getActivity().getId());
				order.setActivityId(activity.getId());
				order.setActivityName(activity.getActivityName());				
			}
			
			if(orderDetail.getSubscribe() != null) {
				Subscribe subscribe = subscribeDao.getById(orderDetail.getSubscribe().getId());
				order.setSubscribeId(subscribe.getId());
				order.setDuration(subscribe.getDuration());
			}
			order.setInvoice(list.getInvoice());
			order.setVersion(list.getVersion());
			order.setIsActive(list.getIsActive());
			data.add(order);
		});

		GetAllOrderDtoRes result = new GetAllOrderDtoRes();
		result.setData(data);

		return result;
	}
	
	public GetAllOrderDtoRes getAllPendingSubscribe(Integer start, Integer max) throws Exception {
		List<Orders> orders;	
		if(start == null) orders = orderDao.getPendingOrderSubscribe();
		else orders = orderDao.getAll(start, max);
		
		List<GetOrderDtoDataRes> data = new ArrayList<>();

		orders.forEach(list -> {
			GetOrderDtoDataRes order = new GetOrderDtoDataRes();
			order.setId(list.getId());
			order.setTransactionStatusId(list.getTransactionStatus().getId());
			
			TransactionStatus transactionStatus = transactionStatusDao.getById(order.getTransactionStatusId());
			order.setTransactionStatusName(transactionStatus.getStatusName());
			
			order.setFileId(list.getFile().getId());
			order.setUserId(list.getUser().getId());
			
			OrderDetail orderDetail = orderDetailDao.getOrderDetailByOrderId(order.getId());
			
			Subscribe subscribe = subscribeDao.getById(orderDetail.getSubscribe().getId());
			order.setSubscribeId(subscribe.getId());
			order.setDuration(subscribe.getDuration());
			
			order.setInvoice(list.getInvoice());
			order.setVersion(list.getVersion());
			order.setIsActive(list.getIsActive());
			data.add(order);
		});

		GetAllOrderDtoRes result = new GetAllOrderDtoRes();
		result.setData(data);

		return result;
	}

	public GetByIdOrderDtoRes getById(String id) throws Exception {
		Orders order = orderDao.getById(id);

		GetOrderDtoDataRes orderData = new GetOrderDtoDataRes();
		orderData.setId(order.getId());
		orderData.setTransactionStatusId(order.getTransactionStatus().getId());
		orderData.setFileId(order.getFile().getId());
		orderData.setUserId(order.getUser().getId());
		orderData.setInvoice(order.getInvoice());
		orderData.setVersion(order.getVersion());
		orderData.setIsActive(order.getIsActive());

		GetByIdOrderDtoRes result = new GetByIdOrderDtoRes();
		result.setData(orderData);

		return result;
	}
	
	public DeleteOrderDtoRes deleteById(String id) throws Exception {
		try {			
			begin();
			boolean isDeleted = orderDao.deleteById(id);
			commit();
			
			DeleteOrderDtoRes orderRes = new DeleteOrderDtoRes();
			if(isDeleted) {
				orderRes.setMsg("Delete Successfully");
			} else {
				orderRes.setMsg("Delete Unsuccessfully");
			}
			
			return orderRes;
		}
		catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public GetByUserIdOrderDtoRes getByUserId(String id) throws Exception {
		List<Orders> orders = orderDao.getOrderByUserId(id);				
		List<GetOrderDtoDataRes> data = new ArrayList<>();

		orders.forEach(list -> {
			GetOrderDtoDataRes order = new GetOrderDtoDataRes();
			order.setId(list.getId());
			order.setTransactionStatusId(list.getTransactionStatus().getId());
			
			TransactionStatus transactionStatus = transactionStatusDao.getById(order.getTransactionStatusId());
			order.setTransactionStatusName(transactionStatus.getStatusName());
			
			order.setFileId(list.getFile().getId());
			order.setUserId(list.getUser().getId());
			
			OrderDetail orderDetail = orderDetailDao.getOrderDetailByOrderId(order.getId());
			
			if(orderDetail.getActivity() != null) {
				Activity activity = activityDao.getById(orderDetail.getActivity().getId());
				order.setActivityName(activity.getActivityName());				
			}
			
			if(orderDetail.getSubscribe() != null) {
				String test = orderDetail.getSubscribe().getId();
				Subscribe subscribe = subscribeDao.getById(test);
				order.setDuration(subscribe.getDuration());
			}
			order.setInvoice(list.getInvoice());
			order.setVersion(list.getVersion());
			order.setIsActive(list.getIsActive());
			data.add(order);
		});

		GetByUserIdOrderDtoRes result = new GetByUserIdOrderDtoRes();
		result.setData(data);

		return result;
	} 
}
