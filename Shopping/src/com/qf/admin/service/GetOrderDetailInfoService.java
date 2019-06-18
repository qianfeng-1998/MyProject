package com.qf.admin.service;

import com.qf.admin.dao.GetOrderDetailInfoDao;

public class GetOrderDetailInfoService {

	public static int getCustomerIdByOrderId(int orderId) {
		return GetOrderDetailInfoDao.getCustomerId(orderId);
	}

}

