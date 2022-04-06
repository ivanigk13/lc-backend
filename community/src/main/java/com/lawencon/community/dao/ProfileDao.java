package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.City;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Industry;
import com.lawencon.community.model.Position;
import com.lawencon.community.model.Profile;
import com.lawencon.community.model.SocialMedia;
import com.lawencon.community.model.User;

@Repository
public class ProfileDao extends AbstractJpaDao<Profile>{

	public Profile getByUserId(String id) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT id, user_id, industry_id, position_id, city_id, file_id, social_media_id, ");
		builder.append("full_name, company_name, phone_number, postal_code, version, is_active ");
		builder.append("FROM profile ");
		builder.append("WHERE user_id = :id");
		Object result = createNativeQuery(builder.toString()).setParameter("id", id).getSingleResult();
		Object[] obj = (Object[]) result;
		Profile profile = new Profile();
		profile.setId(obj[0].toString());
		
		User user = new User();
		user.setId(obj[1].toString());
		profile.setUser(user);
		
		Industry industry = new Industry();
		if(obj[2]!=null) industry.setId(obj[2].toString());
		profile.setIndustry(industry);
		
		Position position = new Position();
		if(obj[3]!=null) position.setId(obj[3].toString());
		profile.setPosition(position);
		
		City city = new City();
		if(obj[4]!=null) city.setId(obj[4].toString());
		profile.setCity(city);
		
		File file = new File();
		if(obj[5]!=null) file.setId(obj[5].toString());
		profile.setFile(file);
		
		SocialMedia media = new SocialMedia();
		if(obj[6]!=null) media.setId(obj[6].toString());
		profile.setSocialMedia(media);
		
		profile.setFullName(obj[7].toString());
		profile.setCompanyName(obj[8].toString());
		profile.setPhoneNumber(obj[9].toString());
		if(obj[10]!=null) profile.setPostalCode(obj[10].toString());
		profile.setVersion(Integer.valueOf(obj[11].toString()));
		profile.setIsActive(Boolean.valueOf(obj[12].toString()));
		
		return profile;
	}
}
