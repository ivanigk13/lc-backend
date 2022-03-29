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
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.community.dto.profile.DeleteProfileDtoRes;
import com.lawencon.community.dto.profile.GetAllProfileDtoRes;
import com.lawencon.community.dto.profile.GetByIdProfileDtoRes;
import com.lawencon.community.dto.profile.InsertProfileDtoRes;
import com.lawencon.community.dto.profile.UpdateProfileDtoRes;
import com.lawencon.community.service.ProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("profiles")
@RequiredArgsConstructor
public class ProfileController {
	
	private final ProfileService profileService;
	
	@GetMapping
	public ResponseEntity<GetAllProfileDtoRes> getAll(@RequestParam("start") Integer start, @RequestParam("max") Integer max) throws Exception {
		GetAllProfileDtoRes result = profileService.getAll(start, max);
		return new ResponseEntity<GetAllProfileDtoRes>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdProfileDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByIdProfileDtoRes result = profileService.getById(id);
		return new ResponseEntity<GetByIdProfileDtoRes>(result, HttpStatus.OK);
	} 
	
	@PostMapping
	public ResponseEntity<InsertProfileDtoRes> insert(@RequestPart(name="data") @Valid String data, @RequestPart(required = true) MultipartFile file) throws Exception {
		InsertProfileDtoRes result = profileService.insert(data, file);
		return new ResponseEntity<InsertProfileDtoRes>(result, HttpStatus.CREATED);
	}
	

	@PutMapping
	public ResponseEntity<UpdateProfileDtoRes> update(@RequestPart(name="data") String data, @RequestPart(required = true) MultipartFile file) throws Exception {
		UpdateProfileDtoRes result = profileService.update(data, file);
		return new ResponseEntity<UpdateProfileDtoRes>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteProfileDtoRes> delete(@PathVariable("id") String id) throws Exception {
		DeleteProfileDtoRes result = profileService.deleteById(id);
		return new ResponseEntity<DeleteProfileDtoRes>(result, HttpStatus.OK);
	}

}
