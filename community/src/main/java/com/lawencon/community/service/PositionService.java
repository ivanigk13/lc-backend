package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.PositionDao;
import com.lawencon.community.dto.position.DeletePositionDtoRes;
import com.lawencon.community.dto.position.GetAllPositionDtoRes;
import com.lawencon.community.dto.position.GetByIdPositionDtoRes;
import com.lawencon.community.dto.position.GetPositionDtoDataRes;
import com.lawencon.community.dto.position.InsertPositionDtoDataRes;
import com.lawencon.community.dto.position.InsertPositionDtoReq;
import com.lawencon.community.dto.position.InsertPositionDtoRes;
import com.lawencon.community.dto.position.UpdatePositionDtoDataRes;
import com.lawencon.community.dto.position.UpdatePositionDtoReq;
import com.lawencon.community.dto.position.UpdatePositionDtoRes;
import com.lawencon.community.model.Position;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PositionService extends BaseCommunityService {
	
	private final PositionDao positionDao;

	public GetAllPositionDtoRes getAll(Integer start, Integer max) throws Exception {
		List<Position> positions;
		if(start == null) positions = positionDao.getAll();
		else positions = positionDao.getAll(start, max);
		
		List<GetPositionDtoDataRes> data = new ArrayList<GetPositionDtoDataRes>();
		
		positions.forEach(position -> {
			GetPositionDtoDataRes positionDataRes = new GetPositionDtoDataRes();
			positionDataRes.setId(position.getId());
			positionDataRes.setPositionCode(position.getPositionCode());
			positionDataRes.setPositionName(position.getPositionName());
			positionDataRes.setVersion(position.getVersion());
			positionDataRes.setIsActive(position.getIsActive());
			
			data.add(positionDataRes);
		});
		
		GetAllPositionDtoRes positionRes = new GetAllPositionDtoRes();
		positionRes.setData(data);
		
		return positionRes;
	}

	public GetByIdPositionDtoRes getById(String id) throws Exception {
		Position position = positionDao.getById(id);
		if(position!=null) {
			GetPositionDtoDataRes positionDataRes = new GetPositionDtoDataRes();
			positionDataRes.setId(position.getId());
			positionDataRes.setPositionCode(position.getPositionCode());
			positionDataRes.setPositionName(position.getPositionName());
			positionDataRes.setVersion(position.getVersion());
			positionDataRes.setIsActive(position.getIsActive());
			
			GetByIdPositionDtoRes positionRes = new GetByIdPositionDtoRes();
			positionRes.setData(positionDataRes);
			
			return positionRes;
		}
		
		throw new RuntimeException("Position Id doesn't exist");
	}
	
	public InsertPositionDtoRes insert(InsertPositionDtoReq data) throws Exception {
		Position position = new Position();
		valBkNotExist(data.getPositionCode(), data.getPositionName());
		position.setPositionCode(data.getPositionCode());
		position.setPositionName(data.getPositionName());
		position.setCreatedBy(getId());
		
		begin();
		position = positionDao.save(position);
		commit();
		
		InsertPositionDtoDataRes positionDataRes = new InsertPositionDtoDataRes();
		positionDataRes.setId(position.getId());
		
		InsertPositionDtoRes positionRes = new InsertPositionDtoRes();
		positionRes.setMsg("Insert Successfully");
		positionRes.setData(positionDataRes);
		
		return positionRes;
	}

	public UpdatePositionDtoRes update(UpdatePositionDtoReq data) throws Exception {
		Position position = positionDao.getById(data.getId());
		valBkNotExist(data.getPositionName());
		position.setPositionName(data.getPositionName());
		position.setUpdatedBy(getId());
		position.setVersion(data.getVersion());
		position.setIsActive(data.getIsActive());
		
		begin();
		position = positionDao.save(position);
		commit();
		
		UpdatePositionDtoDataRes positionDataRes = new UpdatePositionDtoDataRes();
		positionDataRes.setVersion(position.getVersion());
		
		UpdatePositionDtoRes positionRes = new UpdatePositionDtoRes();
		positionRes.setMsg("Update Successfully");
		positionRes.setData(positionDataRes);
		
		return positionRes;
	}

	public DeletePositionDtoRes deleteById(String id) throws Exception {
		
		try {			
			begin();
			boolean isDeleted = positionDao.deleteById(id);
			commit();
			
			DeletePositionDtoRes positionRes = new DeletePositionDtoRes();
			if(isDeleted) {
				positionRes.setMsg("Delete Successfully");
			} else {
				positionRes.setMsg("Delete Unsuccessfully");
			}
			
			return positionRes;
		}
		catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}  

	private void valBkNotExist(String code, String name) {
		Integer flagCode = positionDao.isPositionCodeExist(code);
		Integer flagName = positionDao.isPositionNameExist(name);
		if(flagCode == 1 || flagName == 1) {
			throw new RuntimeException("Position Code or Position Name has existed");
		}
	}
	
	private void valBkNotExist(String name) {
		Integer flagName = positionDao.isPositionNameExist(name);
		if(flagName == 1) {
			throw new RuntimeException("Position Name has existed");
		}
	}
}
