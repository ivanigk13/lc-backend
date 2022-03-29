package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class OrderService extends BaseCommunityService {

	private final OrderDao orderDao;
	private final TransactionStatusDao transactionStatusDao;
	private final UserDao userDao;
	private final FileDao fileDao;

	public InsertOrderDtoRes insert(String content, MultipartFile file) throws Exception {
		try {
			InsertOrderDtoReq orderReq = new ObjectMapper().readValue(content, InsertOrderDtoReq.class);
			Order order = new Order();

			TransactionStatus transactionStatus = transactionStatusDao.getById(transactionStatusDao.getStatusPendingId());
			order.setTransactionStatus(transactionStatus);

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
			order.setInvoice(orderReq.getInvoice());
			order.setCreatedBy(getId());

			Order orderInsert = orderDao.save(order);
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
	
	public GetAllOrderDtoRes getAll(Integer start, Integer max) throws Exception {
		List<Order> orders;	
		if(start == null) orders = orderDao.getAll();
		else orders = orderDao.getAll(start, max);
		
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
}
