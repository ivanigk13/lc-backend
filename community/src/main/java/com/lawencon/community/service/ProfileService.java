package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.base.BaseService;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.ProfileDao;
import com.lawencon.community.dto.profile.DeleteProfileDtoRes;
import com.lawencon.community.dto.profile.GetAllProfileDtoRes;
import com.lawencon.community.dto.profile.GetByIdProfileDtoRes;
import com.lawencon.community.dto.profile.GetProfileDtoDataRes;
import com.lawencon.community.dto.profile.InsertProfileDtoDataRes;
import com.lawencon.community.dto.profile.InsertProfileDtoReq;
import com.lawencon.community.dto.profile.InsertProfileDtoRes;
import com.lawencon.community.dto.profile.UpdateProfileDtoDataRes;
import com.lawencon.community.dto.profile.UpdateProfileDtoReq;
import com.lawencon.community.dto.profile.UpdateProfileDtoRes;
import com.lawencon.community.model.City;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Industry;
import com.lawencon.community.model.Position;
import com.lawencon.community.model.Profile;
import com.lawencon.community.model.SocialMedia;
import com.lawencon.community.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService extends BaseService {
	
	private final ProfileDao profileDao;
	private final FileDao fileDao;
	
	public InsertProfileDtoRes insert(String data, MultipartFile photo) throws Exception {
		try {
			InsertProfileDtoReq profileReq = new ObjectMapper().readValue(data, InsertProfileDtoReq.class);
			Profile profile = new Profile();
			User user = new User();
			user.setId(profileReq.getUserId());
			profile.setUser(user);
			Industry industry = new Industry();
			industry.setId(profileReq.getIndustryId());
			profile.setIndustry(industry);
			Position position = new Position();
			position.setId(profileReq.getPositionId());
			City city = new City();
			city.setId(profileReq.getCityId());
			
			File file = new File();
			String splitterFile = photo.getOriginalFilename().substring(
					photo.getOriginalFilename().lastIndexOf(".") + 1, photo.getOriginalFilename().length());
			file.setExtensionName(splitterFile);
			file.setContent(photo.getBytes());
			file.setCreatedBy("ID created BY");
			
			begin();
			file = fileDao.save(file);
			
			profile.setFile(file);
			SocialMedia socialMedia = new SocialMedia();
			socialMedia.setId(profileReq.getSocialMediaId());
			profile.setFullName(profileReq.getFullName());
			profile.setPhoneNumber(profileReq.getPhoneNumber());
			profile.setPostalCode(profileReq.getPostalCode());
			profile.setCreatedBy("Profile Created By");
			
			profile = profileDao.save(profile);
			commit();
			
			InsertProfileDtoDataRes profileDataRes = new InsertProfileDtoDataRes();
			profileDataRes.setId(profile.getId());
			
			InsertProfileDtoRes profileRes = new InsertProfileDtoRes();
			profileRes.setMsg("Insert Successfully");
			profileRes.setData(profileDataRes);
			
			return profileRes;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
	}

	public UpdateProfileDtoRes update(UpdateProfileDtoReq data, MultipartFile photo) throws Exception {
		try {
			Profile profile = profileDao.getById(data.getId());
			Industry industry = new Industry();
			industry.setId(data.getIndustryId());
			profile.setIndustry(industry);
			Position position = new Position();
			position.setId(data.getPositionId());
			City city = new City();
			city.setId(data.getCityId());
			
			File file = new File();
			String splitterFile = photo.getOriginalFilename().substring(
					photo.getOriginalFilename().lastIndexOf(".") + 1, photo.getOriginalFilename().length());
			file.setExtensionName(splitterFile);
			file.setContent(photo.getBytes());
			file.setCreatedBy("ID created BY");
			
			begin();
			file = fileDao.save(file);
			
			profile.setFile(file);
			SocialMedia socialMedia = new SocialMedia();
			socialMedia.setId(data.getSocialMediaId());
			profile.setFullName(data.getFullName());
			profile.setPhoneNumber(data.getPhoneNumber());
			profile.setPostalCode(data.getPostalCode());
			profile.setUpdatedBy("Profile Updated By");
			profile.setVersion(data.getVersion());
			profile.setIsActive(data.getIsActive());
			
			profile = profileDao.save(profile);
			commit();
			
			UpdateProfileDtoDataRes profileDataRes = new UpdateProfileDtoDataRes();
			profileDataRes.setVersion(profile.getVersion());
			
			UpdateProfileDtoRes profileRes = new UpdateProfileDtoRes();
			profileRes.setMsg("Update Successfully");
			profileRes.setData(profileDataRes);
			
			return profileRes;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
	}

	public GetByIdProfileDtoRes getById(String id) throws Exception {
		Profile profile = profileDao.getById(id);
		GetProfileDtoDataRes profileDataRes = new GetProfileDtoDataRes();
		profileDataRes.setId(profile.getId());
		profileDataRes.setUserId(profile.getUser().getId());
		profileDataRes.setIndustryId(profile.getIndustry().getId());
		profileDataRes.setPositionId(profile.getPosition().getId());
		profileDataRes.setCityId(profile.getCity().getId());
		profileDataRes.setFileId(profile.getFile().getId());
		profileDataRes.setSocialMediaId(profile.getSocialMedia().getId());
		profileDataRes.setFullName(profile.getFullName());
		profileDataRes.setPhoneNumber(profile.getPhoneNumber());
		profileDataRes.setPositionId(profile.getPostalCode());
		profileDataRes.setVersion(profile.getVersion());
		profileDataRes.setIsActive(profile.getIsActive());
		
		GetByIdProfileDtoRes profileRes = new GetByIdProfileDtoRes();
		profileRes.setData(profileDataRes);
		
		return profileRes;
	}

	public GetAllProfileDtoRes getAll() throws Exception {
		List<Profile> profiles = profileDao.getAll();
		
		List<GetProfileDtoDataRes> data = new ArrayList<GetProfileDtoDataRes>();
		
		profiles.forEach(profile -> {
			GetProfileDtoDataRes profileDataRes = new GetProfileDtoDataRes();
			profileDataRes.setId(profile.getId());
			profileDataRes.setUserId(profile.getUser().getId());
			profileDataRes.setIndustryId(profile.getIndustry().getId());
			profileDataRes.setPositionId(profile.getPosition().getId());
			profileDataRes.setCityId(profile.getCity().getId());
			profileDataRes.setFileId(profile.getFile().getId());
			profileDataRes.setSocialMediaId(profile.getSocialMedia().getId());
			profileDataRes.setFullName(profile.getFullName());
			profileDataRes.setPhoneNumber(profile.getPhoneNumber());
			profileDataRes.setPositionId(profile.getPostalCode());
			profileDataRes.setVersion(profile.getVersion());
			profileDataRes.setIsActive(profile.getIsActive());
			
			data.add(profileDataRes);
		});
		
		GetAllProfileDtoRes profileRes = new GetAllProfileDtoRes();
		profileRes.setData(data);
		
		return profileRes;
	}

	public DeleteProfileDtoRes deleteById(String id) throws Exception {
		
		begin();
		profileDao.deleteById(id);
		commit();
		
		DeleteProfileDtoRes profileRes = new DeleteProfileDtoRes();
		profileRes.setMsg("Delete Successfully");
		
		return profileRes;
	}  

}
