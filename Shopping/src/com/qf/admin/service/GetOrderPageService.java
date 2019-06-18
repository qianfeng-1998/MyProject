package com.qf.admin.service;

import java.util.List;

import com.qf.admin.dao.GetOrderPageDao;
import com.qf.bean.OrderInfo;
import com.qf.bean.PageModel;

public class GetOrderPageService {

	public static PageModel<OrderInfo> getPage(int pageNo){
		//10Ãı“ª“≥
		PageModel<OrderInfo> page=new PageModel<>();
		List<OrderInfo> list=GetOrderPageDao.getPageList(pageNo, 10);
		int total=GetOrderPageDao.getCount();
		
		page.setPageNo(pageNo);
		page.setPageSize(10);
		page.setStuCount(total);
		page.setList(list);
		
		return page;
	}
	
	
	public static PageModel<OrderInfo> getPageByStatus(int status,int pageNo){
		
		PageModel<OrderInfo> page=new PageModel<>();
		List<OrderInfo> list=GetOrderPageDao.getPageListByStatus(status,pageNo,10);
		int total=GetOrderPageDao.getCountByStatus(status);
		
		page.setPageNo(pageNo);
		page.setPageSize(10);
		page.setStuCount(total);
		page.setList(list);
		
		return page;
	}
}
