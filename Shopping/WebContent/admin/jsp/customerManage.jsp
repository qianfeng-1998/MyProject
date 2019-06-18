<%@page import="com.qf.bean.BulletinInfo"%>
<%@page import="com.qf.bean.CustomerInfo"%>
<%@page import="com.qf.bean.PageModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%	
PageModel<CustomerInfo> pm = (PageModel)session.getAttribute("cusPage");
if(pm== null){
	pm = new PageModel<CustomerInfo>();
}
List<CustomerInfo> customerList = null;
customerList = pm.getList();
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
<title>客户信息</title>
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

//删除客户信息
function removeCustomer(id){
	if(confirm("如果订单中已有该用户购买的商品，则会删除失败，现在确认删除此客户信息吗？")){
		document.getElementById("op").value="remove";
		document.getElementById("updateId").value=id;
		document.myForm.submit();
	}
}

//修改客户信息
function updateCustomer(id){
	document.getElementById("op").value="toEdit";
	document.getElementById("updateId").value=id;
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

//选择表格中的某个客户信息
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
		alert("请选择要删除的客户信息！");
		return;
	}
	if(confirm("如果订单中已有该用户购买的商品，则会删除失败，现在确认删除选中的客户信息吗？")){
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
	<form name="myForm" action="SeeAllCustomerServlet" method="post" onsubmit="return search_click();">
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
			<div class="titlebar">客户信息管理</div>
			<div class="buttonDiv">
				<span>
					<input type="text" id="keywords" name="keywords" value="<%=keywords==null?"":keywords%>" />
				</span>
				<input class="btn61_21" type="submit" value="查询" />&nbsp;&nbsp;
				<input class="btn61_21" type="button" onclick="delete_all_click();" value="删除客户" />
			</div>
		</div>
	</form>
	<table id="dataTable">
	<tr>
		<th><input type="checkbox" onclick="chkAll_click();" id="chkAll" /></th>
		<th>客户编号</th>
		<th>客户账号/邮箱</th>
		<th>注册时间</th>
		<th>收货人姓名</th>
		<th>固定电话</th>
		<th>移动电话</th>
		<th>收货地址</th>
		<th>操作</th>
	</tr>
	<% 
	BulletinInfo bu=new BulletinInfo();
	if(customerList == null || customerList.isEmpty()){
		out.println("<tr><td colspan='9' align='center'>没有找到任何客户信息！</td></tr>");
	}else{
		for(int i=0;i<customerList.size();i++){
			CustomerInfo customer = customerList.get(i);
	%>
	<tr>
		<td align="center">
			<input type="checkbox" name="chkItems" onclick="chkItems_click(this);" value="<%=customer.getId()%>"/>
		</td>
		<td><%=customer.getId()%></td>
		<td><%=customer.getEmail()%></td>
		<td><%=bu.convertDate(customer.getRegisterTime())%></td>
		<td><%=customer.getName()==null?"未填写":customer.getName()%></td>
		<td><%=customer.getTelphone()==null?"未填写":customer.getTelphone()%></td>
		<td><%=customer.getMovePhone()==null?"未填写":customer.getMovePhone()%></td>
		<td><%=customer.getAddress()==null?"未填写":customer.getAddress()%></td>
		<td align="center">
			<a href="javascript:updateCustomer(<%=customer.getId()%>);">修改</a>
			<a href="javascript:removeCustomer(<%=customer.getId()%>);">删除</a>
		</td>
	</tr>
	<%}}%>
	<tr>
		<td class="pagerTd" colspan="9">
			共找到<%=pm.getStuCount()%>条记录&nbsp;&nbsp;
			第<%=pm.getPageNo()%>/<%=pm.getTotalPages()%>页&nbsp;&nbsp;
			<a href="javascript:toPage( <%=pm.getFirstPage()%>);">首页</a>
			<a href="javascript:toPage(<%=pm.getPreviousPage()%>);">上一页</a>
			<a href="javascript:toPage(<%=pm.getNextPage()%>);">下一页</a>
			<a href="javascript:toPage( <%=pm.getLastPage()%>);">尾页</a>				
		</td>
	</tr>
	</table>
</body>
</html>