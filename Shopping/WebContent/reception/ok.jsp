<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>操作成功！</title>
    <link rel="stylesheet" href="common/css/common.css" type="text/css"></link>
    <style type="text/css">
    #msg_tip{width:100%;height:80px;line-height:80px;text-align:center;border-bottom:1px dashed #1F8E1F;font-size:24pt;font-family:'黑体';color:#1F8E1F;padding-bottom:20px;}
    img{vertical-align:middle;width:80px;height:80px;}
    #msg_info{width:100%;height:200px;padding-top:50px;text-align:left;font-size:10pt;color:#1F8E1F;}
    #msg_info a{color:#EF1D1E;}
    </style>
  </head>
  
  <body>
  	<iframe src="reception/top_index.jsp" name="topFrame" width="100%" height="150px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
    <div id="middle_div">
    	<div id="msg_tip"><img src="images/ok.png" />操作成功！</div>
    	<%Object msg = request.getAttribute("msg"); %>
    	<div id="msg_info"><%=msg==null?"":msg%>&nbsp;&nbsp;<a target="_top" href="reception/index.jsp">返回首页</a></div>
  	</div>
  	<iframe src="reception/bottom_index.jsp" name="bottomFrame" width="100%" height="180px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
  </body>
</html>
