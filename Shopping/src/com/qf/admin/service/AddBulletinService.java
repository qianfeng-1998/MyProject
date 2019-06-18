package com.qf.admin.service;

import com.qf.admin.dao.AddBulletinDao;

public class AddBulletinService {

	public static int addBulletinInfo(String title,String content,int userId) {
		return AddBulletinDao.insertBulletin(title, content, userId);
	}
}
