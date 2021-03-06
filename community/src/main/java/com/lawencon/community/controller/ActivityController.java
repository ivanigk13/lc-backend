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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.community.dto.activity.DeleteActivityDtoRes;
import com.lawencon.community.dto.activity.GetAllActivityDtoRes;
import com.lawencon.community.dto.activity.GetByIdActivityDtoRes;
import com.lawencon.community.dto.activity.GetLastTwoActivityDtoRes;
import com.lawencon.community.dto.activity.InsertActivityDtoRes;
import com.lawencon.community.dto.activity.UpdateActivityDtoRes;
import com.lawencon.community.dto.activity.UpdateActivityTransactionStatusDtoReq;
import com.lawencon.community.dto.activity.UpdateActivityTransactionStatusDtoRes;
import com.lawencon.community.service.ActivityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("activities")
@RequiredArgsConstructor
public class ActivityController {

	private final ActivityService activityService;
	
	@GetMapping
	public ResponseEntity<GetAllActivityDtoRes> getAll(String query, Integer start, Integer max) throws Exception{
		GetAllActivityDtoRes activity = activityService.getAll(query, start, max);
		return new ResponseEntity<GetAllActivityDtoRes>(activity, HttpStatus.OK);		
	}
	
	@PostMapping
	public ResponseEntity<InsertActivityDtoRes> insert(
								@RequestPart(name="data") String data, 
								@RequestPart(required = true) MultipartFile[] file) throws Exception{
		InsertActivityDtoRes activity = activityService.insert(data, file);
		return new ResponseEntity<InsertActivityDtoRes>(activity, HttpStatus.CREATED);		
	}
	
	@PutMapping
	public ResponseEntity<UpdateActivityDtoRes> update(
								@RequestPart(name="data") String data, 
								@RequestPart(required = true) MultipartFile[] file) throws Exception{
		UpdateActivityDtoRes activity = activityService.update(data, file);
		return new ResponseEntity<UpdateActivityDtoRes>(activity, HttpStatus.OK);		
	}
	
	@PutMapping("approve")
	public ResponseEntity<UpdateActivityTransactionStatusDtoRes> updateApprove(@RequestBody @Valid UpdateActivityTransactionStatusDtoReq data) throws Exception{
		UpdateActivityTransactionStatusDtoRes activityType = activityService.updateApprove(data);
		return new ResponseEntity<UpdateActivityTransactionStatusDtoRes>(activityType, HttpStatus.OK);		
	}
	
	@PutMapping("reject")
	public ResponseEntity<UpdateActivityTransactionStatusDtoRes> updateReject(@RequestBody @Valid UpdateActivityTransactionStatusDtoReq data) throws Exception{
		UpdateActivityTransactionStatusDtoRes activityType = activityService.updateReject(data);
		return new ResponseEntity<UpdateActivityTransactionStatusDtoRes>(activityType, HttpStatus.OK);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdActivityDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdActivityDtoRes activity = activityService.getById(id);
		return new ResponseEntity<GetByIdActivityDtoRes>(activity, HttpStatus.OK);		
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<GetAllActivityDtoRes> getAllByUserIdActivity(@PathVariable("id") String id) throws Exception{
		GetAllActivityDtoRes activity = activityService.getAllByUserIdActivity(id);
		return new ResponseEntity<GetAllActivityDtoRes>(activity, HttpStatus.OK);		
	}
	
	@GetMapping("approve/user/{id}")
	public ResponseEntity<GetAllActivityDtoRes> getApprovedUserActivity(@PathVariable("id") String id) throws Exception{
		GetAllActivityDtoRes activity = activityService.getApprovedUserActivity(id);
		return new ResponseEntity<GetAllActivityDtoRes>(activity, HttpStatus.OK);		
	}
	
	@GetMapping("pending")
	public ResponseEntity<GetAllActivityDtoRes> getPendingActivity(Integer start, Integer max) throws Exception{
		GetAllActivityDtoRes activity = activityService.getPendingActivity(start,max);
		return new ResponseEntity<GetAllActivityDtoRes>(activity, HttpStatus.OK);		
	}
	
	@GetMapping("approved-event")
	public ResponseEntity<GetAllActivityDtoRes> getApprovedEvent() throws Exception{
		GetAllActivityDtoRes activity = activityService.getApprovedEventActivity();
		return new ResponseEntity<GetAllActivityDtoRes>(activity, HttpStatus.OK);		
	}
	
	@GetMapping("approved-course")
	public ResponseEntity<GetAllActivityDtoRes> getApprovedCourse() throws Exception{
		GetAllActivityDtoRes activity = activityService.getApprovedCourseActivity();
		return new ResponseEntity<GetAllActivityDtoRes>(activity, HttpStatus.OK);		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteActivityDtoRes> delete(@PathVariable("id") String id) throws Exception{
		DeleteActivityDtoRes activity = activityService.deleteById(id);
		return new ResponseEntity<DeleteActivityDtoRes>(activity, HttpStatus.OK);		
	}
	
	@GetMapping("last-two-event")
	public ResponseEntity<GetLastTwoActivityDtoRes> getLastTwoEvent() throws Exception{
		GetLastTwoActivityDtoRes activity = activityService.getLastTwoEventActivity();
		return new ResponseEntity<GetLastTwoActivityDtoRes>(activity, HttpStatus.OK);		
	}
	
	@GetMapping("last-two-course")
	public ResponseEntity<GetLastTwoActivityDtoRes> getLastTwoCourse() throws Exception{
		GetLastTwoActivityDtoRes activity = activityService.getLastTwoCourseActivity();
		return new ResponseEntity<GetLastTwoActivityDtoRes>(activity, HttpStatus.OK);		
	}
}
