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
		 * 删除单个商品种类
		 * */
		if ("remove".equals(op)) {
			//需要先判断订单表中是否已经存在该商品种类，如果存在则删除失败
			//再判断商品信息中是否存在该种类，如果存在，删除失败.
			
			//得到该id:
			String typeId=request.getParameter("id");
			//检查商品订单表中是否已存在该TypeId对应的GoodsId(可能有多个):
			List<Integer> goodsIdList=RemoveTypeCheckOrderService.getGoodsIdList(Integer.parseInt(typeId));
			if (goodsIdList.size()<=0) {//不存在该typeId对应的商品
				//检验订单表中是否存在该商品Id
				List<OrderGoodsInfo> list=RemoveTypeCheckOrderService.validation(goodsIdList);
				if (list==null||list.isEmpty()) {//订单表中没有该商品&&商品信息表中没有该种类，可以删除
					int row=RemoveTypeCheckOrderService.removeGoodsType(Integer.parseInt(typeId));
					if (row>0) {//删除成功
						response.sendRedirect(request.getContextPath()+"/SeeAllGoodsTypeServlet?pageNo=1");
						return;
					}else {//删除失败
						response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
						return;
					}
				}else {//查询到订单表中已有该种类，删除商品种类失败
					response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
					return;
				}
			}else {//商品中存在该类型，直接失败
				response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
				return;
			}
		/**
		 * 添加商品信息
		 * */
		}else if ("toAdd".equals(op)) {//
			//交由jsp页面处理
			response.sendRedirect(request.getContextPath()+"/admin/jsp/addGoodsType.jsp");
			return;
		/**
		 * 删除多个商品种类
		 * */
		}else if ("removeMore".equals(op)) {//
			//多个id的字符串
			//System.out.println("进入");
			String ids=request.getParameter("removeIds");
			String [] arrs=ids.split(",");
			List<Integer> GoodsIdList=RemoveTypeCheckOrderService.getGoodsIdByArrays(arrs);
			if (GoodsIdList.size()<=0) {//没有一个typeId对应的goodsId，可以删除
				List<OrderGoodsInfo> list=RemoveTypeCheckOrderService.validationGoodsIdArrays(GoodsIdList);
				if (list==null||list.isEmpty()) {
					//订单中没有对应的商品，可以删除
					int row=RemoveTypeCheckOrderService.removeMore(arrs);//row是删除多少个种类的计数
					//System.out.println(row);
					if (row>0) {//删除成功
						HttpSession session=request.getSession();
						session.setAttribute("row", row);
						response.sendRedirect(request.getContextPath()+"/SeeAllGoodsTypeServlet?pageNo=1");
						return;
					}else {
						response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
						return;
					}
				}else {//订单中存在GoodsIds对应商品信息，删除失败
					response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
					return;
				}
			}else {//商品中存在GoodsIds对应商品，删除失败
				response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
				return;
			}
		}else if ("toEdit".equals(op)) {//修改单个
			String typeId=request.getParameter("id");
			//得到单个种类信息
			GoodsTypeInfo gti=UpdateGoodsTypeService.getGoodsTypeInfo(Integer.parseInt(typeId));
			HttpSession session=request.getSession();
			session.setAttribute("type", gti);
			response.sendRedirect(request.getContextPath()+"/admin/jsp/updateGoodsType.jsp");
			return;
		} 
		//得到全部商品种类信息	还需分页
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
