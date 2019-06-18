package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qf.utils.DBUtils;

/**
 * ɾ���û�ǰ������������
 * */
public class RemoveCustomerCheckDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	/**
	 * ���orderInfo��
	 * */
	public static ArrayList<Integer> checkOrderInfoTable(int userId){
		con=DBUtils.getcon();
		ArrayList<Integer> list=null;
		String sql="select * from orderinfo where customerid=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, userId);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int customerId=rs.getInt("customerId");
					list.add(Integer.valueOf(customerId));
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
	
	
	/**
	 * ���CustomerDetailInfo��
	 * ��idֻ�ܶ�Ӧһ�������Է���int��
	 * */
	private static int checkCustomerDetailInfoTable(int userId){
		con=DBUtils.getcon();
		String sql="select * from CustomerDetailInfo where customerId=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, userId);
			rs=psmt.executeQuery();
			int id=-1;
			if (rs!=null) {
				while (rs.next()) {
					id=rs.getInt("customerId");
				}
				return id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//DBUtils.closeCon(con, psmt, rs);
		}
		return -1;
	}
	
	
	/**
	 * ��鹺�ﳵ����id���ܻ��ж����Ʒ��Ϣ
	 * */
	private static ArrayList<Integer> checkShoppingCarTable(int userId){
		con=DBUtils.getcon();
		String sql="select * from shoppingcar where userId=?";
		ArrayList<Integer> list=null;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, userId);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int getUserId=rs.getInt("userid");
					list.add(Integer.valueOf(getUserId));
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	/**
	 * ɾ���ķ���
	 * */
	public static int delete(int userId) {
		con=DBUtils.getcon();
		String sql="delete from customerinfo where id=?";
		int row=-1;
		try {//���CustomerDetailInfo���д�����Ҫ��ɾ����
			if (RemoveCustomerCheckDao.checkCustomerDetailInfoTable(userId)>0) {
				String sql2="delete from CustomerDetailInfo where customerId=?";
				psmt=con.prepareStatement(sql2);
				psmt.setInt(1, userId);
				psmt.executeUpdate();
			}//���shoppingCar���д�����Ҫ��ɾ����
			if (RemoveCustomerCheckDao.checkShoppingCarTable(userId).size()>0) {
				String sql3="delete from shoppingcar where userid=?";
				psmt=con.prepareStatement(sql3);
				psmt.setInt(1, userId);
				psmt.executeUpdate();
			}
			//System.out.println("������"+RemoveCustomerCheckDao.checkCustomerDetailInfoTable(userId));
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, userId);
			row=psmt.executeUpdate();
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt);
		}
		return row;
	}
	
	
	/**
	 * ɾ������û�ʱ����鶩����
	 * */
	public static ArrayList<Integer> checkMoreOrder(String [] array){
		con=DBUtils.getcon();
		String sql="select * from orderinfo where customerid=?";
		ArrayList<Integer> list=null;
		try {
			psmt=con.prepareStatement(sql);
			list=new ArrayList<>();
			for (int i = 0; i < array.length; i++) {
				psmt.setInt(1, Integer.parseInt(array[i]));
				rs=psmt.executeQuery();
				if (rs!=null) {
					while (rs.next()) {
						int orderId=rs.getInt("orderid");
						list.add(Integer.valueOf(orderId));
					}
					continue;
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt, rs);
		}
		return list;
	}
	
	
	/**
	 * ɾ������û��� ��鹺�ﳵ
	 * װ�Ŷ��UserId��list
	 * */
	private static ArrayList<Integer> checkShoppingCarTable(String [] array){
		con=DBUtils.getcon();
		String sql="select * from shoppingcar where userId=?";
		ArrayList<Integer> list=null;
		try {
			psmt=con.prepareStatement(sql);
			list=new ArrayList<>();
			for (int i = 0; i < array.length; i++) {
				psmt.setInt(1, Integer.parseInt(array[i]));
				rs=psmt.executeQuery();
				if (rs!=null) {
					while (rs.next()) {
						int userId=rs.getInt("userId");
						list.add(Integer.valueOf(userId));
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//DBUtils.closeCon(con, psmt, rs);
			//�����ﲻ�ùأ����õķ�����ر�
		}
		return list;
	}
	
	/**
	 * ɾ������ʱ���CustomerDetailInfo��
	 * �õ�ȫ��customerId
	 * */
	private static List<Integer> getIds(String [] arrs){
		con=DBUtils.getcon();
		String sql="select * from customerdetailinfo where customerId=?";
		List<Integer> list=null;
		try {
			psmt=con.prepareStatement(sql);
			list=new ArrayList<>();
			for (int i = 0; i < arrs.length; i++) {
				psmt.setInt(1, Integer.parseInt(arrs[i]));
				rs=psmt.executeQuery();
				if (rs!=null) {
					while (rs.next()) {
						int customerId=rs.getInt("customerId");
						list.add(Integer.valueOf(customerId));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//DBUtils.closeCon(con, psmt, s);
			//�����ﲻ�ùأ����õķ�����ر�
		}
		return list;
	}
	
	
	/**
	 * ɾ������û���Ϣ
	 * */
	public static int deleteMore(String [] array) {
		con=DBUtils.getcon();
		String sql="delete from customerinfo where id=?";
		int row=-1;
		try {
			//�ȼ�鹺�ﳵ��Ϣ
			ArrayList<Integer> list1=RemoveCustomerCheckDao.checkShoppingCarTable(array);
			if (list1!=null&&!(list1.isEmpty())) {//���ﳵ��Ϣ��Ϊ�գ���ɾ��
				for (int i = 0; i < list1.size(); i++) {
					String sql2="delete from shoppingcar where userId=?";
					psmt=con.prepareStatement(sql2);
					psmt.setInt(1, list1.get(i));
					/*int r=*/psmt.executeUpdate();
					//System.out.println("hang shu "+r);
				}
			}
			List<Integer> list2=RemoveCustomerCheckDao.getIds(array);
			if (list2!=null&&!(list2.isEmpty())) {//��ϸ��Ϣ��Ϊ�գ���ɾ��
				for (int i = 0; i < list2.size(); i++) {
					String sql3="delete from customerdetailinfo where customerid=?";
					psmt=con.prepareStatement(sql3);
					psmt.setInt(1, list2.get(i));
					psmt.executeUpdate();
				}
			}
			//ɾ���û���Ϣ��
			for (int i = 0; i < array.length; i++) {
				psmt=con.prepareStatement(sql);
				psmt.setInt(1, Integer.parseInt(array[i]));
				row=psmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt,rs);
		}
		return row;
	}
}
