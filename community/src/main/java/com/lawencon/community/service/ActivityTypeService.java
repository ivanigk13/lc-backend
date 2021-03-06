package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ActivityTypeDao;
import com.lawencon.community.dto.activitytype.DeleteActivityTypeDtoRes;
import com.lawencon.community.dto.activitytype.GetActivityTypeDtoDataRes;
import com.lawencon.community.dto.activitytype.GetAllActivityTypeDtoRes;
import com.lawencon.community.dto.activitytype.GetByIdActivityTypeDtoRes;
import com.lawencon.community.dto.activitytype.InsertActivityTypeDtoDataRes;
import com.lawencon.community.dto.activitytype.InsertActivityTypeDtoReq;
import com.lawencon.community.dto.activitytype.InsertActivityTypeDtoRes;
import com.lawencon.community.dto.activitytype.UpdateActivityTypeDtoDataRes;
import com.lawencon.community.dto.activitytype.UpdateActivityTypeDtoReq;
import com.lawencon.community.dto.activitytype.UpdateActivityTypeDtoRes;
import com.lawencon.community.model.ActivityType;
import com.lawencon.model.SearchQuery;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityTypeService extends BaseCommunityService {

	private final ActivityTypeDao activityTypeDao;

	public GetAllActivityTypeDtoRes getAll(String query, Integer start, Integer max) throws Exception {
		SearchQuery<ActivityType> activityTypes = activityTypeDao.findAll(query, start, max);
		
		List<GetActivityTypeDtoDataRes> data = new ArrayList<>();

		activityTypes.getData().forEach(list -> {
			GetActivityTypeDtoDataRes activityType = new GetActivityTypeDtoDataRes();
			activityType.setId(list.getId());
			activityType.setActivityTypeName(list.getActivityTypeName());
			activityType.setActivityTypeCode(list.getActivityTypeCode());
			activityType.setPrice(list.getPrice());
			activityType.setVersion(list.getVersion());
			activityType.setIsActive(list.getIsActive());
			data.add(activityType);
		});

		GetAllActivityTypeDtoRes result = new GetAllActivityTypeDtoRes();
		result.setData(data);
		result.setRows(activityTypeDao.countAll());

		return result;
	}

	public GetByIdActivityTypeDtoRes getById(String id) throws Exception {
		ActivityType activityType = activityTypeDao.getById(id);
		if(activityType!=null) {
			GetActivityTypeDtoDataRes activityTypeData = new GetActivityTypeDtoDataRes();
			activityTypeData.setId(activityType.getId());
			activityTypeData.setActivityTypeName(activityType.getActivityTypeName());
			activityTypeData.setActivityTypeCode(activityType.getActivityTypeCode());
			activityTypeData.setPrice(activityType.getPrice());
			activityTypeData.setVersion(activityType.getVersion());
			activityTypeData.setIsActive(activityType.getIsActive());
			
			GetByIdActivityTypeDtoRes result = new GetByIdActivityTypeDtoRes();
			result.setData(activityTypeData);
			
			return result;
		}
		
		throw new RuntimeException("Activity Type Id doesn't exist");
	}

	public InsertActivityTypeDtoRes insert(InsertActivityTypeDtoReq data) throws Exception {
		ActivityType activityType = new ActivityType();
		valBkNotExist(data.getActivityTypeCode(), data.getActivityTypeName());
		activityType.setActivityTypeCode(data.getActivityTypeCode());
		activityType.setActivityTypeName(data.getActivityTypeName());
		activityType.setPrice(data.getPrice());		
		activityType.setCreatedBy(getId());

		begin();
		ActivityType activityTypeInsert = activityTypeDao.save(activityType);
		commit();
		
		InsertActivityTypeDtoDataRes activityTypeId = new InsertActivityTypeDtoDataRes();
		activityTypeId.setId(activityTypeInsert.getId());

		InsertActivityTypeDtoRes result = new InsertActivityTypeDtoRes();
		result.setData(activityTypeId);
		result.setMsg("Insert Successfully");
		
		return result;
	}

	public UpdateActivityTypeDtoRes update(UpdateActivityTypeDtoReq data) throws Exception {
		ActivityType activityType = activityTypeDao.getById(data.getId());
		valBkNotExist(data.getActivityTypeName());
		activityType.setActivityTypeName(data.getActivityTypeName());
		activityType.setPrice(data.getPrice());		
		activityType.setVersion(data.getVersion());
		activityType.setIsActive(data.getIsActive());
		activityType.setUpdatedBy(getId());

		begin();
		ActivityType activityTypeUpdate = activityTypeDao.save(activityType);
		commit();

		UpdateActivityTypeDtoDataRes activityTypeVersion = new UpdateActivityTypeDtoDataRes();
		activityTypeVersion.setVersion(activityTypeUpdate.getVersion());

		UpdateActivityTypeDtoRes result = new UpdateActivityTypeDtoRes();
		result.setData(activityTypeVersion);
		result.setMsg("Update Successfully");
		return result;
	}
	
	public DeleteActivityTypeDtoRes deleteById(String id) throws Exception {
		try {			
			begin();
			boolean isDeleted = activityTypeDao.deleteById(id);
			commit();
			
			DeleteActivityTypeDtoRes activityTypeRes = new DeleteActivityTypeDtoRes();
			if(isDeleted) {
				activityTypeRes.setMsg("Delete Successfully");
			} else {
				activityTypeRes.setMsg("Delete Unsuccessfully");
			}
			
			return activityTypeRes;
		}
		catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	private void valBkNotExist(String code, String name) {
		Integer flagCode = activityTypeDao.isActivityTypeCodeExist(code);
		Integer flagName = activityTypeDao.isActivityTypeNameExist(name);
		if(flagCode == 1 || flagName == 1) {
			throw new RuntimeException("Activity Type Code or Activity Type Name has existed");
		}
	}
	
	private void valBkNotExist(String name) {
		Integer flagName = activityTypeDao.isActivityTypeNameExist(name);
		if(flagName == 1) {
			throw new RuntimeException("Activity Type Name has existed");
		}
	}

}
