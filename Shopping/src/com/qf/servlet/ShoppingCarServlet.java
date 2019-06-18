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
@desc:购物车
@date:
*/
@WebServlet("/ShoppingCarServlet")
public class ShoppingCarServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String flag=request.getParameter("op");
		if (flag==null||"".equals(flag)) {
			//System.out.println("这");
			response.sendRedirect(request.getContextPath()+"/reception/cart.jsp");
			return;
		}else {
			String getGoodsId=request.getParameter("id");
			String getQuantity=request.getParameter("num");
			//System.out.println("servlet得到提交的数量："+getQuantity);
			//先判断用户再操作
			HttpSession session=request.getSession();
			Object getUserId=session.getAttribute("userId");
			
			if (getUserId==null||"".equals(getUserId)) {//未登录
				response.sendRedirect(request.getContextPath()+"/reception/login_register.jsp");
				return;
			}else {//已登录
				int userId=(int) session.getAttribute("userId");
				//查询的为视图，可根据name得到userId(不可取：名字可能会重名 ('该字段无约束'))
				ShoppingCarInfo car=new ShoppingCarInfo(Integer.parseInt(getGoodsId), Integer.parseInt(getQuantity), userId);
				//检查购物车中该用户是否已有该商品，有则增加该商品的数量
				ArrayList<ShoppingCarInfo> list2= ShoppingCarService.checkShoppingCarInfo(Integer.parseInt(getGoodsId), userId);
				if (list2.size()>0) {//已查询到，只增加数量
					int row=ShoppingCarService.updateShoppingCar(car);
					if (row>0) {
						//int updateRow=ShoppingCarService.updateShoppingCar(car);
						//System.out.println("servlet新的数量："+car.getQuantity());
						response.sendRedirect(request.getContextPath()+"/OtherJspToCartServlet");
						return;
					}
				}else {
					int row=ShoppingCarService.insertShoppingCar(car);
					if (row>0) {
						response.sendRedirect(request.getContextPath()+"/OtherJspToCartServlet");
					}
				}
				//先添加再获取
				/*if (row>0) {//添加成功
					//list里面放list：
					ArrayList<GoodsInfo> list=new ArrayList<>();
					//根据userId得到该用户的购物车的所有信息
					ArrayList<ShoppingCarInfo> shoppingCarList=ShoppingCarService.getShoppingCarInfo(userId);
					//利用购物车中的goodsId，取得该商品的所有信息和购物车信息
					for (int i=0;i<shoppingCarList.size();i++) {
						//得到goodsId:
						int goodsId=shoppingCarList.get(i).getGoodsId();
						//根据goodsId得到商品信息：
						List<GoodsInfo> singleGoodsInfoList=GetSingleGoodsService.getSingleGoods(goodsId);
						//往需要向session存放的list中添加信息
						for (int j = 0; j < singleGoodsInfoList.size(); j++) {
							list.add(singleGoodsInfoList.get(j));
						}
					}
					session.setAttribute("shoppingCarList", list);
					session.setAttribute("carInfoList", shoppingCarList);
					//过去之后地址栏不能变，否则用户刷新网页则NullPointerException  	应使用转发
					//response.sendRedirect(request.getContextPath()+"/reception/cart.jsp");
					request.getRequestDispatcher("reception/cart.jsp").forward(request, response);
					
				}*/
			}
		}
	}
	/**
	 * 除此之外，还需要装着该商品对应的shoppingCarInfo的完整信息
	 * */

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
