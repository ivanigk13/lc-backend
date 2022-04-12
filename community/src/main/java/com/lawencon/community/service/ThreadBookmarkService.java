package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ThreadBookmarkDao;
import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.threadbookmark.DeleteThreadBookmarkDtoRes;
import com.lawencon.community.dto.threadbookmark.GetAllThreadBookmarkDtoRes;
import com.lawencon.community.dto.threadbookmark.GetByIdThreadBookmarkDtoRes;
import com.lawencon.community.dto.threadbookmark.GetThreadBookmarkDtoDataRes;
import com.lawencon.community.dto.threadbookmark.InsertThreadBookmarkDtoDataRes;
import com.lawencon.community.dto.threadbookmark.InsertThreadBookmarkDtoReq;
import com.lawencon.community.dto.threadbookmark.InsertThreadBookmarkDtoRes;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadBookmark;
import com.lawencon.community.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThreadBookmarkService extends BaseCommunityService {

	private final ThreadBookmarkDao threadBookmarkDao;
	private final ThreadDao threadDao;
	private final UserDao userDao;

	public InsertThreadBookmarkDtoRes insert(InsertThreadBookmarkDtoReq data) throws Exception {
		ThreadBookmark threadBookmark = new ThreadBookmark();

		Thread thread = threadDao.getById(data.getThreadId());
		threadBookmark.setThread(thread);

		User user = userDao.getById(data.getUserId());
		threadBookmark.setUser(user);
		threadBookmark.setCreatedBy(getId());
		

		begin();
		ThreadBookmark threadBookmarkInsert = threadBookmarkDao.save(threadBookmark);
		commit();
		
		InsertThreadBookmarkDtoDataRes threadBookmarkId = new InsertThreadBookmarkDtoDataRes();
		threadBookmarkId.setId(threadBookmarkInsert.getId());

		InsertThreadBookmarkDtoRes result = new InsertThreadBookmarkDtoRes();
		result.setData(threadBookmarkId);
		result.setMsg("Insert Successfully");
		
		return result;
	}

	public GetAllThreadBookmarkDtoRes getAll(Integer start, Integer max) throws Exception {
		List<ThreadBookmark> threadBookmarks;
		if(start==null) threadBookmarks = threadBookmarkDao.getAll();
		else threadBookmarks = threadBookmarkDao.getAll(start, max);
		
		List<GetThreadBookmarkDtoDataRes> data = new ArrayList<>();

		threadBookmarks.forEach(list -> {
			GetThreadBookmarkDtoDataRes threadBookmark = new GetThreadBookmarkDtoDataRes();
			threadBookmark.setId(list.getId());
			threadBookmark.setThreadId(list.getThread().getId());
			threadBookmark.setUserId(list.getUser().getId());
			threadBookmark.setVersion(list.getVersion());
			threadBookmark.setIsActive(list.getIsActive());
			data.add(threadBookmark);
		});

		GetAllThreadBookmarkDtoRes result = new GetAllThreadBookmarkDtoRes();
		result.setData(data);

		return result;
	}
	
	public GetAllThreadBookmarkDtoRes getAllThreadBookmarkByUserId(String id) throws Exception {
		List<ThreadBookmark> threadBookmarks = threadBookmarkDao.getThreadBookmarkByUserId(id);				
		List<GetThreadBookmarkDtoDataRes> data = new ArrayList<>();

		threadBookmarks.forEach(list -> {
			GetThreadBookmarkDtoDataRes threadBookmark = new GetThreadBookmarkDtoDataRes();
			threadBookmark.setId(list.getId());
			threadBookmark.setThreadId(list.getThread().getId());
			threadBookmark.setUserId(list.getUser().getId());
			threadBookmark.setVersion(list.getVersion());
			threadBookmark.setIsActive(list.getIsActive());
			data.add(threadBookmark);
		});

		GetAllThreadBookmarkDtoRes result = new GetAllThreadBookmarkDtoRes();
		result.setData(data);

		return result;
	}

	public GetByIdThreadBookmarkDtoRes getById(String id) throws Exception {
		ThreadBookmark threadBookmark = threadBookmarkDao.getById(id);

		GetThreadBookmarkDtoDataRes threadBookmarkData = new GetThreadBookmarkDtoDataRes();
		threadBookmarkData.setId(threadBookmark.getId());
		threadBookmarkData.setThreadId(threadBookmark.getThread().getId());
		threadBookmarkData.setUserId(threadBookmark.getUser().getId());
		threadBookmarkData.setVersion(threadBookmark.getVersion());
		threadBookmarkData.setIsActive(threadBookmark.getIsActive());

		GetByIdThreadBookmarkDtoRes result = new GetByIdThreadBookmarkDtoRes();
		result.setData(threadBookmarkData);

		return result;
	}
	
	public GetByIdThreadBookmarkDtoRes getThreadBookmarkByThreadId(String id) throws Exception {
		ThreadBookmark threadBookmark = threadBookmarkDao.getThreadBookmarkByThreadId(id);
		GetThreadBookmarkDtoDataRes threadBookmarkData = new GetThreadBookmarkDtoDataRes();
		threadBookmarkData.setId(threadBookmark.getId());
		threadBookmarkData.setThreadId(threadBookmark.getThread().getId());
		threadBookmarkData.setUserId(threadBookmark.getUser().getId());
		threadBookmarkData.setVersion(threadBookmark.getVersion());
		threadBookmarkData.setIsActive(threadBookmark.getIsActive());

		GetByIdThreadBookmarkDtoRes result = new GetByIdThreadBookmarkDtoRes();
		result.setData(threadBookmarkData);

		return result;
	}
	
	public DeleteThreadBookmarkDtoRes deleteById(String id) throws Exception {
		try {			
			begin();
			boolean isDeleted = threadBookmarkDao.deleteById(id);
			commit();
			
			DeleteThreadBookmarkDtoRes threadBookmarkRes = new DeleteThreadBookmarkDtoRes();
			if(isDeleted) {
				threadBookmarkRes.setMsg("Delete Successfully");
			} else {
				threadBookmarkRes.setMsg("Delete Unsuccessfully");
			}
			
			return threadBookmarkRes;
		}
		catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

}
