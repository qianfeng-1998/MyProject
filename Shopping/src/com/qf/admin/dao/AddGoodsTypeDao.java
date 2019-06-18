package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.qf.utils.DBUtils;

public class AddGoodsTypeDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	
	public static int addType(String typeName) {
		con=DBUtils.getcon();
		String sql="insert into goodstype values(null,?)";
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, typeName);
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
