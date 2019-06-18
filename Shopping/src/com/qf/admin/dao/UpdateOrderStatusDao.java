package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.qf.utils.DBUtils;

public class UpdateOrderStatusDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	
	public static int update(int orderId,int status) {
		con=DBUtils.getcon();
		String sql="update orderinfo set status=? where orderid=?";
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, status);
			psmt.setInt(2, orderId);
			row=psmt.executeUpdate();
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt);
		}
		return -1;
	}
	
	
}
