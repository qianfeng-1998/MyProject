package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qf.bean.GoodsTypeInfo;
import com.qf.utils.DBUtils;

public class GetTypePageDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	
	public static List<GoodsTypeInfo> getPageList(int pageNo,int pageSize){
		List<GoodsTypeInfo> list=null;
		String sql=" select * from goodstype order by typeid desc limit ?,?";
		con=DBUtils.getcon();
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, (pageNo-1)*pageSize);
			psmt.setInt(2, pageSize);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int typeId=rs.getInt("typeid");
					String typeName=rs.getString("typeName");
					list.add(new GoodsTypeInfo(typeId, typeName));
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
	
	
	public static int getCount(){
		con=DBUtils.getcon();
		String sql="select count(*) from goodstype";
		int count=0;
		try {
			psmt=con.prepareStatement(sql);
			rs=psmt.executeQuery();
			rs.next();
			count=rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt, rs);
		}
		return count;
	}
}
