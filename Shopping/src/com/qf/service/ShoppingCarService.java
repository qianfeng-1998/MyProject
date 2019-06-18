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
		//�õ�ԭ��������
		ArrayList<ShoppingCarInfo> list=ShoppingCarDao.checkShoppingCar(car.getGoodsId(), car.getUserId());
		int oldNum=list.get(0).getQuantity();
		//System.out.println("serviceԭ����������"+oldNum);
		return ShoppingCarDao.updateShoppingCarQuantity(car,oldNum);
	}
}
