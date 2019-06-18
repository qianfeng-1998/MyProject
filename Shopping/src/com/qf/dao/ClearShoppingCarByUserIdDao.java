package com.qf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.qf.utils.DBUtils;

public class ClearShoppingCarByUserIdDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	//private static ResultSet rs=null;
	
	//删除该用户的全部商品
	public static int clearAll(int userId) {
		con=DBUtils.getcon();
		int row=0;
		String sql="delete from shoppingCar where userId=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, userId);
			row=psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt);
		}
		return row;
	}
	
	//删除该用户指定GoodsId的商品
	public static int clearSingle(int goodsId,int userId) {
		con=DBUtils.getcon();
		String sql="delete from shoppingCar where userId=? and goodsId=?";
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, userId);
			psmt.setInt(2, goodsId);
			row=psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt);
		}
		return row;
	}
}
