package com.qf.admin.service;

import com.qf.admin.dao.UpdateCustomerDao;
import com.qf.bean.CustomerInfo;

public class UpdateCustomerService {

	public static int updateCustomer(CustomerInfo ci) {
		return UpdateCustomerDao.update(ci);
	}
}