package com.qf.admin.service;

import java.util.List;

import com.qf.admin.dao.GetTypePageDao;
import com.qf.bean.GoodsTypeInfo;
import com.qf.bean.PageModel;

public class GetTypePageService {

	public static PageModel<GoodsTypeInfo> getTypePage(int pageNo,int pageSize){
		//5œÓ“ª“≥
		PageModel<GoodsTypeInfo> page=new PageModel<>();
		List<GoodsTypeInfo> list=GetTypePageDao.getPageList(pageNo, 5);
		int total=GetTypePageDao.getCount();
		
		page.setPageNo(pageNo);
		page.setPageSize(5);
		page.setStuCount(total);
		page.setList(list);
		
		return page;
	}
}
