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

import com.lawencon.community.dto.pollingvoter.DeletePollingVoterDtoRes;
import com.lawencon.community.dto.pollingvoter.GetAllPollingVoterDtoRes;
import com.lawencon.community.dto.pollingvoter.GetByIdPollingVoterDtoRes;
import com.lawencon.community.dto.pollingvoter.InsertPollingVoterDtoReq;
import com.lawencon.community.dto.pollingvoter.InsertPollingVoterDtoRes;
import com.lawencon.community.dto.pollingvoter.UpdatePollingVoterDtoReq;
import com.lawencon.community.dto.pollingvoter.UpdatePollingVoterDtoRes;
import com.lawencon.community.service.PollingVoterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("polling-voters")
@RequiredArgsConstructor
public class PollingVoterController {
	
	private final PollingVoterService pollingVoterService;
	
	@GetMapping
	public ResponseEntity<GetAllPollingVoterDtoRes> getAll(@RequestParam("start") Integer start, @RequestParam("max") Integer max) throws Exception {
		GetAllPollingVoterDtoRes result = pollingVoterService.getAll(start, max);
		return new ResponseEntity<GetAllPollingVoterDtoRes>(result, HttpStatus.OK);
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<GetAllPollingVoterDtoRes> getAllByPollingHeaderId(@PathVariable("id") String id) throws Exception {
		GetAllPollingVoterDtoRes result = pollingVoterService.getPollingVoterByUserId(id);
		return new ResponseEntity<GetAllPollingVoterDtoRes>(result, HttpStatus.OK);
	} 
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdPollingVoterDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByIdPollingVoterDtoRes result = pollingVoterService.getById(id);
		return new ResponseEntity<GetByIdPollingVoterDtoRes>(result, HttpStatus.OK);
	} 
	
	@PostMapping
	public ResponseEntity<InsertPollingVoterDtoRes> insert(@RequestBody @Valid InsertPollingVoterDtoReq pollingDetail) throws Exception {
		InsertPollingVoterDtoRes result = pollingVoterService.insert(pollingDetail);
		return new ResponseEntity<InsertPollingVoterDtoRes>(result, HttpStatus.CREATED);
	}
	

	@PutMapping
	public ResponseEntity<UpdatePollingVoterDtoRes> update(@RequestBody @Valid UpdatePollingVoterDtoReq pollingDetail) throws Exception {
		UpdatePollingVoterDtoRes result = pollingVoterService.update(pollingDetail);
		return new ResponseEntity<UpdatePollingVoterDtoRes>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeletePollingVoterDtoRes> delete(@PathVariable("id") String id) throws Exception {
		DeletePollingVoterDtoRes result = pollingVoterService.deleteById(id);
		return new ResponseEntity<DeletePollingVoterDtoRes>(result, HttpStatus.OK);
	}

}
