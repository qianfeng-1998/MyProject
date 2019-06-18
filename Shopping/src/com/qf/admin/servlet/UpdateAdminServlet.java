package com.qf.admin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qf.admin.service.UpdateAdminService;
import com.qf.bean.AdminUser;

/**
@Author:
@desc:
@date:
*/
@WebServlet("/UpdateAdminPwdServlet")
public class UpdateAdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String op=request.getParameter("op");
		String oldPwd=request.getParameter("oldPwd");
		HttpSession session=request.getSession();
		ArrayList<AdminUser> list=(ArrayList) session.getAttribute("loginAdminList");
		//System.out.println("ԭ���룺"+list.get(0).getUserPwd());
		
		if ("resetPass".equals(op)) {//�޸�����
			if (!(oldPwd.equals(list.get(0).getUserPwd()))) {//���ԭ���벻ƥ�䣬�޸�ʧ��
				session.setAttribute("instructions", "���벻ƥ��");
				response.sendRedirect(request.getContextPath()+"/admin/jsp/resetPass.jsp");
				return;
			}else {//�����޸�
				String newPwd=request.getParameter("newPwd");
				int row=UpdateAdminService.updateAdminPwd(newPwd, list.get(0).getUserName());
				if (row>0) {
					response.sendRedirect(request.getContextPath()+"/admin/jsp/ok.jsp");
					return;
				}else {
					response.sendRedirect(request.getContextPath()+"/admin/jsp/resetPass.jsp");
					return;
				}
			}
		}else if ("resetUserName".equals(op)) {//�޸Ĺ���Ա�û���
			String newUserName=request.getParameter("userName");
			int row=UpdateAdminService.updateAdminUserName(newUserName, list.get(0).getUserName());
			if (row>0) {
				response.sendRedirect(request.getContextPath()+"/admin/jsp/ok.jsp");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/jsp/resetPass.jsp");
				return;
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

