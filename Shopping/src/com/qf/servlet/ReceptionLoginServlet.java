package com.qf.servlet;

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

import com.qf.bean.CustomerInfo;
import com.qf.service.LoginImpl;
import com.qf.service.RegisterUser;


/**
@Author:
@desc:��¼��ע��Ϊ1��servlet���ύ�ı�ʶΪop��
	������Ƶ��֪��ע��ɹ�ͬʱ�Ѿ���¼����ת�������ɹ�ҳ��
	��¼�ɹ���ѯ��ͼ�õ�һ��name����session��ű�ʶ������ʱ��
@date:
*/
@WebServlet("/ReceptionLoginServlet")
public class ReceptionLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��¼ע��ҳ
		request.setCharacterEncoding("UTf-8");
		String op=request.getParameter("op");
		
		if ("login".equals(op)) {//�ύ��Ϊ��¼��ť
			String email=request.getParameter("userName");//�˺�
			String pwd=request.getParameter("userPwd");//����
			List<CustomerInfo> list=LoginImpl.validationImpl(email, pwd);
			if (list.size()>0) {//���и��˻�
				
				String name=LoginImpl.getUserName(email);
				HttpSession session=request.getSession();
				session.setAttribute("name", name);
				//��ӵĹ��ܣ����湺�ﳵShoppingCarServlet��Ҫ�õ��û���id
				session.setAttribute("userId", list.get(0).getId());
				//System.out.println("��¼�����"+list.get(0).getId());
				session.setMaxInactiveInterval(-1);
				
				response.sendRedirect(request.getContextPath()+"/reception/index.jsp");
				System.out.println("��¼�ɹ�");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/reception/login_register.jsp");
				System.out.println("��¼ʧ��");
				return;
			}
		}else {//�ύ��Ϊע�ᰴť
			Map map=request.getParameterMap();
			CustomerInfo ci=new CustomerInfo();
			try {
				BeanUtils.populate(ci, map);
				int row=RegisterUser.registerSingleUserService(ci);
				if (row>0) {//ע��ɹ�
					response.sendRedirect(request.getContextPath()+"/reception/ok.jsp");
					System.out.println("ע��ɹ�");
					return;
				}else {
					response.sendRedirect(request.getContextPath()+"/reception/login_register.jsp");
					System.out.println("ע��ʧ��");
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
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

