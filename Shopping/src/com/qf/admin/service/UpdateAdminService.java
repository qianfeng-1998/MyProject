package com.qf.admin.service;

import com.qf.admin.dao.UpdateAdminDao;

public class UpdateAdminService {

	public static int updateAdminPwd(String newPwd,String userName) {
		return UpdateAdminDao.updatePwd(newPwd, userName);
	}
	
	public static int updateAdminUserName(String newUserName,String oldUserName) {
		return UpdateAdminDao.updateUserName(newUserName, oldUserName);
	}
}
