package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.qf.bean.OrderGoodsInfo;
import com.qf.utils.DBUtils;

public class RemoveTypeCheckOrderDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	//����typeId�õ����GoodsId
	public static List<Integer> getGoodsId(int typeId) {
		con=DBUtils.getcon();
		List<Integer> list=null;
		String sql="select * from goodsinfo where typeId=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, typeId);
			rs=psmt.executeQuery();
			rs.next();
			if (rs!=null) {
				int goodsId=-1;
				list=new ArrayList<>();
				while (rs.next()) {
					goodsId=rs.getInt("goodsId");
					list.add(Integer.valueOf(goodsId));
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
	
	//�õ����GoodsId����֤���������Ƿ���ڸ���Ʒ
	public static List<OrderGoodsInfo> validation(List<Integer> list){
		con=DBUtils.getcon();
		String sql="select * from ordergoodsInfo where goodsid=?";
		List<OrderGoodsInfo> list1=null;
		try {
			psmt=con.prepareStatement(sql);
			for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
				int goodsId = iterator.next();
				psmt.setInt(1, goodsId);
				rs=psmt.executeQuery();
				if (rs!=null) {
					list1=new ArrayList<>();
					while (rs.next()) {
						int orderId=rs.getInt("orderid");
						int getGoodsId=rs.getInt("goodsid");
						int quantity=rs.getInt("quantity");
						list1.add(new OrderGoodsInfo(orderId, getGoodsId, quantity));
					}
					return list1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt, rs);
		}
		return null;
	}
	
	//ɾ����Ʒ����
	public static int remove(int typeId) {
		con=DBUtils.getcon();
		String sql="delete from goodstype where typeid=?";
		int row=-1;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, typeId);
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
	
	//���ݶ��TypeId�õ�GoodsId
	public static List<Integer> getGoodsIdByArrays(String [] arrs){
		con=DBUtils.getcon();
		List<Integer> list=new ArrayList<>();
		String sql="select * from goodsinfo where typeId=?";
		try {
			psmt=con.prepareStatement(sql);
			for (int i = 0; i < arrs.length; i++) {
				psmt.setInt(1, Integer.parseInt(arrs[i]));
				rs=psmt.executeQuery();
				if (rs!=null) {
					int goodsId;
					while (rs.next()) {
						goodsId=rs.getInt(1);
						list.add(Integer.valueOf(goodsId));
					}
				}
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt, rs);
		}
		return null;
	}
	
	public static List<OrderGoodsInfo> validationGoodsIdArrays(List<Integer> list){
		con=DBUtils.getcon();
		String sql="select * from ordergoodsInfo where goodsid=?";
		List<OrderGoodsInfo> list1=new ArrayList<>();
		try {
			psmt=con.prepareStatement(sql);
			for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
				int goodsId =  iterator.next();
				psmt.setInt(1, goodsId);
				rs=psmt.executeQuery();
				if (rs!=null) {
					while (rs.next()) {
						int orderId=rs.getInt("orderid");
						int getGoodsId=rs.getInt("goodsid");
						int quantity=rs.getInt("quantity");
						list1.add(new OrderGoodsInfo(orderId, getGoodsId, quantity));
					}
				}
			}
			return list1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt, rs);
		}
		return null;
	}
	
	public static int removeMore(String [] arrs) {
		con=DBUtils.getcon();
		String sql="delete from goodstype where typeid=?";
		int row=0;
		try {
			psmt=con.prepareStatement(sql);
			int rowCount;
			for (int i = 0; i < arrs.length; i++) {
				psmt.setInt(1, Integer.parseInt(arrs[i]));
				rowCount=psmt.executeUpdate();
				row+=rowCount;
			}
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt);
		}
		return 0;
		
	}
}
