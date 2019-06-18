package com.qf.admin.service;

import com.qf.admin.dao.RemoveOrderDao;

public class RemoveOrderService {

	public static int deleteOrderInfo(int orderId) {
		return RemoveOrderDao.removeOrder(orderId);
	}
	
	public static int deleteMoreOrderInfo(String [] arrs) {
		return RemoveOrderDao.removeMore(arrs);
	}
}
