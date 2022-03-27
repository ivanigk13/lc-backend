package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.PollingDetailDao;
import com.lawencon.community.dto.pollingdetail.DeletePollingDetailDtoRes;
import com.lawencon.community.dto.pollingdetail.GetAllPollingDetailDtoRes;
import com.lawencon.community.dto.pollingdetail.GetByIdPollingDetailDtoRes;
import com.lawencon.community.dto.pollingdetail.GetPollingDetailDtoDataRes;
import com.lawencon.community.dto.pollingdetail.InsertPollingDetailDtoDataRes;
import com.lawencon.community.dto.pollingdetail.InsertPollingDetailDtoReq;
import com.lawencon.community.dto.pollingdetail.InsertPollingDetailDtoRes;
import com.lawencon.community.dto.pollingdetail.UpdatePollingDetailDtoDataRes;
import com.lawencon.community.dto.pollingdetail.UpdatePollingDetailDtoReq;
import com.lawencon.community.dto.pollingdetail.UpdatePollingDetailDtoRes;
import com.lawencon.community.model.PollingDetail;
import com.lawencon.community.model.PollingHeader;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PollingDetailService extends BaseCommunityService {
	
	private final PollingDetailDao pollingDetailDao;
	
	public InsertPollingDetailDtoRes insert(InsertPollingDetailDtoReq data) throws Exception {
		PollingDetail pollingDetail = new PollingDetail();
		PollingHeader pollingHeader = new PollingHeader();
		pollingHeader.setId(data.getPollingHeaderId());
		pollingDetail.setPollingHeader(pollingHeader);
		pollingDetail.setPollingName(data.getPollingName());
		pollingDetail.setPollingCounter(data.getPollingCounter());
		pollingDetail.setCreatedBy(getId());
		
		begin();
		pollingDetail = pollingDetailDao.save(pollingDetail);
		commit();
		
		InsertPollingDetailDtoDataRes pollingDetailDataRes = new InsertPollingDetailDtoDataRes();
		pollingDetailDataRes.setId(pollingDetail.getId());
		
		InsertPollingDetailDtoRes pollingDetailRes = new InsertPollingDetailDtoRes();
		pollingDetailRes.setMsg("Insert Successfully");
		pollingDetailRes.setData(pollingDetailDataRes);
		
		return pollingDetailRes;
	}
	
	public UpdatePollingDetailDtoRes update(UpdatePollingDetailDtoReq data) throws Exception {
		PollingDetail pollingDetail = pollingDetailDao.getById(data.getId());
		pollingDetail.setPollingName(data.getPollingName());
		pollingDetail.setPollingCounter(data.getPollingCounter());
		pollingDetail.setUpdatedBy(getId());
		pollingDetail.setVersion(data.getVersion());
		pollingDetail.setIsActive(data.getIsActive());
		
		begin();
		pollingDetail = pollingDetailDao.save(pollingDetail);
		commit();
		
		UpdatePollingDetailDtoDataRes pollingDetailDataRes = new UpdatePollingDetailDtoDataRes();
		pollingDetailDataRes.setVersion(pollingDetail.getVersion());
		
		UpdatePollingDetailDtoRes pollingDetailRes = new UpdatePollingDetailDtoRes();
		pollingDetailRes.setMsg("Update Successfully");
		pollingDetailRes.setData(pollingDetailDataRes);
		
		return pollingDetailRes;
	}
	
	public GetByIdPollingDetailDtoRes getById(String id) throws Exception {
		PollingDetail pollingDetail = pollingDetailDao.getById(id);
		GetPollingDetailDtoDataRes pollingDetailDataRes = new GetPollingDetailDtoDataRes();
		pollingDetailDataRes.setId(pollingDetail.getId());
		pollingDetailDataRes.setPollingHeaderId(pollingDetail.getPollingHeader().getId());
		pollingDetailDataRes.setPollingName(pollingDetail.getPollingName());
		pollingDetailDataRes.setPollingCounter(pollingDetail.getPollingCounter());
		pollingDetailDataRes.setVersion(pollingDetail.getVersion());
		pollingDetailDataRes.setIsActive(pollingDetail.getIsActive());
		
		GetByIdPollingDetailDtoRes pollingDetailRes = new GetByIdPollingDetailDtoRes();
		pollingDetailRes.setData(pollingDetailDataRes);
		
		return pollingDetailRes;
	}
	
	public GetAllPollingDetailDtoRes getAll(Integer start, Integer max) throws Exception {
		List<PollingDetail> pollingDetails;	
		if(start == null) pollingDetails = pollingDetailDao.getAll();
		else pollingDetails = pollingDetailDao.getAll(start, max);
		
		List<GetPollingDetailDtoDataRes> data = new ArrayList<GetPollingDetailDtoDataRes>();
		
		pollingDetails.forEach(pollingDetail -> {
			GetPollingDetailDtoDataRes pollingDetailDataRes = new GetPollingDetailDtoDataRes();
			pollingDetailDataRes.setId(pollingDetail.getId());
			pollingDetailDataRes.setPollingHeaderId(pollingDetail.getPollingHeader().getId());
			pollingDetailDataRes.setPollingName(pollingDetail.getPollingName());
			pollingDetailDataRes.setPollingCounter(pollingDetail.getPollingCounter());
			pollingDetailDataRes.setVersion(pollingDetail.getVersion());
			pollingDetailDataRes.setIsActive(pollingDetail.getIsActive());
			
			data.add(pollingDetailDataRes);
		});
		
		GetAllPollingDetailDtoRes pollingDetailRes = new GetAllPollingDetailDtoRes();
		pollingDetailRes.setData(data);
		
		return pollingDetailRes;
	}
	
	public GetAllPollingDetailDtoRes getAllByPollingHeaderId(String pollingHeaderId) throws Exception {
		List<PollingDetail> pollingDetails = pollingDetailDao.getAllByPollingHeaderId(pollingHeaderId);
		
		List<GetPollingDetailDtoDataRes> data = new ArrayList<GetPollingDetailDtoDataRes>();
		
		pollingDetails.forEach(pollingDetail -> {
			GetPollingDetailDtoDataRes pollingDetailDataRes = new GetPollingDetailDtoDataRes();
			pollingDetailDataRes.setId(pollingDetail.getId());
			pollingDetailDataRes.setPollingHeaderId(pollingDetail.getPollingHeader().getId());
			pollingDetailDataRes.setPollingName(pollingDetail.getPollingName());
			pollingDetailDataRes.setPollingCounter(pollingDetail.getPollingCounter());
			pollingDetailDataRes.setVersion(pollingDetail.getVersion());
			pollingDetailDataRes.setIsActive(pollingDetail.getIsActive());
			
			data.add(pollingDetailDataRes);
		});
		
		GetAllPollingDetailDtoRes pollingDetailRes = new GetAllPollingDetailDtoRes();
		pollingDetailRes.setData(data);
		
		return pollingDetailRes;
	}
	
	public DeletePollingDetailDtoRes deleteById(String id) throws Exception {
		begin();
		pollingDetailDao.deleteById(id);
		commit();
		
		DeletePollingDetailDtoRes pollingDetailRes = new DeletePollingDetailDtoRes();
		pollingDetailRes.setMsg("Delete Successfully");
		
		return pollingDetailRes;
	}
}
