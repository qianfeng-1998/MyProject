
<%@page import="com.qf.bean.BulletinInfo"%>
<%@page import="com.qf.bean.PageModel"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 
<%-- <%
BulletinDao dao = new BulletinDao();
int pageSize=25,pageNo=1;
try{
	pageNo = Integer.parseInt(request.getParameter("pageNo"));
}catch(Exception ex){}
PageModel<BulletinInfo> pm =  dao.getAllBulletinInfo(pageSize,pageNo);
ArrayList<BulletinInfo> bulletinList = pm.getData();
%> --%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	out.println("<base href=\""+basePath+"\">");
	%>
    <title>公告列表-千里之行-在线销售旅游用品、运动手表...</title>
	<link rel="stylesheet" href="common/css/common.css" type="text/css"></link>
	<style type="text/css">
	#bulletinList{padding:10px 3px 3px 3px;}
	#bulletinList span{font-size:11pt;color:#727171;margin-left:30px;border:0px;}
	#bulletinList a{display:block;text-indent:1em;font-size:11pt;color:#4B8ECE;text-decoration:none;height:35px;line-height:35px;border-bottom:1px dashed #727171;}
	#bulletinList a:hover{color:#FE8802;}
	
	.pager{text-align:right;height:25px;line-height:25px;border-bottom:0px;font-size:10pt;}
	</style>
  </head>
  <body>
  	<iframe src="reception/top_index.jsp" name="topFrame" width="100%" height="150px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
    <div id="middle_div">
	    <div class="sitemap">
			您现在所在的位置：<a target="_top" href="reception/index.jsp">网站首页</a>&nbsp;&gt;&nbsp;网站公告
		</div>
		<div id="bulletinList">
			<%-- <%
			BulletinInfo bulletin = null;
			for(int i=0;i<bulletinList.size();i++){
				bulletin = bulletinList.get(i);
			%> --%>
			<%
				PageModel<BulletinInfo> pageList=(PageModel) session.getAttribute("page");
				List<BulletinInfo> list=pageList.getList();
				BulletinInfo bi=null;
				
				for(int i=0;i<list.size();i++){
					bi=list.get(i);
				
			%>
			<a href="reception/bulletin.jsp?id=<%=bi.getId()%>">
				<%=bi.getTitle()%>
				<span><%=bi.convertDate(bi.getCreateTime())%></span>
			</a>
			<%} %>
		</div>
		<div class="pager">
			共有<%=pageList.getStuCount()%>条公告&nbsp;&nbsp;
			第<%=pageList.getPageNo()%>/<%=pageList.getTotalPages()%>页&nbsp;&nbsp; 
			<%-- <a href="showBulletinList.jsp?pageNo=<%=pm.getIndexPageNo()%>">首页</a>
			<a href="showBulletinList.jsp?pageNo=<%=pm.getPreviousPageNo()%>">上一页</a>
			<a href="showBulletinList.jsp?pageNo=<%=pm.getNextPageNo()%>">下一页</a>
			<a href="showBulletinList.jsp?pageNo=<%=pm.getLastPageNo()%>">尾页</a> --%>
			
			<a href="/Shopping/BulletinServlet?pageNo=<%=pageList.getFirstPage()%>">首页</a>
			<a href="/Shopping/BulletinServlet?pageNo=<%=pageList.getPreviousPage()%>">上一页</a>
			<a href="/Shopping/BulletinServlet?pageNo=<%=pageList.getNextPage()%>">下一页</a>
			<a href="/Shopping/BulletinServlet?pageNo=<%=pageList.getLastPage()%>">尾页</a>
		</div>
	</div>
	<iframe src="reception/bottom_index.jsp" name="bottomFrame" width="100%" height="180px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
  </body>
</html>
