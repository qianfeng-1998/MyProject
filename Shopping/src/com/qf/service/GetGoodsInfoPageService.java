package com.qf.service;

import java.util.List;

import com.qf.bean.GoodsInfo;
import com.qf.bean.PageModel;
import com.qf.dao.GetPageGoodsDao;

public class GetGoodsInfoPageService {
	
	public static PageModel<GoodsInfo> getGoodsPage(int pageNo,int typeId){
		//Áù¼þÒ»Ò³
		PageModel<GoodsInfo> page=new PageModel<>();
		List<GoodsInfo> list=GetPageGoodsDao.getPageList(pageNo, 6, typeId);
		int total=GetPageGoodsDao.getCount(typeId);
		
		page.setPageNo(pageNo);
		page.setPageSize(6);
		page.setStuCount(total);
		page.setList(list);
		
		return page;
	}

}
