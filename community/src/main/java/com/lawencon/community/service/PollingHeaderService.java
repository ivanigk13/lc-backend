package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.PollingHeaderDao;
import com.lawencon.community.dto.pollingheader.DeletePollingHeaderDtoRes;
import com.lawencon.community.dto.pollingheader.GetAllPollingHeaderDtoRes;
import com.lawencon.community.dto.pollingheader.GetByIdPollingHeaderDtoRes;
import com.lawencon.community.dto.pollingheader.GetPollingHeaderDtoDataRes;
import com.lawencon.community.dto.pollingheader.InsertPollingHeaderDtoDataRes;
import com.lawencon.community.dto.pollingheader.InsertPollingHeaderDtoReq;
import com.lawencon.community.dto.pollingheader.InsertPollingHeaderDtoRes;
import com.lawencon.community.model.PollingHeader;
import com.lawencon.community.model.Thread;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PollingHeaderService extends BaseCommunityService {

	private final PollingHeaderDao pollingHeaderDao;
	
	public InsertPollingHeaderDtoRes insert(InsertPollingHeaderDtoReq data) throws Exception {
		PollingHeader pollingHeader = new PollingHeader();
		Thread thread = new Thread();
		thread.setId(data.getThreadId());
		pollingHeader.setThread(thread);
		pollingHeader.setCreatedBy(getId());
		
		begin();
		pollingHeader = pollingHeaderDao.save(pollingHeader);
		commit();
		
		InsertPollingHeaderDtoDataRes pollingHeaderDataRes = new InsertPollingHeaderDtoDataRes();
		pollingHeaderDataRes.setId(pollingHeader.getId());
		
		InsertPollingHeaderDtoRes pollingDetailRes = new InsertPollingHeaderDtoRes();
		pollingDetailRes.setMsg("Insert Successfully");
		pollingDetailRes.setData(pollingHeaderDataRes);
	
		return pollingDetailRes;
	}
	
	public GetByIdPollingHeaderDtoRes getById(String id) throws Exception {
		PollingHeader pollingHeader = pollingHeaderDao.getById(id);
		GetPollingHeaderDtoDataRes pollingHeaderDataRes = new GetPollingHeaderDtoDataRes();
		pollingHeaderDataRes.setId(pollingHeader.getId());
		pollingHeaderDataRes.setThreadId(pollingHeader.getThread().getId());
		pollingHeaderDataRes.setVersion(pollingHeader.getVersion());
		pollingHeaderDataRes.setIsActive(pollingHeader.getIsActive());
		
		GetByIdPollingHeaderDtoRes pollingHeaderRes = new GetByIdPollingHeaderDtoRes();
		pollingHeaderRes.setData(pollingHeaderDataRes);
		
		return pollingHeaderRes;
	}
	
	public GetAllPollingHeaderDtoRes getAll() throws Exception {
		List<PollingHeader> pollingHeaders = pollingHeaderDao.getAll();
		
		List<GetPollingHeaderDtoDataRes> data = new ArrayList<GetPollingHeaderDtoDataRes>();
		
		pollingHeaders.forEach(pollingHeader -> {
			GetPollingHeaderDtoDataRes pollingHeaderDataRes = new GetPollingHeaderDtoDataRes();
			pollingHeaderDataRes.setId(pollingHeader.getId());
			pollingHeaderDataRes.setThreadId(pollingHeader.getThread().getId());
			pollingHeaderDataRes.setVersion(pollingHeader.getVersion());
			pollingHeaderDataRes.setIsActive(pollingHeader.getIsActive());
			
			data.add(pollingHeaderDataRes);
		});
		
		GetAllPollingHeaderDtoRes pollingHeaderRes = new GetAllPollingHeaderDtoRes();
		pollingHeaderRes.setData(data);
		
		return pollingHeaderRes;
	}
	
	public DeletePollingHeaderDtoRes deleteById(String id) throws Exception {
		pollingHeaderDao.deleteById(id);
		
		DeletePollingHeaderDtoRes pollingHeaderRes = new DeletePollingHeaderDtoRes();
		pollingHeaderRes.setMsg("Delete Successfully");
		
		return pollingHeaderRes;
	}
}
