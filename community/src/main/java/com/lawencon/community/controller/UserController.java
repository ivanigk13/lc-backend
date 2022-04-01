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

import com.lawencon.community.dto.user.DeleteUserDtoRes;
import com.lawencon.community.dto.user.ForgotPasswordReq;
import com.lawencon.community.dto.user.ForgotPasswordRes;
import com.lawencon.community.dto.user.GetAllUserDtoRes;
import com.lawencon.community.dto.user.GetByIdUserDtoRes;
import com.lawencon.community.dto.user.InsertUserDtoReq;
import com.lawencon.community.dto.user.InsertUserDtoRes;
import com.lawencon.community.dto.user.UpdateUserDtoReq;
import com.lawencon.community.dto.user.UpdateUserDtoRes;
import com.lawencon.community.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@GetMapping
	public ResponseEntity<GetAllUserDtoRes> getAll(
							@RequestParam(value = "start", required = false) Integer start,
							@RequestParam(value = "max", required = false) Integer max) throws Exception{
		GetAllUserDtoRes result = userService.getAll(start, max);
		return new ResponseEntity<GetAllUserDtoRes>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdUserDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdUserDtoRes result = userService.getById(id);
		return new ResponseEntity<GetByIdUserDtoRes>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertUserDtoRes> insert(@RequestBody @Valid InsertUserDtoReq user) throws Exception{
		InsertUserDtoRes result = userService.insert(user);
		return new ResponseEntity<InsertUserDtoRes>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateUserDtoRes> update(@RequestBody @Valid UpdateUserDtoReq user) throws Exception{
		UpdateUserDtoRes result = userService.update(user);
		return new ResponseEntity<UpdateUserDtoRes>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteUserDtoRes> delete(@PathVariable("id") String id) throws Exception{
		DeleteUserDtoRes result = userService.deleteById(id);
		return new ResponseEntity<DeleteUserDtoRes>(result, HttpStatus.OK);
	}
	
	@PutMapping("forgot-password")
	public ResponseEntity<ForgotPasswordRes> forgotPass(@RequestBody @Valid ForgotPasswordReq email) throws Exception{
		ForgotPasswordRes result = userService.forgotPassword(email);
		return new ResponseEntity<ForgotPasswordRes>(result, HttpStatus.OK);
	}
}
