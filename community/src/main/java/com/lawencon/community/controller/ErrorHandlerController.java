package com.lawencon.community.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		Map<String, Object> maps = new HashMap<String, Object>();
		List<String> errors = new ArrayList<String>();
		List<ObjectError> objError = e.getBindingResult().getAllErrors();
		
		for (ObjectError obj : objError) {
			errors.add(obj.getDefaultMessage());
		}
		maps.put("msg", errors);
		e.printStackTrace();
		return new ResponseEntity<>(maps, HttpStatus.BAD_REQUEST);		
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> constraintViolationExceptionHandler(RuntimeException e) {
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("msg", NestedExceptionUtils.getMostSpecificCause(e).getMessage());
		e.printStackTrace();
		return new ResponseEntity<>(maps, HttpStatus.BAD_REQUEST);		
	}
}
