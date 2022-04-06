package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.PollingDetailDao;
import com.lawencon.community.dao.PollingHeaderDao;
import com.lawencon.community.dto.pollingheader.DeletePollingHeaderDtoRes;
import com.lawencon.community.dto.pollingheader.GetAllPollingHeaderDtoRes;
import com.lawencon.community.dto.pollingheader.GetByIdPollingHeaderDtoRes;
import com.lawencon.community.dto.pollingheader.GetPollingHeaderDtoDataRes;
import com.lawencon.community.dto.pollingheader.InsertPollingHeaderDtoDataRes;
import com.lawencon.community.dto.pollingheader.InsertPollingHeaderDtoReq;
import com.lawencon.community.dto.pollingheader.InsertPollingHeaderDtoRes;
import com.lawencon.community.dto.pollingheader.UpdatePollingHeaderDtoDataRes;
import com.lawencon.community.dto.pollingheader.UpdatePollingHeaderDtoReq;
import com.lawencon.community.dto.pollingheader.UpdatePollingHeaderDtoRes;
import com.lawencon.community.model.PollingDetail;
import com.lawencon.community.model.PollingHeader;
import com.lawencon.community.model.Thread;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PollingHeaderService extends BaseCommunityService {

	private final PollingHeaderDao pollingHeaderDao;
	private final PollingDetailDao pollingDetailDao;
	
	public InsertPollingHeaderDtoRes insert(InsertPollingHeaderDtoReq data) throws Exception {
		PollingHeader pollingHeader = new PollingHeader();
		Thread thread = new Thread();
		thread.setId(data.getThreadId());
		pollingHeader.setThread(thread);
		pollingHeader.setTitle(data.getTitle());
		pollingHeader.setCreatedBy(getId());
		
		begin();
		pollingHeader = pollingHeaderDao.save(pollingHeader);
		
		List<String> details = data.getData();
		for(int i = 0; i <  details.size(); i++) {
			PollingHeader header = new PollingHeader();
			header.setId(pollingHeader.getId());
			
			PollingDetail pollingDetail = new PollingDetail();
			pollingDetail.setPollingName(details.get(i));
			pollingDetail.setPollingHeader(header);
			pollingDetail.setCreatedBy(getId());
			
			pollingDetailDao.save(pollingDetail);
		}
		commit();
		
		InsertPollingHeaderDtoDataRes pollingHeaderDataRes = new InsertPollingHeaderDtoDataRes();
		pollingHeaderDataRes.setId(pollingHeader.getId());
		
		InsertPollingHeaderDtoRes pollingDetailRes = new InsertPollingHeaderDtoRes();
		pollingDetailRes.setMsg("Insert Successfully");
		pollingDetailRes.setData(pollingHeaderDataRes);
	
		return pollingDetailRes;
	}
	
	public UpdatePollingHeaderDtoRes update(UpdatePollingHeaderDtoReq data) throws Exception {
		PollingHeader pollingHeader = pollingHeaderDao.getById(data.getId());
		pollingHeader.setTitle(data.getTitle());
		pollingHeader.setUpdatedBy(getId());
		
		begin();
		pollingHeader = pollingHeaderDao.save(pollingHeader);
		commit();
		
		UpdatePollingHeaderDtoDataRes dataRes = new UpdatePollingHeaderDtoDataRes();
		dataRes.setVersion(pollingHeader.getVersion());
		
		UpdatePollingHeaderDtoRes pollingHeaderRes = new UpdatePollingHeaderDtoRes();
		pollingHeaderRes.setMsg("Update Succesfully");
		pollingHeaderRes.setData(dataRes);
		
		return pollingHeaderRes;
	}
	
	public GetByIdPollingHeaderDtoRes getById(String id) throws Exception {
		PollingHeader pollingHeader = pollingHeaderDao.getById(id);
		GetPollingHeaderDtoDataRes pollingHeaderDataRes = new GetPollingHeaderDtoDataRes();
		pollingHeaderDataRes.setId(pollingHeader.getId());
		pollingHeaderDataRes.setThreadId(pollingHeader.getThread().getId());
		pollingHeaderDataRes.setTitle(pollingHeader.getTitle());
		pollingHeaderDataRes.setVersion(pollingHeader.getVersion());
		pollingHeaderDataRes.setIsActive(pollingHeader.getIsActive());
		
		GetByIdPollingHeaderDtoRes pollingHeaderRes = new GetByIdPollingHeaderDtoRes();
		pollingHeaderRes.setData(pollingHeaderDataRes);
		
		return pollingHeaderRes;
	}
	
	public GetAllPollingHeaderDtoRes getAll(Integer start, Integer max) throws Exception {
		List<PollingHeader> pollingHeaders;	
		if(start == null) pollingHeaders = pollingHeaderDao.getAll();
		else pollingHeaders = pollingHeaderDao.getAll(start, max);
		
		List<GetPollingHeaderDtoDataRes> data = new ArrayList<GetPollingHeaderDtoDataRes>();
		
		pollingHeaders.forEach(pollingHeader -> {
			GetPollingHeaderDtoDataRes pollingHeaderDataRes = new GetPollingHeaderDtoDataRes();
			pollingHeaderDataRes.setId(pollingHeader.getId());
			pollingHeaderDataRes.setThreadId(pollingHeader.getThread().getId());
			pollingHeaderDataRes.setTitle(pollingHeader.getTitle());
			pollingHeaderDataRes.setVersion(pollingHeader.getVersion());
			pollingHeaderDataRes.setIsActive(pollingHeader.getIsActive());
			
			data.add(pollingHeaderDataRes);
		});
		
		GetAllPollingHeaderDtoRes pollingHeaderRes = new GetAllPollingHeaderDtoRes();
		pollingHeaderRes.setData(data);
		
		return pollingHeaderRes;
	}
	
	public DeletePollingHeaderDtoRes deleteById(String id) throws Exception {
		try {			
			begin();
			boolean isDeleted = pollingHeaderDao.deleteById(id);
			commit();
			
			DeletePollingHeaderDtoRes pollingHeaderRes = new DeletePollingHeaderDtoRes();
			if(isDeleted) {
				pollingHeaderRes.setMsg("Delete Successfully");
			} else {
				pollingHeaderRes.setMsg("Delete Unsuccessfully");
			}
			
			return pollingHeaderRes;
		}
		catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
