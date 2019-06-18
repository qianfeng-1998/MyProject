package com.qf.service;

import java.util.ArrayList;

import com.qf.bean.BulletinInfo;
import com.qf.dao.GetAllBulletinDao;

public class GetAllBulletinService {

	public static ArrayList<BulletinInfo> getAllBulletion(){
		return GetAllBulletinDao.getAllBulletinInfo();
	}
}
