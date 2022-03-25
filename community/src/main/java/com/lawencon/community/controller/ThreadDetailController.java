package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.threaddetail.GetAllByThreadIdThreadDetailDtoRes;
import com.lawencon.community.dto.threaddetail.GetByIdThreadDetailDtoRes;
import com.lawencon.community.dto.threaddetail.InsertThreadDetailDtoReq;
import com.lawencon.community.dto.threaddetail.InsertThreadDetailDtoRes;
import com.lawencon.community.service.ThreadDetailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("thread-details")
@RequiredArgsConstructor
public class ThreadDetailController {

	private final ThreadDetailService threadDetailService;
	
	@GetMapping("thread/{id}")
	public ResponseEntity<GetAllByThreadIdThreadDetailDtoRes> getAllByThreadId(@PathVariable("id") String id) throws Exception{
		GetAllByThreadIdThreadDetailDtoRes threadDetails = threadDetailService.getAllByThreadId(id);
		return new ResponseEntity<GetAllByThreadIdThreadDetailDtoRes>(threadDetails, HttpStatus.OK);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdThreadDetailDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdThreadDetailDtoRes threadDetail = threadDetailService.getById(id);
		return new ResponseEntity<GetByIdThreadDetailDtoRes>(threadDetail, HttpStatus.OK);		
	}
	
	@PostMapping
	public ResponseEntity<InsertThreadDetailDtoRes> insert(@RequestPart @Valid InsertThreadDetailDtoReq data) throws Exception{
		InsertThreadDetailDtoRes threadDetail = threadDetailService.insert(data);
		return new ResponseEntity<InsertThreadDetailDtoRes>(threadDetail, HttpStatus.CREATED);		
	}
}
