package com.qf.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qf.bean.BulletinInfo;
import com.qf.bean.PageModel;
import com.qf.service.GetBulletinPageService;

/**
@Author:
@desc:
@date:
*/
@WebServlet("/BulletinServlet")
public class BulletinServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//∑÷“≥ µœ÷
		request.setCharacterEncoding("UTF-8");
		String pageNo=request.getParameter("pageNo");
		PageModel<BulletinInfo> page=GetBulletinPageService.getBulletinPageSer(Integer.parseInt(pageNo), 5);
		HttpSession session=request.getSession();
		session.setAttribute("page", page);
		response.sendRedirect(request.getContextPath()+"/reception/showBulletinList.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
