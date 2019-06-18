package com.qf.admin.service;

import com.qf.admin.dao.RemoveGoodsDao;

public class RemoveGoodsService {

	public static int removeMoreGoods(String [] arrs) {
		return RemoveGoodsDao.removeMore(arrs);
	}
	
	public static int removeSingleGoods(int goodsId) {
		return RemoveGoodsDao.removeSingle(goodsId);
	}
}
