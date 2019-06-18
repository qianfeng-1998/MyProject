package com.qf.service;

import java.util.ArrayList;

import com.qf.bean.GoodsInfo;
import com.qf.dao.GetRecommendGoodsDao;

public class GetRecommendGoodsService {
	
	public static ArrayList<GoodsInfo> getRecommendGoods(){
		return GetRecommendGoodsDao.getRecommendList();
	}

}
