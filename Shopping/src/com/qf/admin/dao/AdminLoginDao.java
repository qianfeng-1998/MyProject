package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.qf.bean.AdminUser;
import com.qf.utils.DBUtils;

public class AdminLoginDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	//用于管理员登录验证，根据name和pwd
	public static ArrayList<AdminUser> adminLoginValidation(AdminUser user){
		con=DBUtils.getcon();
		String sql="select * from userInfo where userName=? and userPwd=?";
		ArrayList<AdminUser> list=new ArrayList<>();
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, user.getUserName());
			psmt.setString(2, user.getUserPwd());
			rs=psmt.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					int id=rs.getInt("id");
					String userName=rs.getString("userName");
					String userPwd=rs.getString("userPwd");
					list.add(new AdminUser(id, userName, userPwd));
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt, rs);
		}
		return null;
	}
}
