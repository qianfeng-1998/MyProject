package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.qf.utils.DBUtils;

public class UpdateAdminDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	
	public static int updatePwd(String newPwd,String userName) {
		con=DBUtils.getcon();
		String sql="update userinfo set userPwd=? where userName=?";
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, newPwd);
			psmt.setString(2, userName);
			row=psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt);
		}
		return row;
	}
	
	
	public static int updateUserName(String newUserName,String oldUserName) {
		con=DBUtils.getcon();
		String sql="update userInfo set userName=? where userName=?";
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, newUserName);
			psmt.setString(2, oldUserName);
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
