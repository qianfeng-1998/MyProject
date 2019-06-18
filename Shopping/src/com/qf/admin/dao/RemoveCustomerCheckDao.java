package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qf.utils.DBUtils;

/**
 * 删除用户前检查其他表的类
 * */
public class RemoveCustomerCheckDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	/**
	 * 检查orderInfo表
	 * */
	public static ArrayList<Integer> checkOrderInfoTable(int userId){
		con=DBUtils.getcon();
		ArrayList<Integer> list=null;
		String sql="select * from orderinfo where customerid=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, userId);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int customerId=rs.getInt("customerId");
					list.add(Integer.valueOf(customerId));
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt, rs);
		}
		return null;
	}
	
	
	/**
	 * 检查CustomerDetailInfo表
	 * 该id只能对应一个，所以返回int型
	 * */
	private static int checkCustomerDetailInfoTable(int userId){
		con=DBUtils.getcon();
		String sql="select * from CustomerDetailInfo where customerId=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, userId);
			rs=psmt.executeQuery();
			int id=-1;
			if (rs!=null) {
				while (rs.next()) {
					id=rs.getInt("customerId");
				}
				return id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//DBUtils.closeCon(con, psmt, rs);
		}
		return -1;
	}
	
	
	/**
	 * 检查购物车，该id可能会有多个商品信息
	 * */
	private static ArrayList<Integer> checkShoppingCarTable(int userId){
		con=DBUtils.getcon();
		String sql="select * from shoppingcar where userId=?";
		ArrayList<Integer> list=null;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, userId);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int getUserId=rs.getInt("userid");
					list.add(Integer.valueOf(getUserId));
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	/**
	 * 删除的方法
	 * */
	public static int delete(int userId) {
		con=DBUtils.getcon();
		String sql="delete from customerinfo where id=?";
		int row=-1;
		try {//如果CustomerDetailInfo表中存在需要先删除：
			if (RemoveCustomerCheckDao.checkCustomerDetailInfoTable(userId)>0) {
				String sql2="delete from CustomerDetailInfo where customerId=?";
				psmt=con.prepareStatement(sql2);
				psmt.setInt(1, userId);
				psmt.executeUpdate();
			}//如果shoppingCar表中存在需要先删除：
			if (RemoveCustomerCheckDao.checkShoppingCarTable(userId).size()>0) {
				String sql3="delete from shoppingcar where userid=?";
				psmt=con.prepareStatement(sql3);
				psmt.setInt(1, userId);
				psmt.executeUpdate();
			}
			//System.out.println("方法的"+RemoveCustomerCheckDao.checkCustomerDetailInfoTable(userId));
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, userId);
			row=psmt.executeUpdate();
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt);
		}
		return row;
	}
	
	
	/**
	 * 删除多个用户时，检查订单表
	 * */
	public static ArrayList<Integer> checkMoreOrder(String [] array){
		con=DBUtils.getcon();
		String sql="select * from orderinfo where customerid=?";
		ArrayList<Integer> list=null;
		try {
			psmt=con.prepareStatement(sql);
			list=new ArrayList<>();
			for (int i = 0; i < array.length; i++) {
				psmt.setInt(1, Integer.parseInt(array[i]));
				rs=psmt.executeQuery();
				if (rs!=null) {
					while (rs.next()) {
						int orderId=rs.getInt("orderid");
						list.add(Integer.valueOf(orderId));
					}
					continue;
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt, rs);
		}
		return list;
	}
	
	
	/**
	 * 删除多个用户， 检查购物车
	 * 装着多个UserId的list
	 * */
	private static ArrayList<Integer> checkShoppingCarTable(String [] array){
		con=DBUtils.getcon();
		String sql="select * from shoppingcar where userId=?";
		ArrayList<Integer> list=null;
		try {
			psmt=con.prepareStatement(sql);
			list=new ArrayList<>();
			for (int i = 0; i < array.length; i++) {
				psmt.setInt(1, Integer.parseInt(array[i]));
				rs=psmt.executeQuery();
				if (rs!=null) {
					while (rs.next()) {
						int userId=rs.getInt("userId");
						list.add(Integer.valueOf(userId));
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//DBUtils.closeCon(con, psmt, rs);
			//在这里不用关，调用的方法会关闭
		}
		return list;
	}
	
	/**
	 * 删除更多时检查CustomerDetailInfo表
	 * 得到全部customerId
	 * */
	private static List<Integer> getIds(String [] arrs){
		con=DBUtils.getcon();
		String sql="select * from customerdetailinfo where customerId=?";
		List<Integer> list=null;
		try {
			psmt=con.prepareStatement(sql);
			list=new ArrayList<>();
			for (int i = 0; i < arrs.length; i++) {
				psmt.setInt(1, Integer.parseInt(arrs[i]));
				rs=psmt.executeQuery();
				if (rs!=null) {
					while (rs.next()) {
						int customerId=rs.getInt("customerId");
						list.add(Integer.valueOf(customerId));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//DBUtils.closeCon(con, psmt, s);
			//在这里不用关，调用的方法会关闭
		}
		return list;
	}
	
	
	/**
	 * 删除多个用户信息
	 * */
	public static int deleteMore(String [] array) {
		con=DBUtils.getcon();
		String sql="delete from customerinfo where id=?";
		int row=-1;
		try {
			//先检查购物车信息
			ArrayList<Integer> list1=RemoveCustomerCheckDao.checkShoppingCarTable(array);
			if (list1!=null&&!(list1.isEmpty())) {//购物车信息不为空，先删除
				for (int i = 0; i < list1.size(); i++) {
					String sql2="delete from shoppingcar where userId=?";
					psmt=con.prepareStatement(sql2);
					psmt.setInt(1, list1.get(i));
					/*int r=*/psmt.executeUpdate();
					//System.out.println("hang shu "+r);
				}
			}
			List<Integer> list2=RemoveCustomerCheckDao.getIds(array);
			if (list2!=null&&!(list2.isEmpty())) {//详细信息不为空，先删除
				for (int i = 0; i < list2.size(); i++) {
					String sql3="delete from customerdetailinfo where customerid=?";
					psmt=con.prepareStatement(sql3);
					psmt.setInt(1, list2.get(i));
					psmt.executeUpdate();
				}
			}
			//删除用户信息表
			for (int i = 0; i < array.length; i++) {
				psmt=con.prepareStatement(sql);
				psmt.setInt(1, Integer.parseInt(array[i]));
				row=psmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt,rs);
		}
		return row;
	}
}
