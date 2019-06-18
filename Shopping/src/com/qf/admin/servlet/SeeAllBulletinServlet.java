package com.qf.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qf.admin.service.RemoveBulletinService;
import com.qf.bean.BulletinInfo;
import com.qf.bean.PageModel;
import com.qf.dao.AccordingIdGetBulletinInfo;
import com.qf.service.GetBulletinPageService;

/**
@Author:
@desc:����֮�⣬���ύ�˺ܶ�����ֵ�������ֲ�����ɾ�����桢��
@date:
*/
@WebServlet("/SeeAllBulletinServlet")
public class SeeAllBulletinServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String op=request.getParameter("op");
		
		if ("removeMore".equals(op)) {//ɾ�������Ϣ
			String removeIds=request.getParameter("removeIds");
			String [] arrs=removeIds.split(",");
			int row=RemoveBulletinService.removeBulletin(arrs);
			if (row>0) {
				//�ڲ���ת
				response.sendRedirect(request.getContextPath()+"/SeeAllBulletinServlet?pageNo=1");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"admin/jsp/failed.jsp");
				return;
			}
		}else if("remove".equals(op)) {//ɾ������
			String id=request.getParameter("id");
			int row=RemoveBulletinService.removeSingleBulletin(Integer.parseInt(id));
			if (row>0) {
				//�ڲ���ת
				response.sendRedirect(request.getContextPath()+"/SeeAllBulletinServlet?pageNo=1");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"admin/jsp/failed.jsp");
				return;
			}
		}else if ("toEdit".equals(op)) {//�޸�
			String id=request.getParameter("id");
			//System.out.println("��se"+id);
			//����id�õ��ñ�������ݣ�ת��jsp���ٽ�������servlet����
			List<BulletinInfo> list=AccordingIdGetBulletinInfo.getSingleBulletin(Integer.parseInt(id));
			HttpSession session=request.getSession();
			session.setAttribute("list", list);
			//request.setAttribute("list", list);
			//System.out.println("�õ�List");
			//����ת������֪��Ϊʲô,��ת�ˣ�����û����ʾ
			//request.getRequestDispatcher(request.getContextPath()+"/admin/jsp/updateBulletin.jsp");
			response.sendRedirect(request.getContextPath()+"/admin/jsp/updateBulletin.jsp");
			//System.out.println("��ת��");
			return;
		}
		
		/**
		 * �õ����沢��ʾ:
		 * */
		String pageNo=request.getParameter("pageNo");
		//����ǰ̨�ķ�ҳ
		PageModel<BulletinInfo> page=GetBulletinPageService.getBulletinPageSer(Integer.parseInt(pageNo), 5);
		request.setAttribute("AdminBulletinPage", page);
		request.getRequestDispatcher("admin/jsp/bulletinManage.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
