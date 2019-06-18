package com.qf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.qf.bean.BulletinInfo;
import com.qf.utils.DBUtils;

public class AccordingIdGetBulletinInfo {
	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;

	//根据指定id得到信息
	public static List<BulletinInfo> getSingleBulletin(int id){
		con=DBUtils.getcon();
		String sql="select * from bulletin where id=?";
		List<BulletinInfo> list=null;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, id);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int getId=rs.getInt("id");
					String title=rs.getString("title");
					String content=rs.getString("content");
					int userId=rs.getInt("userId");
					Timestamp createTime=rs.getTimestamp("createTime");
					list.add(new BulletinInfo(getId, title, content, userId, createTime));
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
	
	//根据指定userid得到发布者名字
	public static String getPublisherName(int userId) {
		con=DBUtils.getcon();
		String sql="select * from userinfo where id=?";
		String name=null;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, userId);
			rs=psmt.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					name=rs.getString("userName");
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
