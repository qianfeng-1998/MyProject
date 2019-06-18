package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qf.bean.GoodsInfo;
import com.qf.utils.DBUtils;

public class AddGoodsDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	public static int add(GoodsInfo gi) {
		con=DBUtils.getcon();
		String sql="insert into goodsinfo values(null,?,?,?,?,?,?,?,?,?)";
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, gi.getTypeId());
			psmt.setString(2, gi.getGoodsName());
			psmt.setBigDecimal(3, gi.getPrice());
			psmt.setDouble(4, gi.getDiscount());
			psmt.setInt(5, gi.getIsNew());
			psmt.setInt(6, gi.getIsRecommend());
			psmt.setInt(7, gi.getStatus());
			psmt.setString(8, gi.getPhoto());
			psmt.setString(9, gi.getRemark());
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
