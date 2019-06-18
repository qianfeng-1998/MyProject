package com.qf.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qf.admin.service.GetTypePageService;
import com.qf.admin.service.RemoveTypeCheckOrderService;
import com.qf.admin.service.UpdateGoodsTypeService;
import com.qf.bean.GoodsTypeInfo;
import com.qf.bean.OrderGoodsInfo;
import com.qf.bean.PageModel;

/**
@Author:
@desc:
@date:
*/
@WebServlet("/SeeAllGoodsTypeServlet")
public class SeeAllGoodsTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String op=request.getParameter("op");
		/**
		 * ɾ��������Ʒ����
		 * */
		if ("remove".equals(op)) {
			//��Ҫ���ж϶��������Ƿ��Ѿ����ڸ���Ʒ���࣬���������ɾ��ʧ��
			//���ж���Ʒ��Ϣ���Ƿ���ڸ����࣬������ڣ�ɾ��ʧ��.
			
			//�õ���id:
			String typeId=request.getParameter("id");
			//�����Ʒ���������Ƿ��Ѵ��ڸ�TypeId��Ӧ��GoodsId(�����ж��):
			List<Integer> goodsIdList=RemoveTypeCheckOrderService.getGoodsIdList(Integer.parseInt(typeId));
			if (goodsIdList.size()<=0) {//�����ڸ�typeId��Ӧ����Ʒ
				//���鶩�������Ƿ���ڸ���ƷId
				List<OrderGoodsInfo> list=RemoveTypeCheckOrderService.validation(goodsIdList);
				if (list==null||list.isEmpty()) {//��������û�и���Ʒ&&��Ʒ��Ϣ����û�и����࣬����ɾ��
					int row=RemoveTypeCheckOrderService.removeGoodsType(Integer.parseInt(typeId));
					if (row>0) {//ɾ���ɹ�
						response.sendRedirect(request.getContextPath()+"/SeeAllGoodsTypeServlet?pageNo=1");
						return;
					}else {//ɾ��ʧ��
						response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
						return;
					}
				}else {//��ѯ�������������и����࣬ɾ����Ʒ����ʧ��
					response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
					return;
				}
			}else {//��Ʒ�д��ڸ����ͣ�ֱ��ʧ��
				response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
				return;
			}
		/**
		 * �����Ʒ��Ϣ
		 * */
		}else if ("toAdd".equals(op)) {//
			//����jspҳ�洦��
			response.sendRedirect(request.getContextPath()+"/admin/jsp/addGoodsType.jsp");
			return;
		/**
		 * ɾ�������Ʒ����
		 * */
		}else if ("removeMore".equals(op)) {//
			//���id���ַ���
			//System.out.println("����");
			String ids=request.getParameter("removeIds");
			String [] arrs=ids.split(",");
			List<Integer> GoodsIdList=RemoveTypeCheckOrderService.getGoodsIdByArrays(arrs);
			if (GoodsIdList.size()<=0) {//û��һ��typeId��Ӧ��goodsId������ɾ��
				List<OrderGoodsInfo> list=RemoveTypeCheckOrderService.validationGoodsIdArrays(GoodsIdList);
				if (list==null||list.isEmpty()) {
					//������û�ж�Ӧ����Ʒ������ɾ��
					int row=RemoveTypeCheckOrderService.removeMore(arrs);//row��ɾ�����ٸ�����ļ���
					//System.out.println(row);
					if (row>0) {//ɾ���ɹ�
						HttpSession session=request.getSession();
						session.setAttribute("row", row);
						response.sendRedirect(request.getContextPath()+"/SeeAllGoodsTypeServlet?pageNo=1");
						return;
					}else {
						response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
						return;
					}
				}else {//�����д���GoodsIds��Ӧ��Ʒ��Ϣ��ɾ��ʧ��
					response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
					return;
				}
			}else {//��Ʒ�д���GoodsIds��Ӧ��Ʒ��ɾ��ʧ��
				response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
				return;
			}
		}else if ("toEdit".equals(op)) {//�޸ĵ���
			String typeId=request.getParameter("id");
			//�õ�����������Ϣ
			GoodsTypeInfo gti=UpdateGoodsTypeService.getGoodsTypeInfo(Integer.parseInt(typeId));
			HttpSession session=request.getSession();
			session.setAttribute("type", gti);
			response.sendRedirect(request.getContextPath()+"/admin/jsp/updateGoodsType.jsp");
			return;
		} 
		//�õ�ȫ����Ʒ������Ϣ	�����ҳ
		String pageNo=request.getParameter("pageNo");
		PageModel<GoodsTypeInfo> page=GetTypePageService.getTypePage(Integer.parseInt(pageNo), 5);
		/*request.setAttribute("typePage", page);
		request.getRequestDispatcher(request.getContextPath()+"/admin/jsp/goodsTypeManage.jsp");*/
		HttpSession httpSession=request.getSession();
		httpSession.setAttribute("typePage", page);
		response.sendRedirect(request.getContextPath()+"/admin/jsp/goodsTypeManage.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
