package com.qf.service;

import com.qf.bean.ShoppingCarInfo;
import com.qf.dao.UpdateShoppingCarQuantityDao;

public class UpdateCarService {

	public static int updateCarQuantity(ShoppingCarInfo car) {
		return UpdateShoppingCarQuantityDao.updateCar(car);
	}
}
