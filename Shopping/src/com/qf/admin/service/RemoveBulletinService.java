package com.qf.admin.service;

import com.qf.admin.dao.RemoveBulletinDao;

public class RemoveBulletinService {

	//���
	public static int removeBulletin(String [] arrs) {
		return RemoveBulletinDao.remove(arrs);
	}
	
	//����
	public static int removeSingleBulletin(int id) {
		return RemoveBulletinDao.removeSingle(id);
	}
	
	/**
	 * �޸ĵ���
	 * */
	public static int  updateBulletin(int id,String title,String content) {
		return RemoveBulletinDao.updateSingle(id, title, content);
	}
}
