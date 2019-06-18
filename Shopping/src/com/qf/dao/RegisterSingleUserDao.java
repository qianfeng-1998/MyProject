package com.qf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qf.bean.CustomerInfo;
import com.qf.utils.DBUtils;

public class RegisterSingleUserDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	//需要插入customerdetailinfo表，需要先得到最近一次添加customerinfo的id
	public static int registerSingleUser(CustomerInfo ci) {
		con=DBUtils.getcon();
		int row;
		int sRow;
		String sql="insert into customerinfo values(null,?,?,now())";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, ci.getEmail());
			psmt.setString(2, ci.getPwd());
			row=psmt.executeUpdate();
			if (row>0) {
				String sql2="select * from customerinfo order by id desc limit 0,1";
				psmt=con.prepareStatement(sql2);
				int id = 0;
				rs=psmt.executeQuery();
				if (rs!=null) {
					while (rs.next()) {
						id=rs.getInt(1);
					}
				}
				String sql3="insert into customerdetailinfo values(?,?,?,?,?)";
				psmt=con.prepareStatement(sql3);
				psmt.setInt(1, id);
				psmt.setString(2, ci.getName());
				psmt.setString(3, ci.getTelphone());
				psmt.setString(4, ci.getMovePhone());
				psmt.setString(5, ci.getAddress());
				sRow=psmt.executeUpdate();
				return sRow;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt, rs);
		}
		return -1;
	}
}
