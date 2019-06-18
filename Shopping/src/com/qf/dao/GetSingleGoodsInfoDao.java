package com.qf.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qf.bean.GoodsInfo;
import com.qf.utils.DBUtils;

public class GetSingleGoodsInfoDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	//得到单个商品
	public static List<GoodsInfo> getAllGoods(int goodsId){
		con=DBUtils.getcon();
		String sql="select * from goodsinfo where goodsid=?";
		List<GoodsInfo> list=null;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, goodsId);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int getGoodsId=rs.getInt("goodsId");
					int typeId=rs.getInt("typeId");
					String goodsName=rs.getString("goodsName");
					BigDecimal price=rs.getBigDecimal("price");
					double discount=rs.getDouble("discount");
					int isNew=rs.getInt("isnew");
					int isRecommend=rs.getInt("isRecommend");
					int status=rs.getInt("status");
					String photo=rs.getString("photo");
					String remark=rs.getString("remark");
					list.add(new GoodsInfo(getGoodsId, typeId, goodsName, price, discount, isNew, isRecommend, status, photo, remark));
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

	//根据typeId得到商品信息
	public static List<GoodsInfo> accordingTypeIdGetGoods(int typeId){
		con=DBUtils.getcon();
		String sql="select * from goodsinfo where typeId=?";
		List<GoodsInfo> list=null;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, typeId);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int getGoodsId=rs.getInt("goodsId");
					int getTypeId=rs.getInt("typeId");
					String goodsName=rs.getString("goodsName");
					BigDecimal price=rs.getBigDecimal("price");
					double discount=rs.getDouble("discount");
					int isNew=rs.getInt("isnew");
					int isRecommend=rs.getInt("isRecommend");
					int status=rs.getInt("status");
					String photo=rs.getString("photo");
					String remark=rs.getString("remark");
					list.add(new GoodsInfo(getGoodsId, getTypeId, goodsName, price, discount, isNew, isRecommend, status, photo, remark));
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
	
	//得到后九项商品信息
	public static List<GoodsInfo> getLastNineGoodsInfo(int count){
		con=DBUtils.getcon();
		String sql="select * from goodsinfo limit "+(count-9)+",9";
		List<GoodsInfo> list=null;
		try {
			psmt=con.prepareStatement(sql);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int goodsId=rs.getInt("goodsId");
					int typeId=rs.getInt("typeId");
					String goodsName=rs.getString("goodsName");
					BigDecimal price=rs.getBigDecimal("price");
					double discount=rs.getDouble("discount");
					int isNew=rs.getInt("isnew");
					int isRecommend=rs.getInt("isRecommend");
					int status=rs.getInt("status");
					String photo=rs.getString("photo");
					String remark=rs.getString("remark");
					list.add(new GoodsInfo(goodsId, typeId, goodsName, price, discount, isNew, isRecommend, status, photo, remark));
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
	
	
	//得到最大折扣的前12项商品
	public static List<GoodsInfo> getMaxDiscountNineGoods(){
		String sql="select * from goodsinfo order by discount asc limit 0,12";
		List<GoodsInfo> list=null;
		con=DBUtils.getcon();
		try {
			psmt=con.prepareStatement(sql);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int goodsId=rs.getInt("goodsId");
					int typeId=rs.getInt("typeId");
					String goodsName=rs.getString("goodsName");
					BigDecimal price=rs.getBigDecimal("price");
					double discount=rs.getDouble("discount");
					int isNew=rs.getInt("isnew");
					int isRecommend=rs.getInt("isRecommend");
					int status=rs.getInt("status");
					String photo=rs.getString("photo");
					String remark=rs.getString("remark");
					list.add(new GoodsInfo(goodsId, typeId, goodsName, price, discount, isNew, isRecommend, status, photo, remark));
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
