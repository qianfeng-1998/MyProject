<%@page import="com.qf.bean.AdminUser"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%
/* UserInfo user = (UserInfo)session.getAttribute("user"); */
%>

<%!
//获取Cookie值
/* private String getCookie(HttpServletRequest request,String key){
	if(null==request || null==key || "".equals(key)){
		return "";
	}
	Cookie[] cookies = request.getCookies();
	if(null != cookies){
		for(Cookie c : cookies){
			if(key.equals(c.getName())){
				return c.getValue();
			}
		}
	}
	return "";
}
 */
//获取登录时间
/* private String getLoginTime(HttpSession session){
	String loginTime = (String)session.getAttribute("loginTime");
	Long datetime = Long.parseLong(loginTime);
	return DateTimeUtil.convertDate(new Date(datetime));
} */
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
		<title>千里之行 后台管理</title>
		<style type="text/css">
body {
	width:100%;
	height:100%;
	margin: 0px;
	background-color: #9FC0EC;
	text-align: center;
}

#top_div {
	position:relative;
	top:0px;
	height:90px;
	text-align:left;
	margin-left:auto;
	margin-right:auto;
	width: 1000px;
}

#border_div {
	position:relative;
	top:0px;
	width: 100%;
	height: 26px;
	background: url(admin/images/middle.png) repeat-x 0 100%;
}

#middle_div {
	position:relative;
	padding:10px;
	border:0px;
	width: 1000px;
	height:488px;
	text-align: left;
	margin-left:auto;
	margin-right:auto;
}

#bottom_div{
	position:relative;
	clear:both;
	width:100%;
	height:25px;
	text-align:center;
	font-size:12px;
	line-height:25px;
	background: url(admin/images/middle.jpg) repeat-x 0% 35% #9FC0EC;
	padding-top: 5px;
}



#middle_left_div {
	width: 210px;
	height:100%;
	border: 1px ridge #9FC0EC;
	display:block;
	background:url(admin/images/macback.png);
	float:left;
}
#middle_center_div{
	width:15px;
	padding-left:5px;
	height:100%;
	float:left;
}


#middle_center_div img{
	margin-top:180px;
	vertical-align: middle;
}
#middle_right_div{
	width:766px;
	height:100%;
	float:left;
	border: 1px ridge #9FC0EC;
}
.title_div{
	border:0px;
	height:24px;
	line-height:24px;
	text-indent:1em;
	color:#fff;
	font-weight:bold;
	font-size:14px;
	background:url(admin/images/menubar.png) repeat-x 0 0;
}
#middle_left_div ul{
	margin:0px;
	padding: 0px;
	list-style: none;
	background-color: #F0F0F0;
}
#middle_left_div a{
	margin-left:0px;
	display:block;
	color:#1276C6;
	height:16px;
	line-height:16px;
	font-size: 14px;
	text-decoration: none;
	border-bottom:1px dashed #ccc;
	margin:3px 0px 3px 0px;
	text-align: left;
	text-indent:40px;
	background: url(admin/images/doc.gif) no-repeat 24px 1px #F0F0F0;
	padding:3px;
}
#middle_left_div a:hover{
	height:16px;
	line-height:16px;
	color:#f00;
	border-bottom:1px solid #ccc;
	background-color:#7EC9FC;
	background: url(admin/images/doc_red.gif) no-repeat 24px 1px;
}
#middle_center_div img{
	cursor: pointer;
}
#userInfo{
	height:40px;
	line-height:15px;
	background:url(admin/images/macback.png) #F0F0F0;
	padding:10px 0px 0px 5px;
	font-size:13px;
	padding-left:2em;}
