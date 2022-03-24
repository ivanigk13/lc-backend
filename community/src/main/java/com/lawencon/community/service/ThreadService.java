package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.base.BaseService;
import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.dao.ThreadTypeDao;
import com.lawencon.community.dto.thread.GetAllThreadDtoRes;
import com.lawencon.community.dto.thread.GetByIdThreadDtoRes;
import com.lawencon.community.dto.thread.GetThreadDtoDataRes;
import com.lawencon.community.dto.thread.InsertThreadDtoDataRes;
import com.lawencon.community.dto.thread.InsertThreadDtoReq;
import com.lawencon.community.dto.thread.InsertThreadDtoRes;
import com.lawencon.community.dto.thread.UpdateThreadDtoDataRes;
import com.lawencon.community.dto.thread.UpdateThreadDtoReq;
import com.lawencon.community.dto.thread.UpdateThreadDtoRes;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThreadService extends BaseService {

	private final ThreadDao threadDao;
	private final ThreadTypeDao threadTypeDao;

	public InsertThreadDtoRes insert(InsertThreadDtoReq data) throws Exception {
		Thread thread = new Thread();
		ThreadType threadType = threadTypeDao.getById(data.getThreadTypeId());
		thread.setThreadType(threadType);

		thread.setTitle(data.getTitle());
		thread.setContent(data.getContent());

		begin();
		Thread threadInsert = threadDao.save(thread);
		InsertThreadDtoDataRes threadId = new InsertThreadDtoDataRes();
		threadId.setId(threadInsert.getId());

		InsertThreadDtoRes result = new InsertThreadDtoRes();
		result.setData(threadId);
		result.setMsg("Insert Successfully");
		commit();
		return result;
	}

	public UpdateThreadDtoRes update(UpdateThreadDtoReq data) throws Exception {
		Thread thread = threadDao.getById(data.getId());
		thread.setTitle(data.getTitle());
		thread.setContent(data.getContent());
		thread.setVersion(data.getVersion());
		thread.setIsActive(data.getIsActive());

		begin();
		Thread threadUpdate = threadDao.save(thread);

		UpdateThreadDtoDataRes threadVersion = new UpdateThreadDtoDataRes();
		threadVersion.setVersion(threadUpdate.getVersion());

		UpdateThreadDtoRes result = new UpdateThreadDtoRes();
		result.setData(threadVersion);
		result.setMsg("Update Successfully");
		return result;
	}

	public GetAllThreadDtoRes getAll() throws Exception {
		List<Thread> categories = threadDao.getAll();
		List<GetThreadDtoDataRes> data = new ArrayList<>();

		categories.forEach(list -> {
			GetThreadDtoDataRes thread = new GetThreadDtoDataRes();
			thread.setId(list.getId());
			thread.setTitle(list.getTitle());
			thread.setContent(list.getContent());
			thread.setVersion(list.getVersion());
			thread.setIsActive(list.getIsActive());
			data.add(thread);
		});

		GetAllThreadDtoRes result = new GetAllThreadDtoRes();
		result.setData(data);

		return result;
	}

	public GetByIdThreadDtoRes getById(String id) throws Exception {
		Thread thread = threadDao.getById(id);

		GetThreadDtoDataRes threadData = new GetThreadDtoDataRes();
		threadData.setId(thread.getId());
		threadData.setTitle(thread.getTitle());
		threadData.setContent(thread.getContent());
		threadData.setVersion(thread.getVersion());
		threadData.setIsActive(thread.getIsActive());

		GetByIdThreadDtoRes result = new GetByIdThreadDtoRes();
		result.setData(threadData);

		return result;
	}
}
