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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.industry.DeleteIndustryDtoRes;
import com.lawencon.community.dto.industry.GetAllIndustryDtoRes;
import com.lawencon.community.dto.industry.GetByIdIndustryDtoRes;
import com.lawencon.community.dto.industry.InsertIndustryDtoReq;
import com.lawencon.community.dto.industry.InsertIndustryDtoRes;
import com.lawencon.community.dto.industry.UpdateIndustryDtoReq;
import com.lawencon.community.dto.industry.UpdateIndustryDtoRes;
import com.lawencon.community.service.IndustryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("industries")
@RequiredArgsConstructor
public class IndustryController {

	private final IndustryService industryService;
	
	@PostMapping
	public ResponseEntity<InsertIndustryDtoRes> insert(@RequestPart @Valid InsertIndustryDtoReq data) throws Exception{
		InsertIndustryDtoRes industry = industryService.insert(data);
		return new ResponseEntity<InsertIndustryDtoRes>(industry, HttpStatus.CREATED);		
	}
	
	@PutMapping
	public ResponseEntity<UpdateIndustryDtoRes> update(@RequestPart @Valid UpdateIndustryDtoReq data) throws Exception{
		UpdateIndustryDtoRes industry = industryService.update(data);
		return new ResponseEntity<UpdateIndustryDtoRes>(industry, HttpStatus.OK);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdIndustryDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdIndustryDtoRes industry = industryService.getById(id);
		return new ResponseEntity<GetByIdIndustryDtoRes>(industry, HttpStatus.OK);		
	}
	
	@GetMapping
	public ResponseEntity<GetAllIndustryDtoRes> getAll() throws Exception{
		GetAllIndustryDtoRes industry = industryService.getAll();
		return new ResponseEntity<GetAllIndustryDtoRes>(industry, HttpStatus.OK);		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteIndustryDtoRes> delete(@PathVariable("id") String id) throws Exception{
		DeleteIndustryDtoRes industry = industryService.deleteById(id);
		return new ResponseEntity<DeleteIndustryDtoRes>(industry, HttpStatus.OK);		
	}
}
