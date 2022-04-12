package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.threadbookmark.DeleteThreadBookmarkDtoRes;
import com.lawencon.community.dto.threadbookmark.GetAllThreadBookmarkDtoRes;
import com.lawencon.community.dto.threadbookmark.GetByIdThreadBookmarkDtoRes;
import com.lawencon.community.dto.threadbookmark.InsertThreadBookmarkDtoReq;
import com.lawencon.community.dto.threadbookmark.InsertThreadBookmarkDtoRes;
import com.lawencon.community.service.ThreadBookmarkService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("thread-bookmarks")
@RequiredArgsConstructor
public class ThreadBookmarkController {

	private final ThreadBookmarkService threadBookmarkService;
	
	@GetMapping
	public ResponseEntity<GetAllThreadBookmarkDtoRes> getAll(Integer start, Integer max) throws Exception{
		GetAllThreadBookmarkDtoRes threadBookmark = threadBookmarkService.getAll(start, max);
		return new ResponseEntity<GetAllThreadBookmarkDtoRes>(threadBookmark, HttpStatus.OK);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdThreadBookmarkDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdThreadBookmarkDtoRes threadBookmark = threadBookmarkService.getById(id);
		return new ResponseEntity<GetByIdThreadBookmarkDtoRes>(threadBookmark, HttpStatus.OK);		
	}
	
	@PostMapping
	public ResponseEntity<InsertThreadBookmarkDtoRes> insert(@RequestBody @Valid InsertThreadBookmarkDtoReq data) throws Exception{
		InsertThreadBookmarkDtoRes threadBookmark = threadBookmarkService.insert(data);
		return new ResponseEntity<InsertThreadBookmarkDtoRes>(threadBookmark, HttpStatus.CREATED);		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteThreadBookmarkDtoRes> delete(@PathVariable("id") String id) throws Exception{
		DeleteThreadBookmarkDtoRes threadBookmark = threadBookmarkService.deleteById(id);
		return new ResponseEntity<DeleteThreadBookmarkDtoRes>(threadBookmark, HttpStatus.OK);		
	}
}
