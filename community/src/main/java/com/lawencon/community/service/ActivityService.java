package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.community.dao.ActivityDao;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dto.activity.DeleteActivityDtoRes;
import com.lawencon.community.dto.activity.GetActivityDtoDataRes;
import com.lawencon.community.dto.activity.GetAllActivityDtoRes;
import com.lawencon.community.dto.activity.GetByIdActivityDtoRes;
import com.lawencon.community.dto.activity.InsertActivityDtoDataRes;
import com.lawencon.community.dto.activity.InsertActivityDtoReq;
import com.lawencon.community.dto.activity.InsertActivityDtoRes;
import com.lawencon.community.dto.activity.UpdateActivityDtoDataRes;
import com.lawencon.community.dto.activity.UpdateActivityDtoReq;
import com.lawencon.community.dto.activity.UpdateActivityDtoRes;
import com.lawencon.community.model.Activity;
import com.lawencon.community.model.ActivityType;
import com.lawencon.community.model.Category;
import com.lawencon.community.model.File;
import com.lawencon.community.model.TransactionStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService extends BaseCommunityService {

	private final ActivityDao activityDao;
	private final FileDao fileDao;

	public InsertActivityDtoRes insert(String data, MultipartFile[] files) throws Exception {
		try {
			InsertActivityDtoReq req = new ObjectMapper().readValue(data, InsertActivityDtoReq.class);
			Activity activity = new Activity();
			activity.setActivityName(req.getActivityName());

			ActivityType activityType = new ActivityType();
			activityType.setId(req.getActivityTypeId());
			activity.setActivityType(activityType);

			File file = new File();
			String splitterFile = files[0].getOriginalFilename().substring(
					files[0].getOriginalFilename().lastIndexOf(".") + 1, files[0].getOriginalFilename().length());
			file.setExtensionName(splitterFile);
			file.setContent(files[0].getBytes());
			file.setCreatedBy(getId());

			begin();
			File fileActivity = fileDao.save(file);
			activity.setFile(fileActivity);

			Category category = new Category();
			category.setId(req.getCategoryId());
			activity.setCategory(category);

			TransactionStatus transactionStatus = new TransactionStatus();
			transactionStatus.setId(req.getTransactionStatusId());
			activity.setTransactionStatus(transactionStatus);

			File paymentFile = new File();
			String splitterPaymentFile = files[1].getOriginalFilename().substring(
					files[1].getOriginalFilename().lastIndexOf(".") + 1, files[1].getOriginalFilename().length());
			file.setExtensionName(splitterPaymentFile);
			file.setContent(files[1].getBytes());
			file.setCreatedBy(getId());

			File paymentFileActivity = fileDao.save(paymentFile);
			activity.setFile(paymentFileActivity);

			activity.setDateStart(req.getDateStart());
			activity.setDateEnd(req.getDateEnd());
			activity.setTimeStart(req.getTimeStart());
			activity.setTimeEnd(req.getTimeEnd());

			Activity insertActivity = activityDao.save(activity);
			commit();

			InsertActivityDtoDataRes activityId = new InsertActivityDtoDataRes();
			activityId.setId(insertActivity.getId());

			InsertActivityDtoRes result = new InsertActivityDtoRes();
			result.setData(activityId);
			result.setMsg("Insert Successfully");

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public UpdateActivityDtoRes update(String data, MultipartFile[] files) throws Exception {
		UpdateActivityDtoReq req = new ObjectMapper().readValue(data, UpdateActivityDtoReq.class);
		Activity activity = activityDao.getById(req.getId());
		activity.setActivityName(req.getActivityName());

		File file = new File();
		String splitterFile = files[0].getOriginalFilename().substring(
				files[0].getOriginalFilename().lastIndexOf(".") + 1, files[0].getOriginalFilename().length());
		file.setExtensionName(splitterFile);
		file.setContent(files[0].getBytes());
		file.setUpdatedBy(getId());;

		begin();
		File fileActivity = fileDao.save(file);
		activity.setFile(fileActivity);

		File paymentFile = new File();
		String splitterPaymentFile = files[1].getOriginalFilename().substring(
				files[1].getOriginalFilename().lastIndexOf(".") + 1, files[1].getOriginalFilename().length());
		file.setExtensionName(splitterPaymentFile);
		file.setContent(files[1].getBytes());
		file.setUpdatedBy(getId());

		File paymentFileActivity = fileDao.save(paymentFile);
		activity.setFile(paymentFileActivity);

		activity.setDateStart(req.getDateStart());
		activity.setDateEnd(req.getDateEnd());
		activity.setTimeStart(req.getTimeStart());
		activity.setTimeEnd(req.getTimeEnd());
		activity.setPrice(req.getPrice());
		activity.setLocation(req.getLocation());
		activity.setVersion(req.getVersion());
		activity.setIsActive(req.getIsActive());

		Activity activityUpdate = activityDao.save(activity);
		commit();

		UpdateActivityDtoDataRes activityVersion = new UpdateActivityDtoDataRes();
		activityVersion.setVersion(activityUpdate.getVersion());

		UpdateActivityDtoRes result = new UpdateActivityDtoRes();
		result.setData(activityVersion);
		result.setMsg("Update Successfully");
		return result;
	}

	public GetAllActivityDtoRes getAll(Integer start, Integer max) throws Exception {
		List<Activity> activities;
		if(start == null) activities = activityDao.getAll();
		else activities = activityDao.getAll(start, max);
		
		List<GetActivityDtoDataRes> data = new ArrayList<>();

		activities.forEach(list -> {
			GetActivityDtoDataRes activityData = new GetActivityDtoDataRes();
			activityData.setId(list.getId());
			activityData.setActivityName(list.getActivityName());
			activityData.setActivityTypeId(list.getActivityType().getId());
			activityData.setFileId(list.getFile().getId());
			activityData.setPaymentFileId(list.getPaymentFile().getId());
			activityData.setDateStart(list.getDateStart());
			activityData.setDateEnd(list.getDateEnd());
			activityData.setTimeStart(list.getTimeStart());
			activityData.setTimeEnd(list.getTimeEnd());
			activityData.setPrice(list.getPrice());
			activityData.setLocation(list.getLocation());
			activityData.setCreatedBy(list.getCreatedBy());
			activityData.setVersion(list.getVersion());
			data.add(activityData);
		});

		GetAllActivityDtoRes result = new GetAllActivityDtoRes();
		result.setData(data);
		return result;
	}

	public GetByIdActivityDtoRes getById(String id) throws Exception {
		Activity activity = activityDao.getById(id);

		GetActivityDtoDataRes activityData = new GetActivityDtoDataRes();
		activityData.setId(activity.getId());
		activityData.setActivityName(activity.getActivityName());
		activityData.setActivityTypeId(activity.getActivityType().getId());
		activityData.setFileId(activity.getFile().getId());
		activityData.setPaymentFileId(activity.getPaymentFile().getId());
		activityData.setDateStart(activity.getDateStart());
		activityData.setDateEnd(activity.getDateEnd());
		activityData.setTimeStart(activity.getTimeStart());
		activityData.setTimeEnd(activity.getTimeEnd());
		activityData.setPrice(activity.getPrice());
		activityData.setLocation(activity.getLocation());
		activityData.setCreatedBy(activity.getCreatedBy());
		activityData.setVersion(activity.getVersion());

		GetByIdActivityDtoRes result = new GetByIdActivityDtoRes();
		result.setData(activityData);
		return result;
	}
	
	public DeleteActivityDtoRes deleteById(String id) throws Exception {
		begin();
		activityDao.deleteById(id);
		commit();
		
		DeleteActivityDtoRes activityRes = new DeleteActivityDtoRes();
		activityRes.setMsg("Delete Successfully");
		
		return activityRes;
	}

}
