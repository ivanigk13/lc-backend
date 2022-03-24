package com.lawencon.community.service;

import org.springframework.stereotype.Service;

import com.lawencon.base.BaseService;
import com.lawencon.community.dao.SocialMediaDao;
import com.lawencon.community.dto.socialmedia.GetByIdSocialMediaDtoRes;
import com.lawencon.community.dto.socialmedia.GetSocialMediaDtoDataRes;
import com.lawencon.community.dto.socialmedia.InsertSocialMediaDtoDataRes;
import com.lawencon.community.dto.socialmedia.InsertSocialMediaDtoReq;
import com.lawencon.community.dto.socialmedia.InsertSocialMediaDtoRes;
import com.lawencon.community.dto.socialmedia.UpdateSocialMediaDtoDataRes;
import com.lawencon.community.dto.socialmedia.UpdateSocialMediaDtoReq;
import com.lawencon.community.dto.socialmedia.UpdateSocialMediaDtoRes;
import com.lawencon.community.model.SocialMedia;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocialMediaService extends BaseService{

	private final SocialMediaDao socialMediaDao;
	
	public InsertSocialMediaDtoRes insert(InsertSocialMediaDtoReq data) throws Exception {
		SocialMedia socialMedia = new SocialMedia();
		socialMedia.setInstagram(data.getInstagram());
		socialMedia.setFacebook(data.getFacebook());
		socialMedia.setTwitter(data.getTwitter());

		begin();
		SocialMedia socialMediaInsert = socialMediaDao.save(socialMedia);
		InsertSocialMediaDtoDataRes socialMediaId = new InsertSocialMediaDtoDataRes();
		socialMediaId.setId(socialMediaInsert.getId());

		InsertSocialMediaDtoRes result = new InsertSocialMediaDtoRes();
		result.setData(socialMediaId);
		result.setMsg("Insert Successfully");
		commit();
		return result;
	}

	public UpdateSocialMediaDtoRes update(UpdateSocialMediaDtoReq data) throws Exception {
		SocialMedia socialMedia = socialMediaDao.getById(data.getId());
		
		if(data.getInstagram() != null) {
		socialMedia.setInstagram(data.getInstagram());
		}
		if(data.getFacebook() != null) {
		socialMedia.setFacebook(data.getFacebook());
		}
		
		if(data.getTwitter() != null) {
		socialMedia.setTwitter(data.getTwitter());
		}
		socialMedia.setVersion(data.getVersion());
		socialMedia.setIsActive(data.getIsActive());
		

		begin();
		SocialMedia socialMediaUpdate = socialMediaDao.save(socialMedia);

		UpdateSocialMediaDtoDataRes socialMediaVersion = new UpdateSocialMediaDtoDataRes();
		socialMediaVersion.setVersion(socialMediaUpdate.getVersion());

		UpdateSocialMediaDtoRes result = new UpdateSocialMediaDtoRes();
		result.setData(socialMediaVersion);
		result.setMsg("Update Successfully");
		return result;
	}

	public GetByIdSocialMediaDtoRes getById(String id) throws Exception {
		SocialMedia socialMedia = socialMediaDao.getById(id);

		GetSocialMediaDtoDataRes socialMediaData = new GetSocialMediaDtoDataRes();
		socialMediaData.setId(socialMedia.getId());
		if(socialMedia.getInstagram() != null) {
		socialMediaData.setInstagram(socialMedia.getInstagram());
		}
		if(socialMedia.getFacebook() != null) {
		socialMediaData.setFacebook(socialMedia.getFacebook());
		}
		if(socialMedia.getTwitter() != null) {
		socialMediaData.setTwitter(socialMedia.getTwitter());
		}
		socialMediaData.setVersion(socialMedia.getVersion());
		socialMediaData.setIsActive(socialMedia.getIsActive());

		GetByIdSocialMediaDtoRes result = new GetByIdSocialMediaDtoRes();
		result.setData(socialMediaData);

		return result;
	}
	
}
