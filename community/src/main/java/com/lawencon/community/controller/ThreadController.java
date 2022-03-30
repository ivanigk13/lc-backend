package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.lawencon.community.dto.thread.GetAllThreadDtoRes;
import com.lawencon.community.dto.thread.GetByIdThreadDtoRes;
import com.lawencon.community.dto.thread.InsertThreadDtoRes;
import com.lawencon.community.dto.thread.UpdateThreadDtoReq;
import com.lawencon.community.dto.thread.UpdateThreadDtoRes;
import com.lawencon.community.service.ThreadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("threads")
@RequiredArgsConstructor
public class ThreadController {

	private final ThreadService threadService;
	
	@GetMapping
	public ResponseEntity<GetAllThreadDtoRes> getAll(
							@RequestParam(value = "start", required = false) Integer start,
							@RequestParam(value = "max", required = false) Integer max) throws Exception{
		GetAllThreadDtoRes thread = threadService.getAll(start, max);
		return new ResponseEntity<GetAllThreadDtoRes>(thread, HttpStatus.OK);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdThreadDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdThreadDtoRes thread = threadService.getById(id);
		return new ResponseEntity<GetByIdThreadDtoRes>(thread, HttpStatus.OK);		
	}
	
	@PostMapping
	public ResponseEntity<InsertThreadDtoRes> insert(
								@RequestPart(name="data") String data, 
								@RequestPart(required = false) MultipartFile file) throws Exception{
		InsertThreadDtoRes thread = threadService.insert(data, file);
		return new ResponseEntity<InsertThreadDtoRes>(thread, HttpStatus.CREATED);		
	}
	
	@PutMapping
	public ResponseEntity<UpdateThreadDtoRes> update(@RequestBody @Valid UpdateThreadDtoReq data) throws Exception{
		UpdateThreadDtoRes thread = threadService.update(data);
		return new ResponseEntity<UpdateThreadDtoRes>(thread, HttpStatus.OK);		
	}
}
