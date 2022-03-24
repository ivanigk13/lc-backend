package com.lawencon.community.constant;

public enum ActivityType {
	EVENT("AT01"), COURSE("AT02");
	
	private String activityTypeCode;
	
	ActivityType(String activityTypeCode) {
		this.activityTypeCode = activityTypeCode;
	}

	public String getActivityTypeCode() {
		return activityTypeCode;
	}
	
}
