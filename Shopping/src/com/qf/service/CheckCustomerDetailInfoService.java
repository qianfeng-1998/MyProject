package com.qf.service;

import java.util.ArrayList;

import com.qf.bean.CustomerInfo;
import com.qf.dao.CheckDetailInfoDao;

public class CheckCustomerDetailInfoService {

	public static ArrayList<CustomerInfo> checkDetailInfo(int userId){
		return CheckDetailInfoDao.check(userId);
	}
}
