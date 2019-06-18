package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qf.utils.DBUtils;

public class RemoveGoodsDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	//删除多个商品
	public static int removeMore(String [] arrs) {
		con=DBUtils.getcon();
		String sql="delete from goodsinfo where goodsid=?";
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			for (int i = 0; i < arrs.length; i++) {
				psmt.setInt(1, Integer.parseInt(arrs[i]));
				row=psmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt);
		}
		return row;
	}
	
	//删除单个商品信息
	public static int removeSingle(int goodsId) {
		con=DBUtils.getcon();
		String sql="delete from goodsInfo where goodsid=?";
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, goodsId);
			row=psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt);
		}
		return row;
	}
}
