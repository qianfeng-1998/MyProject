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

import com.qf.admin.service.UpdateGoodsService;
import com.qf.bean.GoodsInfo;

/**
@Author:
@desc:
@date:
*/
@WebServlet("/UpdateGoodsServlet")
public class UpdateGoodsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Map map=request.getParameterMap();
		GoodsInfo gi=new GoodsInfo();
		try {
			BeanUtils.populate(gi, map);
			//System.out.println(gi.getGoodsId());
			int row=UpdateGoodsService.updateGoods(gi);
			if (row>0) {
				response.sendRedirect(request.getContextPath()+"/AllGoodsPageServlet?pageNo=1");
				return ;
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
