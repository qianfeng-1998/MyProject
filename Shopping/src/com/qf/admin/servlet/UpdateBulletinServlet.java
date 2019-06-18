package com.qf.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.admin.service.RemoveBulletinService;

/**
@Author:
@desc:
@date:
*/
@WebServlet("/UpdateBulletinServlet")
public class UpdateBulletinServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String id=request.getParameter("id");
		String content=request.getParameter("content");
		String title=request.getParameter("title");
		int row=RemoveBulletinService.updateBulletin(Integer.parseInt(id), title, content);
		if (row>0) {
			response.sendRedirect(request.getContextPath()+"/SeeAllBulletinServlet?pageNo=1");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"admin/jsp/failed.jsp");
			return;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
