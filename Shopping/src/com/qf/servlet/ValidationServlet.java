package com.qf.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.bean.CustomerInfo;
import com.qf.service.ValidationService;

/**
@Author:
@desc:ajax验证   login_register.jsp中callback函数使用
@date:
*/
@WebServlet("/Validation")
public class ValidationServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String email=request.getParameter("email");
		List<CustomerInfo> list=ValidationService.validationService(email);
		if (list.size()>0) {
			if (list.get(0).getEmail().equals(email)) {
				response.getWriter().write("true");
			}else {//不等于
				response.getWriter().write("false");
			}
		}else {//没有查询到，说明该邮箱可以使用
			response.getWriter().write("false");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
