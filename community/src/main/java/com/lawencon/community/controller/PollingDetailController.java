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
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.pollingdetail.DeletePollingDetailDtoRes;
import com.lawencon.community.dto.pollingdetail.GetAllPollingDetailDtoRes;
import com.lawencon.community.dto.pollingdetail.GetByIdPollingDetailDtoRes;
import com.lawencon.community.dto.pollingdetail.InsertPollingDetailDtoReq;
import com.lawencon.community.dto.pollingdetail.InsertPollingDetailDtoRes;
import com.lawencon.community.dto.pollingdetail.UpdatePollingDetailDtoReq;
import com.lawencon.community.dto.pollingdetail.UpdatePollingDetailDtoRes;
import com.lawencon.community.service.PollingDetailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("polling-details")
@RequiredArgsConstructor
public class PollingDetailController {
	
	private final PollingDetailService pollingDetailService;
	
	@GetMapping
	public ResponseEntity<GetAllPollingDetailDtoRes> getAll(
							@RequestParam(value = "start", required = false) Integer start,
							@RequestParam(value = "max", required = false) Integer max) throws Exception{
		GetAllPollingDetailDtoRes result = pollingDetailService.getAll(start, max);
		return new ResponseEntity<GetAllPollingDetailDtoRes>(result, HttpStatus.OK);
	}
	
	@GetMapping("header/{id}")
	public ResponseEntity<GetAllPollingDetailDtoRes> getAllByPollingHeaderId(@PathVariable("id") String id) throws Exception {
		GetAllPollingDetailDtoRes result = pollingDetailService.getAllByPollingHeaderId(id);
		return new ResponseEntity<GetAllPollingDetailDtoRes>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdPollingDetailDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByIdPollingDetailDtoRes result = pollingDetailService.getById(id);
		return new ResponseEntity<GetByIdPollingDetailDtoRes>(result, HttpStatus.OK);
	} 
	
	@PostMapping
	public ResponseEntity<InsertPollingDetailDtoRes> insert(@RequestBody @Valid InsertPollingDetailDtoReq pollingDetail) throws Exception {
		InsertPollingDetailDtoRes result = pollingDetailService.insert(pollingDetail);
		return new ResponseEntity<InsertPollingDetailDtoRes>(result, HttpStatus.CREATED);
	}
	

	@PutMapping
	public ResponseEntity<UpdatePollingDetailDtoRes> update(@RequestBody @Valid UpdatePollingDetailDtoReq pollingDetail) throws Exception {
		UpdatePollingDetailDtoRes result = pollingDetailService.update(pollingDetail);
		return new ResponseEntity<UpdatePollingDetailDtoRes>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeletePollingDetailDtoRes> delete(@PathVariable("id") String id) throws Exception {
		DeletePollingDetailDtoRes result = pollingDetailService.deleteById(id);
		return new ResponseEntity<DeletePollingDetailDtoRes>(result, HttpStatus.OK);
	}
}
