package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.IndustryDao;
import com.lawencon.community.dto.industry.DeleteIndustryDtoRes;
import com.lawencon.community.dto.industry.GetAllIndustryDtoRes;
import com.lawencon.community.dto.industry.GetByIdIndustryDtoRes;
import com.lawencon.community.dto.industry.GetIndustryDtoDataRes;
import com.lawencon.community.dto.industry.InsertIndustryDtoDataRes;
import com.lawencon.community.dto.industry.InsertIndustryDtoReq;
import com.lawencon.community.dto.industry.InsertIndustryDtoRes;
import com.lawencon.community.dto.industry.UpdateIndustryDtoDataRes;
import com.lawencon.community.dto.industry.UpdateIndustryDtoReq;
import com.lawencon.community.dto.industry.UpdateIndustryDtoRes;
import com.lawencon.community.model.Industry;
import com.lawencon.model.SearchQuery;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IndustryService extends BaseCommunityService {

	private final IndustryDao industryDao;
	
	public GetAllIndustryDtoRes getAll(String query, Integer start, Integer max) throws Exception{
		SearchQuery<Industry> industries = industryDao.findAll(query, start, max);
		List<GetIndustryDtoDataRes> data = new ArrayList<>();
		
		industries.getData().forEach(list -> {
			GetIndustryDtoDataRes industry = new GetIndustryDtoDataRes();
			industry.setId(list.getId());
			industry.setIndustryName(list.getIndustryName());
			industry.setIndustryCode(list.getIndustryCode());
			industry.setVersion(list.getVersion());
			industry.setIsActive(list.getIsActive());
			data.add(industry);
		});
		
		GetAllIndustryDtoRes result = new GetAllIndustryDtoRes();
		result.setData(data);
		result.setRows(industryDao.countAll());
		
		return result;
	}
	
	public GetByIdIndustryDtoRes getById(String id) throws Exception{
		Industry industry = industryDao.getById(id);
		if(industry!=null) {
			GetIndustryDtoDataRes industryData = new GetIndustryDtoDataRes();
			industryData.setId(industry.getId());
			industryData.setIndustryName(industry.getIndustryName());
			industryData.setIndustryCode(industry.getIndustryCode());
			industryData.setVersion(industry.getVersion());
			industryData.setIsActive(industry.getIsActive());
			
			GetByIdIndustryDtoRes result = new GetByIdIndustryDtoRes();
			result.setData(industryData);		
			
			return result;
		}
		
		throw new RuntimeException("Industry Id doesn't exist");
	}
	
	public InsertIndustryDtoRes insert(InsertIndustryDtoReq data) throws Exception {
		Industry industry = new Industry();
		valBkNotExist(data.getIndustryCode(), data.getIndustryName());
		industry.setIndustryName(data.getIndustryName());
		industry.setIndustryCode(data.getIndustryCode());
		industry.setCreatedBy(getId());
		
		begin();
		Industry industryInsert = industryDao.save(industry);
		commit();
		
		InsertIndustryDtoDataRes industryId = new InsertIndustryDtoDataRes();
		industryId.setId(industryInsert.getId());
		
		InsertIndustryDtoRes result = new InsertIndustryDtoRes();
		result.setData(industryId);
		result.setMsg("Insert Successfully");
		
		return result;
	}
	
	public UpdateIndustryDtoRes update(UpdateIndustryDtoReq data) throws Exception{
		Industry industry = industryDao.getById(data.getId());
		valBkNotExist(data.getIndustryName());
		industry.setIndustryName(data.getIndustryName());
		industry.setUpdatedBy(getId());
		industry.setVersion(data.getVersion());
		industry.setIsActive(data.getIsActive());
		
		begin();
		Industry industryUpdate = industryDao.save(industry);
		commit();
		
		UpdateIndustryDtoDataRes industryVersion = new UpdateIndustryDtoDataRes();
		industryVersion.setVersion(industryUpdate.getVersion());
		
		UpdateIndustryDtoRes result = new UpdateIndustryDtoRes();
		result.setData(industryVersion);
		result.setMsg("Update Successfully");
		return result;
	}
	
	public DeleteIndustryDtoRes deleteById(String id) throws Exception {
		try {			
			begin();
			boolean isDeleted = industryDao.deleteById(id);
			commit();
			
			DeleteIndustryDtoRes industryRes = new DeleteIndustryDtoRes();
			if(isDeleted) {
				industryRes.setMsg("Delete Successfully");
			} else {
				industryRes.setMsg("Delete Unsuccessfully");
			}
			
			return industryRes;
		}
		catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	private void valBkNotExist(String code, String name) {
		Integer flagCode = industryDao.isIndustryCodeExist(code);
		Integer flagName = industryDao.isIndustryNameExist(name);
		if(flagCode == 1 || flagName == 1) {
			throw new RuntimeException("Industry Code or Industry Name has existed");
		}
	}
	
	private void valBkNotExist(String name) {
		Integer flagName = industryDao.isIndustryNameExist(name);
		if(flagName == 1) {
			throw new RuntimeException("Industry Name has existed");
		}
	}
}
