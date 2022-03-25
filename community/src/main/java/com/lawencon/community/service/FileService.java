package com.lawencon.community.service;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.FileDao;
import com.lawencon.community.model.File;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService extends BaseCommunityService{
	
	private final FileDao fileDao;
	
	public File getById(String id) {
		return fileDao.getById(id);
	}

}
