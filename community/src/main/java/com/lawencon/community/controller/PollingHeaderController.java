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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.pollingheader.DeletePollingHeaderDtoRes;
import com.lawencon.community.dto.pollingheader.GetAllPollingHeaderDtoRes;
import com.lawencon.community.dto.pollingheader.GetByIdPollingHeaderDtoRes;
import com.lawencon.community.dto.pollingheader.InsertPollingHeaderDtoReq;
import com.lawencon.community.dto.pollingheader.InsertPollingHeaderDtoRes;
import com.lawencon.community.service.PollingHeaderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("polling-headers")
@RequiredArgsConstructor
public class PollingHeaderController {

	private final PollingHeaderService pollingHeaderService;

	@GetMapping
	public ResponseEntity<GetAllPollingHeaderDtoRes> getAll(@RequestParam("start") Integer start, @RequestParam("max") Integer max) throws Exception {
		GetAllPollingHeaderDtoRes result = pollingHeaderService.getAll(start, max);
		return new ResponseEntity<GetAllPollingHeaderDtoRes>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByIdPollingHeaderDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByIdPollingHeaderDtoRes result = pollingHeaderService.getById(id);
		return new ResponseEntity<GetByIdPollingHeaderDtoRes>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertPollingHeaderDtoRes> insert(@RequestBody @Valid InsertPollingHeaderDtoReq pollingDetail)
			throws Exception {
		InsertPollingHeaderDtoRes result = pollingHeaderService.insert(pollingDetail);
		return new ResponseEntity<InsertPollingHeaderDtoRes>(result, HttpStatus.CREATED);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeletePollingHeaderDtoRes> delete(@PathVariable("id") String id) throws Exception {
		DeletePollingHeaderDtoRes result = pollingHeaderService.deleteById(id);
		return new ResponseEntity<DeletePollingHeaderDtoRes>(result, HttpStatus.OK);
	}
}