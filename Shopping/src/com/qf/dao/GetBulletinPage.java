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


public class GetBulletinPage {
	
	static List<BulletinInfo> list=null;
	String sql="select * from bulletin";
	static Connection conn=null;
	static PreparedStatement psmt=null;
	static ResultSet rs=null;
	
	public static List<BulletinInfo> getPageStu(int pageNo,int pageSize) {
        list=new ArrayList<BulletinInfo>();
        pageSize=5;
        try {
            conn=DBUtils.getcon();            
            String sql="select * from bulletin limit ?,?";
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1, (pageNo-1)*pageSize);
            psmt.setInt(2, pageSize);
            rs=psmt.executeQuery();
            while(rs.next()){
            	int id=rs.getInt("id");
				String title=rs.getString("title");
				String content=rs.getString("content");
				int userId=rs.getInt("userId");
				Timestamp createTime=rs.getTimestamp("createTime");
				list.add(new BulletinInfo(id, title, content, userId, createTime));              
            }
            return list;
        } catch (SQLException e) {
			e.printStackTrace();
		} finally{
        	DBUtils.closeCon(conn, psmt, rs);
        }
        return  null;
    }
	
	
	public static int getBulletinCount() {
		int bulletinCount = 0;
		try {
            conn=DBUtils.getcon();
            String sql="select count(*) from bulletin";
            psmt= conn.prepareStatement(sql);
            rs=psmt.executeQuery();
            rs.next();
            bulletinCount=rs.getInt(1);
        } catch (SQLException e) {
			e.printStackTrace();
		}
        finally{
            DBUtils.closeCon(conn, psmt, rs);
        }
        return bulletinCount;
	}

}
