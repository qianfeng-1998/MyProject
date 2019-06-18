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

public class ValidationDao {
	public static List<CustomerInfo> validationEmail(String email){
		Connection con=DBUtils.getcon();
		List<CustomerInfo> list=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		String sql="select * from customerinfo where email=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, email);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int id=rs.getInt("id");
					String getEmail=rs.getString("email");
					String pwd=rs.getString("pwd");
					Timestamp registerTime=rs.getTimestamp("registerTime");
					list.add(new CustomerInfo(id, getEmail, pwd, registerTime));
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
