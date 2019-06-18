package com.qf.admin.service;

import com.qf.admin.dao.UpdateGoodsDao;
import com.qf.bean.GoodsInfo;

public class UpdateGoodsService {

	public static int updateGoods(GoodsInfo gi) {
		return UpdateGoodsDao.updateSingle(gi);
	}
}
