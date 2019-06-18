package com.qf.admin.service;

import java.util.ArrayList;

import com.qf.admin.dao.AdminLoginDao;
import com.qf.bean.AdminUser;

public class AdminLohinService {

	public static ArrayList<AdminUser> validation(AdminUser user){
		return AdminLoginDao.adminLoginValidation(user);
	}
}
