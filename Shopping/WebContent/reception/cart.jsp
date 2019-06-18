<%@page import="com.qf.bean.ShoppingCarInfo"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.qf.service.GetGoodsTypeService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.qf.bean.OrderGoodsInfo"%>
<%@page import="com.qf.bean.GoodsInfo"%>
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
//System.out.println(basePath+":"+path);
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车-千里之行，在线销售旅游产品</title>
<link rel="stylesheet" href="common/css/common.css" type="text/css"></link>
<style type="text/css">
	table{width:100%;border:1px solid #AACDED;border-collapse: collapse;font-size:12px;}
	.title_table{border-bottom-width:0px;}
	th{background-color:#EBF4FB;font-size:13px;color:#444;font-weight:normal;}
	td,th{border:1px solid #AACDED;padding:3px;}
	a,img{border:none;vertical-align:middle;}
	.btn_div{clear:both;padding:3px;text-align: center;font-size:14px;color:#444;padding-top:30px;}
	.quantity{width:30px;border:1px solid #444;}
	.goodsImg{width:50px;height:50px;float:left;margin:3px;}
	.goodsName{float:left;height:50px;line-height:50px;}
	.sumPrice_div{font-size:12px;margin:5px 0px 30px 0px;}
	.sumPrice_div span{font-size:24pt;font-family:Georgia,'Times new roman,serif';color:#FF6600;font-weight:bold;}
	.sumPrice_div .sum{float:left;}
	.sumPrice_div .clear{float:right;font-size:11pt;padding-right:5px;padding-top:5px;}
</style>
<script type="text/javascript">
//更改商品数量
//参数id为商品编号
function changeQuantity(id){
	var quantity_input = document.getElementById("quantity_"+id);
	if(quantity_input != null){
		var quantity = quantity_input.value;
		if(isNaN(quantity) || parseInt(quantity)<0){
			alert("商品数量应该是大于等于0的整数！");
			return;
		}
		document.changeForm.id.value=id;
		document.changeForm.quantity.value=quantity;
		document.changeForm.submit();
	}
}
</script>
</head>
<body>
<iframe src="reception/top_index.jsp" name="topFrame" width="100%" height="150px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
<div id="middle_div">
	<div class="sitemap">
		您现在所在的位置：<a target="_top" href="reception/index.jsp">网站首页</a>&nbsp;&gt;&nbsp;购物车
	</div>
	<form name="changeForm" action="ClearShoppingCarServlet" method="post">
		<input type="hidden" name="op" value="change" />
		<input type="hidden" name="id" value="" />
		<input type="hidden" name="quantity" value="" />
	</form>
	<img src="images/buycart_logo.gif" style="margin:8px 3px 3px 0px;" /><br />
	<%-- <%
	Cart cart = (Cart)session.getAttribute("cart");
	if(cart == null){
		cart = new Cart();
	}
	%> --%>
	<table>
		<tr>
			<th>商品名称</th>
			<th>商品类型</th>
			<th>价格</th>
			<th>折扣</th>
			<th>数量</th>
			<th>小计</th>
			<th>操作</th>
		</tr>
		<%-- <%
			HashMap<Integer,OrderGoodsInfo> items = cart.getCart();
			if(items.isEmpty()){
				out.println("<tr><td colspan='7' align='center'>购物车中没有任何商品！</td></tr>");
			}else{
				Iterator<Integer> it = items.keySet().iterator();
				OrderGoodsInfo orderGoods = null;
				GoodsInfo goods = null;
				while(it.hasNext()){
					orderGoods = items.get(it.next());
					goods = orderGoods.getGoodsInfo();
		%> --%>
		<%-- <tr>
			<td>
				<img class="goodsImg" src="product/<%=goods.getPhoto()%>">
				<div class="goodsName">
					<a href="product.jsp?id=<%=goods.getGoodsId()%>"><%=goods.getGoodsName()%></a>
				</div>
			</td>
			<td>
				<a target="_top" href="products.jsp?id=<%=goods.getGoodsType().getTypeId()%>">
					<%=goods.getGoodsType().getTypeName()%>
				</a>
			</td>
			<td>
				<%=goods.getPrice()%>元
			</td>
			<td>
				<%=goods.getDiscount()!=10?goods.getDiscount():"-"%>
			</td>
			<td align="center">
				<input class="quantity" id="quantity_<%=goods.getGoodsId()%>" type="text" value="<%=orderGoods.getQuantity()%>" />
			</td>
			<td>
				<%=orderGoods.getSumPrice()%>元
			</td>
			<td align="center">
				<a href="javascript:changeQuantity('<%=goods.getGoodsId()%>')">更改数量</a>
				<a href="servlet/CartManage?op=remove&id=<%=goods.getGoodsId()%>">删除商品</a>
			</td>
		</tr> --%>
		<%-- <%}}%> --%>
		<%
			ArrayList<GoodsInfo> list=(ArrayList) session.getAttribute("shoppingCarList");
			ArrayList<ShoppingCarInfo> carInfoList=(ArrayList) session.getAttribute("carInfoList");
			double price=0;
			if(list.isEmpty()){
				out.println("<tr><td colspan='7' align='center'>购物车中没有任何商品！</td></tr>");
				price=0;
			}else{
				GoodsInfo goods=null;
				ShoppingCarInfo car=null;
				for(int i=0;i<list.size();i++){
					goods=list.get(i);
					car=carInfoList.get(i);
					price+=(goods.getPrice().multiply(new BigDecimal((goods.getDiscount()/10.0))).doubleValue())*car.getQuantity();
		%>
		<tr>
			<td>
				<img class="goodsImg" src="product/<%=goods.getPhoto()%>">
				<div class="goodsName">
					<a href="reception/product.jsp?typeId=<%=goods.getTypeId()%>&goodId=<%=goods.getGoodsId()%>"><%=goods.getGoodsName()%></a>
				</div>
			</td>
			<td>
				<a target="_top" href="GetTypeIdGoodsServlet?typeId=<%=goods.getTypeId()%>&pageNo=1<%-- <%=goods.getGoodsType().getTypeId()%> --%>">
					<%=GetGoodsTypeService.getTypeName(goods.getTypeId())%>
					<%-- <%=goods.getTypeName()%> --%>
				</a>
			</td>
			<td>
				<%=goods.getPrice()%>元
			</td>
			<td>
				<%=goods.getDiscount()!=10?goods.getDiscount():"-"%>
			</td>
			<td align="center">
				<input class="quantity" id="quantity_<%=goods.getGoodsId()%>" type="text" value="<%=car.getQuantity()%>" />
			</td>
			<td>
			<%=
				//打折后的价格,此价格下面需要求出总和
				(goods.getPrice().multiply(new BigDecimal((goods.getDiscount()/10.0))).doubleValue())*car.getQuantity()
			%>
				<%-- <%=orderGoods.getSumPrice()%> --%>元
			</td>
			<td align="center">
				<a href="javascript:changeQuantity('<%=goods.getGoodsId()%>')">更改数量</a>
				<a href="ClearShoppingCarServlet?op=deleteSingle&goodsId=<%=goods.getGoodsId()%>">删除商品</a>
			</td>
		</tr>
		<%
			}
		} 
		%>
	</table>
<div class="sumPrice_div">
	<div class="sum">商品金额总计：<span>&yen;<%=price %></span>&nbsp;&nbsp;</div>
	<div class="clear"><a href="ClearShoppingCarServlet">清空购物车</a></div>
</div>
<div class="btn_div">
	您可以&nbsp;<a target="_top" href="ValidationDetailInfoServlet"><img src="images/jrjs.gif" /></a>
	，也可以&nbsp;<a target="_top" href="reception/index.jsp"><img src="images/jxgm.gif" /></a>
</div>
</div>
<iframe src="reception/bottom_index.jsp" name="bottomFrame" width="100%" height="180px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</body>
</html>