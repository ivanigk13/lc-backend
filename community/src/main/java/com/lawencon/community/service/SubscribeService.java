package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.SubscribeDao;
import com.lawencon.community.dto.subscribe.DeleteSubscribeDtoRes;
import com.lawencon.community.dto.subscribe.GetAllSubscribeDtoRes;
import com.lawencon.community.dto.subscribe.GetByIdSubscribeDtoRes;
import com.lawencon.community.dto.subscribe.GetSubscribeDtoDataRes;
import com.lawencon.community.dto.subscribe.InsertSubscribeDtoDataRes;
import com.lawencon.community.dto.subscribe.InsertSubscribeDtoReq;
import com.lawencon.community.dto.subscribe.InsertSubscribeDtoRes;
import com.lawencon.community.dto.subscribe.UpdateSubscribeDtoDataRes;
import com.lawencon.community.dto.subscribe.UpdateSubscribeDtoReq;
import com.lawencon.community.dto.subscribe.UpdateSubscribeDtoRes;
import com.lawencon.community.model.Subscribe;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscribeService extends BaseCommunityService{

	private final SubscribeDao subscribeDao;
	
	public InsertSubscribeDtoRes insert(InsertSubscribeDtoReq data) throws Exception {
		Subscribe subscribe = new Subscribe();
		subscribe.setDuration(data.getDuration());
		subscribe.setPrice(data.getPrice());
		subscribe.setCreatedBy(getId());

		begin();
		Subscribe subscribeInsert = subscribeDao.save(subscribe);
		commit();
		InsertSubscribeDtoDataRes subscribeId = new InsertSubscribeDtoDataRes();
		subscribeId.setId(subscribeInsert.getId());

		InsertSubscribeDtoRes result = new InsertSubscribeDtoRes();
		result.setData(subscribeId);
		result.setMsg("Insert Successfully");
		
		return result;
	}

	public UpdateSubscribeDtoRes update(UpdateSubscribeDtoReq data) throws Exception {
		Subscribe subscribe = subscribeDao.getById(data.getId());
		subscribe.setDuration(data.getDuration());
		subscribe.setPrice(data.getPrice());
		subscribe.setUpdatedBy(getId());
		subscribe.setVersion(data.getVersion());
		subscribe.setIsActive(data.getIsActive());

		begin();
		Subscribe subscribeUpdate = subscribeDao.save(subscribe);
		commit();

		UpdateSubscribeDtoDataRes subscribeVersion = new UpdateSubscribeDtoDataRes();
		subscribeVersion.setVersion(subscribeUpdate.getVersion());

		UpdateSubscribeDtoRes result = new UpdateSubscribeDtoRes();
		result.setData(subscribeVersion);
		result.setMsg("Update Successfully");
		return result;
	}

	public GetAllSubscribeDtoRes getAll(Integer start, Integer max) throws Exception {
		List<Subscribe> subscribes;
		if(start ==null) subscribes = subscribeDao.getAll();
		else subscribes = subscribeDao.getAll(start, max);
		
		List<GetSubscribeDtoDataRes> data = new ArrayList<>();

		subscribes.forEach(list -> {
			GetSubscribeDtoDataRes subscribe = new GetSubscribeDtoDataRes();
			subscribe.setId(list.getId());
			subscribe.setDuration(list.getDuration());
			subscribe.setPrice(list.getPrice());
			subscribe.setVersion(list.getVersion());
			subscribe.setIsActive(list.getIsActive());
			data.add(subscribe);
		});

		GetAllSubscribeDtoRes result = new GetAllSubscribeDtoRes();
		result.setData(data);

		return result;
	}

	public GetByIdSubscribeDtoRes getById(String id) throws Exception {
		Subscribe subscribe = subscribeDao.getById(id);

		GetSubscribeDtoDataRes subscribeData = new GetSubscribeDtoDataRes();
		subscribeData.setId(subscribe.getId());
		subscribeData.setDuration(subscribe.getDuration());
		subscribeData.setPrice(subscribe.getPrice());
		subscribeData.setVersion(subscribe.getVersion());
		subscribeData.setIsActive(subscribe.getIsActive());

		GetByIdSubscribeDtoRes result = new GetByIdSubscribeDtoRes();
		result.setData(subscribeData);

		return result;
	}
	
	public DeleteSubscribeDtoRes deleteById(String id) throws Exception {
		try {			
			begin();
			boolean isDeleted = subscribeDao.deleteById(id);
			commit();
			
			DeleteSubscribeDtoRes subscribeRes = new DeleteSubscribeDtoRes();
			if(isDeleted) {
				subscribeRes.setMsg("Delete Successfully");
			} else {
				subscribeRes.setMsg("Delete Unsuccessfully");
			}
			
			return subscribeRes;
		}
		catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
