package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qf.utils.DBUtils;

public class AddBulletinDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	public static int insertBulletin(String title,String content,int userId) {
		con=DBUtils.getcon();
		String sql="insert into bulletin values(null,?,?,?,now())";
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setInt(3, userId);
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
