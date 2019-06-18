<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@page import="com.qf.bean.PageModel"%>
<%@page import="com.qf.bean.GoodsInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%
/* 
	String typeId = request.getParameter("id");
	if(typeId==null){
		response.sendRedirect("index.jsp");
		return;
	}
	GoodsInfoDao dao = new GoodsInfoDao();
	ArrayList<SalesGoods> salesGoodsList = dao.getSalesGoods(Integer.parseInt(typeId));
 
	int pageNo = 1; 
	try{
		pageNo = Integer.parseInt(request.getParameter("pageNo"));
	}catch(Exception e){}

	PageModel<GoodsInfo> pm = dao.getAllGoodsByTypeId(12,pageNo,Integer.parseInt(typeId));
	ArrayList<GoodsInfo> goodsList = pm.getData();
	GoodsInfo goods = goodsList.get(0);
*/
	
	int sellNum=0;
	PageModel<GoodsInfo> pm=(PageModel)session.getAttribute("goodsPage");
	List<GoodsInfo> list=pm.getList();
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
		<title>千里之行-在线销售旅游用品 登山攀岩器材、户外服装、户外桌椅、睡袋垫子、野营用品、野营帐篷、运动手表...</title>
<link rel="stylesheet" href="common/css/common.css" type="text/css"></link>
<style type="text/css">

/*中部-左边产品导航*/
#middle_left_div{float:left;width:250px;margin-top:20px;}

#paihang{border:1px solid #CCE3F1;padding:3px;margin-left:3px;}
#paihang .product_title{text-align:left;color:#2F85EF;margin:0px;padding:3px;font-weight:bold;background:url(images/best.gif) no-repeat left center #fff;text-indent:27px;}
#paihang table{clear:both;border-collapse:collapse;margin-top:3px;width:100%;font-size:11pt;border-spacing:0px;}
#paihang table td{border-top:1px dashed #ccc;height:35px;}
#paihang table a{display:block;color:#2C6DB2;text-decoration:none;text-indent:30px;height:17px;font-size:10pt;}
#paihang table a:hover{color:#E8860F;}
.goods_0 a{background:url(images/paihang.png) no-repeat 0px -5px;}
.goods_1 a{background:url(images/paihang.png) no-repeat 0px -31px;}
.goods_2 a{background:url(images/paihang.png) no-repeat 0px -57px;}
.goods_3 a{background:url(images/paihang.png) no-repeat 0px -83px;}
.goods_4 a{background:url(images/paihang.png) no-repeat 0px -109px;}
.goods_5 a{background:url(images/paihang.png) no-repeat 0px -135px;}
.goods_6 a{background:url(images/paihang.png) no-repeat 0px -161px;}
.goods_7 a{background:url(images/paihang.png) no-repeat 0px -187px;}
.goods_8 a{background:url(images/paihang.png) no-repeat 0px -213px;}
.goods_9 a{background:url(images/paihang.png) no-repeat 0px -239px;}

