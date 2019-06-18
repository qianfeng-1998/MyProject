package com.qf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.qf.bean.ShoppingCarInfo;
import com.qf.utils.DBUtils;

public class ShoppingCarDao {
	
	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	//��ӹ��ﳵ��Ϣ
	public static int addCarDao(ShoppingCarInfo car) {
		con=DBUtils.getcon();
		String sql="insert into shoppingcar values(?,?,?)";
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, car.getGoodsId());
			psmt.setInt(2, car.getQuantity());
			psmt.setInt(3, car.getUserId());
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

	
	//�õ����ﳵ��Ϣ������id
	public static ArrayList<ShoppingCarInfo> getShoppingCarInfo(int userId){
		con=DBUtils.getcon();
		String sql="select * from shoppingcar where userid=?";
		ArrayList<ShoppingCarInfo> list=null;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, userId);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int goodsId=rs.getInt("goodsid");
					int quantity=rs.getInt("quantity");
					int getUserId=rs.getInt("userid");
					list.add(new ShoppingCarInfo(goodsId, quantity, getUserId));
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
	
	//��鹺�ﳵ���Ƿ���ڸ��û��Ѿ��и���Ʒ
	public static ArrayList<ShoppingCarInfo> checkShoppingCar(int goodsId,int userId){
		con=DBUtils.getcon();
		String sql="select * from shoppingcar where userid=? and goodsId=?";
		ArrayList<ShoppingCarInfo> list=null;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, userId);
			psmt.setInt(2, goodsId);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int getGoodsId=rs.getInt("goodsid");
					int quantity=rs.getInt("quantity");
					int getUserId=rs.getInt("userId");
					list.add(new ShoppingCarInfo(getGoodsId, quantity, getUserId));
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
	
	//��ԭ�������Ļ����ϼ������ʱ����Ʒ������
	public static int updateShoppingCarQuantity(ShoppingCarInfo car,int oldNum) {
		con=DBUtils.getcon();
		String sql="update shoppingCar set quantity=? where userId=? and goodsId=?";
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, car.getQuantity()+oldNum);
			//System.out.println("dao�µ�������"+car.getQuantity());
			//System.out.println("daoԭ����������"+oldNum);
			//System.out.println("dao�ܵ�������"+ (car.getQuantity()+oldNum));
			psmt.setInt(2, car.getUserId());
			psmt.setInt(3, car.getGoodsId());
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
