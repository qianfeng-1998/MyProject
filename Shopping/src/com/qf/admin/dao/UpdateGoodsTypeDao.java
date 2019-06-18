package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qf.bean.GoodsTypeInfo;
import com.qf.utils.DBUtils;

public class UpdateGoodsTypeDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	//修改单个种类
	public static int update(GoodsTypeInfo gti) {
		con=DBUtils.getcon();
		String sql="update goodstype set typename=? where typeId=?";
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, gti.getTypeName());
			psmt.setInt(2, gti.getTypeId());
			row=psmt.executeUpdate();
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt);
		}
		return -1;
	}
	
	public static GoodsTypeInfo getTypeInfo(int typeId) {
		con=DBUtils.getcon();
		GoodsTypeInfo gti=null;
		String sql="select * from goodstype where typeid=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, typeId);
			rs=psmt.executeQuery();
			if (rs!=null) {
				gti=new GoodsTypeInfo();
				while (rs.next()) {
					int getTypeId=rs.getInt(1);
					String typeName=rs.getString(2);
					gti.setTypeId(getTypeId);
					gti.setTypeName(typeName);
				}
				return gti;
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
