package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.qf.bean.OrderInfo;
import com.qf.utils.DBUtils;

public class GetOrderPageDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	/*select * from orderinfo,ordergoodsinfo where orderinfo.orderid=ordergoodsinfo.orderid limit 0,0*/
	public static List<OrderInfo> getPageList(int pageNo,int pageSize){
		List<OrderInfo> list=null;
		String sql="select * from orderinfo,ordergoodsinfo where orderinfo.orderid=ordergoodsinfo.orderid limit ?,?";
		con=DBUtils.getcon();
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, (pageNo-1)*pageSize);
			psmt.setInt(2, pageSize);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int orderId=rs.getInt("orderid");
					int customerId=rs.getInt("customerId");
					int status=rs.getInt("status");
					int goodsId=rs.getInt("goodsId");
					int quantity=rs.getInt("quantity");
					Timestamp orderTime=rs.getTimestamp("orderTime");
					list.add(new OrderInfo(orderId, customerId, status, goodsId, quantity, orderTime));
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
	
	
	public static int getCount(){
		con=DBUtils.getcon();
		String sql="select count(*) from orderinfo,ordergoodsinfo where orderinfo.orderid=ordergoodsinfo.orderid";
		int count=0;
		try {
			psmt=con.prepareStatement(sql);
			rs=psmt.executeQuery();
			rs.next();
			count=rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt, rs);
		}
		return count;
	}
	
	
	//根据订单状态获得信息
	public static List<OrderInfo> getPageListByStatus(int status,int pageNo,int pageSize){
		con=DBUtils.getcon();
		String sql="select * from orderinfo,ordergoodsinfo where orderinfo.orderid=ordergoodsinfo.orderid and status=? limit ?,?";
		List<OrderInfo> list=null;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, status);
			psmt.setInt(2, (pageNo-1)*pageSize);
			psmt.setInt(3, pageSize);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int orderId=rs.getInt("orderid");
					int customerId=rs.getInt("customerId");
					int getStatus=rs.getInt("status");
					int goodsId=rs.getInt("goodsId");
					int quantity=rs.getInt("quantity");
					Timestamp orderTime=rs.getTimestamp("orderTime");
					list.add(new OrderInfo(orderId, customerId, getStatus, goodsId, quantity, orderTime));
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
	
	public static int getCountByStatus(int status) {
		con=DBUtils.getcon();
		String sql="select count(*) from orderinfo,ordergoodsinfo where orderinfo.orderid=ordergoodsinfo.orderid and status=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, status);
			rs=psmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt, rs);
		}
		return -1;
	}
}
