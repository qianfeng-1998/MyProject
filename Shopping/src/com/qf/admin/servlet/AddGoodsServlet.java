package com.qf.admin.servlet;

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

import com.qf.admin.service.AddGoodsService;
import com.qf.bean.GoodsInfo;
import com.qf.bean.GoodsTypeInfo;
import com.qf.service.GetGoodsTypeService;

/**
@Author:
@desc:�����Ʒ����Ҫ�ȵõ���Ʒ������
@date:
*/
@WebServlet("/AddGoodsServlet")
public class AddGoodsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String op=request.getParameter("op");
		if ("add".equals(op)) {//�����Ʒ
			Map map=request.getParameterMap();
			GoodsInfo gi=new GoodsInfo();
			try {
				BeanUtils.populate(gi, map);
				int row=AddGoodsService.insertGoodsService(gi);
				if (row>0) {
					//��ת����Ʒ��Ϣ����
					//System.out.println("��ӳɹ�");
					response.sendRedirect(request.getContextPath()+"/AllGoodsPageServlet?pageNo=1");
					return;
				}else {
					response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
					//System.out.println("���ʧ��");
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
		//����ǰ̨Dao���GetGoodsTypeService�õ�ȫ������
		List<GoodsTypeInfo> list=GetGoodsTypeService.getTypes();
	
		//request.setAttribute("typeList", list);
		//request.getRequestDispatcher(request.getContextPath()+"/admin/jsp/addGoods.jsp");
		HttpSession session=request.getSession();
		session.setAttribute("typeList", list);
		response.sendRedirect(request.getContextPath()+"/admin/jsp/addGoods.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