td.num{font-size:10pt;color:#999;text-align:right;}
td.num span{color:#E8860F;font-weight:bold;font-size:12pt;}

/*中部-右边*/
#middle_right_div{float:right;width:690px;border:0px solid blue;}

.product_table{width:100%;margin:3px;}
.product_table td{border-bottom:1px dashed #ccc;height:80px;padding:10px;}
.product_table a.img{float:left;text-decoration:none;}
.product_table img {width:100px;height:100px;border:0px;}
.product_table ul{float:left;list-style:none;width:120px;margin:3px;padding:3px;font-size:10pt;}
.product_table li .discount {font-size:12pt;font-family:Georgia,Times new roman,serif;color:#FF6600;font-weight:bold;}
.product_table li{padding-top:5px;}
.product_table li a{color:#1A66B3;text-decoration:none;}
.product_table li a:hover{text-decoration:underline;}
.product_table .before_price{text-decoration:line-through;color:#878787;font-size:11pt;}
.product_table .discount_price{color:#CC3300;margin-left:10px;font-size:11pt;}

.pager{text-align:center;height:25px;line-height:25px;border-bottom:0px;font-size:10pt;}
</style>
	</head>
	<body>
		<iframe src="reception/top_index.jsp" name="topFrame" width="100%" height="150px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
		<div id="middle_div">
			<div class="sitemap">
				您现在所在的位置：<a target="_top" href="reception/index.jsp">网站首页</a>&nbsp;&gt;&nbsp;分类商品
			</div>
			<div id="middle_left_div">
				<div id="paihang">
					<div class="product_title">销售排行</div>
					<table>
						<%
						/* if(salesGoodsList.isEmpty()){
							out.println("<tr><td align='center'>此分类商品共售出了&nbsp;0&nbsp;件！</td></tr>");
						}else{
							SalesGoods salesGoods = null;
							for(int i=0;i<salesGoodsList.size();i++){ 
								salesGoods = salesGoodsList.get(i); */
						if(list.isEmpty()){
							out.println("<tr><td align='center'>此分类商品共售出了&nbsp;0&nbsp;件！</td></tr>");
						}else{
							GoodsInfo goods = null;
							for(int i=0;i<list.size();i++){ 
								goods = list.get(i);	
						%>
						<tr>
							<td class="goods_<%=i%>">
								<a target="_top" href="reception/product.jsp?typeId=<%=goods.getTypeId()%>&goodId=<%=goods.getGoodsId()%>">
									<%=goods.getGoodsName() %></a>
							</td>
							<%-- 此处为商品已售出的件数  应查询订单表--%>
							<td class="num"><span>0</span>件</td>
						</tr>
						<%}}%>
					</table>
				</div>
			</div>
			<div id="middle_right_div">
				<div style="margin-bottom:10px;">
					<table class="product_table">
						<tr>
					<%
					boolean flag = false;
					GoodsInfo goods=null;
					String price=null;
					for(int i=0;i<list.size();i++){
						goods = list.get(i);
						flag = goods.getDiscount()!=10;
						try{
							if(goods.getDiscount()==10){
								price=goods.getPrice()+"";
							}else{
								price=(goods.getPrice().multiply(new BigDecimal(goods.getDiscount())).doubleValue()/10+"");
								//System.out.println(price.length());
							}
						}catch(ArithmeticException e){
							price=(goods.getPrice().doubleValue()*(goods.getDiscount()/10)+"").substring(0,5);
						}
					%>
					<td>
						<a class="img" href="reception/product.jsp?typeId=<%=goods.getTypeId()%>&goodId=<%=goods.getGoodsId() %>">
							<img src="product/<%=goods.getPhoto()%>" /></a>
						<ul>
						<%if(flag){%>
							<li><span class="discount"><%=goods.getDiscount()%></span>折</li>
							<%}%>
							<li><a href="reception/product.jsp?typeId=<%=goods.getTypeId()%>&goodId=<%=goods.getGoodsId() %>">
								<%=goods.getGoodsName()%></a></li>
							<li>
							<%if(flag){%>
								<span class="before_price">&yen;<%=goods.getPrice()%></span>
							<%}%>
								<span class="discount_price">&yen;<%=price%></span>
							</li>
						</ul>
					</td>
					<%
						if((i+1)%3==0){
							out.println("</tr><tr>");
						}
					} 
					%>
					</tr>
					</table>
					<div class="pager">
						共有<%=pm.getStuCount()%>件商品&nbsp;&nbsp;
						第<%=pm.getPageNo()%>/<%=pm.getTotalPages()%>页&nbsp;&nbsp;
						<%-- <a href="products.jsp?id=<%=typeId%>&pageNo=<%=pm.getIndexPageNo()%>">首页</a> --%>
						<a href="GetTypeIdGoodsServlet?typeId=<%=goods.getTypeId()%>&pageNo=<%=pm.getFirstPage()%>">首页</a>
						
						<%-- <a href="products.jsp?id=<%=typeId%>&&pageNo=<%=pm.getPreviousPageNo()%>">上一页</a> --%>
						<a href="GetTypeIdGoodsServlet?typeId=<%=goods.getTypeId()%>&pageNo=<%=pm.getPreviousPage()%>">上一页</a>
						
						<%-- <a href="products.jsp?id=<%=typeId%>&&pageNo=<%=pm.getNextPageNo()%>">下一页</a> --%>
						<a href="GetTypeIdGoodsServlet?typeId=<%=goods.getTypeId()%>&pageNo=<%=pm.getNextPage()%>">下一页</a>
						
						<%-- <a href="products.jsp?id=<%=typeId%>&&pageNo=<%=pm.getLastPageNo()%>">尾页</a> --%>
						<a href="GetTypeIdGoodsServlet?typeId=<%=goods.getTypeId()%>&pageNo=<%=pm.getLastPage()%>">尾页</a>
							
					</div>
				</div>
			</div>
		</div>
		<iframe src="reception/bottom_index.jsp" name="bottomFrame" width="100%" height="180px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
	</body>
</html>