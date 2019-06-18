package com.qf.admin.service;

import java.util.List;

import com.qf.admin.dao.GetSingleOrderDao;
import com.qf.bean.OrderInfo;

public class GetSingleOrderService {

	public static List<OrderInfo> getSingleOrder(int orderId){
		return GetSingleOrderDao.getSingle(orderId);
	}
}
