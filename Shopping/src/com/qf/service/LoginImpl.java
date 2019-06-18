package com.qf.service;

import java.util.List;

import com.qf.bean.CustomerInfo;
import com.qf.dao.ReceptionLoginDao;

public class LoginImpl {

	public static List<CustomerInfo> validationImpl(String email,String pwd){
		return ReceptionLoginDao.loginValidation(email, pwd);
	}
	
	public static String getUserName(String email) {
		return ReceptionLoginDao.getUserName(email);
	}
}
