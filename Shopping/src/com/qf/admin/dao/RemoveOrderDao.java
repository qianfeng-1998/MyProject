package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.qf.utils.DBUtils;

public class RemoveOrderDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	
	/**
	 * 删除订单信息，需要先删除ordergoodsInfo表，再删除orderinfo表
	 * */
	public static int removeOrder(int orderId) {
		con=DBUtils.getcon();
		String sql="delete from ordergoodsinfo where orderid=?";
		int sRow=-1;
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, orderId);
			sRow=psmt.executeUpdate();
			if (sRow>0) {
				String sql2="delete from orderinfo where orderid=?";
				psmt=con.prepareStatement(sql2);
				psmt.setInt(1, orderId);
				row=psmt.executeUpdate();
				return row;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt);
		}
		return row;
	}
	
	
	/**
	 * 删除多个订单信息，参数为数组
	 * 需要先删除ordergoodsInfo表，再删除orderInfo表
	 * */
	public static int removeMore(String [] arrs) {
		con=DBUtils.getcon();
		String sql="delete from ordergoodsinfo where orderid=?";
		int sRow=0;
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			int count=0;
			for (int i = 0; i < arrs.length; i++) {
				psmt.setInt(1, Integer.parseInt(arrs[i]));
				sRow=psmt.executeUpdate();
				count+=sRow;
			}
			if (count>0) {
				String sql2="delete from orderinfo where orderid=?";
				int count2=0;
				psmt=con.prepareStatement(sql2);
				for (int i = 0; i < arrs.length; i++) {
					psmt.setInt(1, Integer.parseInt(arrs[i]));
					row=psmt.executeUpdate();
					count2+=row;
				}
				return count2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt);
		}
		return row;
	}
	
}
