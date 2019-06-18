package com.qf.service;

import com.qf.dao.ClearShoppingCarByUserIdDao;

public class ClearShoppingCarService {

	public static int clearAllCar(int userId) {
		return ClearShoppingCarByUserIdDao.clearAll(userId);
	}
	
	public static int clearSingleCar(int goodsId,int userId) {
		return ClearShoppingCarByUserIdDao.clearSingle(goodsId, userId);
	}
}
