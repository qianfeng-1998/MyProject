package com.qf.admin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qf.admin.service.GetCustomerPageService;
import com.qf.admin.service.RemoveCustomerCheckService;
import com.qf.bean.CustomerInfo;
import com.qf.bean.PageModel;
import com.qf.service.GetCustomerAllInfoService;

/**
@Author:
@desc:�鿴�����������û���Ϣ	��ҳ
@date:
*/
@WebServlet("/SeeAllCustomerServlet")
public class SeeAllCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String op=request.getParameter("op");

		//System.out.println(op);
		if (op!=null&&!("".equals(op))&&!("null".equals(op))) {
			switch (op) {
			case "remove":
				/*ɾ�������û���Ϣ	�ύ���Ǳ��е�Id	
				 * ɾ���û���Ϣ����Ҫ���ж�CustomerDetailInfo��OrderInfo�����Ƿ���ڸ�����
				 * �ȵõ�orderInfo���Ƿ���ڣ�������������Ѵ��ڸ��û���ֱ�Ӳ���ɾ����
				 * �ж�CustomerDetailInfo������ñ��д��ڸ����ã���ɾ���˱�
				 * 
				 * ��ʱ����shoppingCar����Ҳ������customerinfo��
				 * �����������ﳵ��Ϣ���и�Id����ɾ�����ﳵ����ɾ��customerinfo��
				 * */
				String id=request.getParameter("id");
				int userId=Integer.parseInt(id);
				ArrayList<Integer> list=RemoveCustomerCheckService.checkOrderInfo(userId);
				//System.out.println(list.size());
				if (list==null||list.isEmpty()) {//Ϊ�գ�����ɾ��
					int row=RemoveCustomerCheckService.deleteUser(userId);
					//System.out.println(row);
					if (row>0) {
						response.sendRedirect(request.getContextPath()+"/SeeAllCustomerServlet?pageNo=1");
						return;
					}else {
						response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
						return;
					}
				}else {//orderInfo�����Ѵ��ڸ����ݣ�����ɾ��
					response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
					return;
				}
				//break;
			case "removeMore":
				/*
				 * ɾ������û���Ϣ
				 * �õ����Ƕ���û����
				 * ԭ����ɾ������һ��
				 * */
				String ids=request.getParameter("removeIds");
				String [] arrs=ids.split(",");
				//�ȼ�鶩����
				ArrayList<Integer> list1=RemoveCustomerCheckService.removeMoreCheckOrderInfo(arrs);
				if (list1!=null&&!(list1.isEmpty())) {//��Ϊ�գ�ֱ��ʧ��
					response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
					return;
				}else {//Ϊ�գ�����ɾ��
					int row=RemoveCustomerCheckService.deleteMore(arrs);
					if (row>0) {
						response.sendRedirect(request.getContextPath()+"/SeeAllCustomerServlet?pageNo=1");
						return;
					}else {
						response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
						return;
					}
				}
				//break;
			case "toEdit":
				/*
				 * �޸ĵ����û���Ϣ
				 * ����id���õ������û�ȫ����Ϣ
				 * ��������servlet����
				 * */
				String userId1=request.getParameter("id");
				//����ǰ̨service�õ������û���Ϣ
				ArrayList<CustomerInfo> customerList=GetCustomerAllInfoService.getAllInfo(Integer.parseInt(userId1));
				HttpSession session=request.getSession();
				session.setAttribute("list", customerList);
				response.sendRedirect(request.getContextPath()+"/admin/jsp/updateCustomer.jsp");
				return;
				//break;
			default:
				
				break;
			}
		}
		//�õ���ҳ�û���Ϣ
		/*
		 * customerdetailinfo����Ҫ����id��ã��Ȳ�ѯǰ��customerinfo���õ�һ��list��
		 * ����list�е�id�Ƚ�customerdetailinfo��id��
		 * ���ƥ���ˣ���ȥ��ѯ�õ���ϸ��Ϣ
		 *���ûƥ�䣬list����ϸ��Ϣ���Null
		 *(ע�⣬���صı�����һ��List)
		 * */
		String getPageNo=request.getParameter("pageNo");
		int pageNo=Integer.parseInt(getPageNo);
		PageModel<CustomerInfo> page=GetCustomerPageService.getUserPageService(pageNo);
		HttpSession session=request.getSession();
		session.setAttribute("cusPage", page);
		response.sendRedirect(request.getContextPath()+"/admin/jsp/customerManage.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
