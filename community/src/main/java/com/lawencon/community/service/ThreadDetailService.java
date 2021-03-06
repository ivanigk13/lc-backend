package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ProfileDao;
import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.dao.ThreadDetailDao;
import com.lawencon.community.dto.threaddetail.GetAllByThreadIdThreadDetailDtoRes;
import com.lawencon.community.dto.threaddetail.GetByIdThreadDetailDtoRes;
import com.lawencon.community.dto.threaddetail.GetThreadDetailDtoDataRes;
import com.lawencon.community.dto.threaddetail.InsertThreadDetailDtoDataRes;
import com.lawencon.community.dto.threaddetail.InsertThreadDetailDtoReq;
import com.lawencon.community.dto.threaddetail.InsertThreadDetailDtoRes;
import com.lawencon.community.model.Profile;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadDetail;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThreadDetailService extends BaseCommunityService{
	
	private final ThreadDetailDao threadDetailDao;
	private final ThreadDao threadDao;
	private final ProfileDao profileDao;
	
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
	
	public GetAllByThreadIdThreadDetailDtoRes getAllByThreadId(String id) throws Exception {
		List<ThreadDetail> threadDetails = threadDetailDao.getAllByThreadId(id);
		List<GetThreadDetailDtoDataRes> datas = new ArrayList<GetThreadDetailDtoDataRes>();
		for(ThreadDetail threadDetail : threadDetails) {
			GetThreadDetailDtoDataRes data = new GetThreadDetailDtoDataRes();
			data.setId(threadDetail.getId());
			
			Profile profile = profileDao.getByUserId(threadDetail.getCreatedBy());
			if(profile.getFile() != null) {
				data.setProfilePictureId(profile.getFile().getId());				
			}
			
			data.setFullName(profile.getFullName());
			
			data.setThreadId(threadDetail.getThread().getId());
			data.setComment(threadDetail.getComment());
			data.setVersion(threadDetail.getVersion());
			data.setIsActive(threadDetail.getIsActive());
			
			datas.add(data);
		}

		GetAllByThreadIdThreadDetailDtoRes result = new GetAllByThreadIdThreadDetailDtoRes();
		result.setData(datas);

		return result;
	}
	
	public Integer getTotalCommentByThreadId(String threadId) throws Exception{
		return threadDetailDao.getTotalCommentByThreadId(threadId);
	}
	
}
