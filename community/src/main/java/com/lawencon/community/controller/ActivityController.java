package com.lawencon.community.controller;

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
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.community.dto.activity.DeleteActivityDtoRes;
import com.lawencon.community.dto.activity.GetAllActivityDtoRes;
import com.lawencon.community.dto.activity.GetAllByUserIdActivityRes;
import com.lawencon.community.dto.activity.GetByIdActivityDtoRes;
import com.lawencon.community.dto.activity.InsertActivityDtoRes;
import com.lawencon.community.dto.activity.UpdateActivityDtoRes;
import com.lawencon.community.service.ActivityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("activities")
@RequiredArgsConstructor
public class ActivityController {

	private final ActivityService activityService;
	
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
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdActivityDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdActivityDtoRes activity = activityService.getById(id);
		return new ResponseEntity<GetByIdActivityDtoRes>(activity, HttpStatus.OK);		
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<GetAllByUserIdActivityRes> getAllByUserIdActivity(@PathVariable("id") String id) throws Exception{
		GetAllByUserIdActivityRes activity = activityService.getAllByUserIdActivity(id);
		return new ResponseEntity<GetAllByUserIdActivityRes>(activity, HttpStatus.OK);		
	}
	
	@GetMapping
	public ResponseEntity<GetAllActivityDtoRes> getAll(@RequestParam("start") Integer start, @RequestParam("max") Integer max) throws Exception{
		GetAllActivityDtoRes activity = activityService.getAll(start, max);
		return new ResponseEntity<GetAllActivityDtoRes>(activity, HttpStatus.OK);		
	}
	
	@GetMapping("pending")
	public ResponseEntity<GetAllByUserIdActivityRes> getPendingActivity() throws Exception{
		GetAllByUserIdActivityRes activity = activityService.getPendingActivity();
		return new ResponseEntity<GetAllByUserIdActivityRes>(activity, HttpStatus.OK);		
	}
	
	@GetMapping("approved-event")
	public ResponseEntity<GetAllByUserIdActivityRes> getApprovedEvent() throws Exception{
		GetAllByUserIdActivityRes activity = activityService.getApprovedEventActivity();
		return new ResponseEntity<GetAllByUserIdActivityRes>(activity, HttpStatus.OK);		
	}
	
	@GetMapping("approved-course")
	public ResponseEntity<GetAllByUserIdActivityRes> getApprovedCourse() throws Exception{
		GetAllByUserIdActivityRes activity = activityService.getApprovedCourseActivity();
		return new ResponseEntity<GetAllByUserIdActivityRes>(activity, HttpStatus.OK);		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteActivityDtoRes> delete(@PathVariable("id") String id) throws Exception{
		DeleteActivityDtoRes activity = activityService.deleteById(id);
		return new ResponseEntity<DeleteActivityDtoRes>(activity, HttpStatus.OK);		
	}
}
