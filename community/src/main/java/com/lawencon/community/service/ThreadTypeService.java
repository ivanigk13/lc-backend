package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ThreadTypeDao;
import com.lawencon.community.dto.threadtype.DeleteThreadTypeDtoRes;
import com.lawencon.community.dto.threadtype.GetAllThreadTypeDtoRes;
import com.lawencon.community.dto.threadtype.GetByIdThreadTypeDtoRes;
import com.lawencon.community.dto.threadtype.GetThreadTypeDtoDataRes;
import com.lawencon.community.dto.threadtype.InsertThreadTypeDtoDataRes;
import com.lawencon.community.dto.threadtype.InsertThreadTypeDtoReq;
import com.lawencon.community.dto.threadtype.InsertThreadTypeDtoRes;
import com.lawencon.community.dto.threadtype.UpdateThreadTypeDtoDataRes;
import com.lawencon.community.dto.threadtype.UpdateThreadTypeDtoReq;
import com.lawencon.community.dto.threadtype.UpdateThreadTypeDtoRes;
import com.lawencon.community.model.ThreadType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThreadTypeService extends BaseCommunityService {
	
	private final ThreadTypeDao threadTypeDao;
	
	public GetAllThreadTypeDtoRes getAll(Integer start, Integer max) throws Exception {
		List<ThreadType> threadTypes;
		if(start==null) threadTypes = threadTypeDao.getAll();
		else threadTypes = threadTypeDao.getAll(start, max);
		
		List<GetThreadTypeDtoDataRes> data = new ArrayList<GetThreadTypeDtoDataRes>();
		
		threadTypes.forEach(threadType -> {
			GetThreadTypeDtoDataRes threadTypeDataRes = new GetThreadTypeDtoDataRes();
			threadTypeDataRes.setId(threadType.getId());
			threadTypeDataRes.setThreadTypeCode(threadType.getThreadTypeCode());
			threadTypeDataRes.setThreadTypeName(threadType.getThreadTypeName());
			threadTypeDataRes.setVersion(threadType.getVersion());
			threadTypeDataRes.setIsActive(threadType.getIsActive());
			
			data.add(threadTypeDataRes);
		});
		
		GetAllThreadTypeDtoRes threadTypeRes = new GetAllThreadTypeDtoRes();
		threadTypeRes.setData(data);
		
		return threadTypeRes;
	}

	public GetByIdThreadTypeDtoRes getById(String id) throws Exception {
		ThreadType threadType = threadTypeDao.getById(id);
		if(threadType!=null) {
			GetThreadTypeDtoDataRes threadTypeDataRes = new GetThreadTypeDtoDataRes();
			threadTypeDataRes.setId(threadType.getId());
			threadTypeDataRes.setThreadTypeCode(threadType.getThreadTypeCode());
			threadTypeDataRes.setThreadTypeName(threadType.getThreadTypeName());
			threadTypeDataRes.setVersion(threadType.getVersion());
			threadTypeDataRes.setIsActive(threadType.getIsActive());
			
			GetByIdThreadTypeDtoRes threadTypeRes = new GetByIdThreadTypeDtoRes();
			threadTypeRes.setData(threadTypeDataRes);
			
			return threadTypeRes;
		}
		
		throw new RuntimeException("ThreadType Id doesn't exist");
	}
	
	public InsertThreadTypeDtoRes insert(InsertThreadTypeDtoReq data) throws Exception {
		ThreadType threadType = new ThreadType();
		valBkNotExist(data.getThreadTypeCode(), data.getThreadTypeName());
		threadType.setThreadTypeCode(data.getThreadTypeCode());
		threadType.setThreadTypeName(data.getThreadTypeCode());
		threadType.setCreatedBy(getId());
		
		begin();
		threadType = threadTypeDao.save(threadType);
		commit();
		
		InsertThreadTypeDtoDataRes threadTypeDataRes = new InsertThreadTypeDtoDataRes();
		threadTypeDataRes.setId(threadType.getId());
		
		InsertThreadTypeDtoRes threadTypeRes = new InsertThreadTypeDtoRes();
		threadTypeRes.setMsg("Insert Successfully");
		threadTypeRes.setData(threadTypeDataRes);
		
		return threadTypeRes;
	}
	
	public UpdateThreadTypeDtoRes update(UpdateThreadTypeDtoReq data) throws Exception {
		ThreadType threadType = threadTypeDao.getById(data.getId());
		valBkNotExist(data.getThreadTypeName());
		threadType.setThreadTypeName(data.getThreadTypeName());
		threadType.setUpdatedBy(getId());
		threadType.setVersion(data.getVersion());
		threadType.setIsActive(data.getIsActive());
		
		begin();
		threadType = threadTypeDao.save(threadType);
		commit();
		
		UpdateThreadTypeDtoDataRes threadTypeDataRes = new UpdateThreadTypeDtoDataRes();
		threadTypeDataRes.setVersion(threadType.getVersion());
		
		UpdateThreadTypeDtoRes threadTypeRes = new UpdateThreadTypeDtoRes();
		threadTypeRes.setMsg("Update Successfully");
		threadTypeRes.setData(threadTypeDataRes);
		
		return threadTypeRes;
	}
	
	public DeleteThreadTypeDtoRes deleteById(String id) throws Exception {
		try {			
			begin();
			boolean isDeleted = threadTypeDao.deleteById(id);
			commit();
			
			DeleteThreadTypeDtoRes threadTypeRes = new DeleteThreadTypeDtoRes();
			if(isDeleted) {
				threadTypeRes.setMsg("Delete Successfully");
			} else {
				threadTypeRes.setMsg("Delete Unsuccessfully");
			}
			
			return threadTypeRes;
		}
		catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	private void valBkNotExist(String code, String name) {
		Integer flagCode = threadTypeDao.isThreadTypeCodeExist(code);
		Integer flagName = threadTypeDao.isThreadTypeNameExist(name);
		if(flagCode == 1 || flagName == 1) {
			throw new RuntimeException("ThreadType Code or ThreadType Name has existed");
		}
	}
	
	private void valBkNotExist(String name) {
		Integer flagName = threadTypeDao.isThreadTypeNameExist(name);
		if(flagName == 1) {
			throw new RuntimeException("ThreadType Name has existed");
		}
	}
}
