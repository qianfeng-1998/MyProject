package com.qf.admin.service;

import com.qf.admin.dao.UpdateOrderStatusDao;

public class UpdateOrderStatusService {

	public static int updateStatus(int status,int orderId) {
		int changeStatus;
		if (status==0) {
			changeStatus=1;
		}else {
			changeStatus=0;
		}
		return UpdateOrderStatusDao.update(orderId, changeStatus);
	}
}
