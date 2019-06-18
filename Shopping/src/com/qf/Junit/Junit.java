package com.qf.Junit;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.qf.admin.dao.GetCustomerPageDao;
import com.qf.admin.dao.GetOrderPageDao;
import com.qf.admin.dao.RemoveCustomerCheckDao;
import com.qf.admin.dao.RemoveTypeCheckOrderDao;
import com.qf.admin.service.GetSingleOrderService;
import com.qf.bean.BulletinInfo;
import com.qf.bean.CustomerInfo;
import com.qf.bean.GoodsInfo;
import com.qf.bean.OrderInfo;
import com.qf.dao.RegisterSingleUserDao;
import com.qf.service.GetSingleGoodsService;
import com.qf.utils.DBUtils;

public class Junit {
	
	@Test
	public void test1() {
		Connection con=DBUtils.getcon();
		if (con!=null) {
			System.out.println("成功");
		}else {
			System.out.println("失败");
		}
	}
	
	@Test
	public void test2() {
		int row=RegisterSingleUserDao.registerSingleUser(new CustomerInfo(4, "f@sina.com", "123456", null, 0, "张三", "5221287", "15058465841", "guizhou"));
		if (row>0) {
			System.out.println("成功");
		}else {
			System.out.println("失败");
		}
	}

	@Test
	public void test3() {
		BulletinInfo bi=new BulletinInfo();
		String time=bi.convertDate(new Timestamp(new Date().getTime()));
		System.out.println(time);
		
	}
	
	@Test
	public void test4() {
		List<Integer> list=RemoveTypeCheckOrderDao.getGoodsId(5);
		for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			System.out.println(integer);
		}
		//System.out.println(goodsId);
	}
	
	
	@Test
	public void test5() {
		List<OrderInfo> list=GetOrderPageDao.getPageList(1, 4);
		for (Iterator<OrderInfo> iterator = list.iterator(); iterator.hasNext();) {
			OrderInfo orderInfo = (OrderInfo) iterator.next();
			System.out.println("编号："+orderInfo.getOrderId());
			System.out.println("时间："+orderInfo.getOrderTime());
			System.out.println("商品编号："+orderInfo.getGoodsId());
			System.out.println("用户编号："+orderInfo.getCustomerId());
			System.out.println("数量："+orderInfo.getQuantity());
			System.out.println("订单状态："+orderInfo.getStatus());
			System.out.println("________________________");
		}
	}
	

	@Test
	public void test6() {
		System.out.println(GetOrderPageDao.getCount());
	}
	
	@Test
	public void test7() {
		System.out.println(GetOrderPageDao.getCountByStatus(0));
	}
	
	@Test
	public void test8() {
		List<OrderInfo> orderList=GetSingleOrderService.getSingleOrder(8);
		List<GoodsInfo> goodsList=new ArrayList<>();
		int goodsId;
		for (Iterator<OrderInfo> iterator = orderList.iterator(); iterator.hasNext();) {
			OrderInfo goodsInfo = iterator.next();
			goodsId=goodsInfo.getGoodsId();
			List<GoodsInfo> gList=GetSingleGoodsService.getSingleGoods(goodsId);
			goodsList.add(gList.get(0));
		}
		System.out.println("商品名称:"+goodsList.get(0).getGoodsName()+"---");
		System.out.println("商品名称:"+goodsList.get(1).getGoodsName()+"---");
		System.out.println(goodsList.size());
	}
	
	@Test
	public void test9() {
		List<CustomerInfo> list=GetCustomerPageDao.getPage(1, 10);
		System.out.println("list的大小："+list.size());
		for (Iterator<CustomerInfo> iterator = list.iterator(); iterator.hasNext();) {
			CustomerInfo customerInfo = (CustomerInfo) iterator.next();
			System.out.println(customerInfo.getAddress());
		}
	}
	
	
	
	@Test
	public void test10() {
		ArrayList<Integer> list=RemoveCustomerCheckDao.checkMoreOrder("1,2,3,4,5,6".split(","));
		if (list!=null&&!(list.isEmpty())) {
			System.out.println("不为空");
			System.out.println(list);
			for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
				Integer integer = iterator.next();
				System.out.println(integer);
			}
		}else {
			System.out.println("weikog");
		}
	}

	
	
}
