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

import com.lawencon.community.dto.threadtype.DeleteThreadTypeDtoRes;
import com.lawencon.community.dto.threadtype.GetAllThreadTypeDtoRes;
import com.lawencon.community.dto.threadtype.GetByIdThreadTypeDtoRes;
import com.lawencon.community.dto.threadtype.InsertThreadTypeDtoReq;
import com.lawencon.community.dto.threadtype.InsertThreadTypeDtoRes;
import com.lawencon.community.dto.threadtype.UpdateThreadTypeDtoReq;
import com.lawencon.community.dto.threadtype.UpdateThreadTypeDtoRes;
import com.lawencon.community.service.ThreadTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("thread-types")
@RequiredArgsConstructor
public class ThreadTypeController {

	private final ThreadTypeService threadTypeService;
	
	@GetMapping
	public ResponseEntity<GetAllThreadTypeDtoRes> getAll(
							@RequestParam(value = "start", required = false) Integer start,
							@RequestParam(value = "max", required = false) Integer max) throws Exception{
		GetAllThreadTypeDtoRes threadType = threadTypeService.getAll(start, max);
		return new ResponseEntity<GetAllThreadTypeDtoRes>(threadType, HttpStatus.OK);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdThreadTypeDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdThreadTypeDtoRes threadType = threadTypeService.getById(id);
		return new ResponseEntity<GetByIdThreadTypeDtoRes>(threadType, HttpStatus.OK);		
	}
	
	@PostMapping
	public ResponseEntity<InsertThreadTypeDtoRes> insert(@RequestPart @Valid InsertThreadTypeDtoReq data) throws Exception{
		InsertThreadTypeDtoRes threadType = threadTypeService.insert(data);
		return new ResponseEntity<InsertThreadTypeDtoRes>(threadType, HttpStatus.CREATED);		
	}
	
	@PutMapping
	public ResponseEntity<UpdateThreadTypeDtoRes> update(@RequestPart @Valid UpdateThreadTypeDtoReq data) throws Exception{
		UpdateThreadTypeDtoRes threadType = threadTypeService.update(data);
		return new ResponseEntity<UpdateThreadTypeDtoRes>(threadType, HttpStatus.OK);		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteThreadTypeDtoRes> delete(@PathVariable("id") String id) throws Exception{
		DeleteThreadTypeDtoRes threadType = threadTypeService.deleteById(id);
		return new ResponseEntity<DeleteThreadTypeDtoRes>(threadType, HttpStatus.OK);		
	}
}
