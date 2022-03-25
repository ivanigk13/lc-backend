package com.lawencon.community.service;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.dao.ThreadLikeDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.threadlike.GetByIdThreadLikeDtoRes;
import com.lawencon.community.dto.threadlike.GetThreadLikeDtoDataRes;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoDataRes;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoReq;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoRes;
import com.lawencon.community.dto.threadlike.UpdateThreadLikeDtoDataRes;
import com.lawencon.community.dto.threadlike.UpdateThreadLikeDtoReq;
import com.lawencon.community.dto.threadlike.UpdateThreadLikeDtoRes;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadLike;
import com.lawencon.community.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThreadLikeService extends BaseCommunityService{
	
	private final ThreadLikeDao threadLikeDao;
	private final ThreadDao threadDao;
	private final UserDao userDao;
	
	public InsertThreadLikeDtoRes insert(InsertThreadLikeDtoReq data) throws Exception {
		ThreadLike threadLike = new ThreadLike();
		Thread thread = threadDao.getById(data.getThreadId());
		threadLike.setThread(thread);
		
		User user = userDao.getById(data.getUserId());
		threadLike.setUser(user);		
		threadLike.setLikeCounter(data.getLikeCounter());
		threadLike.setCreatedBy(getId());
		
		begin();
		ThreadLike threadLikeInsert = threadLikeDao.save(threadLike);
		InsertThreadLikeDtoDataRes threadLikeId = new InsertThreadLikeDtoDataRes();
		threadLikeId.setId(threadLikeInsert.getId());

		InsertThreadLikeDtoRes result = new InsertThreadLikeDtoRes();
		result.setData(threadLikeId);
		result.setMsg("Insert Successfully");
		commit();
		return result;
	}

	public UpdateThreadLikeDtoRes update(UpdateThreadLikeDtoReq data) throws Exception {
		ThreadLike threadLike = threadLikeDao.getById(data.getId());
		Thread thread = threadDao.getById(data.getThreadId());
		threadLike.setThread(thread);
		
		User user = userDao.getById(data.getUserId());
		threadLike.setUser(user);		
		threadLike.setLikeCounter(data.getLikeCounter());
		threadLike.setUpdatedBy(getId());
		threadLike.setVersion(data.getVersion());
		threadLike.setIsActive(data.getIsActive());

		begin();
		ThreadLike threadLikeUpdate = threadLikeDao.save(threadLike);
		commit();

		UpdateThreadLikeDtoDataRes threadLikeVersion = new UpdateThreadLikeDtoDataRes();
		threadLikeVersion.setVersion(threadLikeUpdate.getVersion());

		UpdateThreadLikeDtoRes result = new UpdateThreadLikeDtoRes();
		result.setData(threadLikeVersion);
		result.setMsg("Update Successfully");
		return result;
	}

	public GetByIdThreadLikeDtoRes getById(String id) throws Exception {
		ThreadLike threadLike = threadLikeDao.getById(id);

		GetThreadLikeDtoDataRes threadLikeData = new GetThreadLikeDtoDataRes();
		threadLikeData.setId(threadLike.getId());
		threadLikeData.setThreadId(threadLike.getThread().getId());
		threadLikeData.setUserId(threadLike.getUser().getId());
		threadLikeData.setLikeCounter(threadLike.getLikeCounter());
		threadLikeData.setVersion(threadLike.getVersion());
		threadLikeData.setIsActive(threadLike.getIsActive());

		GetByIdThreadLikeDtoRes result = new GetByIdThreadLikeDtoRes();
		result.setData(threadLikeData);

		return result;
	}

}
