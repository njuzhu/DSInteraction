<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/index.css">
<link rel="stylesheet" type="text/css" href="../css/play.css">
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/play.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<title>播放内容页面</title>
</head>
<body>
<%@include file="header.jsp"%>
        <div class="banner">
            <ol class="breadcrumb">
                <li class="active">播放</li>
            </ol>
        </div>
        
        <%List<String> cinemaNames = (List<String>)session.getAttribute("cinemaNames"); %>
        <div class="wrap">
            <div class="list">
                <fieldset>
                    <legend>搜索栏</legend>
                    <div class="btn-group">
                         <button id="cinema_btn" class="btn btn-green">选择电影院</button> <button data-toggle="dropdown" class="btn btn-green dropdown-toggle"><span class="caret"></span></button>
                        <ul id="cinema_ul" class="dropdown-menu">
                        	<%if(cinemaNames != null){
                        		for(int i = 0; i <cinemaNames.size(); i++){ %>
                        		<li>
	                                <a href="#"><%=cinemaNames.get(i) %></a>
	                            </li>
                        	<%}} %>
                        </ul>
                    </div>
                    <div class="btn-group">
                         <button id="hall_btn" class="btn btn-green">选择电影厅</button> <button data-toggle="dropdown" class="btn btn-green dropdown-toggle"><span class="caret"></span></button>
                        <ul id="hall_ul" class="dropdown-menu">
                        </ul>
                    </div>
                    <div class="btn-group">
                         <button id="period_btn" class="btn btn-green">选择今日场次</button> <button data-toggle="dropdown" class="btn btn-green dropdown-toggle"><span class="caret"></span></button>
                        <ul id="period_ul" class="dropdown-menu">
                        </ul>
                    </div>
                    <button id="search_btn" class="btn btn-green" type="button"><span id="searchPlayList" class="glyphicon glyphicon-search">搜索</span></button>
                </fieldset>
            </div>
            <div id="playList" class="list" style="display:none;">
                <fieldset>
                    <legend>播放单</legend>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>编号</th>
                                <th>类型</th>
                                <th>关键字</th>
                                <th>时长</th>
                            </tr>
                        </thead>
                        <tbody id="list_table">
                            <!-- <tr>
                                <td>1</td>
                                <td>选择题</td>
                                <td>宝马</td>
                                <td>50秒</td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>选择题</td>
                                <td>宝马</td>
                                <td>45秒</td>
                            </tr> -->
                        </tbody>
                    </table>
                    <div>
                        <button id="startPlay" class="btn btn-green" type="button"><span class="glyphicon glyphicon-play">开始播放</span></button>
                    </div>
                </fieldset>
            </div>
        </div>
        
        
        
	</body>
</html>