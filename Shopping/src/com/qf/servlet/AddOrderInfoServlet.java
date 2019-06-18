package com.qf.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qf.bean.ShoppingCarInfo;
import com.qf.service.AddOrderInfoService;
import com.qf.service.ClearShoppingCarService;

/**
@Author:
@desc:
@date:
*/
@WebServlet("/AddOrderInfoServlet")
public class AddOrderInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession();
		int userId=(int) session.getAttribute("userId");
		ArrayList<ShoppingCarInfo> list=(ArrayList) session.getAttribute("carInfoList");
		int row=AddOrderInfoService.addOrder(userId, list);
		if (row>0) {
			//添加成功，则将该用户的购物车信息清空
			int clearRow=ClearShoppingCarService.clearAllCar(userId);
			if (clearRow>0) {
				response.sendRedirect(request.getContextPath()+"/reception/ok.jsp");
				return;
			}
		}else {
			response.sendRedirect(request.getContextPath()+"/reception/failed.jsp");
			return;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

