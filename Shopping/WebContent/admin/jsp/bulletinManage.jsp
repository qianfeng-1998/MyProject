<%@page import="com.qf.service.AccordingIdGetUserName"%>
<%@page import="com.qf.bean.BulletinInfo"%>
<%@page import="com.qf.bean.PageModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
out.println("<base href=\""+basePath+"\">");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告信息管理</title>
<link style="text/csss" rel="stylesheet" href="admin/css/common.css">
<link style="text/csss" rel="stylesheet" href="admin/css/data_manage.css">
<style type="text/css">
.title_td{
	width:500px;
}
</style>
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

//删除商品类型
function removeBulletin(id){
	if(confirm("确认删除此公告信息吗?")){
		document.getElementById("op").value="remove";
		document.getElementById("updateId").value=id;
		document.myForm.submit();
	}
}

//修改商品类型
function updateBulletin(id){
	document.getElementById("op").value="toEdit";
	document.getElementById("updateId").value=id;
	document.myForm.submit();
}

//新增商品类型
function addBulletin(){
	window.location.href="admin/jsp/addBulletin.jsp";
}

//全选、全不选
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

//选择表格中的某条公告
//obj代表当前选中的复选框对象
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
		alert("请选择要删除的公告信息！");
		return;
	}
	if(confirm("确认删除这些公告信息吗？")){
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
	<%	
		PageModel<BulletinInfo> pm = (PageModel<BulletinInfo>)request.getAttribute("AdminBulletinPage");
		if(pm== null){
			System.out.println("komng");
			pm = new PageModel<BulletinInfo>();
		}
		List<BulletinInfo> items =pm.getList();
		BulletinInfo b=new BulletinInfo();
	%>
	<form name="myForm" action="SeeAllBulletinServlet" method="post" onsubmit="return search_click();">
		<%
			String op = request.getParameter("op");
			String keywords = request.getParameter("keywords");
		%>
		<input type="hidden" id="op" name="op" value="<%=op==null?"":op%> " />
		<input type="hidden" id="updateId" name="id" />
		<input type="hidden" name="pageSize" value="<%=pm.getPageSize()%>" />
		<input type="hidden" id="pageNo" name="pageNo" value="<%=pm.getPageNo()%>" />
		<input type="hidden" id="removeIds" name="removeIds" />
		<div class="opDiv">
			<div class="titlebar">公告信息管理</div>
			<div class="buttonDiv">
				<span>
					<input type="text" id="keywords" name="keywords" value="<%=keywords==null?"":keywords%>" />
				</span>
				<input class="btn61_21" type="submit" value="查询" />
				
				<input class="btn61_21" type="button" onclick="addBulletin()" value="添加公告" />
				<input class="btn61_21" type="button" onclick="delete_all_click();" value="删除公告" />
			</div>
		</div>
	</form>
	<table id="dataTable">
	<tr>
		<th><input type="checkbox" onclick="chkAll_click();" id="chkAll" /></th>
		<th width="100px">公告编号</th>
		<th>标题</th>
		<th>发布者</th>
		<th width="150px">发布时间</th>
		<th width="100px">操作</th>
	</tr>
	<% 
	if(items == null || items.isEmpty()){
		out.println("<tr><td colspan='6' align='center'>没有找到任何公告信息！</td></tr>");
	}else{
		for(int i=0;i<items.size();i++){
			BulletinInfo item = items.get(i); 
	%>
	<tr>
		<td align="center">
			<input type="checkbox" name="chkItems" onclick="chkItems_click(this);" value="<%=item.getId()%>"/>
		</td>
		<td><%=item.getId()%></td>
		<td class="title_td">
		<%=item.getTitle() %>
		</td>
		<td> <%-- <%=item.getUser().getUserName()%> --%>
			<%=AccordingIdGetUserName.getSingleBulletinName(item.getUserId()) %>
		</td>
		<td><%=b.convertDate(item.getCreateTime())%></td>
		<td align="center">
			<a href="javascript:updateBulletin(<%=item.getId()%>);">修改</a>
			<a href="javascript:removeBulletin(<%=item.getId()%>);">删除</a>
		</td>
	</tr>
	<% }} %>
	<tr>
		<td class="pagerTd" colspan="6">
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