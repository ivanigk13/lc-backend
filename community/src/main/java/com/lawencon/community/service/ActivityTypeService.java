package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.base.BaseService;
import com.lawencon.community.dao.ActivityTypeDao;
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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityTypeService extends BaseService {

	private final ActivityTypeDao activityTypeDao;

	public InsertActivityTypeDtoRes insert(InsertActivityTypeDtoReq data) throws Exception {
		ActivityType activityType = new ActivityType();
		activityType.setActivityTypeName(data.getActivityTypeName());
		activityType.setActivityTypeCode(data.getActivityTypeCode());

		begin();
		ActivityType activityTypeInsert = activityTypeDao.save(activityType);
		InsertActivityTypeDtoDataRes activityTypeId = new InsertActivityTypeDtoDataRes();
		activityTypeId.setId(activityTypeInsert.getId());

		InsertActivityTypeDtoRes result = new InsertActivityTypeDtoRes();
		result.setData(activityTypeId);
		result.setMsg("Insert Successfully");
		commit();
		return result;
	}

	public UpdateActivityTypeDtoRes update(UpdateActivityTypeDtoReq data) throws Exception {
		ActivityType activityType = activityTypeDao.getById(data.getId());
		activityType.setActivityTypeName(data.getActivityTypeName());
		activityType.setVersion(data.getVersion());
		activityType.setIsActive(data.getIsActive());

		begin();
		ActivityType activityTypeUpdate = activityTypeDao.save(activityType);

		UpdateActivityTypeDtoDataRes activityTypeVersion = new UpdateActivityTypeDtoDataRes();
		activityTypeVersion.setVersion(activityTypeUpdate.getVersion());

		UpdateActivityTypeDtoRes result = new UpdateActivityTypeDtoRes();
		result.setData(activityTypeVersion);
		result.setMsg("Update Successfully");
		return result;
	}

	public GetAllActivityTypeDtoRes getAll() throws Exception {
		List<ActivityType> activityTypes = activityTypeDao.getAll();
		List<GetActivityTypeDtoDataRes> data = new ArrayList<>();

		activityTypes.forEach(list -> {
			GetActivityTypeDtoDataRes ActivityType = new GetActivityTypeDtoDataRes();
			ActivityType.setId(list.getId());
			ActivityType.setActivityTypeName(list.getActivityTypeName());
			ActivityType.setActivityTypeCode(list.getActivityTypeCode());
			ActivityType.setVersion(list.getVersion());
			ActivityType.setIsActive(list.getIsActive());
			data.add(ActivityType);
		});

		GetAllActivityTypeDtoRes result = new GetAllActivityTypeDtoRes();
		result.setData(data);

		return result;
	}

	public GetByIdActivityTypeDtoRes getById(String id) throws Exception {
		ActivityType activityType = activityTypeDao.getById(id);

		GetActivityTypeDtoDataRes ActivityTypeData = new GetActivityTypeDtoDataRes();
		ActivityTypeData.setId(activityType.getId());
		ActivityTypeData.setActivityTypeName(activityType.getActivityTypeName());
		ActivityTypeData.setActivityTypeCode(activityType.getActivityTypeCode());
		ActivityTypeData.setVersion(activityType.getVersion());
		ActivityTypeData.setIsActive(activityType.getIsActive());

		GetByIdActivityTypeDtoRes result = new GetByIdActivityTypeDtoRes();
		result.setData(ActivityTypeData);

		return result;
	}

}
