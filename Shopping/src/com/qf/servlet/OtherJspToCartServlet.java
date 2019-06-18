package com.qf.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qf.bean.GoodsInfo;
import com.qf.bean.ShoppingCarInfo;
import com.qf.service.GetSingleGoodsService;
import com.qf.service.ShoppingCarService;

/**
@Author:
@desc:����ҳ����ת�����ﳵ���ȷ��ʴ�servlet
@date:
*/
@WebServlet("/OtherJspToCartServlet")
public class OtherJspToCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession();
		Object getUserId=session.getAttribute("userId");
		if (getUserId==null||"".equals(getUserId)) {//δ��¼
			response.sendRedirect(request.getContextPath()+"/reception/login_register.jsp");
			return;
		}else {
			int userId=(int) session.getAttribute("userId");
			ArrayList<ShoppingCarInfo> shoppingCarList=ShoppingCarService.getShoppingCarInfo(userId);
			ArrayList<GoodsInfo> list=new ArrayList<>();
			for (int i = 0; i < shoppingCarList.size(); i++) {
				int goodsId=shoppingCarList.get(i).getGoodsId();
				List<GoodsInfo> singleGoodsInfoList=GetSingleGoodsService.getSingleGoods(goodsId);
				for (int j = 0; j < singleGoodsInfoList.size(); j++) {
					list.add(singleGoodsInfoList.get(j));
				}
			}
			session.setAttribute("shoppingCarList", list);
			session.setAttribute("carInfoList", shoppingCarList);
			//��ȥ֮���ַ�����ܱ䣬�����û�ˢ����ҳ��NullPointerException  	Ӧʹ��ת��
			//response.sendRedirect(request.getContextPath()+"/reception/cart.jsp");
			request.getRequestDispatcher("reception/cart.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
