package com.qf.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qf.admin.service.GetDoodsPageService;
import com.qf.admin.service.RemoveGoodsService;
import com.qf.bean.GoodsInfo;
import com.qf.bean.GoodsTypeInfo;
import com.qf.bean.PageModel;
import com.qf.service.GetGoodsTypeService;
import com.qf.service.GetSingleGoodsService;

/**
@Author:
@desc:
@date:
*/
@WebServlet("/AllGoodsPageServlet")
public class AllGoodsPageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String op=request.getParameter("op");
		if ("removeMore".equals(op)) {//ɾ�����
			String id=request.getParameter("removeIds");
			String [] arrs=id.split(",");
			int row=RemoveGoodsService.removeMoreGoods(arrs);
			if (row>0) {
				response.sendRedirect(request.getContextPath()+"/AllGoodsPageServlet?pageNo=1");
				return ;
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
				return;
			}
		}else if ("toAdd".equals(op)) {//�����Ʒ����������servlet����
			response.sendRedirect(request.getContextPath()+"/AddGoodsServlet");
			return;
		}else if ("remove".equals(op)) {//ɾ��������Ʒ
			String id=request.getParameter("id");
			int row=RemoveGoodsService.removeSingleGoods(Integer.parseInt(id));
			if (row>0) {
				response.sendRedirect(request.getContextPath()+"/AllGoodsPageServlet?pageNo=1");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
				return;
			}
		}else if ("toEdit".equals(op)) {//�޸���Ʒ��Ϣ
			String id=request.getParameter("id");
			//�õ���id��Ӧ����Ʒ��Ϣ
			//����ǰ̨service�õ���ӦgoodsId����Ʒ��Ϣ
			List<GoodsInfo> list1=GetSingleGoodsService.getSingleGoods(Integer.parseInt(id));
			//����ǰ̨service�õ�ȫ��goodsType
			List<GoodsTypeInfo> list2=GetGoodsTypeService.getTypes();
			HttpSession session=request.getSession();
			session.setAttribute("singleGoodsList", list1);
			session.setAttribute("allTypeList", list2);
			response.sendRedirect(request.getContextPath()+"/admin/jsp/updateGoods.jsp");
			return;
		}
		String getPageNo=request.getParameter("pageNo");
		PageModel<GoodsInfo> page=GetDoodsPageService.getGoodsPage(Integer.parseInt(getPageNo), 10);
		HttpSession session=request.getSession();
		session.setAttribute("page", page);
		response.sendRedirect(request.getContextPath()+"/admin/jsp/goodsManage.jsp");
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
