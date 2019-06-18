package com.qf.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qf.bean.GoodsInfo;
import com.qf.bean.ShoppingCarInfo;
import com.qf.service.GetSingleGoodsService;
import com.qf.service.ShoppingCarService;

/**
@Author:
@desc:���ﳵ
@date:
*/
@WebServlet("/ShoppingCarServlet")
public class ShoppingCarServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String flag=request.getParameter("op");
		if (flag==null||"".equals(flag)) {
			//System.out.println("��");
			response.sendRedirect(request.getContextPath()+"/reception/cart.jsp");
			return;
		}else {
			String getGoodsId=request.getParameter("id");
			String getQuantity=request.getParameter("num");
			//System.out.println("servlet�õ��ύ��������"+getQuantity);
			//���ж��û��ٲ���
			HttpSession session=request.getSession();
			Object getUserId=session.getAttribute("userId");
			
			if (getUserId==null||"".equals(getUserId)) {//δ��¼
				response.sendRedirect(request.getContextPath()+"/reception/login_register.jsp");
				return;
			}else {//�ѵ�¼
				int userId=(int) session.getAttribute("userId");
				//��ѯ��Ϊ��ͼ���ɸ���name�õ�userId(����ȡ�����ֿ��ܻ����� ('���ֶ���Լ��'))
				ShoppingCarInfo car=new ShoppingCarInfo(Integer.parseInt(getGoodsId), Integer.parseInt(getQuantity), userId);
				//��鹺�ﳵ�и��û��Ƿ����и���Ʒ���������Ӹ���Ʒ������
				ArrayList<ShoppingCarInfo> list2= ShoppingCarService.checkShoppingCarInfo(Integer.parseInt(getGoodsId), userId);
				if (list2.size()>0) {//�Ѳ�ѯ����ֻ��������
					int row=ShoppingCarService.updateShoppingCar(car);
					if (row>0) {
						//int updateRow=ShoppingCarService.updateShoppingCar(car);
						//System.out.println("servlet�µ�������"+car.getQuantity());
						response.sendRedirect(request.getContextPath()+"/OtherJspToCartServlet");
						return;
					}
				}else {
					int row=ShoppingCarService.insertShoppingCar(car);
					if (row>0) {
						response.sendRedirect(request.getContextPath()+"/OtherJspToCartServlet");
					}
				}
				//������ٻ�ȡ
				/*if (row>0) {//��ӳɹ�
					//list�����list��
					ArrayList<GoodsInfo> list=new ArrayList<>();
					//����userId�õ����û��Ĺ��ﳵ��������Ϣ
					ArrayList<ShoppingCarInfo> shoppingCarList=ShoppingCarService.getShoppingCarInfo(userId);
					//���ù��ﳵ�е�goodsId��ȡ�ø���Ʒ��������Ϣ�͹��ﳵ��Ϣ
					for (int i=0;i<shoppingCarList.size();i++) {
						//�õ�goodsId:
						int goodsId=shoppingCarList.get(i).getGoodsId();
						//����goodsId�õ���Ʒ��Ϣ��
						List<GoodsInfo> singleGoodsInfoList=GetSingleGoodsService.getSingleGoods(goodsId);
						//����Ҫ��session��ŵ�list�������Ϣ
						for (int j = 0; j < singleGoodsInfoList.size(); j++) {
							list.add(singleGoodsInfoList.get(j));
						}
					}
					session.setAttribute("shoppingCarList", list);
					session.setAttribute("carInfoList", shoppingCarList);
					//��ȥ֮���ַ�����ܱ䣬�����û�ˢ����ҳ��NullPointerException  	Ӧʹ��ת��
					//response.sendRedirect(request.getContextPath()+"/reception/cart.jsp");
					request.getRequestDispatcher("reception/cart.jsp").forward(request, response);
					
				}*/
			}
		}
	}
	/**
	 * ����֮�⣬����Ҫװ�Ÿ���Ʒ��Ӧ��shoppingCarInfo��������Ϣ
	 * */

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
