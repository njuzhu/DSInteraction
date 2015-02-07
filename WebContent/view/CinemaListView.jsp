<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/index.css">
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<title>电影院管理页面</title>
</head>
<body>
 <%@include file="header.jsp"%>
        <div class="banner">
            <ol class="breadcrumb">
                <li class="active">管理</li>
                <li class="active">电影院管理</li>
            </ol>
            <div class="search">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="搜索">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button" style="height:34px"><span class="glyphicon glyphicon-search"></span></button>
                        </span>
                    </div>
            </div>
        </div>
</body>
</html>