package com.qf.service;

import java.util.ArrayList;

import com.qf.bean.ShoppingCarInfo;
import com.qf.dao.AddOrderInfoDao;

public class AddOrderInfoService {

	public static int addOrder(int userId,ArrayList<ShoppingCarInfo> list) {
		return AddOrderInfoDao.addOrder(userId, list);
	}
}
