package com.qf.admin.service;

import java.util.List;

import com.qf.admin.dao.RemoveTypeCheckOrderDao;
import com.qf.bean.OrderGoodsInfo;

public class RemoveTypeCheckOrderService {

	public static List<Integer> getGoodsIdList(int typeId){
		return RemoveTypeCheckOrderDao.getGoodsId(typeId);
	}
	
	public static List<OrderGoodsInfo> validation(List<Integer> list){
		return RemoveTypeCheckOrderDao.validation(list);
	}
	
	public static int removeGoodsType(int typeId) {
		return RemoveTypeCheckOrderDao.remove(typeId);
	}
	
	public static List<Integer> getGoodsIdByArrays(String [] arrs){
		return RemoveTypeCheckOrderDao.getGoodsIdByArrays(arrs);
	}
	
	public static List<OrderGoodsInfo> validationGoodsIdArrays(List<Integer> list){
		return RemoveTypeCheckOrderDao.validationGoodsIdArrays(list);
	}
	
	public static int removeMore(String [] arrs) {
		return RemoveTypeCheckOrderDao.removeMore(arrs);
	}
}
