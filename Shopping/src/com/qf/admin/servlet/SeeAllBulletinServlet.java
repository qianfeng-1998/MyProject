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
@desc:除此之外，还提交了很多隐藏值，即各种操作（删除公告、）
@date:
*/
@WebServlet("/SeeAllBulletinServlet")
public class SeeAllBulletinServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String op=request.getParameter("op");
		
		if ("removeMore".equals(op)) {//删除多个信息
			String removeIds=request.getParameter("removeIds");
			String [] arrs=removeIds.split(",");
			int row=RemoveBulletinService.removeBulletin(arrs);
			if (row>0) {
				//内部跳转
				response.sendRedirect(request.getContextPath()+"/SeeAllBulletinServlet?pageNo=1");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"admin/jsp/failed.jsp");
				return;
			}
		}else if("remove".equals(op)) {//删除单个
			String id=request.getParameter("id");
			int row=RemoveBulletinService.removeSingleBulletin(Integer.parseInt(id));
			if (row>0) {
				//内部跳转
				response.sendRedirect(request.getContextPath()+"/SeeAllBulletinServlet?pageNo=1");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"admin/jsp/failed.jsp");
				return;
			}
		}else if ("toEdit".equals(op)) {//修改
			String id=request.getParameter("id");
			//System.out.println("到se"+id);
			//根据id得到该标题的内容，转交jsp，再交由其他servlet处理
			List<BulletinInfo> list=AccordingIdGetBulletinInfo.getSingleBulletin(Integer.parseInt(id));
			HttpSession session=request.getSession();
			session.setAttribute("list", list);
			//request.setAttribute("list", list);
			//System.out.println("得到List");
			//不能转发，不知道为什么,跳转了，但是没有显示
			//request.getRequestDispatcher(request.getContextPath()+"/admin/jsp/updateBulletin.jsp");
			response.sendRedirect(request.getContextPath()+"/admin/jsp/updateBulletin.jsp");
			//System.out.println("跳转了");
			return;
		}
		
		/**
		 * 得到公告并显示:
		 * */
		String pageNo=request.getParameter("pageNo");
		//引用前台的分页
		PageModel<BulletinInfo> page=GetBulletinPageService.getBulletinPageSer(Integer.parseInt(pageNo), 5);
		request.setAttribute("AdminBulletinPage", page);
		request.getRequestDispatcher("admin/jsp/bulletinManage.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
