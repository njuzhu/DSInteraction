<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/index.css">
<link rel="stylesheet" type="text/css" href="../css/point.css">
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<script type="text/javascript" src="../js/point.js"></script>
<title>积分管理页面</title>
</head>
<body>
<%@include file="header.jsp"%>
        <div class="banner">
            <ol class="breadcrumb">
                <li class="active">管理</li>
                <li class="active">积分管理</li>
            </ol>
        </div>
        <div class="wrap">
            <h1 id="usr_h1">用户搜索</h1>
            <div id="search_line" class="input-group">
                <input type="text" class="form-control" placeholder="用户名/邮箱搜索">
                <span class="input-group-btn search_usr">
                    <button class="btn btn-green" type="button"><span class="glyphicon glyphicon-search"></span></button>
                </span>
            </div>
        </div>
        <form id="findUsers" action="/DSInteraction/view/findUsers" method="post">
        	<input type="hidden" name="keyword" value="" />
        </form>
	</body>
</html>