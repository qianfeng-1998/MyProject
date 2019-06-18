<%@page import="com.qf.service.GetGoodsTypeService"%>
<%@page import="com.qf.bean.GoodsInfo"%>
<%@page import="com.qf.bean.PageModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<%	
PageModel<GoodsInfo> pm = (PageModel<GoodsInfo>)session.getAttribute("page");
List<GoodsInfo> goodsList = null;
if(pm== null){
	//System.out.println("为空");
	pm = new PageModel<GoodsInfo>();
}
goodsList = pm.getList(); 
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
<title>商品信息</title>
<link style="text/csss" rel="stylesheet" href="admin/css/common.css">
<link style="text/csss" rel="stylesheet" href="admin/css/data_manage.css">
<script type="text/javascript">
//验证表示操作的值是否为""或者为query
function checkOpValue(){
	var op = document.getElementById("op");
	if(""!=op.value && "query"!=op.value){
		op.value="";
	}
}
//前往某页
function toPage(pageNo){
	checkOpValue();
	document.getElementById("pageNo").value=pageNo;
	document.myForm.submit();
}

//删除商品
function removeGoods(id){
	if(confirm("确认删除此商品信息吗？")){
		document.getElementById("op").value="remove";
		document.getElementById("updateId").value=id;
		document.myForm.submit();
	}
}

//修改商品
function updateGoods(id){
	document.getElementById("op").value="toEdit";
	document.getElementById("updateId").value=id;
	document.myForm.submit();
}

//新增商品
function addGoods(){
	document.getElementById("op").value="toAdd";
	document.myForm.submit();
}

//全选/全反选
function chkAll_click(){
	var chkAll = document.getElementById("chkAll");
	var dataTable = document.getElementById("dataTable");
	if(chkAll != null){
		var items = dataTable.getElementsByTagName("input");
		if(items != null){
			for(var i=0;i<items.length;i++){
				if("chkItems" == items[i].name){
					items[i].checked = chkAll.checked;
				}
			}
		}
	}
}

//选择表格中的某个商品
function chkItems_click(obj){
	var chkAll = document.getElementById("chkAll");
	var dataTable = document.getElementById("dataTable");
	if(obj!=null && chkAll!=null){
		if(obj.checked){
			chkAll.checked = true;
			return;
		}
		var items = dataTable.getElementsByTagName("input");
		if(items != null){
			for(var i=0;i<items.length;i++){
				if("chkItems"==items[i].name && items[i].checked){
					chkAll.check=true;
					return;
				}
			}
			chkAll.checked = false;
		}
	}
}

//查询
function search_click(){
	document.getElementById("op").value="query";
	return true;
}

//删除多个
function delete_all_click(){
	var chkAll = document.getElementById("chkAll");
	if(chkAll==null || !chkAll.checked){
		alert("请选择要删除的商品！");
		return;
	}
	if(confirm("确认删除这些商品信息吗？")){
		document.getElementById("op").value="removeMore";
		var str_params = "";
		var dataTable = document.getElementById("dataTable");
		var items = dataTable.getElementsByTagName("input");
		if(items != null){
			for(var i=0;i<items.length;i++){
				if("chkItems"==items[i].name && items[i].checked){
					str_params += items[i].value+",";
				}
			}
		}
		document.getElementById("removeIds").value=str_params;
		document.myForm.submit();
	}
}
</script>
</head>
<body>
	<form name="myForm" action="AllGoodsPageServlet" method="post" onsubmit="return search_click();">
		<%
			String op = request.getParameter("op");
			String keywords = request.getParameter("keywords");
		%>
		<input type="hidden" id="op" name="op" value="<%=op==null?"":op%>" />
		<input type="hidden" id="updateId" name="id" />
		<input type="hidden" name="pageSize" value="<%=pm.getPageSize()%>" />
		<input type="hidden" id="pageNo" name="pageNo" value="<%=pm.getPageNo()%>" />
		<input type="hidden" id="removeIds" name="removeIds" />
		<div class="opDiv">
			<div class="titlebar">商品信息管理</div>
			<div class="buttonDiv">
				<span>
					<input type="text" id="keywords" name="keywords" value="<%=keywords==null?"":keywords%>" />
				</span>
				<input class="btn61_21" type="submit" value="查询" />
				
				<input class="btn61_21" type="button" onclick="addGoods()" value="添加商品" />
				<input class="btn61_21" type="button" onclick="delete_all_click()" value="删除商品" />
			</div>
		</div>
	</form>
	<table id="dataTable">
	<tr>
		<th><input type="checkbox" onclick="chkAll_click();" id="chkAll" /></th>
		<th>商品编号</th>
		<th>商品类别</th>
		<th>商品名称</th>
		<th>商品价格</th>
		<th>商品折扣</th>
		<th>商品图片</th>
		<th>是否新品</th>
		<th>是否推荐</th>
		<th>商品状态</th>
		<th>操作</th>
	</tr>
	<% 
	if(goodsList == null || goodsList.isEmpty()){
		out.println("<tr><td colspan='11' align='center'>没有任何商品信息！</td></tr>");
	}else{
		for(int i=0;i<goodsList.size();i++){
			GoodsInfo goods = goodsList.get(i); 
	%>
	<tr>
		<td align="center">
			<input type="checkbox" name="chkItems" onclick="chkItems_click(this);" value="<%=goods.getGoodsId()%>"/>
		</td>
		<td><%=goods.getGoodsId()%></td>
		
		<td><%=GetGoodsTypeService.getTypeName(goods.getTypeId())%></td>
		<td><%=goods.getGoodsName()%></td>
		<td>&yen;&nbsp;<%=goods.getPrice()%></td>
		<td><%=goods.getDiscount()==10?"-":goods.getDiscount()%></td>
		<td><%=goods.getPhoto()%></td>
		<td><%=goods.getIsNew()==0?"新品":"非新品"%></td>
		<td><%=goods.getIsRecommend()==0?"推荐":"不推荐"%></td>
		<td><%=goods.getStatus()==0?"上架":"下架"%></td>
		<td align="center">
			<a href="javascript:updateGoods(<%=goods.getGoodsId()%>);">修改</a>
			<a href="javascript:removeGoods(<%=goods.getGoodsId()%>);">删除</a>
		</td>
	</tr>
	<% }} %>
	<tr>
		<td class="pagerTd" colspan="11">
			共找到<%=pm.getStuCount()%>件商品&nbsp;&nbsp;
			第<%=pm.getPageNo()%>/ <%=pm.getTotalPages()%>页&nbsp;&nbsp;
			<a href="javascript:toPage( <%=pm.getFirstPage()%>);">首页</a>
			<a href="javascript:toPage(<%=pm.getPreviousPage()%>);">上一页</a>
			<a href="javascript:toPage(<%=pm.getNextPage()%>);">下一页</a>
			<a href="javascript:toPage(<%=pm.getLastPage()%>);">尾页</a>				
		</td>
	</tr>
	</table>
</body>
</html>