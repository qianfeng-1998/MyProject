package com.qf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.qf.bean.CustomerInfo;
import com.qf.utils.DBUtils;

public class AddCustomerDetailInfoDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	
	public static int insert(CustomerInfo ci) {
		con=DBUtils.getcon();
		String sql="update customerdetailinfo set name=?,telphone=?,movephone=?,address=? where customerid=?";
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, ci.getName());
			psmt.setString(2, ci.getTelphone());
			psmt.setString(3, ci.getMovePhone());
			psmt.setString(4, ci.getAddress());
			psmt.setInt(5, ci.getCustomerId());
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
