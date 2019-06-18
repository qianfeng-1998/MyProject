package com.qf.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.admin.service.AddGoodsTypeService;

/**
@Author:
@desc:
@date:
*/
@WebServlet("/AddGoodsTypeServlet")
public class AddGoodsTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String typeName=request.getParameter("typeName");
		int row=AddGoodsTypeService.addGoodsType(typeName);
		if (row>0) {
			response.sendRedirect(request.getContextPath()+"/admin/jsp/ok.jsp");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
			return;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
