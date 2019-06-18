package com.qf.service;

import java.util.List;

import com.qf.bean.GoodsInfo;
import com.qf.dao.GetAllGoodsInfoDao;

public class GetGoodsInfoService {
	
	public static List<GoodsInfo> getGoodsInfo(){
		return GetAllGoodsInfoDao.getAllGoods();
	}

}
