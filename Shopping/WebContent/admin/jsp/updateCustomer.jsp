<%@page import="com.qf.bean.BulletinInfo"%>
<%@page import="com.qf.bean.CustomerInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
ArrayList<CustomerInfo> list = (ArrayList)session.getAttribute("list");
BulletinInfo bu=new BulletinInfo();
CustomerInfo customer=list.get(0);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
out.println("<base href=\""+basePath+"\">");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看/修改客户信息</title>
<link style="text/csss" rel="stylesheet" href="admin/css/add_skin.css">
<link style="text/csss" rel="stylesheet" href="admin/css/common.css">
<script type="text/javascript">
	function check(){
		 var email=document.getElementById("email").value;
		 var name=document.getElementById("name").value;
		 var telphone=document.getElementById("telphone").value;
		 var movePhone=document.getElementById("movePhone").value;
		 var address=document.getElementById("address").value;
		 if (email=="<%=customer.getEmail()%>") {
			 alert("修改后的邮箱不能与原来的邮箱一样！");
			 return false;
		}
		if (name=="") {
			alert("收货人姓名不能为空！");
			return false;
		}
		if (telphone=="") {
			alert("固定电话不能为空！");
			return false;
		}
		if (movePhone=="") {
			alert("移动电话不能为空！");
			return false;
		}
		if (address=="") {
			alert("收货地址不能为空！");
			return false;
		}
		return true;
	}
</script>
<style type="text/css">
.name_td{width:180px;}
.long_input_text{width:300px;}
#number_span{padding:3px 10px 3px 10px;font-size:120%;border:1px solid #ADAEB0;background-color:#fff;color:#ADAEB0;font-weight:bold;}
</style>
</head>
<body>
<form action="UpdateCustomerServlet" method="post" onsubmit="return check()">
	<input type="hidden" name="id" value="<%=customer.getId()%>" />
	<div class="opDiv">
			<div class="titlebar">查看/修改客户信息</div>
	</div>
	<table class="addTable">
		<tr>
			<td class="name_td">客户编号:</td>
			<td><span id="number_span"><%=customer.getId()%></span></td>
		</tr>
		<tr>
			<td class="name_td">客户账号/邮箱:</td>
			<td><input type="text" name="email" id="email" class="long_input_text" value="<%=customer.getEmail()%>" /></td>
		</tr>
		<tr>
			<td class="name_td">注册时间</td>
			<td><%=bu.convertDate(customer.getRegisterTime())%></td>
		</tr>
		<tr>
			<td class="name_td">收货人姓名:</td>
			<td>
				<input type="text" maxlength="20" name="name" id="name" value="<%=customer.getName()%>" />
			</td>
		</tr>
		<tr>
			<td class="name_td">固定电话:</td>
			<td>
				<input type="text" maxlength="20" name="telphone" id="telphone" value="<%=customer.getTelphone()%>" />
			</td>
		</tr>
		<tr>
			<td class="name_td">移动电话:</td>
			<td>
				<input type="text" maxlength="20" name="movePhone" id="movePhone" value="<%=customer.getMovePhone()%>" />
			</td>
		</tr>
		<tr>
			<td class="name_td">收货地址:</td>
			<td>
				<input type="text" name="address" class="long_input_text" id="address" value="<%=customer.getAddress()%>" />
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2" class="btn_td">
				<input class="btn61_21" type="submit" value="提交表单" />&nbsp;&nbsp;
				<input class="btn61_21" type="reset" value="重置表单" />&nbsp;&nbsp;
				<input class="btn61_21" type="button" onclick="window.history.go(-1);" value="返回" />
			</td>
		</tr>
	</table>
</form>
</body>
</html>