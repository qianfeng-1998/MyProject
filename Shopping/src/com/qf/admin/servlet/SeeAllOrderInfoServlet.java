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

	//���ڸ��ݶ���״̬��ѯʱ��������һ�ε��ύ��״̬��40��ʹ�ã������һҳʱ���õ���status
	int oldStatus;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String op=request.getParameter("op");
		
		/**
		 * ����״̬�鿴���������ܵ�ֵ��0��1  ����Ҫ��ҳ
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
		 * �޸Ķ���״̬
		 * �ύ��orderId��status
		 * */
		}else if ("changeStatus".equals(op)) {
			String status=request.getParameter("status");
			String id=request.getParameter("id");
			//����id�޸�״̬
			int row=UpdateOrderStatusService.updateStatus(Integer.parseInt(status), Integer.parseInt(id));
			if (row>0) {//�޸ĳɹ�
				response.sendRedirect(request.getContextPath()+"/SeeAllOrderInfoServlet?pageNo=1");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
				return;
			}
		
		/**
		 * �鿴��������Ʒ��ϸ��Ϣ
		 * */
		}else if ("toDetail".equals(op)) {
			String orderId=request.getParameter("id");
			//���ݸ�orderId�õ�ȫ��������Ʒ
			List<OrderInfo> orderList=GetSingleOrderService.getSingleOrder(Integer.parseInt(orderId));
			//�õ����û���Ϣ
			int userId=GetOrderDetailInfoService.getCustomerIdByOrderId(Integer.parseInt(orderId));
			//ǰ̨Service
			List<CustomerInfo> list=GetCustomerAllInfoService.getAllInfo(userId);
			
			//����orderList�е�goodsId�õ���Ʒ��Ϣ
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
		 * ɾ��������Ϣ�����ݶ������
		 * */
		}else if ("remove".equals(op)) {
			String orderId=request.getParameter("id");
			int row=RemoveOrderService.deleteOrderInfo(Integer.parseInt(orderId));
			if (row>0) {//ɾ���ɹ�
				response.sendRedirect(request.getContextPath()+"/SeeAllOrderInfoServlet?pageNo=1");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
				return;
			}
		}
		
		/**
		 * ɾ���������
		 * */
		else if ("removeMore".equals(op)) {
			String orderIds=request.getParameter("removeIds");
			String [] arrs=orderIds.split(",");
			int row=RemoveOrderService.deleteMoreOrderInfo(arrs);
			if (row>0) {//ɾ���ɹ�
				response.sendRedirect(request.getContextPath()+"/SeeAllOrderInfoServlet?pageNo=1");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
				return;
			}
		}
		//�õ���ҳ������Ϣ		ͬʱ�õ��û���Ϣ
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
