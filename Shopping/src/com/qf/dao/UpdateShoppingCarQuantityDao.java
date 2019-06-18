package com.qf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.qf.bean.ShoppingCarInfo;
import com.qf.utils.DBUtils;

public class UpdateShoppingCarQuantityDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	
	public static int updateCar(ShoppingCarInfo car) {
		con=DBUtils.getcon();
		String sql="update shoppingcar set quantity=? where userId=? and goodsid=?";
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, car.getQuantity());
			psmt.setInt(2, car.getUserId());
			psmt.setInt(3, car.getGoodsId());
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
