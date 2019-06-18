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

public class GetAllGoodsInfoDao {
	
	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	//得到全部商品
	public static List<GoodsInfo> getAllGoods(){
		con=DBUtils.getcon();
		String sql="select * from goodsinfo";
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

}
