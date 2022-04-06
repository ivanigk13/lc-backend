package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class ProfileService extends BaseCommunityService {
	
	private final ProfileDao profileDao;
	private final FileDao fileDao;
	
	public InsertProfileDtoRes insert(InsertProfileDtoReq profileReq) throws Exception {
		try {
			Profile profile = new Profile();
			User user = new User();
			user.setId(profileReq.getUserId());
			profile.setUser(user);
			profile.setFullName(profileReq.getFullName());
			profile.setPhoneNumber(profileReq.getPhoneNumber());
			profile.setCompanyName(profileReq.getCompanyName());
			Industry industry = new Industry();
			industry.setId(profileReq.getIndustryId());
			profile.setIndustry(industry);
			Position position = new Position();
			position.setId(profileReq.getPositionId());
			profile.setPosition(position);		
			profile.setCreatedBy(getId());
			
			begin();
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

	public UpdateProfileDtoRes update(String data, MultipartFile photo) throws Exception {
		try {
			UpdateProfileDtoReq profileReq = new ObjectMapper().readValue(data, UpdateProfileDtoReq.class);
			Profile profile = profileDao.getById(profileReq.getId());
			Industry industry = new Industry();
			industry.setId(profileReq.getIndustryId());
			profile.setIndustry(industry);
			Position position = new Position();
			position.setId(profileReq.getPositionId());
			profile.setPosition(position);
			City city = new City();
			city.setId(profileReq.getCityId());
			profile.setCity(city);
			
			File file = new File();
			String splitterFile = photo.getOriginalFilename().substring(
					photo.getOriginalFilename().lastIndexOf(".") + 1, photo.getOriginalFilename().length());
			file.setExtensionName(splitterFile);
			file.setContent(photo.getBytes());
			file.setCreatedBy(getId());
			
			begin();
			file = fileDao.save(file);
			
			profile.setFile(file);
			if(profileReq.getSocialMediaId() != null) {
				SocialMedia socialMedia = new SocialMedia();
				socialMedia.setId(profileReq.getSocialMediaId());
				profile.setSocialMedia(socialMedia);
			}
			profile.setFullName(profileReq.getFullName());
			profile.setCompanyName(profileReq.getCompanyName());
			profile.setPhoneNumber(profileReq.getPhoneNumber());
			profile.setPostalCode(profileReq.getPostalCode());
			profile.setUpdatedBy(getId());
			profile.setVersion(profileReq.getVersion());
			profile.setIsActive(profileReq.getIsActive());
			
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
		if(profile.getCity()!=null) {
			profileDataRes.setCityId(profile.getCity().getId());
			profileDataRes.setProvinceId(profile.getCity().getProvince().getId());		
		}
		if(profile.getFile()!=null) {
			profileDataRes.setFileId(profile.getFile().getId());
		}
		
		if(profile.getSocialMedia() != null ) {
			profileDataRes.setSocialMediaId(profile.getSocialMedia().getId());
		}
		profileDataRes.setFullName(profile.getFullName());
		profileDataRes.setCompanyName(profile.getCompanyName());
		profileDataRes.setPhoneNumber(profile.getPhoneNumber());
		profileDataRes.setPostalCode(profile.getPostalCode());
		profileDataRes.setVersion(profile.getVersion());
		profileDataRes.setIsActive(profile.getIsActive());
		
		GetByIdProfileDtoRes profileRes = new GetByIdProfileDtoRes();
		profileRes.setData(profileDataRes);
		
		return profileRes;
	}

	public GetAllProfileDtoRes getAll(Integer start, Integer max) throws Exception {
		List<Profile> profiles;
		if(start == null) profiles = profileDao.getAll();
		else profiles = profileDao.getAll(start, max);
		
		List<GetProfileDtoDataRes> data = new ArrayList<GetProfileDtoDataRes>();
		
		profiles.forEach(profile -> {
			GetProfileDtoDataRes profileDataRes = new GetProfileDtoDataRes();
			profileDataRes.setId(profile.getId());
			profileDataRes.setUserId(profile.getUser().getId());
			profileDataRes.setIndustryId(profile.getIndustry().getId());
			profileDataRes.setPositionId(profile.getPosition().getId());
			if(profile.getCity()!=null) {
				profileDataRes.setCityId(profile.getCity().getId());
				profileDataRes.setProvinceId(profile.getCity().getProvince().getId());		
			}
			
			if(profile.getSocialMedia() != null) {
				profileDataRes.setFileId(profile.getFile().getId());
				profileDataRes.setSocialMediaId(profile.getSocialMedia().getId());
			}
			
			profileDataRes.setFullName(profile.getFullName());
			profileDataRes.setCompanyName(profile.getCompanyName());
			profileDataRes.setPhoneNumber(profile.getPhoneNumber());
			profileDataRes.setPostalCode(profile.getPostalCode());
			profileDataRes.setVersion(profile.getVersion());
			profileDataRes.setIsActive(profile.getIsActive());
			
			data.add(profileDataRes);
		});
		
		GetAllProfileDtoRes profileRes = new GetAllProfileDtoRes();
		profileRes.setData(data);
		
		return profileRes;
	}

	public DeleteProfileDtoRes deleteById(String id) throws Exception {
		
		try {			
			begin();
			boolean isDeleted = profileDao.deleteById(id);
			commit();
			
			DeleteProfileDtoRes profileRes = new DeleteProfileDtoRes();
			if(isDeleted) {
				profileRes.setMsg("Delete Successfully");
			} else {
				profileRes.setMsg("Delete Unsuccessfully");
			}
			
			return profileRes;
		}
		catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}  

	public GetByIdProfileDtoRes getByUserId(String id) throws Exception {
		Profile profile = profileDao.getByUserId(id);
		GetProfileDtoDataRes profileDataRes = new GetProfileDtoDataRes();
		profileDataRes.setId(profile.getId());
		profileDataRes.setUserId(profile.getUser().getId());
		profileDataRes.setIndustryId(profile.getIndustry().getId());
		profileDataRes.setPositionId(profile.getPosition().getId());
		if(profile.getCity()!=null) {
			profileDataRes.setCityId(profile.getCity().getId());
			profileDataRes.setProvinceId(profile.getCity().getProvince().getId());		
		}
		if(profile.getFile()!=null) {
			profileDataRes.setFileId(profile.getFile().getId());
		}
		
		if(profile.getSocialMedia() != null ) {
			profileDataRes.setSocialMediaId(profile.getSocialMedia().getId());
		}
		profileDataRes.setFullName(profile.getFullName());
		profileDataRes.setCompanyName(profile.getCompanyName());
		profileDataRes.setPhoneNumber(profile.getPhoneNumber());
		profileDataRes.setPostalCode(profile.getPostalCode());
		profileDataRes.setVersion(profile.getVersion());
		profileDataRes.setIsActive(profile.getIsActive());
		
		GetByIdProfileDtoRes profileRes = new GetByIdProfileDtoRes();
		profileRes.setData(profileDataRes);
		
		return profileRes;
	}
}
