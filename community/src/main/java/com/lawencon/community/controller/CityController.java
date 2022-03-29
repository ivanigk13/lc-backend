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

import com.lawencon.community.dto.city.DeleteCityDtoRes;
import com.lawencon.community.dto.city.GetAllCityDtoRes;
import com.lawencon.community.dto.city.GetByIdCityDtoRes;
import com.lawencon.community.dto.city.InsertCityDtoReq;
import com.lawencon.community.dto.city.InsertCityDtoRes;
import com.lawencon.community.dto.city.UpdateCityDtoReq;
import com.lawencon.community.dto.city.UpdateCityDtoRes;
import com.lawencon.community.service.CityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("cities")
@RequiredArgsConstructor
public class CityController {

	private final CityService cityService;
	
	@PostMapping
	public ResponseEntity<InsertCityDtoRes> insert(@RequestPart @Valid InsertCityDtoReq data) throws Exception{
		InsertCityDtoRes city = cityService.insert(data);
		return new ResponseEntity<InsertCityDtoRes>(city, HttpStatus.CREATED);		
	}
	
	@PutMapping
	public ResponseEntity<UpdateCityDtoRes> update(@RequestPart @Valid UpdateCityDtoReq data) throws Exception{
		UpdateCityDtoRes city = cityService.update(data);
		return new ResponseEntity<UpdateCityDtoRes>(city, HttpStatus.OK);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdCityDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdCityDtoRes city = cityService.getById(id);
		return new ResponseEntity<GetByIdCityDtoRes>(city, HttpStatus.OK);		
	}
	
	@GetMapping
	public ResponseEntity<GetAllCityDtoRes> getAll(
							@RequestParam(value = "start", required = false) Integer start,
							@RequestParam(value = "max", required = false) Integer max) throws Exception{
		GetAllCityDtoRes city = cityService.getAll(start, max);
		return new ResponseEntity<GetAllCityDtoRes>(city, HttpStatus.OK);		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteCityDtoRes> delete(@PathVariable("id") String id) throws Exception{
		DeleteCityDtoRes city = cityService.deleteById(id);
		return new ResponseEntity<DeleteCityDtoRes>(city, HttpStatus.OK);		
	}
	
}
