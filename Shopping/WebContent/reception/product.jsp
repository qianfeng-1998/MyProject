<%@page import="com.qf.service.GetGoodsTypeService"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@page import="com.qf.service.GetSingleGoodsService"%>
<%@page import="com.qf.bean.GoodsInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("typeId");
 	String goodId=request.getParameter("goodId");
	List<GoodsInfo> list=null;
	GoodsInfo goods=null;
	String price=null;
	//System.out.println(id);
	//System.out.println("页面输出："+session.getAttribute("userId"));
	String typeName=GetGoodsTypeService.getTypeName(Integer.parseInt(id));
	if(id != null){
		list=GetSingleGoodsService.getSingleGoods(Integer.parseInt(goodId)); 
		goods=list.get(0);
		if(goods.getDiscount()!=0){
			int len=4;
			try{
				if(goods.getDiscount()==10){
					price=goods.getPrice()+"";
				}else{
					price=(goods.getPrice().multiply(new BigDecimal(goods.getDiscount())).doubleValue()/10+"");
					//System.out.println(price.length());
				}
			}catch(ArithmeticException e){
				price=(goods.getPrice().doubleValue()*(goods.getDiscount()/10)+"");
			}
		}else{
			price=(goods.getPrice().doubleValue()+"");
		}
	}else{
		return;
	}
	boolean flag =goods.getDiscount() !=10;
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
<title>产品信息-千里之行，在线销售旅游产品</title>
<link rel="stylesheet" href="common/css/common.css" type="text/css"></link>
<style type="text/css">

#goodsImg{float:left;margin-left:10px;border:1px solid #CDCDCD;width:310px;height:310px;text-align:left;overflow:hidden;}
#goodsImg .discount_div{position:absolute;left:50%;top:175px;margin-left:-490px;width:980px;z-index:1000;}
#goodsImg .discount_div div{position:relative;left:12px;top:3px;width:310px;text-align:left;color:#838181;}

#detail{float:left;text-align:left;padding-left:10px;width:350px;font-size:10pt;}
.discount {font-size:30pt;font-family:Georgia,'Times new roman,serif';color:#FF6600;font-weight:bold;}
.before_price{text-decoration:line-through;color:#878787;font-size:14pt;}
.discount_price{color:#FF5500;margin-left:10px;font-size:24pt;font-family:'宋体';font-weight:'Times new roman';}
#detail p.title{font-weight:bold;font-size:14pt;}
#op_div{border:1px solid #98BFF0;height:70px;padding:5px;background-color:#E5EAF0;color:#404040;}
#op_div .num input{width:30px;height:15px;border:1px solid #404040;}
#op_div .btn{text-align:center;padding:5px;}
#op_div .btn input{cursor:pointer;width:140px;height:37px;border:0px;background:url(images/buy_button.png) no-repeat -280px 0px;margin:0px;padding:0px;}
</style>
<script type="text/javascript">
function changebg_hover(obj){
	obj.style.backgroundColor="#FFF3D9";
	obj.style.border="1px solid #E37A08";
}

function changebg_out(obj){
	obj.style.backgroundColor="#E5EAF0";
	obj.style.border="1px solid #98BFF0";
}

function submit_form(){
	var num = document.goodsForm.num.value;
	if(isNaN(num) || parseInt(num)<0){
		alert("商品数量应该是大于等于0的整数！");
		return false;
	}
	return true;
}

window.onload=function(){
	var goodsPhoto = document.getElementById("goodsPhoto");
	goodsPhoto.style.position="relative";
	goodsPhoto.style.top=(155-parseInt(goodsPhoto.height)/2)+"px";
	goodsPhoto.style.left=(155-parseInt(goodsPhoto.width)/2)+"px";
}
</script>
</head>
<body>
<iframe src="reception/top_index.jsp" name="topFrame" width="100%" height="150px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
<div id="middle_div">
	<div class="sitemap">
		您现在所在的位置：<a target="_top" href="reception/index.jsp">网站首页</a>&nbsp;&gt;&nbsp;产品详细信息
	</div>
	<div>
		<div id="goodsImg">
			<img id="goodsPhoto" src="product/<%=goods.getPhoto()%>"></img>
			<%if(flag){%>
			<div class="discount_div">
				<div><span class="discount"><%=goods.getDiscount()%></span>折</div>
			</div>
			<%}%>
		</div>
		<div id="detail">
			<p class="title"><%=goods.getGoodsName()%></p>
			<p>
			<%if(flag){%>
			原价:<span class="before_price">&yen;<%=goods.getPrice()%></span>&nbsp;&nbsp;
			<%}%>
			现价:<span class="discount_price">&yen;<%=price%></span></p>
			<p>商品类别：<a target="_top" href="GetTypeIdGoodsServlet?typeId=<%=goods.getTypeId()%>&pageNo=1">
				<%=typeName%></a></p>
			<p>描述：<%=goods.getRemark()%></p>
			<form name="goodsForm" action="ShoppingCarServlet" method="post" onsubmit="return submit_form()">
				<input type="hidden" name="op" value="add" />
				<input type="hidden" name="id" value="<%=goods.getGoodsId()%>" />
				<div id="op_div" onmouseover="changebg_hover(this)" onmouseout="changebg_out(this)">
					<div class="num">我&nbsp;要&nbsp;买：<input type="text" name="num" value="1" />&nbsp;件</div>
					<div class="btn">
						<input type="submit" value="" />&nbsp;&nbsp;
						<a href="OtherJspToCartServlet">查看购物车</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<iframe src="reception/bottom_index.jsp" name="bottomFrame" width="100%" height="180px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</body>
</html>