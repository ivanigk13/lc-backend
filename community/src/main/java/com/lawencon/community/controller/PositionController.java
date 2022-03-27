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

import com.lawencon.community.dto.position.DeletePositionDtoRes;
import com.lawencon.community.dto.position.GetAllPositionDtoRes;
import com.lawencon.community.dto.position.GetByIdPositionDtoRes;
import com.lawencon.community.dto.position.InsertPositionDtoReq;
import com.lawencon.community.dto.position.InsertPositionDtoRes;
import com.lawencon.community.dto.position.UpdatePositionDtoReq;
import com.lawencon.community.dto.position.UpdatePositionDtoRes;
import com.lawencon.community.service.PositionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("positions")
@RequiredArgsConstructor
public class PositionController {
	
	private final PositionService positionService;
	
	@GetMapping
	public ResponseEntity<GetAllPositionDtoRes> getAll(@RequestParam("start") Integer start, @RequestParam("max") Integer max) throws Exception {
		GetAllPositionDtoRes result = positionService.getAll(start, max);
		return new ResponseEntity<GetAllPositionDtoRes>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdPositionDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByIdPositionDtoRes result = positionService.getById(id);
		return new ResponseEntity<GetByIdPositionDtoRes>(result, HttpStatus.OK);
	} 
	
	@PostMapping
	public ResponseEntity<InsertPositionDtoRes> insert(@RequestBody @Valid InsertPositionDtoReq pollingDetail) throws Exception {
		InsertPositionDtoRes result = positionService.insert(pollingDetail);
		return new ResponseEntity<InsertPositionDtoRes>(result, HttpStatus.CREATED);
	}
	

	@PutMapping
	public ResponseEntity<UpdatePositionDtoRes> update(@RequestBody @Valid UpdatePositionDtoReq pollingDetail) throws Exception {
		UpdatePositionDtoRes result = positionService.update(pollingDetail);
		return new ResponseEntity<UpdatePositionDtoRes>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeletePositionDtoRes> delete(@PathVariable("id") String id) throws Exception {
		DeletePositionDtoRes result = positionService.deleteById(id);
		return new ResponseEntity<DeletePositionDtoRes>(result, HttpStatus.OK);
	}

}
