package com.qf.admin.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qf.bean.GoodsInfo;
import com.qf.utils.DBUtils;

public class GetGoodsPageDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	
	public static List<GoodsInfo> getPageList(int pageNo,int pageSize){
		List<GoodsInfo> list=null;
		String sql=" select * from goodsinfo order by goodsid desc limit ?,?";
		con=DBUtils.getcon();
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, (pageNo-1)*pageSize);
			psmt.setInt(2, pageSize);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int goodsId=rs.getInt("goodsId");
					int getTypeId=rs.getInt("typeId");
					String goodsName=rs.getString("goodsName");
					BigDecimal price=rs.getBigDecimal("price");
					double discount=rs.getDouble("discount");
					int isNew=rs.getInt("isnew");
					int isRecommend=rs.getInt("isRecommend");
					int status=rs.getInt("status");
					String photo=rs.getString("photo");
					String remark=rs.getString("remark");
					list.add(new GoodsInfo(goodsId, getTypeId, goodsName, price, discount, isNew, isRecommend, status, photo, remark));
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
		String sql="select count(*) from goodsinfo";
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
