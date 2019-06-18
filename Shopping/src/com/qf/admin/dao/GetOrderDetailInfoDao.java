package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qf.utils.DBUtils;

public class GetOrderDetailInfoDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	public static int getCustomerId(int orderId) {
		con=DBUtils.getcon();
		String sql="select customerId from orderinfo where orderid=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, orderId);
			rs=psmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtils.closeCon(con, psmt, rs);
		}
		return -1;
	}
	
}
