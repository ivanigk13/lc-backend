package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.community.dao.FileDao;
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
import com.lawencon.community.model.File;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThreadService extends BaseCommunityService {

	private final ThreadDao threadDao;
	private final ThreadTypeDao threadTypeDao;
	private final FileDao fileDao;

	public InsertThreadDtoRes insert(String content, MultipartFile file) throws Exception {
		try {
			InsertThreadDtoReq threadReq = new ObjectMapper().readValue(content, InsertThreadDtoReq.class);
			Thread thread = new Thread();
			ThreadType threadType = threadTypeDao.getById(threadReq.getThreadTypeId());
			thread.setThreadType(threadType);
			
			begin();
			if(file != null ) {
				File fileThread = new File();
				String splitter = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, 
						file.getOriginalFilename().length());
				fileThread.setExtensionName(splitter);
				fileThread.setContent(file.getBytes());
				fileThread.setCreatedBy(getId());
				
				fileThread = fileDao.save(fileThread);
				thread.setFile(fileThread);
			}

			thread.setTitle(threadReq.getTitle());
			thread.setContent(threadReq.getContent());
			thread.setCreatedBy(getId());

			Thread threadInsert = threadDao.save(thread);
			commit();
			
			InsertThreadDtoDataRes threadId = new InsertThreadDtoDataRes();
			threadId.setId(threadInsert.getId());

			InsertThreadDtoRes result = new InsertThreadDtoRes();
			result.setData(threadId);
			result.setMsg("Insert Successfully");
			
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}


	public UpdateThreadDtoRes update(UpdateThreadDtoReq data) throws Exception {
		Thread thread = threadDao.getById(data.getId());
		thread.setTitle(data.getTitle());
		thread.setContent(data.getContent());
		thread.setUpdatedBy(getId());
		thread.setVersion(data.getVersion());
		thread.setIsActive(data.getIsActive());

		begin();
		Thread threadUpdate = threadDao.save(thread);
		commit();

		UpdateThreadDtoDataRes threadVersion = new UpdateThreadDtoDataRes();
		threadVersion.setVersion(threadUpdate.getVersion());

		UpdateThreadDtoRes result = new UpdateThreadDtoRes();
		result.setData(threadVersion);
		result.setMsg("Update Successfully");
		return result;
	}

	public GetAllThreadDtoRes getAll(Integer start, Integer max) throws Exception {
		List<Thread> threads;
		if(start==null) threads = threadDao.getAll();
		else threads = threadDao.getAll(start, max);
		
		List<GetThreadDtoDataRes> data = new ArrayList<>();

		threads.forEach(list -> {
			GetThreadDtoDataRes thread = new GetThreadDtoDataRes();
			thread.setId(list.getId());
			thread.setThreadTypeId(list.getThreadType().getId());
			thread.setThreadTypeName(list.getThreadType().getThreadTypeName());
			if(list.getFile()!=null) {
				thread.setFileId(list.getFile().getId());
			}
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
		threadData.setThreadTypeId(thread.getThreadType().getId());
		threadData.setThreadTypeName(thread.getThreadType().getThreadTypeName());
		threadData.setFileId(thread.getFile().getId());
		threadData.setTitle(thread.getTitle());
		threadData.setContent(thread.getContent());
		threadData.setVersion(thread.getVersion());
		threadData.setIsActive(thread.getIsActive());

		GetByIdThreadDtoRes result = new GetByIdThreadDtoRes();
		result.setData(threadData);

		return result;
	}
}
