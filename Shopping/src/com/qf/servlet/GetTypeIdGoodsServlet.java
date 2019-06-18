package com.qf.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qf.bean.GoodsInfo;
import com.qf.bean.PageModel;
import com.qf.service.GetGoodsInfoPageService;


/**
@Author:
@desc:分页得到商品信息
@date:
*/
@WebServlet("/GetTypeIdGoodsServlet")
public class GetTypeIdGoodsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String getPageNo=request.getParameter("pageNo");
		String getTypeId=request.getParameter("typeId");
		
		int pageNo=Integer.parseInt(getPageNo);
		int typeId=Integer.parseInt(getTypeId);
		
		PageModel<GoodsInfo> page=GetGoodsInfoPageService.getGoodsPage(pageNo, typeId);
		
		HttpSession session=request.getSession();
		session.setAttribute("goodsPage", page);
		
		response.sendRedirect(request.getContextPath()+"/reception/products.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
