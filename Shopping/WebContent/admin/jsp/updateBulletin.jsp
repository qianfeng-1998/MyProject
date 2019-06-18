<%@page import="com.qf.service.AccordingIdGetUserName"%>
<%@page import="com.qf.bean.BulletinInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
out.println("<base href=\""+basePath+"\">");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看/修改公告信息</title>
<link style="text/csss" rel="stylesheet" href="admin/css/add_skin.css">
<link style="text/csss" rel="stylesheet" href="admin/css/common.css">
<style type="text/css">
.name_td{width:100px;}
.long_inputbox{width:500px;}
#number_span{padding:3px 10px 3px 10px;font-size:120%;border:1px solid #ADAEB0;background-color:#fff;color:#ADAEB0;font-weight:bold;}
</style>
</head>
<body>
<%
	List<BulletinInfo> list=(List) session.getAttribute("list");
	//System.out.println("到jsp");
	BulletinInfo bu=list.get(0);
%>
<form action="UpdateBulletinServlet" method="post">
	<input type="hidden" name="id" value="<%=bu.getId()%>" />
	<div class="opDiv">
			<div class="titlebar">查看/修改公告信息</div>
	</div>
	<table class="addTable">
		<tr>
			<td class="name_td">编号:</td>
			<td>
				<span id="number_span"><%=bu.getId()%></span>
			</td>
		</tr>
		<tr>
			<td class="name_td">标题:</td>
			<td><input type="text" class="long_inputbox" name="title" value="<%=bu.getTitle()%>" /></td>
		</tr>
		<tr>
			<td class="name_td">内容:</td>
			<td>
				<%
					String content = bu.getContent();
					content = null==content?"":content;
					if(null!=content){
						content = content.replaceAll("</p><p>", "\r\n");
						content = content.replaceAll("<p>","");
						content = content.replaceAll("</p>","");
					} 
				%>
				<textarea name="content" rows="10" cols="80"><%=content%></textarea>
			</td>
		</tr>
		<tr>
			<td class="name_td">发布者:</td>
			<td><%=AccordingIdGetUserName.getSingleBulletinName(bu.getUserId()) %></td>
		</tr>
		<tr>
			<td class="name_td">发布时间:</td>
			<td><%=bu.convertDate(bu.getCreateTime())%></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<input class="btn61_21" type="submit" value="提交表单" />&nbsp;&nbsp;
				<input class="btn61_21" type="reset" value="重置表单" />&nbsp;&nbsp;
				<input class="btn61_21" type="button" onclick="window.history.go(-1);" value="返回" />
			</td>
		</tr>
	</table>
</form>
</body>
</html>