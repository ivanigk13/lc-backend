package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.SocialMedia;

@Repository
public class SocialMediaDao extends AbstractJpaDao<SocialMedia>{

	public SocialMedia getByUserId(String userId) {
		String sql = "SELECT id, instagram, facebook, twitter, version, is_active FROM social_media WHERE created_by = :id";
		Object result = createNativeQuery(sql).setParameter("id", userId).getSingleResult();
		Object[] obj = (Object[]) result;
		SocialMedia socialMedia = new SocialMedia();
		socialMedia.setId(obj[0].toString());
		if(obj[1]!=null) socialMedia.setInstagram(obj[1].toString());
		if(obj[2]!=null) socialMedia.setFacebook(obj[2].toString());
		if(obj[3]!=null) socialMedia.setTwitter(obj[3].toString());
		socialMedia.setVersion(Integer.valueOf(obj[4].toString()));
		socialMedia.setIsActive(Boolean.valueOf(obj[5].toString()));
		
		return socialMedia;
	}
}
