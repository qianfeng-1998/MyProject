package com.qf.admin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qf.admin.service.AddBulletinService;
import com.qf.bean.AdminUser;

/**
@Author:
@desc:
@date:
*/
@WebServlet("/AddBulletinServlet")
public class AddBulletinServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String title=request.getParameter("title");
		String content=request.getParameter("content");
		HttpSession session=request.getSession();
		ArrayList<AdminUser> list=(ArrayList) session.getAttribute("loginAdminList");
		int userId=list.get(0).getId();
		int row=AddBulletinService.addBulletinInfo(title, content, userId);
		if (row>0) {
			response.sendRedirect(request.getContextPath()+"/SeeAllBulletinServlet?pageNo=1");
			return ;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
			return;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
