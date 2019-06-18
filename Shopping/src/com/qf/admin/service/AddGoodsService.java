package com.qf.admin.service;

import com.qf.admin.dao.AddGoodsDao;
import com.qf.bean.GoodsInfo;

public class AddGoodsService {

	public static int insertGoodsService(GoodsInfo gi) {
		return AddGoodsDao.add(gi);
	}
}
