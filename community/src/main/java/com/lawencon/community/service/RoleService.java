package com.lawencon.community.service;

import org.springframework.stereotype.Service;

import com.lawencon.base.BaseService;
import com.lawencon.community.dao.RoleDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService extends BaseService {
	
	private final RoleDao roleDao;
	
}
