package com.qf.admin.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.qf.admin.service.UpdateGoodsTypeService;
import com.qf.bean.GoodsTypeInfo;

/**
@Author:
@desc:
@date:
*/
@WebServlet("/UpdateTypeServlet")
public class UpdateTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Map map=request.getParameterMap();
		GoodsTypeInfo gti=new GoodsTypeInfo();
		try {
			BeanUtils.populate(gti, map);
			int row=UpdateGoodsTypeService.updateTypeInfo(gti);
			if (row>0) {
				response.sendRedirect(request.getContextPath()+"/SeeAllGoodsTypeServlet?pageNo=1");
				return;
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
