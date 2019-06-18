package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.qf.bean.CustomerInfo;
import com.qf.utils.DBUtils;

public class UpdateCustomerDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	
	public static int update(CustomerInfo ci) {
		con=DBUtils.getcon();
		String sql="update customerinfo set email=? where id=?";
		int sRow=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, ci.getEmail());
			psmt.setInt(2, ci.getId());
			int row=psmt.executeUpdate();
			if (row>0) {
				String sql2="update customerdetailinfo set name=?,telphone=?,movePhone=?,address=? where customerid=?";
				psmt=con.prepareStatement(sql2);
				psmt.setString(1, ci.getName());
				psmt.setString(2, ci.getTelphone());
				psmt.setString(3, ci.getMovePhone());
				psmt.setString(4, ci.getAddress());
				psmt.setInt(5, ci.getId());
				sRow=psmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt);
		}
		return sRow;
	}
}
