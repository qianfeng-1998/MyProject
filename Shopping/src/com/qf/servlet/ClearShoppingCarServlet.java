package com.qf.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qf.bean.ShoppingCarInfo;
import com.qf.service.ClearShoppingCarService;
import com.qf.service.UpdateCarService;

/**
@Author:
@desc:
@date:
*/
@WebServlet("/ClearShoppingCarServlet")
public class ClearShoppingCarServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession();
		int userId=(int) session.getAttribute("userId");
		String op=request.getParameter("op");
		if ("change".equals(op)) {//更新数量
			String getQuantity=request.getParameter("quantity");
			String getGoodsId=request.getParameter("id");
			ShoppingCarInfo car=new ShoppingCarInfo(Integer.parseInt(getGoodsId), Integer.parseInt(getQuantity), userId);
			int row=UpdateCarService.updateCarQuantity(car);
			if (row>0) {
				response.sendRedirect(request.getContextPath()+"/OtherJspToCartServlet");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/reception/failed.jsp");
				return;
			}
		}
		if ("deleteSingle".equals(op)) {//删除单个
			String getGoodsId=request.getParameter("goodsId");
			int row=ClearShoppingCarService.clearSingleCar(Integer.parseInt(getGoodsId), userId);
			if (row>0) {
				response.sendRedirect(request.getContextPath()+"/OtherJspToCartServlet");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/reception/failed.jsp");
				return;
			}
		}
		//删除该用户的全部购物车商品
		int row=ClearShoppingCarService.clearAllCar(userId);
		if (row>0) {
			response.sendRedirect(request.getContextPath()+"/OtherJspToCartServlet");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/reception/failed.jsp");
			return;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
