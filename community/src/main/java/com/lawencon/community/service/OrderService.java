package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.base.BaseService;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.OrderDao;
import com.lawencon.community.dao.TransactionStatusDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.order.DeleteOrderDtoRes;
import com.lawencon.community.dto.order.GetAllOrderDtoRes;
import com.lawencon.community.dto.order.GetByIdOrderDtoRes;
import com.lawencon.community.dto.order.GetOrderDtoDataRes;
import com.lawencon.community.dto.order.InsertOrderDtoDataRes;
import com.lawencon.community.dto.order.InsertOrderDtoReq;
import com.lawencon.community.dto.order.InsertOrderDtoRes;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Order;
import com.lawencon.community.model.TransactionStatus;
import com.lawencon.community.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService extends BaseService {

	private final OrderDao orderDao;
	private final TransactionStatusDao transactionStatusDao;
	private final UserDao userDao;
	private final FileDao fileDao;

	public InsertOrderDtoRes insert(InsertOrderDtoReq data) throws Exception {
		Order order = new Order();

		TransactionStatus transactionStatus = transactionStatusDao.getById(data.getTransactionStatusId());
		order.setTransactionStatus(transactionStatus);

		User user = userDao.getById(data.getUserId());
		order.setUser(user);

		File file = fileDao.getById(data.getFileId());
		order.setFile(file);
		order.setInvoice(data.getInvoice());

		begin();
		Order orderInsert = orderDao.save(order);
		InsertOrderDtoDataRes orderId = new InsertOrderDtoDataRes();
		orderId.setId(orderInsert.getId());

		InsertOrderDtoRes result = new InsertOrderDtoRes();
		result.setData(orderId);
		result.setMsg("Insert Successfully");
		commit();
		return result;
	}

	public GetAllOrderDtoRes getAll() throws Exception {
		List<Order> orders = orderDao.getAll();
		List<GetOrderDtoDataRes> data = new ArrayList<>();

		orders.forEach(list -> {
			GetOrderDtoDataRes order = new GetOrderDtoDataRes();
			order.setId(list.getId());
			order.setTransactionStatusId(list.getTransactionStatus().getId());
			order.setFileId(list.getFile().getId());
			order.setUserId(list.getUser().getId());
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
		Order order = orderDao.getById(id);

		GetOrderDtoDataRes orderData = new GetOrderDtoDataRes();
		orderData.setId(order.getId());
		orderData.setTransactionStatusId(order.getTransactionStatus().getId());
		orderData.setFileId(order.getFile().getId());
		orderData.setUserId(order.getUser().getId());
		orderData.setInvoice(order.getInvoice());
		orderData.setIsActive(order.getIsActive());

		GetByIdOrderDtoRes result = new GetByIdOrderDtoRes();
		result.setData(orderData);

		return result;
	}
	
	public DeleteOrderDtoRes deleteById(String id) throws Exception {
		begin();
		orderDao.deleteById(id);
		commit();
		
		DeleteOrderDtoRes orderRes = new DeleteOrderDtoRes();
		orderRes.setMsg("Delete Successfully");
		
		return orderRes;
	}
}
