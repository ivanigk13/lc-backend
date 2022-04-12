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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.transactionstatus.DeleteTransactionStatusDtoRes;
import com.lawencon.community.dto.transactionstatus.GetAllTransactionStatusDtoRes;
import com.lawencon.community.dto.transactionstatus.GetByIdTransactionStatusDtoRes;
import com.lawencon.community.dto.transactionstatus.InsertTransactionStatusDtoReq;
import com.lawencon.community.dto.transactionstatus.InsertTransactionStatusDtoRes;
import com.lawencon.community.dto.transactionstatus.UpdateTransactionStatusDtoReq;
import com.lawencon.community.dto.transactionstatus.UpdateTransactionStatusDtoRes;
import com.lawencon.community.service.TransactionStatusService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("transaction-statuses")
@RequiredArgsConstructor
public class TransactionStatusController {
	
	private final TransactionStatusService transactionStatusService;
	
	@GetMapping
	public ResponseEntity<GetAllTransactionStatusDtoRes> getAll(String query, Integer start, Integer max) throws Exception{
		GetAllTransactionStatusDtoRes transactionStatus = transactionStatusService.getAll(query, start, max);
		return new ResponseEntity<GetAllTransactionStatusDtoRes>(transactionStatus, HttpStatus.OK);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdTransactionStatusDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByIdTransactionStatusDtoRes transactionStatus = transactionStatusService.getById(id);
		return new ResponseEntity<GetByIdTransactionStatusDtoRes>(transactionStatus, HttpStatus.OK);		
	}
	
	@GetMapping("approve")
	public ResponseEntity<String> getApproveId() throws Exception{
		String transactionStatus = transactionStatusService.getApprovedId();
		return new ResponseEntity<String>(transactionStatus, HttpStatus.OK);		
	}
	
	@GetMapping("reject")
	public ResponseEntity<String> getRejectId() throws Exception{
		String transactionStatus = transactionStatusService.getRejectId();
		return new ResponseEntity<String>(transactionStatus, HttpStatus.OK);		
	}
	
	@PostMapping
	public ResponseEntity<InsertTransactionStatusDtoRes> insert(@RequestPart @Valid InsertTransactionStatusDtoReq data) throws Exception{
		InsertTransactionStatusDtoRes transactionStatus = transactionStatusService.insert(data);
		return new ResponseEntity<InsertTransactionStatusDtoRes>(transactionStatus, HttpStatus.CREATED);		
	}
	
	@PutMapping
	public ResponseEntity<UpdateTransactionStatusDtoRes> update(@RequestPart @Valid UpdateTransactionStatusDtoReq data) throws Exception{
		UpdateTransactionStatusDtoRes transactionStatus = transactionStatusService.update(data);
		return new ResponseEntity<UpdateTransactionStatusDtoRes>(transactionStatus, HttpStatus.OK);		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteTransactionStatusDtoRes> delete(@PathVariable("id") String id) throws Exception{
		DeleteTransactionStatusDtoRes transactionStatus = transactionStatusService.deleteById(id);
		return new ResponseEntity<DeleteTransactionStatusDtoRes>(transactionStatus, HttpStatus.OK);		
	}	
}
