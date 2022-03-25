package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.base.BaseService;
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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IndustryService extends BaseService{

	private final IndustryDao industryDao;
	
	public InsertIndustryDtoRes insert(InsertIndustryDtoReq data) throws Exception {
		Industry Industry = new Industry();
		Industry.setIndustryName(data.getIndustryName());
		Industry.setIndustryCode(data.getIndustryCode());
		
		begin();
		Industry IndustryInsert = industryDao.save(Industry);
		InsertIndustryDtoDataRes IndustryId = new InsertIndustryDtoDataRes();
		IndustryId.setId(IndustryInsert.getId());
		
		InsertIndustryDtoRes result = new InsertIndustryDtoRes();
		result.setData(IndustryId);
		result.setMsg("Insert Successfully");
		commit();
		return result;
	}
	
	public UpdateIndustryDtoRes update(UpdateIndustryDtoReq data) throws Exception{
		Industry Industry = industryDao.getById(data.getId());
		Industry.setIndustryName(data.getIndustryName());
		Industry.setVersion(data.getVersion());
		Industry.setIsActive(data.getIsActive());
		
		begin();
		Industry IndustryUpdate = industryDao.save(Industry);
		commit();
		
		UpdateIndustryDtoDataRes IndustryVersion = new UpdateIndustryDtoDataRes();
		IndustryVersion.setVersion(IndustryUpdate.getVersion());
		
		UpdateIndustryDtoRes result = new UpdateIndustryDtoRes();
		result.setData(IndustryVersion);
		result.setMsg("Update Successfully");
		return result;
	}
	
	public GetAllIndustryDtoRes getAll() throws Exception{
		List<Industry> Industrys = industryDao.getAll();
		List<GetIndustryDtoDataRes> data = new ArrayList<>();
		
		Industrys.forEach(list -> {
			GetIndustryDtoDataRes Industry = new GetIndustryDtoDataRes();
			Industry.setId(list.getId());
			Industry.setIndustryName(list.getIndustryName());
			Industry.setIndustryCode(list.getIndustryCode());
			Industry.setVersion(list.getVersion());
			Industry.setIsActive(list.getIsActive());
			data.add(Industry);
		});
		
		GetAllIndustryDtoRes result = new GetAllIndustryDtoRes();
		result.setData(data);
		
		return result;
	}
	
	public GetByIdIndustryDtoRes getById(String id) throws Exception{
		Industry Industry = industryDao.getById(id);
		
		GetIndustryDtoDataRes IndustryData = new GetIndustryDtoDataRes();
		IndustryData.setId(Industry.getId());
		IndustryData.setIndustryName(Industry.getIndustryName());
		IndustryData.setIndustryCode(Industry.getIndustryCode());
		IndustryData.setVersion(Industry.getVersion());
		IndustryData.setIsActive(Industry.getIsActive());
		
		GetByIdIndustryDtoRes result = new GetByIdIndustryDtoRes();
		result.setData(IndustryData);		
		
		return result;		
	}
	
	public DeleteIndustryDtoRes deleteById(String id) throws Exception {
		begin();
		industryDao.deleteById(id);
		commit();
		
		DeleteIndustryDtoRes industryRes = new DeleteIndustryDtoRes();
		industryRes.setMsg("Delete Successfully");
		
		return industryRes;
	}
}