</style>
<script type="text/javascript">
var width_before="766px";
var width_hidden = "978px";
function close_open_leftmenu(imgBtn){
	var left_div = document.getElementById("middle_left_div");
	var right_div= document.getElementById("middle_right_div");
	if(left_div != null && right_div!=null){
		if(left_div.style.display=='block' || left_div.style.display==''){
			left_div.style.display="none";
			right_div.style.width=width_hidden;
			imgBtn.src="admin/images/open_left.gif";
			imgBtn.title="展开左边";
		}else{
			left_div.style.display="block";
			right_div.style.width=width_before;
			imgBtn.src="admin/images/close_left.gif";
			imgBtn.title="折叠左边";
		}
	}
}
function loadImage(){
	var imgs = ["admin/images/doc.gif",
	        	"admin/images/doc_red.gif",
	        	"admin/images/open_left.gif",
	        	"admin/images/close_left.gif"];
	for(var i=0;i<imgs.length;i++){
		var img = new Image();
		img.src=imgs[i];
	}
}

function setPageSize(){
	var width = parseInt(window.screen.width);
	var height = parseInt(window.screen.height);
	if(width>1024){
		document.getElementById("top_div").style.width="1256px";
		document.getElementById("middle_div").style.width="1256px";
		document.getElementById("middle_right_div").style.width="1022px";
		width_before="1022px";
		width_hidden = "1234px";
	}
}

function loadPage(){
	loadImage();
	setPageSize();
}
</script>
	</head>
	<body onload="loadPage()">
		<div id="top_div">
			<img src="admin/images/logo.gif" />
		</div>
		<%
			ArrayList<AdminUser> list=(ArrayList) session.getAttribute("loginAdminList");
			String time=(String)session.getAttribute("time");
		%>
		<div id="border_div" ></div>
		<div id="middle_div">
			<div id="middle_left_div">
				<div id="userInfo">
					登录用户：<%=list.get(0).getUserName()%> <br/>
					登录时间：<%=time%>
				</div>
				<div class="title_div">
					公告管理
				</div>
				<ul>
					<li>
						<a href="admin/jsp/addBulletin.jsp" target="mainFrame">发布公告</a>
					</li>
					<li>
						<a href="SeeAllBulletinServlet?pageNo=1" target="mainFrame">公告管理</a>
					</li>
				</ul>
				<div class="title_div">
					商品管理
				</div>
				<ul>
					<li>
						<a href="AddGoodsServlet" target="mainFrame">添加商品</a>
					</li>
					<li>
						<a href="AllGoodsPageServlet?pageNo=1" target="mainFrame">商品信息管理</a>
					</li>
					<li>
						<a href="admin/jsp/addGoodsType.jsp" target="mainFrame">添加商品类型</a>
					</li>
					<li>
						<a href="SeeAllGoodsTypeServlet?pageNo=1" target="mainFrame">商品类型管理</a>
					</li>
				</ul>
				<div class="title_div">
					订单管理
				</div>
				<ul>
					<li>
						<a href="SeeAllOrderInfoServlet?pageNo=1" target="mainFrame">订单信息管理</a>
					</li>
				</ul>
				<div class="title_div">
					客户管理
				</div>
				<ul>
					<li>
						<a href="SeeAllCustomerServlet?pageNo=1" target="mainFrame">客户信息管理</a>
					</li>
				</ul>
				<div class="title_div">
					系统管理
				</div>
				<ul>
					<li>
						<a href="admin/jsp/resetPass.jsp" target="mainFrame">修改密码</a>
					</li>
					<li>
						<a href="admin/jsp/resetUserName.jsp" target="mainFrame">修改用户名</a>
					</li>
					<li>
						<a href="admin/jsp/login.jsp" target="_top">退出系统</a>
					</li>
				</ul>
			</div>
			<div id="middle_center_div">
				<img src="admin/images/close_left.gif" title="折叠左边" onclick="close_open_leftmenu(this);"/>
			</div>
			<div id="middle_right_div">
				<iframe name="mainFrame" src="admin/jsp/welcome.jsp" frameborder="0" marginwidth="0" marginheight="0" width="100%" height="100%"></iframe>
			</div>
		</div>
		<div id="bottom_div">重庆足下软件学院&copy;(2009-2010)All Rights Reserved.</div>
	</body>
</html>