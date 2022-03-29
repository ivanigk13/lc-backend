package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.subscribe.DeleteSubscribeDtoRes;
import com.lawencon.community.dto.subscribe.GetAllSubscribeDtoRes;
import com.lawencon.community.dto.subscribe.GetByIdSubscribeDtoRes;
import com.lawencon.community.dto.subscribe.InsertSubscribeDtoReq;
import com.lawencon.community.dto.subscribe.InsertSubscribeDtoRes;
import com.lawencon.community.dto.subscribe.UpdateSubscribeDtoReq;
import com.lawencon.community.dto.subscribe.UpdateSubscribeDtoRes;
import com.lawencon.community.service.SubscribeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("subscribes")
@RequiredArgsConstructor
public class SubscribeController {

	private final SubscribeService subscribeService;
	
	@GetMapping
	public ResponseEntity<GetAllSubscribeDtoRes> getAll(
							@RequestParam(value = "start", required = false) Integer start,
							@RequestParam(value = "max", required = false) Integer max) throws Exception{
		GetAllSubscribeDtoRes subscribe = subscribeService.getAll(start, max);
		return new ResponseEntity<GetAllSubscribeDtoRes>(subscribe, HttpStatus.OK);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdSubscribeDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdSubscribeDtoRes subscribe = subscribeService.getById(id);
		return new ResponseEntity<GetByIdSubscribeDtoRes>(subscribe, HttpStatus.OK);		
	}
	
	@PostMapping
	public ResponseEntity<InsertSubscribeDtoRes> insert(@RequestPart @Valid InsertSubscribeDtoReq data) throws Exception{
		InsertSubscribeDtoRes subscribe = subscribeService.insert(data);
		return new ResponseEntity<InsertSubscribeDtoRes>(subscribe, HttpStatus.CREATED);		
	}
	
	@PutMapping
	public ResponseEntity<UpdateSubscribeDtoRes> update(@RequestPart @Valid UpdateSubscribeDtoReq data) throws Exception{
		UpdateSubscribeDtoRes subscribe = subscribeService.update(data);
		return new ResponseEntity<UpdateSubscribeDtoRes>(subscribe, HttpStatus.OK);		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteSubscribeDtoRes> delete(@PathVariable("id") String id) throws Exception{
		DeleteSubscribeDtoRes subscribe = subscribeService.deleteById(id);
		return new ResponseEntity<DeleteSubscribeDtoRes>(subscribe, HttpStatus.OK);		
	}
}
