package com.qf.admin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qf.admin.service.GetCustomerPageService;
import com.qf.admin.service.RemoveCustomerCheckService;
import com.qf.bean.CustomerInfo;
import com.qf.bean.PageModel;
import com.qf.service.GetCustomerAllInfoService;

/**
@Author:
@desc:查看、操作所有用户信息	分页
@date:
*/
@WebServlet("/SeeAllCustomerServlet")
public class SeeAllCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String op=request.getParameter("op");

		//System.out.println(op);
		if (op!=null&&!("".equals(op))&&!("null".equals(op))) {
			switch (op) {
			case "remove":
				/*删除单个用户信息	提交的是表中的Id	
				 * 删除用户信息，需要先判断CustomerDetailInfo表、OrderInfo表中是否存在该引用
				 * 先得到orderInfo表是否存在，如果订单表中已存在该用户，直接不予删除，
				 * 判断CustomerDetailInfo表，如果该表中存在该引用，先删除此表
				 * 
				 * 临时错误，shoppingCar表中也引用了customerinfo表
				 * 解决：如果购物车信息中有该Id，先删除购物车，再删除customerinfo表
				 * */
				String id=request.getParameter("id");
				int userId=Integer.parseInt(id);
				ArrayList<Integer> list=RemoveCustomerCheckService.checkOrderInfo(userId);
				//System.out.println(list.size());
				if (list==null||list.isEmpty()) {//为空，可以删除
					int row=RemoveCustomerCheckService.deleteUser(userId);
					//System.out.println(row);
					if (row>0) {
						response.sendRedirect(request.getContextPath()+"/SeeAllCustomerServlet?pageNo=1");
						return;
					}else {
						response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
						return;
					}
				}else {//orderInfo表中已存在该数据，不予删除
					response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
					return;
				}
				//break;
			case "removeMore":
				/*
				 * 删除多个用户信息
				 * 得到的是多个用户编号
				 * 原理与删除单个一样
				 * */
				String ids=request.getParameter("removeIds");
				String [] arrs=ids.split(",");
				//先检查订单表
				ArrayList<Integer> list1=RemoveCustomerCheckService.removeMoreCheckOrderInfo(arrs);
				if (list1!=null&&!(list1.isEmpty())) {//不为空，直接失败
					response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
					return;
				}else {//为空，可以删除
					int row=RemoveCustomerCheckService.deleteMore(arrs);
					if (row>0) {
						response.sendRedirect(request.getContextPath()+"/SeeAllCustomerServlet?pageNo=1");
						return;
					}else {
						response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
						return;
					}
				}
				//break;
			case "toEdit":
				/*
				 * 修改单个用户信息
				 * 根据id，得到单个用户全部信息
				 * 交由其他servlet处理
				 * */
				String userId1=request.getParameter("id");
				//掉用前台service得到单个用户信息
				ArrayList<CustomerInfo> customerList=GetCustomerAllInfoService.getAllInfo(Integer.parseInt(userId1));
				HttpSession session=request.getSession();
				session.setAttribute("list", customerList);
				response.sendRedirect(request.getContextPath()+"/admin/jsp/updateCustomer.jsp");
				return;
				//break;
			default:
				
				break;
			}
		}
		//得到分页用户信息
		/*
		 * customerdetailinfo表需要根据id获得，先查询前面customerinfo表，得到一个list，
		 * 根据list中的id比较customerdetailinfo的id，
		 * 如果匹配了，就去查询得到详细信息
		 *如果没匹配，list的详细信息添加Null
		 *(注意，返回的必须是一个List)
		 * */
		String getPageNo=request.getParameter("pageNo");
		int pageNo=Integer.parseInt(getPageNo);
		PageModel<CustomerInfo> page=GetCustomerPageService.getUserPageService(pageNo);
		HttpSession session=request.getSession();
		session.setAttribute("cusPage", page);
		response.sendRedirect(request.getContextPath()+"/admin/jsp/customerManage.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
