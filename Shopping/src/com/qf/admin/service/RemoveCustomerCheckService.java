package com.qf.admin.service;

import java.util.ArrayList;

import com.qf.admin.dao.RemoveCustomerCheckDao;

public class RemoveCustomerCheckService {

	public static ArrayList<Integer> checkOrderInfo(int userId){
		return RemoveCustomerCheckDao.checkOrderInfoTable(userId);
	}
	
	public static int deleteUser(int userId) {
		return RemoveCustomerCheckDao.delete(userId);
	}
	
	public static ArrayList<Integer> removeMoreCheckOrderInfo(String [] array){
		return RemoveCustomerCheckDao.checkMoreOrder(array);
	}
	
	public static int deleteMore(String[] array) {
		return RemoveCustomerCheckDao.deleteMore(array);
	}
}
