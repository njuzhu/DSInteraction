<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../css/login.css">
<title>登录</title>
</head>
<body>
<div class="wrapper">
	<h1 align="center">欢迎，请登录</h1>
		<form action="loginPro" method="post" >
		<div class="loginBox">
			<div class="loginBoxCenter">
				<p><label for="Username">用户名:</label></p>
				<p><input type="text" class="loginInput" autofocus="autofocus" required="required"   name="account" /></p>
				<p><label for="password">密码:</label></p>
				<p><input type="password" id="password" name="password" class="loginInput" required="required" /></p>
			</div>
			<div class="loginBoxButtons">
				<button class="loginBtn" type="submit">登录</button></p>
			</div>
			
		</div>
		</form>
	</div>
</body>
</html>