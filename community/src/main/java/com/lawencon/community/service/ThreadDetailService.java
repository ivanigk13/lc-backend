package com.lawencon.community.service;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.dao.ThreadDetailDao;
import com.lawencon.community.dto.threaddetail.GetByIdThreadDetailDtoRes;
import com.lawencon.community.dto.threaddetail.GetThreadDetailDtoDataRes;
import com.lawencon.community.dto.threaddetail.InsertThreadDetailDtoDataRes;
import com.lawencon.community.dto.threaddetail.InsertThreadDetailDtoReq;
import com.lawencon.community.dto.threaddetail.InsertThreadDetailDtoRes;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadDetail;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThreadDetailService extends BaseCommunityService{
	
	private final ThreadDetailDao threadDetailDao;
	private final ThreadDao threadDao;
	
	public InsertThreadDetailDtoRes insert(InsertThreadDetailDtoReq data) throws Exception {
		ThreadDetail threadDetail = new ThreadDetail();
		
		Thread thread = threadDao.getById(data.getThreadId());
		threadDetail.setThread(thread);
		threadDetail.setComment(data.getComment());
		threadDetail.setCreatedBy(getId());
		

		begin();
		ThreadDetail threadDetailInsert = threadDetailDao.save(threadDetail);
		commit();
		
		InsertThreadDetailDtoDataRes threadDetailId = new InsertThreadDetailDtoDataRes();
		threadDetailId.setId(threadDetailInsert.getId());

		InsertThreadDetailDtoRes result = new InsertThreadDetailDtoRes();
		result.setData(threadDetailId);
		result.setMsg("Insert Successfully");
		
		return result;
	}

	public GetByIdThreadDetailDtoRes getById(String id) throws Exception {
		ThreadDetail threadDetail = threadDetailDao.getById(id);

		GetThreadDetailDtoDataRes threadDetailData = new GetThreadDetailDtoDataRes();
		threadDetailData.setId(threadDetail.getId());
		threadDetailData.setThreadId(threadDetail.getThread().getId());
		threadDetailData.setComment(threadDetail.getComment());
		threadDetailData.setVersion(threadDetail.getVersion());
		threadDetailData.setIsActive(threadDetail.getIsActive());

		GetByIdThreadDetailDtoRes result = new GetByIdThreadDetailDtoRes();
		result.setData(threadDetailData);

		return result;
	}
}
