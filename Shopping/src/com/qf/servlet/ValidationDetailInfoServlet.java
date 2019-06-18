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
@desc:�����û���ϸ��Ϣ�����Ƿ��Ѿ�����ϸ��Ϣ�����û�У�����ȷ��ǰ��Ҫ����ת��peisong.jspҳ����д�����Ϣ
@date:
*/
@WebServlet("/ValidationDetailInfoServlet")
public class ValidationDetailInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session=request.getSession();
		int userId=(int) session.getAttribute("userId");
		ArrayList<CustomerInfo> list=CheckCustomerDetailInfoService.checkDetailInfo(userId);
		if (!("".equals(list.get(0).getAddress()))) {//�Ѵ��ڣ�˵�����û�����д�����Ϣ
			//����Id�õ��û������Ϣ
			//System.out.println("��Ϊ��");
			ArrayList<CustomerInfo> userList=GetCustomerAllInfoService.getAllInfo(userId);
			session.setAttribute("userList", userList);
			response.sendRedirect(request.getContextPath()+"/reception/confirm.jsp");
			return;
		}else {
			//System.out.println("Ϊ��");
			response.sendRedirect(request.getContextPath()+"/reception/peisong.jsp");
			return;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

