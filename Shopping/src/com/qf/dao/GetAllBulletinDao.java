package com.qf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.qf.bean.BulletinInfo;
import com.qf.utils.DBUtils;

public class GetAllBulletinDao {
	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	//得到全部标题信息
	public static ArrayList<BulletinInfo> getAllBulletinInfo(){
		con=DBUtils.getcon();
		String sql="select * from bulletin";
		ArrayList<BulletinInfo> list=null;
		try {
			psmt=con.prepareStatement(sql);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int id=rs.getInt("id");
					String title=rs.getString("title");
					String content=rs.getString("content");
					int userId=rs.getInt("userId");
					Timestamp createTime=rs.getTimestamp("createTime");
					list.add(new BulletinInfo(id, title, content, userId, createTime));
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
