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
<title>填写配送信息-千里之行-在线销售旅游产品</title>
<link rel="stylesheet" href="common/css/common.css" type="text/css"></link>
<style type="text/css">
	table{width:100%;}
	.title{background:url(images/peisong_logo.gif) no-repeat 0px center;border-bottom:1px solid #9999FF;height:30px;line-height:38px;font-size:12pt;color:#2F85EF;text-indent:30px;font-weight:bold;}
	.input_box{width:140px;height:18px;vertical-align:center;}
	.input_box_long{width:350px;height:18px;vertical-align:center;}
	td{height:35px;border-bottom:1px dashed #ccc;padding:3px;}
	.tooltip{font-size:9pt;color:#999;font-weight:normal;padding-left:5px;white-space:nowrap;}
	.btn61_21 {width:61px;height: 21px;line-height:21px;border-width: 0px;border-style:none;padding: 0px;font-size:12px;margin-right: 3px;background: url(images/btn.png) no-repeat 0 0;cursor: pointer;}
</style>
</head>
<body>
<iframe src="reception/top_index.jsp" name="topFrame" width="100%" height="150px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
<div id="middle_div">
	<div class="sitemap">
		您现在所在的位置：<a target="_top" href="index.jsp">网站首页</a>&nbsp;&gt;&nbsp;填写配送信息
	</div>
	<div class="title">填写配送信息</div>
	<form action="AddCustomerDetailinfoServlet" method="post">
		<input type="hidden" name="customerId" value="<%=session.getAttribute("userId") %>" />
		<table>
			<tr>
				<td width="90px">收货人姓名:</td>
				<td>
					<input class="input_box" name="name" type="text" value="" />
					<span class="tooltip">请填写真实的姓名。</span>
				</td>
			</tr>
			<tr>
				<td>固定电话:</td>
				<td>
					<input class="input_box"  type="text" name="telphone" />
					<span class="tooltip">固定电话与移动电话至少选填一项。</span>
				</td>
			</tr>
			<tr>
				<td>移动电话:</td>
				<td>
					<input class="input_box"  type="text" name="movePhone" />
					<span class="tooltip">固定电话与移动电话至少选填一项。</span>
				</td>
			</tr>
			<tr>
				<td>收货地址:</td>
				<td>
					<input class="input_box_long" type="text" name="address"  />
					<span class="tooltip">请详细填写真实地址。</span>
				</td>
			</tr>
		</table>
		<div style="height:40px;line-height:40px;padding-left:110px;">
			<input type="submit" class="btn61_21" value="提交" />&nbsp;&nbsp;
			<input type="button" class="btn61_21" value="重置" />
		</div>
	</form>
	</div>
	<iframe src="reception/bottom_index.jsp" name="bottomFrame" width="100%" height="180px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</body>
</html>