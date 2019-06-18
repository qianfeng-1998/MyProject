package com.qf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.qf.bean.CustomerInfo;
import com.qf.utils.DBUtils;

public class GetSingleCustomerAllInfoDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	public static ArrayList<CustomerInfo> getSingleAllInfo(int userId){
		con=DBUtils.getcon();
		String sql="select * from allCoustomerInfo where id=?";
		ArrayList<CustomerInfo> list=null;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, userId);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int id=rs.getInt("id");
					String email=rs.getString("email");
					String pwd=rs.getString("pwd");
					Timestamp registerTime=rs.getTimestamp("registerTime");
					
					int customerId=rs.getInt("customerId");
					String name=rs.getString("name");
					String telphone=rs.getString("telphone");
					String movePhone=rs.getString("movePhone");
					String address=rs.getString("address");
					list.add(new CustomerInfo(id, email, pwd, registerTime, customerId, name, telphone, movePhone, address));
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
