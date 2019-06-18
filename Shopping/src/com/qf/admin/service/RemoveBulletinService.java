package com.qf.admin.service;

import com.qf.admin.dao.RemoveBulletinDao;

public class RemoveBulletinService {

	//多个
	public static int removeBulletin(String [] arrs) {
		return RemoveBulletinDao.remove(arrs);
	}
	
	//单个
	public static int removeSingleBulletin(int id) {
		return RemoveBulletinDao.removeSingle(id);
	}
	
	/**
	 * 修改单个
	 * */
	public static int  updateBulletin(int id,String title,String content) {
		return RemoveBulletinDao.updateSingle(id, title, content);
	}
}
