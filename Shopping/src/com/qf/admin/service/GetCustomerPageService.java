package com.qf.admin.service;

import java.util.List;

import com.qf.admin.dao.GetCustomerPageDao;
import com.qf.bean.CustomerInfo;
import com.qf.bean.PageModel;

public class GetCustomerPageService {

	public static PageModel<CustomerInfo> getUserPageService(int pageNo){
		//5��һҳ
		PageModel<CustomerInfo> page=new PageModel<>();
		List<CustomerInfo> list=GetCustomerPageDao.getPage(pageNo, 5);
		int total=GetCustomerPageDao.getCount();
		
		page.setPageNo(pageNo);
		page.setPageSize(5);
		page.setStuCount(total);
		page.setList(list);
		//System.out.println("servicelist�Ĵ�С��"+list.size());
		
		return page;
	}
}
