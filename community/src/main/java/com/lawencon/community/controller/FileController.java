package com.lawencon.community.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.model.File;
import com.lawencon.community.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("files")
@RequiredArgsConstructor
public class FileController {

	private final FileService fileService;
	
	@GetMapping("{id}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable("id") String id){
		File file = fileService.getById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=attachment." + file.getExtensionName());

        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_OCTET_STREAM).body(file.getContent());
	}
}
