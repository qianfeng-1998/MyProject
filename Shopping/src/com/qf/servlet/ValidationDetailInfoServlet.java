package com.qf.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qf.bean.CustomerInfo;
import com.qf.service.CheckCustomerDetailInfoService;
import com.qf.service.GetCustomerAllInfoService;

/**
@Author:
@desc:检验用户详细信息表中是否已经有详细信息，如果没有，订单确认前需要先跳转到peisong.jsp页面填写相关信息
@date:
*/
@WebServlet("/ValidationDetailInfoServlet")
public class ValidationDetailInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session=request.getSession();
		int userId=(int) session.getAttribute("userId");
		ArrayList<CustomerInfo> list=CheckCustomerDetailInfoService.checkDetailInfo(userId);
		if (!("".equals(list.get(0).getAddress()))) {//已存在，说明该用户已填写相关信息
			//根据Id得到用户相关信息
			//System.out.println("不为空");
			ArrayList<CustomerInfo> userList=GetCustomerAllInfoService.getAllInfo(userId);
			session.setAttribute("userList", userList);
			response.sendRedirect(request.getContextPath()+"/reception/confirm.jsp");
			return;
		}else {
			//System.out.println("为空");
			response.sendRedirect(request.getContextPath()+"/reception/peisong.jsp");
			return;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

