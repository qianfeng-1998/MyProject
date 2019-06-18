package com.qf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qf.bean.GoodsTypeInfo;
import com.qf.utils.DBUtils;

public class GetGoodsTypeDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	//得到全部goodstype
	public static List<GoodsTypeInfo> getGoodsType(){
		con=DBUtils.getcon();
		String sql="select * from goodstype";
		List<GoodsTypeInfo> list=null;
		try {
			psmt=con.prepareStatement(sql);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int typeId=rs.getInt("typeid");
					String typeName=rs.getString("typename");
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
	
	//根据指定typeId得到TypeName
	
	public static String getTypeName(int typeId) {
		con=DBUtils.getcon();
		String typeName=null;
		String sql="select * from goodsType where typeid=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, typeId);
			rs=psmt.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					typeName=rs.getString("typeName");
				}
				return typeName;
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
