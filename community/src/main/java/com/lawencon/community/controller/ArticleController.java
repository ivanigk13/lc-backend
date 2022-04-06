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

import com.lawencon.community.dto.article.DeleteArticleDtoRes;
import com.lawencon.community.dto.article.GetAllArticleDtoRes;
import com.lawencon.community.dto.article.GetByIdArticleDtoRes;
import com.lawencon.community.dto.article.GetLastTwoArticleDtoRes;
import com.lawencon.community.dto.article.InsertArticleDtoRes;
import com.lawencon.community.dto.article.UpdateArticleDtoRes;
import com.lawencon.community.service.ArticleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("articles")
@RequiredArgsConstructor
public class ArticleController {

	private final ArticleService articleService;
	
	@GetMapping
	public ResponseEntity<GetAllArticleDtoRes> getAll(
							@RequestParam(value = "start", required = false) Integer start,
							@RequestParam(value = "max", required = false) Integer max) throws Exception{
		GetAllArticleDtoRes article = articleService.getAll(start, max);
		return new ResponseEntity<GetAllArticleDtoRes>(article, HttpStatus.OK);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdArticleDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdArticleDtoRes article = articleService.getById(id);
		return new ResponseEntity<GetByIdArticleDtoRes>(article, HttpStatus.OK);		
	}
	
	@PostMapping
	public ResponseEntity<InsertArticleDtoRes> insert(
								@RequestPart(name="data") String data, 
								@RequestPart(required = false) MultipartFile file) throws Exception{
		InsertArticleDtoRes article = articleService.insert(data, file);
		return new ResponseEntity<InsertArticleDtoRes>(article, HttpStatus.CREATED);		
	}
	
	@PutMapping
	public ResponseEntity<UpdateArticleDtoRes> update(@RequestPart(name="data") String data, 
													@RequestPart(required = false) MultipartFile file) throws Exception{
		UpdateArticleDtoRes article = articleService.update(data, file);
		return new ResponseEntity<UpdateArticleDtoRes>(article, HttpStatus.OK);		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteArticleDtoRes> delete(@PathVariable("id") String id) throws Exception{
		DeleteArticleDtoRes result = articleService.deleteById(id);
		return new ResponseEntity<DeleteArticleDtoRes>(result, HttpStatus.OK);
	}
	
	@GetMapping("last-two")
	public ResponseEntity<GetLastTwoArticleDtoRes> getLastTwo() throws Exception{
		GetLastTwoArticleDtoRes article = articleService.getLastTwo();
		return new ResponseEntity<GetLastTwoArticleDtoRes>(article, HttpStatus.OK);		
	}
}
