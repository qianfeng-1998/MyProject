<%@page import="com.qf.service.GetGoodsTypeService"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.qf.bean.GoodsInfo"%>
<%@page import="com.qf.bean.ShoppingCarInfo"%>
<%@page import="com.qf.bean.BulletinInfo"%>
<%@page import="com.qf.bean.CustomerInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
out.println("<base href=\""+basePath+"\">");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单确认-千里之行，在线销售旅游产品</title>
<link rel="stylesheet" href="common/css/common.css" type="text/css"></link>
<style type="text/css">
div.title{text-align:center;font-size:14pt;font-family:'黑体';height:28px;line-height:28px;margin:5px;}
div.title span{font-family:'华文行楷','楷体','黑体';font-size:18pt;margin-right:3px;}

div.item_title{text-align:left;margin-top:3px;border:1px solid #ccc;color:#000;font-size:10pt;background:url(images/item.gif) no-repeat 3px 7px #E6F0F9;height:28px;line-height:28px;text-indent:15px;}
div.item_content{text-align:center;padding:10px 0px 10px 0px;border:1px solid #ccc;border-top-width:0px;}

table {border:1px solid #ccc;border-collapse:collapse;width:960px;text-align:left;color:#484848;font-size:11pt;}
td{height:30px;line-height:30px;border:1px solid #ccc;text-indent:1em;}
th{height:30px;line-height:30px;border:1px solid #ccc;}
td.title{width:120px;text-align:left;background-color:#F6F6F6;}

th{font-size:11pt;height:24px;line-height:24px;background-color:#F6F6F6;text-align:center;}
.goodsImg{width:50px;height:50px;float:left;margin:3px;}
.goodsName{float:left;height:50px;line-height:50px;}
td.sum span{font-size:18pt;font-family:Georgia,'Times new roman,serif';color:#FF6600;font-weight:bold;}

div.op{height:40px;line-height:40px;text-align:center;}
.btn61_21 {width:61px;height:21px;vertical-align:middle;line-height:21px;border-width: 0px;border-style:none;padding: 0px;font-size:12px;margin-right: 3px;background: url(images/btn.png) no-repeat 0 0;cursor: pointer;}
</style>
</head>
<body>
<iframe src="reception/top_index.jsp" name="topFrame" width="100%" height="150px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
<div id="middle_div">
	<div class="sitemap">
		您现在所在的位置：<a target="_top" href="index.jsp">网站首页</a>&nbsp;&gt;&nbsp;
		<a target="_top" href="cart.jsp">购物车</a>&nbsp;&gt;&nbsp;订单确认
	</div>
	<%
	/* CustomerInfo customer = (CustomerInfo)session.getAttribute("customer");
	CustomerDetailInfo detail = customer.getDetailInfo(); */
		ArrayList<CustomerInfo> list=(ArrayList) session.getAttribute("userList");
		BulletinInfo bu=new BulletinInfo();
		CustomerInfo ci=list.get(0);
	%>
	<div class="title"><span>千里之行</span>购物订单确认表</div>
	<div>
		<div class="item_title">客户信息</div>
		<div class="item_content">
			<table align="center">
				<tr>
					<td class="title">客户编号</td>
					<td> <%=ci.getId()%> </td>
					<td class="title">注册时间</td>
					<td><%=bu.convertDate(ci.getRegisterTime())%></td>
				</tr>
				<tr>
					<td class="title">客户账户/邮箱</td>
					<td><%=ci.getEmail()%></td>
					<td class="title">收货人姓名</td>
					<td><%=ci.getName()%></td>
				</tr>
				<tr>
					<td class="title">固定电话</td>
					<td><%=ci.getTelphone()%></td>
					<td class="title">移动电话</td>
					<td><%=ci.getMovePhone()%> </td>
				</tr>
				<tr>
					<td class="title">收货地址</td>
					<td colspan="3"><%=ci.getAddress()%> </td>
				</tr>
			</table>
		</div>
		<div class="item_title">订单信息</div>
		<div class="item_content">
			<%
			/* Cart cart = (Cart)session.getAttribute("cart"); */
			%>
			<table align="center">
				<tr>
					<th>编号</th>
					<th>商品名称</th>
					<th>商品类型</th>
					<th>价格</th>
					<th>折扣</th>
					<th>数量</th>
					<th>小计</th>
				</tr>
				<%
					/* HashMap<Integer,OrderGoodsInfo> items = cart.getCart();
					Iterator<Integer> it = items.keySet().iterator();
					OrderGoodsInfo orderGoods = null;
					GoodsInfo goods = null;
					while(it.hasNext()){
						orderGoods = items.get(it.next());
						goods = orderGoods.getGoodsInfo(); */
					ArrayList<GoodsInfo> list1=(ArrayList) session.getAttribute("shoppingCarList");
					ArrayList<ShoppingCarInfo> carInfoList=(ArrayList) session.getAttribute("carInfoList");
					double price=0;
					GoodsInfo goods=null;
					ShoppingCarInfo car=null;
					for(int i=0;i<list1.size();i++){
						goods=list1.get(i);
						car=carInfoList.get(i);
						price+=(goods.getPrice().multiply(new BigDecimal((goods.getDiscount()/10.0))).doubleValue())*car.getQuantity();
				%>
				<tr>
					<td><%=goods.getGoodsId()%></td>
					<td>
						<img class="goodsImg" src="product/<%=goods.getPhoto()%>">
						<div class="goodsName"><%=goods.getGoodsName()%></div>
					</td>
					<td>
						<%=GetGoodsTypeService.getTypeName(goods.getTypeId())%>
					</td>
					<td>
						<%=goods.getPrice()%>元
					</td>
					<td>
						 <%=goods.getDiscount()!=10?goods.getDiscount():"-"%>
					</td>
					<td align="center">
						<%=car.getQuantity()%>
					</td>
					<td>
						 <%=(goods.getPrice().multiply(new BigDecimal((goods.getDiscount()/10.0))).doubleValue())*car.getQuantity()%>元
					</td>
				</tr>
				<% } %>
				<tr>
					<td colspan="7" class="sum">
						此订单中共有<span><%=list1.size()%></span>件商品，总计金额<span><%=price%></span>元！
					</td>
				</tr>
			</table>
		</div>
		
	</div>
	<div class="op">
		<form action="AddOrderInfoServlet" method="post">
			<input type="hidden" name="op" value="add" />
			<input type="submit" class="btn61_21" value="确认订单" />&nbsp;&nbsp;
			<input type="button" onclick="window.top.location.href='OtherJspToCartServlet'" class="btn61_21" value="返&nbsp;回" />
		</form>
	</div>
</div>
<iframe src="reception/bottom_index.jsp" name="bottomFrame" width="100%" height="180px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</body>
</html>