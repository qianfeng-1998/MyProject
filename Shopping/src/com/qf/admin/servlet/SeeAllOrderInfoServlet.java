package com.qf.admin.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qf.admin.service.GetOrderDetailInfoService;
import com.qf.admin.service.GetOrderPageService;
import com.qf.admin.service.GetSingleOrderService;
import com.qf.admin.service.RemoveOrderService;
import com.qf.admin.service.UpdateOrderStatusService;
import com.qf.bean.CustomerInfo;
import com.qf.bean.GoodsInfo;
import com.qf.bean.OrderInfo;
import com.qf.bean.PageModel;
import com.qf.service.GetCustomerAllInfoService;
import com.qf.service.GetSingleGoodsService;

/**
@Author:
@desc:
@date:
*/
@WebServlet("/SeeAllOrderInfoServlet")
public class SeeAllOrderInfoServlet extends HttpServlet {

	//用于根据订单状态查询时，保存上一次的提交的状态，40行使用，点击下一页时，得到该status
	int oldStatus;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String op=request.getParameter("op");
		
		/**
		 * 根据状态查看订单，可能的值：0或1  还需要分页
		 * */
		if ("queryByStatus".equals(op)) {
			String status=request.getParameter("status");
			oldStatus=Integer.parseInt(status);
			String pageNo=request.getParameter("pageNo");
			PageModel<OrderInfo> page=GetOrderPageService.getPageByStatus(Integer.parseInt(status), Integer.parseInt(pageNo));
			HttpSession session=request.getSession();
			session.setAttribute("orderPage", page);
			response.sendRedirect(request.getContextPath()+"/admin/jsp/orderManage.jsp?op=queryByStatus&status="+oldStatus);
			return;
			
		/**
		 * 修改订单状态
		 * 提交的orderId和status
		 * */
		}else if ("changeStatus".equals(op)) {
			String status=request.getParameter("status");
			String id=request.getParameter("id");
			//根据id修改状态
			int row=UpdateOrderStatusService.updateStatus(Integer.parseInt(status), Integer.parseInt(id));
			if (row>0) {//修改成功
				response.sendRedirect(request.getContextPath()+"/SeeAllOrderInfoServlet?pageNo=1");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
				return;
			}
		
		/**
		 * 查看订单中商品详细信息
		 * */
		}else if ("toDetail".equals(op)) {
			String orderId=request.getParameter("id");
			//根据该orderId得到全部订单商品
			List<OrderInfo> orderList=GetSingleOrderService.getSingleOrder(Integer.parseInt(orderId));
			//得到该用户信息
			int userId=GetOrderDetailInfoService.getCustomerIdByOrderId(Integer.parseInt(orderId));
			//前台Service
			List<CustomerInfo> list=GetCustomerAllInfoService.getAllInfo(userId);
			
			//根据orderList中的goodsId得到商品信息
			List<GoodsInfo> goodsList=new ArrayList<>();
			for (Iterator<OrderInfo> iterator = orderList.iterator(); iterator.hasNext();) {
				OrderInfo goodsInfo = iterator.next();
				int goodsId=goodsInfo.getGoodsId();
				List<GoodsInfo> gList=GetSingleGoodsService.getSingleGoods(goodsId);
				goodsList.add(gList.get(0));
			}
			
			HttpSession session=request.getSession();
			session.setAttribute("userInfo", list);
			session.setAttribute("goodsList", goodsList);
			session.setAttribute("orderList", orderList);
			response.sendRedirect(request.getContextPath()+"/admin/jsp/showOrderDetail.jsp");
			return;
		
		/**
		 * 删除订单信息，根据订单编号
		 * */
		}else if ("remove".equals(op)) {
			String orderId=request.getParameter("id");
			int row=RemoveOrderService.deleteOrderInfo(Integer.parseInt(orderId));
			if (row>0) {//删除成功
				response.sendRedirect(request.getContextPath()+"/SeeAllOrderInfoServlet?pageNo=1");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
				return;
			}
		}
		
		/**
		 * 删除多个订单
		 * */
		else if ("removeMore".equals(op)) {
			String orderIds=request.getParameter("removeIds");
			String [] arrs=orderIds.split(",");
			int row=RemoveOrderService.deleteMoreOrderInfo(arrs);
			if (row>0) {//删除成功
				response.sendRedirect(request.getContextPath()+"/SeeAllOrderInfoServlet?pageNo=1");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
				return;
			}
		}
		//得到分页订单信息		同时得到用户信息
		String pageNo=request.getParameter("pageNo");
		PageModel<OrderInfo> page=GetOrderPageService.getPage(Integer.parseInt(pageNo));
		HttpSession session=request.getSession();
		session.setAttribute("orderPage", page);
		response.sendRedirect(request.getContextPath()+"/admin/jsp/orderManage.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
