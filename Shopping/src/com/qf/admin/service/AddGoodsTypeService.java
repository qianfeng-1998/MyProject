package com.qf.admin.service;

import com.qf.admin.dao.AddGoodsTypeDao;

public class AddGoodsTypeService {

	public static int addGoodsType(String typeName) {
		return AddGoodsTypeDao.addType(typeName);
	}
}
