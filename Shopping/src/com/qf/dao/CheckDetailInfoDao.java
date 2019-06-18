package com.qf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.qf.bean.CustomerInfo;
import com.qf.utils.DBUtils;

public class CheckDetailInfoDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	//根据customerId得到用户信息
	public static ArrayList<CustomerInfo> check(int userId){
		con=DBUtils.getcon();
		ArrayList<CustomerInfo> list=null;
		String sql="select * from customerdetailinfo where customerId=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, userId);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int customerId=rs.getInt("customerId");
					String name=rs.getString("name");
					String telphone=rs.getString("telphone");
					String movePhone=rs.getString("movePhone");
					String address=rs.getString("address");
					list.add(new CustomerInfo(customerId, name, telphone, movePhone, address));
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
