<%@page import="com.qf.service.AccordingIdGetUserName"%>
<%@page import="java.util.List"%>
<%@page import="com.qf.dao.AccordingIdGetBulletinInfo"%>
<%@page import="com.qf.bean.BulletinInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String id = request.getParameter("id");
if(null==id){
	response.sendRedirect("showBulletinList.jsp");
	return;
}
/*
BulletinDao dao = new BulletinDao();
BulletinInfo item =dao.getBulletinInfo(Integer.parseInt(id)) ;
if(item==null){
	response.sendRedirect("showBulletinList.jsp");
	return;
} */
//得到单个标题的信息
	BulletinInfo bi=new BulletinInfo();
	List<BulletinInfo> list= AccordingIdGetBulletinInfo.getSingleBulletin(Integer.parseInt(id));
	if(list.size()<=0){
		response.sendRedirect("showBulletinList.jsp");
		return;
	}
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
<title><%=list.get(0).getTitle()+"-"%>公告信息-千里之行-在线销售旅游用品 登山攀岩器材、户外服装、户外桌椅、睡袋垫子、野营用品、野营帐篷、运动手表...</title>
	<link rel="stylesheet" href="common/css/common.css" type="text/css"></link>
	<style type="text/css">
	.div_bulletin {border: 1px solid #CCE3F1;padding:20px 10px 20px 10px;}
	.content {font-size: 14px;text-align: left;padding:10px;margin-left:auto;margin-right:auto;padding:10px 50px 10px 50px;}
	p{text-indent: 2em;word-spacing: 5px;line-height: 24px;}
	.title {font-size: 20px;margin-bottom:15px;text-align: center;font-weight: bold;}
	.info {font-size: 12px;margin: 3px;text-align: center;}
	</style>
</head>
<body>
	<iframe src="reception/top_index.jsp" name="topFrame" width="100%" height="150px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
	<div id="middle_div">
	    <div class="sitemap">
			您现在所在的位置：<a target="_top" href="reception/index.jsp">网站首页</a>&nbsp;&gt;&nbsp;
			<a href="/Shopping/BulletinServlet?pageNo=1">网站公告</a>&nbsp;&gt;&nbsp;公告信息
		</div>
		<div class="div_bulletin">
			<div class="title"><%=list.get(0).getTitle()%></div>
			<div class="info">
				<%=bi.convertDate(list.get(0).getCreateTime())%>&nbsp;&nbsp;
				发布者：<%-- 根据指定的userid得到发布者名字<%=item.getUser().getUserName()%> --%>
				<%=AccordingIdGetUserName.getSingleBulletinName(list.get(0).getUserId())%>
			</div>
			<div class="content"><%=list.get(0).getContent()%></div>
		</div>
	</div>
	<iframe src="reception/bottom_index.jsp" name="bottomFrame" width="100%" height="180px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</body>
</html>