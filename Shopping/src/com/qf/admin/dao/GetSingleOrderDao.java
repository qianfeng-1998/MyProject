package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.qf.bean.OrderInfo;
import com.qf.utils.DBUtils;

public class GetSingleOrderDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	public static List<OrderInfo> getSingle(int orderId){
		con=DBUtils.getcon();
		String sql="select * from orderinfo,ordergoodsinfo where orderinfo.orderid=or"
				+ "dergoodsinfo.orderid and orderinfo.orderid=?";
		List<OrderInfo> list=null;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, orderId);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int getOrderId=rs.getInt("orderid");
					int customerId=rs.getInt("customerId");
					int getStatus=rs.getInt("status");
					int goodsId=rs.getInt("goodsId");
					int quantity=rs.getInt("quantity");
					Timestamp orderTime=rs.getTimestamp("orderTime");
					list.add(new OrderInfo(getOrderId, customerId, getStatus, goodsId, quantity, orderTime));
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
