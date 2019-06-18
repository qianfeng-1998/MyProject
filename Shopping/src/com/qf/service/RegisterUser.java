package com.qf.service;

import com.qf.bean.CustomerInfo;
import com.qf.dao.RegisterSingleUserDao;

public class RegisterUser {
	public static int registerSingleUserService(CustomerInfo ci) {
		return RegisterSingleUserDao.registerSingleUser(ci);
	}

}
