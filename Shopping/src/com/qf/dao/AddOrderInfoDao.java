package com.qf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.qf.bean.ShoppingCarInfo;
import com.qf.utils.DBUtils;

public class AddOrderInfoDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	//添加orderInfo信息
	public static int addOrder(int userId,ArrayList<ShoppingCarInfo> list) {
		con=DBUtils.getcon();
		String sql="insert into orderinfo values(null,?,0,now())";
		int row=-1;
		int sRow=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, userId);
			row=psmt.executeUpdate();
			if (row>0) {
				//第一个值为orderId，必须得到刚刚添加的orderInfo表最新的orderId
				String sql2="select * from orderinfo where customerid=? order by orderid desc";
				psmt=con.prepareStatement(sql2);
				psmt.setInt(1, userId);
				rs=psmt.executeQuery();
				int orderId=0;
				if (rs!=null) {
					rs.next();
					orderId=rs.getInt(1);
				}
				String sql1="insert into ordergoodsinfo values(?,?,?)";
				for (int i = 0; i < list.size(); i++) {
					psmt=con.prepareStatement(sql1);
					psmt.setInt(1, orderId);
					psmt.setInt(2, list.get(i).getGoodsId());
					psmt.setInt(3, list.get(i).getQuantity());
					sRow=psmt.executeUpdate();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt);
		}
		return sRow;
	}
	
}
