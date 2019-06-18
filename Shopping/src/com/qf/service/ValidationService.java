package com.qf.service;

import java.util.List;

import com.qf.bean.CustomerInfo;
import com.qf.dao.ValidationDao;

public class ValidationService {

	public static List<CustomerInfo> validationService(String email){
		return ValidationDao.validationEmail(email);
	}
}
