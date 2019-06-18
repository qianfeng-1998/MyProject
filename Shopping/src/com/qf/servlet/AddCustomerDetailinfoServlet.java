package com.qf.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.qf.bean.CustomerInfo;
import com.qf.service.AddCustomerDetailInfoService;

/**
@Author:
@desc:
@date:
*/
@WebServlet("/AddCustomerDetailinfoServlet")
public class AddCustomerDetailinfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Map map=request.getParameterMap();
		CustomerInfo ci=new CustomerInfo();
		try {
			BeanUtils.populate(ci, map);
			int row=AddCustomerDetailInfoService.insertDetail(ci);
			if (row>0) {
				response.sendRedirect(request.getContextPath()+"/OtherJspToCartServlet");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/reception/failed.jsp");
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
