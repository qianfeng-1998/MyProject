<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
out.println("<base href=\""+basePath+"\">");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加商品类型</title>
<link style="text/csss" rel="stylesheet" href="admin/css/add_skin.css">
<link style="text/csss" rel="stylesheet" href="admin/css/common.css">
<script type="text/javascript">
function check(){
	var typeName=document.getElementById("typeName").value;
	if (typeName=="") {
		alert("请输入类型名称！");
		return false;
	}else if (confirm("确认添加此商品类型吗？")) {
		return true;
	}
}
</script>
<style type="text/css">
.name_td{width:100px;}
.btn_td{text-indent:50px;}
</style>
</head>
<body>
<form action="AddGoodsTypeServlet"  method="post" onsubmit="return check()">
	<div class="opDiv">
			<div class="titlebar">添加商品类型</div>
		</div>
	<table class="addTable">
		<tr>
			<td class="name_td">类型名称:</td>
			<td><input type="text" name="typeName" id="typeName"/></td>
		</tr>
		<tr>
			<td colspan="2" class="btn_td">
				<input class="btn61_21" type="submit" value="提交表单" />&nbsp;&nbsp;
				<input class="btn61_21" type="reset" value="重置表单" />
			</td>
		</tr>
	</table>
</form>
</body>
</html>