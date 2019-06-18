package com.qf.admin.service;

import java.util.List;

import com.qf.admin.dao.GetGoodsPageDao;
import com.qf.bean.GoodsInfo;
import com.qf.bean.PageModel;

public class GetDoodsPageService {

	
	public static PageModel<GoodsInfo> getGoodsPage(int pageNo,int pageSize){
		//10¼þÒ»Ò³
		PageModel<GoodsInfo> page=new PageModel<>();
		List<GoodsInfo> list=GetGoodsPageDao.getPageList(pageNo, 10);
		int total=GetGoodsPageDao.getCount();
		
		page.setPageNo(pageNo);
		page.setPageSize(10);
		page.setStuCount(total);
		page.setList(list);
		
		return page;
	}
}
