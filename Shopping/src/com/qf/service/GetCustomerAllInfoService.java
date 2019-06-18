package com.qf.service;

import java.util.ArrayList;

import com.qf.bean.CustomerInfo;
import com.qf.dao.GetSingleCustomerAllInfoDao;

public class GetCustomerAllInfoService {

	public static ArrayList<CustomerInfo> getAllInfo(int userId){
		return GetSingleCustomerAllInfoDao.getSingleAllInfo(userId);
	}
}
