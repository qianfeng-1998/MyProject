package com.qf.service;

import java.util.List;

import com.qf.bean.GoodsTypeInfo;
import com.qf.dao.GetGoodsTypeDao;

public class GetGoodsTypeService {

	public static List<GoodsTypeInfo> getTypes(){
		return GetGoodsTypeDao.getGoodsType();
	}
	public static String getTypeName(int typeId) {
		return GetGoodsTypeDao.getTypeName(typeId);
	}
}
