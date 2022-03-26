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

import com.lawencon.community.dto.activitytype.DeleteActivityTypeDtoRes;
import com.lawencon.community.dto.activitytype.GetAllActivityTypeDtoRes;
import com.lawencon.community.dto.activitytype.GetByIdActivityTypeDtoRes;
import com.lawencon.community.dto.activitytype.InsertActivityTypeDtoReq;
import com.lawencon.community.dto.activitytype.InsertActivityTypeDtoRes;
import com.lawencon.community.dto.activitytype.UpdateActivityTypeDtoReq;
import com.lawencon.community.dto.activitytype.UpdateActivityTypeDtoRes;
import com.lawencon.community.service.ActivityTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("activity-types")
@RequiredArgsConstructor
public class ActivityTypeController {

	private final ActivityTypeService activityTypeService;
	
	@PostMapping
	public ResponseEntity<InsertActivityTypeDtoRes> insert(@RequestPart @Valid InsertActivityTypeDtoReq data) throws Exception{
		InsertActivityTypeDtoRes activityType = activityTypeService.insert(data);
		return new ResponseEntity<InsertActivityTypeDtoRes>(activityType, HttpStatus.CREATED);		
	}
	
	@PutMapping
	public ResponseEntity<UpdateActivityTypeDtoRes> update(@RequestPart @Valid UpdateActivityTypeDtoReq data) throws Exception{
		UpdateActivityTypeDtoRes activityType = activityTypeService.update(data);
		return new ResponseEntity<UpdateActivityTypeDtoRes>(activityType, HttpStatus.OK);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdActivityTypeDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdActivityTypeDtoRes activityType = activityTypeService.getById(id);
		return new ResponseEntity<GetByIdActivityTypeDtoRes>(activityType, HttpStatus.OK);		
	}
	
	@GetMapping()
	public ResponseEntity<GetAllActivityTypeDtoRes> getAll(@RequestParam("start") Integer start, @RequestParam("max") Integer max) throws Exception{
		GetAllActivityTypeDtoRes activityType = activityTypeService.getAll(start, max);
		return new ResponseEntity<GetAllActivityTypeDtoRes>(activityType, HttpStatus.OK);		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteActivityTypeDtoRes> delete(@PathVariable("id") String id) throws Exception{
		DeleteActivityTypeDtoRes activityType = activityTypeService.deleteById(id);
		return new ResponseEntity<DeleteActivityTypeDtoRes>(activityType, HttpStatus.OK);		
	}
		
}
