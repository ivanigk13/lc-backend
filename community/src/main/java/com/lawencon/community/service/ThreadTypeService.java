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
	
	public InsertThreadTypeDtoRes insert(InsertThreadTypeDtoReq data) throws Exception {
		ThreadType threadType = new ThreadType();
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

	public GetByIdThreadTypeDtoRes getById(String id) throws Exception {
		ThreadType threadType = threadTypeDao.getById(id);
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
	
	public GetAllThreadTypeDtoRes getAll() throws Exception {
		List<ThreadType> threadTypes = threadTypeDao.getAll();
		
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
	
	public DeleteThreadTypeDtoRes deleteById(String id) throws Exception {
		begin();
		threadTypeDao.deleteById(id);
		commit();
		
		DeleteThreadTypeDtoRes threadTypeRes = new DeleteThreadTypeDtoRes();
		threadTypeRes.setMsg("Delete Successfully");
		
		return threadTypeRes;
	}
}
