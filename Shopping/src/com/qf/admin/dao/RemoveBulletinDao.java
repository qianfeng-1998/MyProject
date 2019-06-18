package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.qf.utils.DBUtils;

public class RemoveBulletinDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	
	//ɾ�����
	public static int remove(String [] arrs) {
		con=DBUtils.getcon();
		String sql="delete from bulletin where id=?";
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			for (int i = 0; i < arrs.length; i++) {
				psmt.setInt(1, Integer.parseInt(arrs[i]));
				row=psmt.executeUpdate();
			}
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt);
		}
		return -1;
	}
	
	//ɾ������
	public static int removeSingle(int id) {
		con=DBUtils.getcon();
		String sql="delete from bulletin where id=?";
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, id);
			row=psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt);
		}
		return row;
	}
	
	//�޸ĵ�����Ϣ
	public static int updateSingle(int id,String title,String content) {
		con=DBUtils.getcon();
		String sql="update bulletin set title=?,content=? where id=?";
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setInt(3, id);
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
