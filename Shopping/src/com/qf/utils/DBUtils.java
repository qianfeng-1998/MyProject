package com.qf.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {

	private static Connection con=null;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库加载失败");
			e.printStackTrace();
		}
	}
	public static Connection getcon() {
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping", "root", "123456");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		return con;
	}
	public static void closeCon(Connection con,PreparedStatement psmt) {
		try {
			if (con!=null) {
				con.close();
			}if (psmt!=null) {
				psmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void closeCon(Connection con,PreparedStatement psmt,ResultSet rs) {
		try {
			if (con!=null) {
				con.close();
			}if (psmt!=null) {
				psmt.close();
			}if (rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
