<%@page import="com.qf.service.GetCustomerAllInfoService"%>
<%@page import="com.qf.bean.BulletinInfo"%>
<%@page import="com.qf.bean.CustomerInfo"%>
<%@page import="com.qf.bean.OrderInfo"%>
<%@page import="com.qf.bean.PageModel"%>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%	
PageModel<OrderInfo> pm = (PageModel)session.getAttribute("orderPage");
List<OrderInfo> orderList = pm.getList();
BulletinInfo bu=new BulletinInfo();

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
<title>订单列表</title>
<link style="text/csss" rel="stylesheet" href="admin/css/common.css">
<link style="text/csss" rel="stylesheet" href="admin/css/data_manage.css">
<style type="text/css">
.no_confirm{color:#f00;}
</style>
<script type="text/javascript">
//验证表示操作的值是否为""或者为query
function checkOpValue(){
	var op = document.getElementById("op");
	if(""!=op.value && "query"!=op.value && "queryByStatus"!=op.value){
		op.value="";
	}
}
//前往某页
function toPage(pageNo){
	checkOpValue();
	document.getElementById("pageNo").value=pageNo;
	document.myForm.submit();
}

//删除订单
function removeOrder(id,status){
	if (status==1) {
		alert("该订单为已确认状态，删除失败！");
		return;
	}
	if(confirm("此订单下可能有多个商品，将会删除此订单编号下所有订单，现在确认删除此订单吗?")){
		document.getElementById("op").value="remove";
		document.getElementById("updateId").value=id;
		document.myForm.submit();
	}
}

//查看详情
function showDetail(id){
	document.getElementById("op").value="toDetail";
	document.getElementById("updateId").value=id;
	document.myForm.submit();
}

//改变状态（确认订单/取消确认）
function changeStatus(id,status){
	if (confirm("温馨提示：该订单下可能有多个商品，将会修改该订单ID的全部订单状态，继续吗？")) {
		document.getElementById("op").value="changeStatus";
		document.getElementById("orderStatus").value=status;
		document.getElementById("updateId").value=id;
		document.myForm.submit();
	}
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

//选择表格中的某个订单时
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

//根据状态查询
function queryByStatus(status){
	document.getElementById("op").value="queryByStatus";
	document.getElementById("orderStatus").value=status;
	document.myForm.submit();
}

//删除多个
function delete_all_click(){
	var chkAll = document.getElementById("chkAll");
	if(chkAll==null || !chkAll.checked){
		alert("请选择要删除的商品！");
		return;
	}
	if(confirm("确认删除这些订单吗？")){
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
	<form name="myForm" action="SeeAllOrderInfoServlet" method="post" onsubmit="return search_click();">
		<%
			String op = request.getParameter("op");
			String keywords = request.getParameter("keywords");
		%>
		<input type="hidden" id="op" name="op" value="<%=op==null?"":op%>" />
		<input type="hidden" id="updateId" name="id" />
		<input type="hidden" name="pageSize" value="<%=pm.getPageSize()%>" />
		<input type="hidden" id="pageNo" name="pageNo" value="<%=pm.getPageNo()%>" />
		<input type="hidden" id="removeIds" name="removeIds" />
		<input type="hidden" id="orderStatus" name="status" value="<%=request.getParameter("status")%>" />
		<div class="opDiv">
			<div class="titlebar">订单信息管理</div>
			<div class="buttonDiv">
				<span>
					<input type="text" id="keywords" name="keywords" value="<%=keywords==null?"":keywords%>" />
				</span>
				<input class="btn61_21" type="submit" value="查询" />
				&nbsp;&nbsp;
				<input class="btn61_21" type="button" onclick="delete_all_click();" value="删除订单" />
			</div>
		</div>
	</form>
	<table id="dataTable">
	<tr>
		<th><input type="checkbox" onclick="chkAll_click();" id="chkAll" /></th>
		<th>订单编号</th>
		<th>订单状态</th>
		<th>下单时间</th>
		<th>客户账户/邮箱</th>
		<th>收货人</th>
		<th>固定电话</th>
		<th>移动电话</th>
		<th>操作</th>
	</tr>
	<% 
	CustomerInfo ci=null;
	if(orderList == null || orderList.isEmpty()){
		out.println("<tr><td colspan='9' align='center'>没有找到任何订单信息！</td></tr>");
	}else{
		for(int i=0;i<orderList.size();i++){
			OrderInfo order = orderList.get(i);
			ArrayList<CustomerInfo> list= GetCustomerAllInfoService.getAllInfo(order.getCustomerId());
			ci=list.get(0);
			/* CustomerDetailInfo detail = customer.getDetailInfo(); */
	%>
	<tr>
		<td align="center">
			<input type="checkbox" name="chkItems" onclick="chkItems_click(this);" value="<%=order.getOrderId()%>"/>
		</td>
		<td> <%=order.getOrderId()%></td>
		<td>
			<%
			 String strStatus="已确认";
			String title = "查询所有已确认的订单";
			
			if(order.getStatus()==0){
				strStatus="<span class='no_confirm'>未确认</span>";
				title = "查询所有未确认的订单";
			} 
			%>
			<a href="javascript:queryByStatus(<%=order.getStatus()%>)" title="<%=title%>">
				<%=strStatus%></a>
		</td>
		<td><%=bu.convertDate(order.getOrderTime())%></td>
		<td>
			<a href="SeeAllCustomerServlet?op=toEdit&id=<%=ci.getId()%>" title="查看客户信息"><%=ci.getEmail()%></a>
		</td>
		<td><%=ci.getName()%></td>
		<td><%=ci.getTelphone()%></td>
		<td><%=ci.getMovePhone()%></td>
		<td align="center">
			<% 
			if(order.getStatus()==0){
				out.println("<a class='no_confirm' href=\"javascript:changeStatus("+order.getOrderId()+","+order.getStatus()+")\">确认订单</a>");
			}else{
				out.println("<a href=\"javascript:changeStatus("+order.getOrderId()+","+order.getStatus()+")\">取消确认</a>");
			} 
			%>&nbsp;
			<a href="javascript:showDetail(<%=order.getOrderId()%>);" title="订单详情">详情</a>&nbsp;
			<a href="javascript:removeOrder(<%=order.getOrderId()%>,<%=order.getStatus()%>);" title="删除此订单">删除</a>
		</td>
	</tr>
	<%}}%>
	<tr>
		<td class="pagerTd" colspan="9">
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