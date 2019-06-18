package com.qf.service;

import java.util.ArrayList;

import com.qf.bean.ShoppingCarInfo;
import com.qf.dao.ShoppingCarDao;

public class ShoppingCarService {
	
	public static int insertShoppingCar(ShoppingCarInfo car) {
		return ShoppingCarDao.addCarDao(car);
	}
	
	public static ArrayList<ShoppingCarInfo> getShoppingCarInfo(int userId){
		return ShoppingCarDao.getShoppingCarInfo(userId);
	}

	public static ArrayList<ShoppingCarInfo> checkShoppingCarInfo(int goodsId,int userId){
		return ShoppingCarDao.checkShoppingCar(goodsId, userId);
	}
	
	public static int updateShoppingCar(ShoppingCarInfo car) {
		//得到原来的数量
		ArrayList<ShoppingCarInfo> list=ShoppingCarDao.checkShoppingCar(car.getGoodsId(), car.getUserId());
		int oldNum=list.get(0).getQuantity();
		//System.out.println("service原来的数量："+oldNum);
		return ShoppingCarDao.updateShoppingCarQuantity(car,oldNum);
	}
}
