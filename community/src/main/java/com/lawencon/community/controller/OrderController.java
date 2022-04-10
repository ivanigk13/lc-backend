package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.community.dto.order.DeleteOrderDtoRes;
import com.lawencon.community.dto.order.GetAllOrderDtoRes;
import com.lawencon.community.dto.order.GetByIdOrderDtoRes;
import com.lawencon.community.dto.order.GetByUserIdOrderDtoRes;
import com.lawencon.community.dto.order.InsertOrderDtoRes;
import com.lawencon.community.dto.order.UpdateOrderDtoReq;
import com.lawencon.community.dto.order.UpdateOrderDtoRes;
import com.lawencon.community.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@PostMapping
	public ResponseEntity<InsertOrderDtoRes> insert(
								@RequestPart(name="data") String data, 
								@RequestPart(required = true) MultipartFile file) throws Exception{
		InsertOrderDtoRes order = orderService.insert(data, file);
		return new ResponseEntity<InsertOrderDtoRes>(order, HttpStatus.CREATED);		
	}
	
	@PutMapping("approve")
	public ResponseEntity<UpdateOrderDtoRes> updateApprove(@RequestBody @Valid UpdateOrderDtoReq data) throws Exception{
		UpdateOrderDtoRes activityType = orderService.updateApprove(data);
		return new ResponseEntity<UpdateOrderDtoRes>(activityType, HttpStatus.OK);		
	}
	
	@PutMapping("reject")
	public ResponseEntity<UpdateOrderDtoRes> updateReject(@RequestBody @Valid UpdateOrderDtoReq data) throws Exception{
		UpdateOrderDtoRes activityType = orderService.updateReject(data);
		return new ResponseEntity<UpdateOrderDtoRes>(activityType, HttpStatus.OK);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdOrderDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdOrderDtoRes order = orderService.getById(id);
		return new ResponseEntity<GetByIdOrderDtoRes>(order, HttpStatus.OK);		
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<GetByUserIdOrderDtoRes> getByUserId(@PathVariable("id") String id) throws Exception{
		GetByUserIdOrderDtoRes order = orderService.getByUserId(id);
		return new ResponseEntity<GetByUserIdOrderDtoRes>(order, HttpStatus.OK);		
	}
	
	@GetMapping
	public ResponseEntity<GetAllOrderDtoRes> getAll(
							@RequestParam(value = "start", required = false) Integer start,
							@RequestParam(value = "max", required = false) Integer max) throws Exception{
		GetAllOrderDtoRes order = orderService.getAll(start, max);
		return new ResponseEntity<GetAllOrderDtoRes>(order, HttpStatus.OK);		
	}
	
	@GetMapping("subscribe")
	public ResponseEntity<GetAllOrderDtoRes> getAllSubscribe(
							@RequestParam(value = "start", required = false) Integer start,
							@RequestParam(value = "max", required = false) Integer max) throws Exception{
		GetAllOrderDtoRes order = orderService.getAllPendingSubscribe(start, max);
		return new ResponseEntity<GetAllOrderDtoRes>(order, HttpStatus.OK);		
	}
	
	@GetMapping("activity/{id}")
	public ResponseEntity<GetAllOrderDtoRes> getPendingOrderByActivityId(@PathVariable("id") String id) throws Exception{
		GetAllOrderDtoRes order = orderService.getPendingOrderByActivityId(id);
		return new ResponseEntity<GetAllOrderDtoRes>(order, HttpStatus.OK);		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteOrderDtoRes> delete(@PathVariable("id") String id) throws Exception{
		DeleteOrderDtoRes order = orderService.deleteById(id);
		return new ResponseEntity<DeleteOrderDtoRes>(order, HttpStatus.OK);		
	}
}
