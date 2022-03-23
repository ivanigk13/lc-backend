package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Profile extends BaseEntity{

	private static final long serialVersionUID = -6714493065941123329L;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user; 
	
	@OneToOne
	@JoinColumn(name = "industry_id")
	private Industry industry;
	
	@OneToOne
	@JoinColumn(name = "position_id")
	private Position position;
	
	@OneToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	@OneToOne
	@JoinColumn(name = "social_media_id")
	private SocialMedia socialMedia;
	
	private String fullName;
	private String companyName;
	private String phoneNumber;
	private String postalCode;
}
