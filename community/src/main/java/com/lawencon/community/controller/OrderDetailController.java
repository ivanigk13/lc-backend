package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.orderdetail.DeleteOrderDetailDtoRes;
import com.lawencon.community.dto.orderdetail.GetAllOrderDetailDtoRes;
import com.lawencon.community.dto.orderdetail.GetByIdOrderDetailDtoRes;
import com.lawencon.community.dto.orderdetail.GetByOrderIdDtoRes;
import com.lawencon.community.dto.orderdetail.InsertOrderDetailDtoReq;
import com.lawencon.community.dto.orderdetail.InsertOrderDetailDtoRes;
import com.lawencon.community.service.OrderDetailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("order-details")
@RequiredArgsConstructor
public class OrderDetailController {

	private final OrderDetailService orderDetailService;
	
	@PostMapping
	public ResponseEntity<InsertOrderDetailDtoRes> insert(@RequestBody @Valid InsertOrderDetailDtoReq data) throws Exception{
		InsertOrderDetailDtoRes orderDetail = orderDetailService.insert(data);
		return new ResponseEntity<InsertOrderDetailDtoRes>(orderDetail, HttpStatus.CREATED);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdOrderDetailDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdOrderDetailDtoRes orderDetail = orderDetailService.getById(id);
		return new ResponseEntity<GetByIdOrderDetailDtoRes>(orderDetail, HttpStatus.OK);		
	}
	
	@GetMapping("order/{id}")
	public ResponseEntity<GetByOrderIdDtoRes> getByOrderId(@PathVariable("id") String id) throws Exception{
		GetByOrderIdDtoRes orderDetail = orderDetailService.getByOrderId(id);
		return new ResponseEntity<GetByOrderIdDtoRes>(orderDetail, HttpStatus.OK);		
	}
	
	@GetMapping
	public ResponseEntity<GetAllOrderDetailDtoRes> getAll(@RequestParam(value = "start", required = false) Integer start, @RequestParam(value = "max", required = false) Integer max) throws Exception{
		GetAllOrderDetailDtoRes orderDetail = orderDetailService.getAll(start, max);
		return new ResponseEntity<GetAllOrderDetailDtoRes>(orderDetail, HttpStatus.OK);		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteOrderDetailDtoRes> delete(@PathVariable("id") String id) throws Exception{
		DeleteOrderDetailDtoRes orderDetail = orderDetailService.deleteById(id);
		return new ResponseEntity<DeleteOrderDetailDtoRes>(orderDetail, HttpStatus.OK);		
	}	
}
