package com.qf.service;

import java.util.List;

import com.qf.bean.GoodsInfo;
import com.qf.dao.GetSingleGoodsInfoDao;

public class GetSingleGoodsService {
	
	public static List<GoodsInfo> getSingleGoods(int goodsId){
		return GetSingleGoodsInfoDao.getAllGoods(goodsId);
	}
	
	public static List<GoodsInfo> accordingTypeIdGetGoodsInfo(int typeId){
		return GetSingleGoodsInfoDao.accordingTypeIdGetGoods(typeId);
	}

	public static List<GoodsInfo> getNineGoods(int count){
		return GetSingleGoodsInfoDao.getLastNineGoodsInfo(count);
	}
	
	public static List<GoodsInfo> getMaxDiscount(){
		return GetSingleGoodsInfoDao.getMaxDiscountNineGoods();
	}
}
