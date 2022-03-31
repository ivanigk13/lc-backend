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

import com.lawencon.community.dto.socialmedia.GetByIdSocialMediaDtoRes;
import com.lawencon.community.dto.socialmedia.InsertSocialMediaDtoReq;
import com.lawencon.community.dto.socialmedia.InsertSocialMediaDtoRes;
import com.lawencon.community.dto.socialmedia.UpdateSocialMediaDtoReq;
import com.lawencon.community.dto.socialmedia.UpdateSocialMediaDtoRes;
import com.lawencon.community.service.SocialMediaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("social-media")
@RequiredArgsConstructor
public class SocialMediaController {
	
	private final SocialMediaService socialMediaService;
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdSocialMediaDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdSocialMediaDtoRes socialMedia = socialMediaService.getByUserId(id);
		return new ResponseEntity<GetByIdSocialMediaDtoRes>(socialMedia, HttpStatus.OK);		
	}
	
	@PostMapping
	public ResponseEntity<InsertSocialMediaDtoRes> insert(@RequestBody @Valid InsertSocialMediaDtoReq data) throws Exception{
		InsertSocialMediaDtoRes socialMedia = socialMediaService.insert(data);
		return new ResponseEntity<InsertSocialMediaDtoRes>(socialMedia, HttpStatus.CREATED);		
	}
	
	@PutMapping
	public ResponseEntity<UpdateSocialMediaDtoRes> update(@RequestBody @Valid UpdateSocialMediaDtoReq data) throws Exception{
		UpdateSocialMediaDtoRes socialMedia = socialMediaService.update(data);
		return new ResponseEntity<UpdateSocialMediaDtoRes>(socialMedia, HttpStatus.OK);		
	}
}
