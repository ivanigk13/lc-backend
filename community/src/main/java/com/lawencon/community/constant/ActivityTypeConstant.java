package com.lawencon.community.constant;

public enum ActivityTypeConstant {
	EVENT("AT01"), COURSE("AT02");
	
	private String activityTypeCode;
	
	ActivityTypeConstant(String activityTypeCode) {
		this.activityTypeCode = activityTypeCode;
	}

	public String getActivityTypeCode() {
		return activityTypeCode;
	}
	
}
