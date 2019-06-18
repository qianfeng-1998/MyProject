package com.qf.admin.service;

import com.qf.admin.dao.UpdateGoodsTypeDao;
import com.qf.bean.GoodsTypeInfo;

public class UpdateGoodsTypeService {

	public static int updateTypeInfo(GoodsTypeInfo gti) {
		return UpdateGoodsTypeDao.update(gti);
	}
	
	public static GoodsTypeInfo getGoodsTypeInfo(int typeId) {
		return UpdateGoodsTypeDao.getTypeInfo(typeId);
	}
}
