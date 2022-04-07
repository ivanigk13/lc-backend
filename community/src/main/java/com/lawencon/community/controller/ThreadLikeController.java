package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.threadlike.GetByIdThreadLikeDtoRes;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoReq;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoRes;
import com.lawencon.community.dto.threadlike.UpdateThreadLikeDtoReq;
import com.lawencon.community.dto.threadlike.UpdateThreadLikeDtoRes;
import com.lawencon.community.service.ThreadLikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("thread-likes")
@RequiredArgsConstructor
public class ThreadLikeController {

	private final ThreadLikeService threadLikeService;
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdThreadLikeDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdThreadLikeDtoRes threadLike = threadLikeService.getById(id);
		return new ResponseEntity<GetByIdThreadLikeDtoRes>(threadLike, HttpStatus.OK);		
	}
	
	@PostMapping
	public ResponseEntity<InsertThreadLikeDtoRes> insert(@RequestBody @Valid InsertThreadLikeDtoReq data) throws Exception{
		InsertThreadLikeDtoRes threadLike = threadLikeService.insert(data);
		return new ResponseEntity<InsertThreadLikeDtoRes>(threadLike, HttpStatus.CREATED);		
	}
	
	@PutMapping
	public ResponseEntity<UpdateThreadLikeDtoRes> update(@RequestBody @Valid UpdateThreadLikeDtoReq data) throws Exception{
		UpdateThreadLikeDtoRes threadLike = threadLikeService.update(data);
		return new ResponseEntity<UpdateThreadLikeDtoRes>(threadLike, HttpStatus.CREATED);		
	}
	
	@GetMapping("thread/{id}")
	public ResponseEntity<Integer> getLikeCounterByThreadId(@PathVariable("id") String id) throws Exception{
		Integer counter = threadLikeService.getLikeCounterByThreadId(id);
		return new ResponseEntity<Integer>(counter, HttpStatus.OK);		
	}
	
	@GetMapping("thread/{threadId}/{userId}")
	ResponseEntity<Integer> isUserLikeByThreadId(
										@PathVariable("threadId") String threadId, 
										@PathVariable("userId") String userId) throws Exception{
		Integer counter = threadLikeService.isUserLikeByThreadId(threadId, userId);
		return new ResponseEntity<Integer>(counter, HttpStatus.OK);	
	}
	
}
