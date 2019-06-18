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
<title>修改密码</title>
<link style="text/csss" rel="stylesheet" href="admin/css/add_skin.css">
<link style="text/csss" rel="stylesheet" href="admin/css/common.css">
<style type="text/css">
.btn_td{text-indent:50px;}
</style>
<script type="text/javascript">
function submit_form(){
	//旧密码
	var oldPass = document.myForm.oldPwd;
	if(""==oldPass.value){
		alert("旧密码不能为空！");
		oldPass.focus();
		return false;
	}
	
	//新密码
	var newPass = document.myForm.newPwd;
	//确认密码
	var confirmPwd = document.myForm.confirmPwd;
	if(""==newPass.value){
		alert("新密码不能为空！");
		newPass.focus();
		return false;
	}
	if(""==confirmPwd.value){
		alert("确认密码不能为空！");
		confirmPwd.focus();
		return false;		
	}
	if(newPass.value != confirmPwd.value){
		alert("两次输入的密码不一致！(新密码和确认密码必须相同！)");
		newPass.value=confirmPwd.value="";
		newPass.focus();
		return false;			
	}
	return true;
}

function rollback() {
	var instructions=document.getElementById("instructions").value;
	if (instructions=="密码不匹配") {
		alert("原密码错误");
	}
}
</script>
</head>
<body onload="rollback()">
<form name="myForm" action="UpdateAdminPwdServlet" method="post" onsubmit="return submit_form();">
	<input type="hidden" name="op" value="resetPass" />
	<input type="hidden" id="instructions" value="${instructions}">
	<div class="opDiv">
		<div class="titlebar">修改密码</div>
	</div>
	<table class="addTable">
		<tr>
			<td class="name_td" width="100px">旧&nbsp;密&nbsp;码：</td>
			<td><input type="password" name="oldPwd" /></td>
		</tr>
		<tr>
			<td class="name_td">新&nbsp;密&nbsp;码：</td>
			<td><input type="password" name="newPwd" /></td>
		</tr>
		<tr>
			<td class="name_td">确认密码：</td>
			<td><input type="password" name="confirmPwd" /></td>
		</tr>
		<tr>
			<td colspan="2" align="left" class="btn_td">
				<input class="btn61_21" type="submit" value="确认" />&nbsp;&nbsp;
				<input class="btn61_21" type="reset" value="重置" />
			</td>
		</tr>
	</table>
</form>
</body>
</html>