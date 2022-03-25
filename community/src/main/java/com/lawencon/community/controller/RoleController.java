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

import com.lawencon.community.dto.role.DeleteRoleDtoRes;
import com.lawencon.community.dto.role.GetAllRoleDtoRes;
import com.lawencon.community.dto.role.GetByIdRoleDtoRes;
import com.lawencon.community.dto.role.InsertRoleDtoReq;
import com.lawencon.community.dto.role.InsertRoleDtoRes;
import com.lawencon.community.dto.role.UpdateRoleDtoReq;
import com.lawencon.community.dto.role.UpdateRoleDtoRes;
import com.lawencon.community.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
public class RoleController {
	
	private final RoleService roleService;

	@GetMapping
	public ResponseEntity<GetAllRoleDtoRes> getAll(
							@RequestParam("start") Integer start,
							@RequestParam("max") Integer max) throws Exception{
		GetAllRoleDtoRes result = roleService.getAll(start,max);
		return new ResponseEntity<GetAllRoleDtoRes>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdRoleDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdRoleDtoRes result = roleService.getById(id);
		return new ResponseEntity<GetByIdRoleDtoRes>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertRoleDtoRes> insert(@RequestBody @Valid InsertRoleDtoReq role) throws Exception{
		InsertRoleDtoRes result = roleService.insert(role);
		return new ResponseEntity<InsertRoleDtoRes>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateRoleDtoRes> update(@RequestBody @Valid UpdateRoleDtoReq role) throws Exception{
		UpdateRoleDtoRes result = roleService.update(role);
		return new ResponseEntity<UpdateRoleDtoRes>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRoleDtoRes> delete(@PathVariable("id") String id) throws Exception{
		DeleteRoleDtoRes result = roleService.deleteById(id);
		return new ResponseEntity<DeleteRoleDtoRes>(result, HttpStatus.OK);
	}

}
