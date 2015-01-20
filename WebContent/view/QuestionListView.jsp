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
<title>选择题列表页面</title>
</head>
<body>
    <%@include file="header.jsp"%>
        <div class="banner">
            <ol class="breadcrumb">
                <li class="active">题库</li>
                <li class="active">选择题</li>
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
        <div class="wrap">
            <div class="list">
                <fieldset>
                    <legend>选择题列表</legend>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>编号</th>
                                <th>名称</th>
                                <th>时间</th>
                                <th>查看</th>
                                <th>编辑</th>
                                <th>删除</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>我就是个名字</td>
                                <td>1:10</td>
                                <td><a href=""><span class="glyphicon glyphicon-eye-open"></span></a></td>
                                <td><a href=""><span class="glyphicon glyphicon-pencil"></span></a></td>
                                <td><a href=""><span class="glyphicon glyphicon-remove"></span></a></td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>我就是个名字我就是个名字我就是个名字我就是个名字我就是个名字</td>
                                <td>1:10</td>
                                <td><a href=""><span class="glyphicon glyphicon-eye-open"></span></a></td>
                                <td><a href=""><span class="glyphicon glyphicon-pencil"></span></a></td>
                                <td><a href=""><span class="glyphicon glyphicon-remove"></span></a></td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="bottom clearfix">
                         <div class="add">
                            <a href="QuestionCreateView.jsp" data-toggle="tooltip" data-placement="right" title="新建选择题"><span class="glyphicon glyphicon-plus-sign"></span></a>
                         </div>
                         <div class="page">
                            <a href="">上一页</a>
                            <span>第2页，共3页</span>
                            <a href="">下一页</a>
                         </div>
                    </div>
                </fieldset>
            </div>
        </div>
</body>
</html>