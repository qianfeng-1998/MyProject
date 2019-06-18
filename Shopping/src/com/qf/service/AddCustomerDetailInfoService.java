package com.qf.service;

import com.qf.bean.CustomerInfo;
import com.qf.dao.AddCustomerDetailInfoDao;

public class AddCustomerDetailInfoService {

	public static int insertDetail(CustomerInfo ci) {
		return AddCustomerDetailInfoDao.insert(ci);
	}
}
