package com.qf.admin.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.qf.admin.service.AdminLohinService;
import com.qf.bean.AdminUser;

/**
@Author:
@desc:管理员登录，查询userInfo表
@date:
*/
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Map map=request.getParameterMap();
		AdminUser user=new AdminUser();
		try {
			BeanUtils.populate(user, map);
			ArrayList<AdminUser> list=AdminLohinService.validation(user);
			if (list!=null) {
				if (list.size()>0) {//登录成功
					HttpSession session=request.getSession();
					Date d=new Date();
					String time=d.toLocaleString();
					session.setAttribute("loginAdminList", list);
					session.setAttribute("time", time);
					session.setMaxInactiveInterval(-1);
					response.sendRedirect(request.getContextPath()+"/admin/jsp/index.jsp");
					return;
				}
				else {
					response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
					return;
				}
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
				return;
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
