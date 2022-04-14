package com.lawencon.community.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.orderdetail.GetIncomeActivityDtoDataRes;
import com.lawencon.community.dto.orderdetail.GetIncomeActivityDtoRes;
import com.lawencon.community.dto.orderdetail.GetParticipantDtoRes;
import com.lawencon.community.service.OrderDetailService;
import com.lawencon.util.JasperUtil;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("report")
@RequiredArgsConstructor
public class ReportController {
	
	private final OrderDetailService orderDetailService;
	
	@GetMapping("activity/{id}")
	public ResponseEntity<?> getReportActivityById(@PathVariable("id") String id) throws Exception, JRException {
		GetParticipantDtoRes res = orderDetailService.getParticipant(id);
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		byte[] data = JasperUtil.responseToByteArray(res.getData(), "ReportMemberJoinActivity", map);
		
		String fileName = "Participant " + res.getData().get(0).getActivityTypeName() + " " +res.getData().get(0).getActivityName() + ".pdf";
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "file; filename=\"" + fileName + "\"").contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("activity-income/{id}")
	public ResponseEntity<?> getReportIncomeByActivityId(@PathVariable("id") String id) throws Exception, JRException {
		List<GetIncomeActivityDtoDataRes> res = orderDetailService.getIncome(id);
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		byte[] data = JasperUtil.responseToByteArray(res, "ReportMemberIncomeActivity", map);
		
		String fileName = "Income " + res.get(0).getActivityTypeName() + " " +res.get(0).getActivityName() + ".pdf";
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "file; filename=\"" + fileName + "\"").contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("activity")
	public ResponseEntity<?> getReportActivity() throws Exception, JRException {
		GetParticipantDtoRes res = orderDetailService.getAllParticipant();
		
		Map<String, Object> map = new HashMap<>();
		byte[] data = JasperUtil.responseToByteArray(res.getData(), "ReportAdminActivity", map);
		
		String fileName = "Report Participant Activity.pdf";
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "file; filename=\"" + fileName + "\"").contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("activity-income")
	public ResponseEntity<?> getReportIncome() throws Exception, JRException {
		GetIncomeActivityDtoRes res = orderDetailService.getAllIncome();
		
		Map<String, Object> map = new HashMap<>();
		byte[] data = JasperUtil.responseToByteArray(res.getData(), "ReportAdminIncomeActivity", map);
		
		String fileName = "Report Income Activity.pdf";
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "file; filename=\"" + fileName + "\"").contentType(MediaType.APPLICATION_PDF).body(data);
	}
}
