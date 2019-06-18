package com.qf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.qf.bean.CustomerInfo;
import com.qf.utils.DBUtils;

public class ReceptionLoginDao{
	
	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;

	//前台验证登录密码及用户名||根据email和pwd得到单个用户信息
	public static List<CustomerInfo> loginValidation(String email,String pwd) {
		con=DBUtils.getcon();
		String sql="select * from CustomerInfo where email=? and pwd=?";
		List<CustomerInfo> list=null;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, email);
			psmt.setString(2, pwd);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int id=rs.getInt("id");
					String getEmail=rs.getString("email");
					String getPwd=rs.getString("pwd");
					Timestamp reg=rs.getTimestamp("registerTime");
					list.add(new CustomerInfo(id, getEmail, getPwd, reg));
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
	
	//得到用户名
	public static String getUserName(String email) {
		con=DBUtils.getcon();
		String name=null;
		String sql="select * from allcoustomerinfo where email=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, email);
			rs=psmt.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					name=rs.getString("name");
				}
				return name;
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
