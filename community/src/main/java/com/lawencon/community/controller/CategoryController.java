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

import com.lawencon.community.dto.category.DeleteCategoryDtoRes;
import com.lawencon.community.dto.category.GetAllCategoryDtoRes;
import com.lawencon.community.dto.category.GetByIdCategoryDtoRes;
import com.lawencon.community.dto.category.InsertCategoryDtoReq;
import com.lawencon.community.dto.category.InsertCategoryDtoRes;
import com.lawencon.community.dto.category.UpdateCategoryDtoReq;
import com.lawencon.community.dto.category.UpdateCategoryDtoRes;
import com.lawencon.community.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<InsertCategoryDtoRes> insert(@RequestBody @Valid InsertCategoryDtoReq data) throws Exception{
		InsertCategoryDtoRes category = categoryService.insert(data);
		return new ResponseEntity<InsertCategoryDtoRes>(category, HttpStatus.CREATED);		
	}
	
	@PutMapping
	public ResponseEntity<UpdateCategoryDtoRes> update(@RequestBody @Valid UpdateCategoryDtoReq data) throws Exception{
		UpdateCategoryDtoRes category = categoryService.update(data);
		return new ResponseEntity<UpdateCategoryDtoRes>(category, HttpStatus.OK);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdCategoryDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdCategoryDtoRes category = categoryService.getById(id);
		return new ResponseEntity<GetByIdCategoryDtoRes>(category, HttpStatus.OK);		
	}
	
	@GetMapping()
	public ResponseEntity<GetAllCategoryDtoRes> getAll(
							@RequestParam(value = "start", required = false) Integer start,
							@RequestParam(value = "max", required = false) Integer max) throws Exception{
		GetAllCategoryDtoRes category = categoryService.getAll(start, max);
		return new ResponseEntity<GetAllCategoryDtoRes>(category, HttpStatus.OK);		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteCategoryDtoRes> delete(@PathVariable("id") String id) throws Exception{
		DeleteCategoryDtoRes category = categoryService.deleteById(id);
		return new ResponseEntity<DeleteCategoryDtoRes>(category, HttpStatus.OK);		
	}
}
