package com.qf.service;

import com.qf.dao.AccordingIdGetBulletinInfo;

public class AccordingIdGetUserName {
	public static String getSingleBulletinName(int userId) {
		//System.out.println(userId);
		return AccordingIdGetBulletinInfo.getPublisherName(userId);
	}

}
