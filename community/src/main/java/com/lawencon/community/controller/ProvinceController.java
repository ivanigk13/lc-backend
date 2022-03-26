package com.lawencon.community.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.province.GetAllProvinceDtoRes;
import com.lawencon.community.dto.province.GetByIdProvinceDtoRes;
import com.lawencon.community.service.ProvinceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("provinces")
@RequiredArgsConstructor
public class ProvinceController {
	
	private ProvinceService provinceService;
	
	@GetMapping
	public ResponseEntity<GetAllProvinceDtoRes> getAll(@RequestParam("start") Integer start, @RequestParam("max") Integer max) throws Exception {
		GetAllProvinceDtoRes result = provinceService.getAll(start, max);
		return new ResponseEntity<GetAllProvinceDtoRes>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdProvinceDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByIdProvinceDtoRes result = provinceService.getById(id);
		return new ResponseEntity<GetByIdProvinceDtoRes>(result, HttpStatus.OK);
	} 

}
