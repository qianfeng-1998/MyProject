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
<title>千里之行-底部</title>
<style type="text/css">
body{height:120px;text-align:center;font-size:9pt;background:url(images/bg.jpg) repeat-x 0px -30px;padding-top:30px;margin:0px;}
/*底部*/
#bottom_div{width:100%;border:1px solid #C4E0F6;border-left-width:0px;border-right-width:0px;height:85px;background:url(images/box_title_bg.jpg) repeat-x 0% 0%;}
#bottom_div div{float:left;margin:3px;font-size:10pt;width:24%;}
#bottom_div span.title{clear:both;display:block;text-align:left;text-indent:100px;font-size:10pt;font-weight:bold;padding:3px;}
#bottom_div ul{clear:both;margin:3px;padding:5px 5px 5px 120px;text-align:left;white-space:nowrap;}
#bottom_div a{color:#004B91;text-decoration:none;}
#bottom_div a:hover{text-decoration:underline;}

div.bottom{position:relative;left:0px;top:20px;clear:both;}
</style>
</head>
<body>
<div id="bottom_div">
	<div>
		<span class="title">我的订单</span>
		<ul>
			<li><a href="javascript:;">如何下订单</a></li>
			<li><a href="javascript:;">跟踪最近的订单</a></li>
			<li><a href="javascript:;">查看或修改订单</a></li>
		</ul>
	</div>
	<div>
		<span class="title">如何付款</span>
		<ul>
			<li><a href="javascript:;">付款方式</a></li>
			<li><a href="javascript:;">查看我的礼品卡余额</a></li>
			<li><a href="javascript:;">查看我的电子帐户</a></li>
		</ul>
	</div>
	<div>
		<span class="title">配送信息</span>
		<ul>
			<li><a href="javascript:;">配送费收取标准</a></li>
			<li><a href="javascript:;">国内订单配送时间和配送范围</a></li>
			<li><a href="javascript:;">海外订单配送时间和配送范围</a></li>
		</ul>
	</div>
	<div>
		<span class="title">需要帮助</span>
		<ul>
			<li><a href="javascript:;">汇款单招领</a></li>
			<li><a href="javascript:;">忘记了密码</a></li>
			<li><a href="javascript:;">参考帮助中心</a></li>
		</ul>
	</div>
</div>
<div class="bottom">重庆足下软件学院 | 千里之行&copy;(2009-2010)版权所有.</div>
</body>
</html>