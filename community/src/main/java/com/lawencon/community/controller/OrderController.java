package com.lawencon.community.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.community.dto.order.DeleteOrderDtoRes;
import com.lawencon.community.dto.order.GetAllOrderDtoRes;
import com.lawencon.community.dto.order.GetByIdOrderDtoRes;
import com.lawencon.community.dto.order.InsertOrderDtoRes;
import com.lawencon.community.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@PostMapping
	public ResponseEntity<InsertOrderDtoRes> insert(@RequestPart(name="data") String data, @RequestPart(required = true) MultipartFile file) 
			throws Exception{
		InsertOrderDtoRes order = orderService.insert(data, file);
		return new ResponseEntity<InsertOrderDtoRes>(order, HttpStatus.CREATED);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdOrderDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdOrderDtoRes order = orderService.getById(id);
		return new ResponseEntity<GetByIdOrderDtoRes>(order, HttpStatus.OK);		
	}
	
	@GetMapping
	public ResponseEntity<GetAllOrderDtoRes> getAll() throws Exception{
		GetAllOrderDtoRes order = orderService.getAll();
		return new ResponseEntity<GetAllOrderDtoRes>(order, HttpStatus.OK);		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteOrderDtoRes> delete(@PathVariable("id") String id) throws Exception{
		DeleteOrderDtoRes order = orderService.deleteById(id);
		return new ResponseEntity<DeleteOrderDtoRes>(order, HttpStatus.OK);		
	}
}
