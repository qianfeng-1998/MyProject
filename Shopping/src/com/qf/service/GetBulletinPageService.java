package com.qf.service;

import java.util.List;

import com.qf.bean.BulletinInfo;
import com.qf.bean.PageModel;
import com.qf.dao.GetBulletinPage;

public class GetBulletinPageService {
	
	public static PageModel<BulletinInfo> getBulletinPageSer(int pageNo,int pageSize){
		PageModel<BulletinInfo> page=new PageModel<>();
		List<BulletinInfo> list=GetBulletinPage.getPageStu(pageNo, pageSize);
		int total=GetBulletinPage.getBulletinCount();
		
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setStuCount(total);
		page.setList(list);
		
		return page;
	}
}
