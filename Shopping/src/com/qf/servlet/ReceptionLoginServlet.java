package com.qf.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.qf.bean.CustomerInfo;
import com.qf.service.LoginImpl;
import com.qf.service.RegisterUser;


/**
@Author:
@desc:登录与注册为1个servlet，提交的标识为op。
	根据视频得知，注册成功同时已经登录，跳转到操作成功页面
	登录成功查询视图得到一个name，往session存放标识，设置时间
@date:
*/
@WebServlet("/ReceptionLoginServlet")
public class ReceptionLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//登录注册页
		request.setCharacterEncoding("UTf-8");
		String op=request.getParameter("op");
		
		if ("login".equals(op)) {//提交的为登录按钮
			String email=request.getParameter("userName");//账号
			String pwd=request.getParameter("userPwd");//密码
			List<CustomerInfo> list=LoginImpl.validationImpl(email, pwd);
			if (list.size()>0) {//已有该账户
				
				String name=LoginImpl.getUserName(email);
				HttpSession session=request.getSession();
				session.setAttribute("name", name);
				//添加的功能：后面购物车ShoppingCarServlet需要用到用户的id
				session.setAttribute("userId", list.get(0).getId());
				//System.out.println("登录输出："+list.get(0).getId());
				session.setMaxInactiveInterval(-1);
				
				response.sendRedirect(request.getContextPath()+"/reception/index.jsp");
				System.out.println("登录成功");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/reception/login_register.jsp");
				System.out.println("登录失败");
				return;
			}
		}else {//提交的为注册按钮
			Map map=request.getParameterMap();
			CustomerInfo ci=new CustomerInfo();
			try {
				BeanUtils.populate(ci, map);
				int row=RegisterUser.registerSingleUserService(ci);
				if (row>0) {//注册成功
					response.sendRedirect(request.getContextPath()+"/reception/ok.jsp");
					System.out.println("注册成功");
					return;
				}else {
					response.sendRedirect(request.getContextPath()+"/reception/login_register.jsp");
					System.out.println("注册失败");
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
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

