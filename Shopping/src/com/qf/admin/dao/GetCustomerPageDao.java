package com.qf.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.qf.bean.CustomerInfo;
import com.qf.utils.DBUtils;

public class GetCustomerPageDao {

	private static Connection con=null;
	private static PreparedStatement psmt=null;
	private static ResultSet rs=null;
	
	
	/**
	 * 得到用户分页信息
	 * */
	public static List<CustomerInfo> getPage(int pageNo,int pageSize){
		//得到全部customerId：
		ArrayList<Integer> detailList=GetCustomerPageDao.getAllCustomerId();
		
		String sql="select * from customerInfo limit ?,?";
		con=DBUtils.getcon();
		ResultSet res=null;
		List<CustomerInfo> list=null;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, (pageNo-1)*pageSize);
			psmt.setInt(2, pageSize);
			res=psmt.executeQuery();
			if (res!=null) {
				list=new ArrayList<>();
				while (res.next()) {
					int id=res.getInt("id");
					String email=res.getString("email");
					String pwd=res.getString("pwd");
					Timestamp registerTime=res.getTimestamp("registerTime");
					
					int customerId=0;
					String name=null;
					String telphone=null;
					String movePhone=null;
					String address=null;
					for (int i=0;i< detailList.size(); i++) {
						int getId = detailList.get(i);
						if (getId==id) {
							ArrayList<CustomerInfo> infoList=GetCustomerPageDao.getSingleInfo(id);
							CustomerInfo ct=infoList.get(0);
							customerId=ct.getCustomerId();
							name=ct.getName();
							telphone=ct.getTelphone();
							movePhone=ct.getMovePhone();
							address=ct.getAddress();
							break;
						}
					}
					list.add(new CustomerInfo(id, email, pwd, registerTime, customerId, name, telphone, movePhone, address));
					//System.out.println("list添加次数");
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeCon(con, psmt, res);
		}
		return list;
	}
	
	//得到全部Id
	private static ArrayList<Integer> getAllCustomerId(){
		con=DBUtils.getcon();
		String sql="select customerid from customerdetailinfo";
		ArrayList<Integer> list=null;
		try {
			psmt=con.prepareStatement(sql);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int id=rs.getInt("customerid");
					list.add(Integer.valueOf(id));
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		return null;
	}
	
	//根据Id得到信息
	private static ArrayList<CustomerInfo> getSingleInfo(int customerId){
		con=DBUtils.getcon();
		String sql="select * from customerdetailinfo where customerid=?";
		ArrayList<CustomerInfo> list=null;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, customerId);
			rs=psmt.executeQuery();
			if (rs!=null) {
				list=new ArrayList<>();
				while (rs.next()) {
					int getCustomerId=rs.getInt("customerId");
					String name=rs.getString("name");
					String telphone=rs.getString("telphone");
					String movePhone=rs.getString("movePhone");
					String address=rs.getString("address");
					list.add(new CustomerInfo(getCustomerId, name, telphone, movePhone, address));
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		return null;
	}
	
	/**
	 * 得到总数据量
	 * */
	public static int getCount() {
		con=DBUtils.getcon();
		String sql="select count(*) from customerinfo";
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
