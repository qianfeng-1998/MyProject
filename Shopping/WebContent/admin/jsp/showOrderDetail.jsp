<%@page import="com.qf.service.GetGoodsTypeService"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.qf.bean.GoodsInfo"%>
<%@page import="com.qf.bean.BulletinInfo"%>
<%@page import="com.qf.bean.CustomerInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.qf.bean.OrderInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<%
List<OrderInfo> orderList = (List)session.getAttribute("orderList");
ArrayList<CustomerInfo> userInfo=(ArrayList)session.getAttribute("userInfo");
OrderInfo order=orderList.get(0);
CustomerInfo user=userInfo.get(0);
BulletinInfo bi=new BulletinInfo();
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
<title>显示订单详细信息</title>
<link style="text/csss" rel="stylesheet" href="admin/css/add_skin.css">
<link style="text/csss" rel="stylesheet" href="admin/css/data_manage.css">
<link style="text/csss" rel="stylesheet" href="admin/css/common.css">
<style type="text/css">
.name_td{width:120px;}
.addTable td{height:10px;}
#dataTable_div{height:200px;overflow-y:scroll;border-bottom:1px dashed #CBCCCE;}
</style>
</head>
<body>
	<div class="opDiv">
		<div class="titlebar">订单详细信息</div>
	</div>
	<table class="addTable">
		<tr>
			<td class="name_td">订单编号:</td>
			<td><%=order.getOrderId()%></td>
			<td class="name_td">订单状态:</td>
			<td><%=order.getStatus()==0?"未确认":"已确认"%></td>
			<td class="name_td">下单时间:</td>
			<td colspan="3"><%=bi.convertDate(order.getOrderTime())%></td>
		</tr>
		<tr>
			<td class="name_td">客户编号:</td>
			<td><%=user.getId()%></td>
			<td class="name_td">客户账户/邮箱:</td>
			<td>
				<a href="servlet/CustomerManage?op=toEdit&id=<%=user.getId()%>">
					<%=user.getEmail()%></a>
			</td>
			<td class="name_td">注册时间:</td>
			<td><%=bi.convertDate(user.getRegisterTime())%></td>
		</tr>
		<tr>
			<td class="name_td">收货人姓名:</td>
			<td><%=user.getName()%></td>
			<td class="name_td">固定电话:</td>
			<td> <%=user.getTelphone()%></td>
			<td class="name_td">移动电话:</td>
			<td><%=user.getMovePhone()%></td>
		</tr>
		<tr>
			<td class="name_td">收货地址:</td>
			<td colspan="5"><%=user.getAddress()%></td>
		</tr>
	</table>
	<div id="dataTable_div">
		<table id="dataTable">
			<tr>
				<th>商品编号</th>
				<th>商品类别</th>
				<th>商品名称</th>
				<th>商品价格</th>
				<th>商品折扣</th>
				<th>订购数量</th>
				<th>小计</th>
			</tr>
			<%
			List<GoodsInfo> goodsList=(List) session.getAttribute("goodsList");
			GoodsInfo goods = null;
			double sumPrice = 0.0;
			double price = 0.0;
			for(int i=0;i<goodsList.size();i++){
				goods=goodsList.get(i);
				price+=(goods.getPrice().multiply(new BigDecimal((goods.getDiscount()/10.0))).doubleValue())*order.getQuantity();
				sumPrice += price;
			%>
			<tr>
				<td><%=goods.getGoodsId()%></td>
				<td>
					<a href="servlet/GoodsTypeManage?op=toEdit&id=<%=goods.getTypeId()%>">
						<%=GetGoodsTypeService.getTypeName(goods.getTypeId())%></a>
				</td>
				<td>
					<a href="servlet/GoodsManage?op=toEdit&id=<%=goods.getGoodsId()%>"> <%=goods.getGoodsName()%></a>
				</td>
				<td>&yen;<%=goods.getPrice()%></td>
				<td><%=goods.getDiscount()==10?"-":goods.getDiscount()%></td>
				<td><%=order.getQuantity()%></td>
				<td><%=price%></td>
			</tr>
			<% }%>
			<tr>
				<td>总金额：</td>
				<td colspan="6">&yen;&nbsp;<%=sumPrice%></td>
			</tr>
		</table>
	</div>
	<div style="margin-top:20px;padding-right:20px;text-align: right;">
		<input class="btn61_21" type="button" onclick="window.history.back();" value="返回" />
	</div>
</body>
</html>