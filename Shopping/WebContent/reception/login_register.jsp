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
<title>登录/注册-千里之行-在线销售旅游产品</title>
<link rel="stylesheet" href="common/css/common.css" type="text/css"></link>
<style type="text/css">
#middle_div{height:460px;}
#login_div{float:left;width:400px;height:100%;text-align: left;background:url(images/login_border_y.gif) repeat-y 100% 0%;}
#register_div{float:left;text-align:center;width:480px;height:100%;padding-left:50px;}
.btn_login{width:57px;height:57px;background:url(images/btn_login.gif) no-repeat 0px 0px;border:0px;margin-left:20px;padding:0px;cursor:pointer;}
.btn_register{width:125px;height:53px;background:url(images/btn_register.gif) no-repeat 0px 0px;border:0px;margin:0px;padding:0px;cursor:pointer;}
.input_box{width:120px;height:18px;vertical-align:center;}
.input_box_long{width:300px;height:18px;vertical-align:center;}
.register_table {width:100%;text-align: left;}
.register_table td{border-bottom:1px dashed #ccc;height:30px;line-height:30px;}
.tooltip{font-size:9pt;color:#999;font-weight:normal;padding-left:5px;white-space:nowrap;}
#gaoji{text-align:left;width:100%;margin-left:auto;margin-right:auto;}
#gaoji div{height:30px;border-bottom:1px dashed #ccc;padding:3px;}
.btn61_21 {width:61px;height:21px;vertical-align:middle;line-height:21px;border-width: 0px;border-style:none;padding: 0px;font-size:12px;margin-right: 3px;background: url(images/btn.png) no-repeat 0 0;cursor: pointer;}
.errorDiv{color:#f00;font-size:12pt;}
form{clear:both;}
img{margin-bottom:20px;}
</style>
<script type="text/javascript" src="common/js/ajax.js"></script>
<script type="text/javascript">
function showGaojiOption(chk){
	var gaoji = document.getElementById("gaoji");
	if(chk.checked){
		gaoji.style.display="block";
	}else{
		gaoji.style.display="none";
	}
}

var isSubmitForm = false;
//验证Email
function validateEmail(){
	var email_input = document.registerForm.email;
	if(email_input.value==""){
		alert("请输入用户账户/邮箱！");
		email_input.focus();
		return;
	}
	var url = "<%=basePath%>/Validation";
	var params = "email="+email_input.value;
	sendRequest("post",url,params,callback);
}

function callback(xmlHttp){
	var text = xmlHttp.responseText;
	var isValidate = false;
	var emailMsg = document.getElementById("emailMsg");
	if(text=="true"){
		emailMsg.innerHTML = "<span style='color:#f00;'>该邮箱已被其他人注册！</span>";
	}else if(text=="false"){
		emailMsg.innerHTML = "<span style='color:#29D134;'>该邮箱可以使用!</span>";
		isValidate = true;
	}else{
		emailMsg.innerHTML = "<span style='color:#f00;'>请正确填写邮箱地址！</span>";
		document.registerForm.email.value="";
		document.registerForm.email.focus();
	}

	if(isSubmitForm && isValidate){
		isSubmitForm = false;
		if(checkForm()){
			document.registerForm.submit();
		}
	}
}

function submitForm(){
	isSubmitForm = true;
	validateEmail();
}

function checkForm(){
	var pwd_input = document.registerForm.pwd;
	var repwd_input = document.registerForm.repwd;
	if(pwd_input.value==""){
		alert("请输入密码！");
		pwd_input.focus();
		return false;
	}
	if(repwd_input.value == ""){
		alert("请输入确认密码！");
		repwd_input.focus();
		return false;
	}
	if(pwd_input.value != repwd_input.value){
		alert("两次输入的密码不一致，请重新输入！");
		pwd_input.value="";
		repwd_input.value="";
		pwd_input.focus();
		return false;
	}
	var chkGaoji = document.registerForm.chkGaoji.checked;
	if(chkGaoji){
		var name_input = document.registerForm.name;
		if(name_input.value==""){
			alert("请输入用户名！");
			name_input.focus();
			return false;
		}
		var telphone_input = document.registerForm.telphone;
		var movePhone_input = document.registerForm.movePhone;
		if(telphone_input.value == "" && movePhone_input.value==""){
			alert("固定电话与移动电话至少选填一项！");
			telphone_input.focus();
			return false;
		}
		var address_input = document.registerForm.address;
		if(address_input.value == ""){
			alert("请填写收货地址！");
			address_input.focus();
			return false;
		}
	}
	return true;
}
</script>
</head>
<body>
<iframe src="reception/top_index.jsp?op=login" name="topFrame" width="100%" height="150px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
<div id="middle_div">
	<div class="sitemap">
		您现在所在的位置：<a target="_top" href="index.jsp">网站首页</a>&nbsp;&gt;&nbsp;注册/登录
	</div>
	<div id="login_div">
		<form name="loginForm" action="ReceptionLoginServlet" method="post" target="_top">
		<img src="images/login_logo.gif"  />
			<input type="hidden" name="op" value="login" />
			<%Object error = request.getAttribute("failed");%>
			<div class="errorDiv"><%=error==null?"":"登录失败，用户名或密码错误！"%></div>
			<table align="center">
				<tr>
					<td>用户账户/邮箱:</td>
					<td><input class="input_box" type="text" name="userName" /></td>		
					<td rowspan="2"><input type="submit" class="btn_login" value="" /></td>			
				</tr>
				<tr>
					<td>用户密码:</td>
					<td><input class="input_box" type="password" name="userPwd" /></td>				
				</tr>
			</table>
		</form>
	</div>
	<div id="register_div">
		<img src="images/register_logo.gif" align="left" />
		<form name="registerForm" action="ReceptionLoginServlet" method="post">
			<input type="hidden" name="op" value="register" />
			<table align="center" class="register_table">
				<tr>
					<td>用户账户/邮箱:</td>
					<td>
						<input class="input_box" type="text" name="email" />
						<input type="button" class="btn61_21" onclick="validateEmail()" value="检测邮箱" />
						<span id="emailMsg"></span>
					</td>		
				</tr>
				<tr>
					<td>密码:</td>
					<td>
						<input class="input_box" type="password" name="pwd" />	
						<span class="tooltip">登录密码。</span>
					</td>			
				</tr>
				<tr>
					<td>确认密码:</td>
					<td>
						<input class="input_box" type="password" name="repwd" />
						<span class="tooltip">请再次输入密码。</span>
					</td>		
				</tr>
				<tr>
					<td colspan="2" style="font-weight:bold;">
						<input type="checkbox" onclick="showGaojiOption(this)" name="chkGaoji" value="0" />
						高级选项<span class="tooltip">高级选项填写关于配送的信息。若勾选此选项，则以下必填。</span>
					</td>
				</tr>
			</table>
			<div id="gaoji" style="display:none;">
				<div>收货人姓名:
					<input class="input_box" name="name" type="text" value="" />
					<span class="tooltip">请填写真实的姓名。</span>
				</div>	
				<div>固定电话:&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="input_box"  type="text" name="telphone" />
					<span class="tooltip">固定电话与移动电话至少选填一项。</span>
				</div>	
				<div>移动电话:&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="input_box"  type="text" name="movePhone" />
					<span class="tooltip">固定电话与移动电话至少选填一项。</span>
				</div>
				<div>收货地址:&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="input_box_long" type="text" name="address"  />
					<span class="tooltip">请正确填写。</span>
				</div>	
			</div>
		</form>
		<div style="text-align:right;"><input type="button" class="btn_register" onclick="submitForm()" value=""/></div>
	</div>
</div>
<iframe src="reception/bottom_index.jsp" name="bottomFrame" width="100%" height="180px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</body>
</html>