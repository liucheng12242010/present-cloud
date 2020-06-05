<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<link rel="stylesheet" href="layui/layui.all.js" media="all">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.cookie.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>到云资源管理系统登录</title>
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
			type:'post'
			,url:'login'
			,success:function(data){
			}
		});
	})
	function resetValue() {
		document.getElementById("userName").value = "";
		document.getElementById("password").value = "";
	}
	function register(){
		window.location = "register.jsp";
	}
</script>
<style type="text/css">
body {
	font-size: 12px;
	margin: 0;
	padding: 0;
	width: 100%
}

#main { //
	background: url("images/login.jpg") no-repeat; //
	background: #aa00ee;
	background-position: center;
	background: -webkit-gradient(linear, 0% 0%, 100% 100%, from(#aaccee),
		to(#aa00ee));
	background-attachment: fixed;
	width: 1250px;
	height: 590px;
	margin: 0 auto;
	background-position: center;
}

form {
	margin: 0 auto;
	/* 	position: absolute; */
	left: 380px;
	top: 385px;
}

#input {
	
}

.inp {
	block: block;
	height: 30px;
	width: 200px;
}

#error {
	padding: 10px 0;
}

.btn {
	block: block;
	height: 30px;
	width: 60px;
}

.title {
	font-size: 30px;
	padding-top: 150px;
	font-family: 微软雅黑;
	color: white;
	margin: 0 auto;
}

.error {
	margin-left:60px;
	font-size:15px;
	width: 150px;
	height: 30px;
	color: red;
	
}

.login {
	
}

#loginDiv {
	background:url("images/denglu.jpg")    ;
	width: 350px;
	padding-top:30px;
	padding-bottom: 30px;
	margin-top:40px;
	background-size:100% 100%;
}
.logo{
	float: right;
	width: 200px;
}
</style>
</head>
<body class="layui-layout-body">

	<div id="main" align="center">
		<img class = "logo" src="" >
		<p class="title" >---欢迎来到到云管理系统---</p>
		<div id="loginDiv">
			<form action="login" method="post" class="login">
				<s:token></s:token>
				<div class="error">${error}</div>
				<h2>
					账号：<input type="text" value="" class="inp" name="user.userId"
						id="userName" /> <br>
				</h2>
				<h2>
					密 码：   <input type="password" value="" class="inp"
						name="user.password" id="password" /> <br>
				</h2>
				
				<div align="center">
					<input class="btn" type="submit" value="登录" /> &nbsp; 
					<input class="btn" type="button" value="重置" onclick="resetValue()" /> &nbsp;
					<input class="btn" type="button" value="注册" onclick="register()" />
					<br>
				</div>
			</form>
		</div>
	</div>
</body>
</html>