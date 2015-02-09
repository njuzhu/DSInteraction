<%@page import="java.util.List"%>
<%@page import="model.User"%>
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
<title>积分管理页面</title>
</head>
<body>
<%@include file="header.jsp"%>
        <div class="banner">
            <ol class="breadcrumb">
                <li class="active">管理</li>
                <li class="active">积分管理</li>
            </ol>
            <div class="search">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="用户名/邮箱搜索">
                        <span class="input-group-btn search_usr">
                            <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
                        </span>
                    </div>
            </div>
            <form id="findUsers" action="/DSInteraction/view/findUsers" method="post">
	        	<input type="hidden" name="keyword" value="" />
	        </form>
        </div>
        <div class="wrap">
            <div class="list">
                <fieldset>
                	<%String keyword = (String)session.getAttribute("keyword"); %>
                    <legend>包含"<%=keyword %>"关键字的相关用户</legend>
                    
                    <%List<User> users = (List<User>)session.getAttribute("users");%>
                    <%if(users != null){ 
                    	for(User user:users){
                    		String name = user.getName();
                    		String email = user.getEmail();
                    		int point = user.getPoint();
                    		String image = user.getImage();%>
                    		<div class="block clearfix">
		                        <div class="left_blc">
		                            <img src="../images/icon.jpg" />
		                        </div>
		                        <div class="right_blc">
		                            <div class="usr_name">
		                                <label>用户名：</label>
		                                <span><%=name %></span>
		                            </div>
		                            <div class="usr_email">
		                                <label>邮箱：</label>
		                                <span><%=email %></span>
		                            </div>
		                            <div class="usr_point">
		                                <label>积分：</label>
		                                <span><%=point %></span>
		                            </div>
		                            <div class="point">
		                                <span class="change_label">兑换</span>
		                                <button class="btn btn-default minus" type="button"><span class="glyphicon glyphicon-minus"></span></button>
		                                <input type="text" class="form-control point_val">
		                                <button class="btn btn-default plus" type="button"><span class="glyphicon glyphicon-plus"></span></button>
		                                <span class="point_label">积分</span>
		                                <button class="btn btn-green change_btn" type="button"><span>确定</span></button>
		                                <input class="usr_id" type="hidden" value="<%=user.getId() %>"/>
		                                <input class="usr_password" type="hidden" value="<%=user.getPassword() %>" />
		                            </div>
		                        </div>
		                    </div>
                    <%}
                    }else{ %>
                    	<div class="no_usr">
	                        <span>对不起，没有相关用户！</span>
	                    </div>
                    <%} %>
                </fieldset>
            </div>
        </div>
        <script type="text/javascript" src="../js/pointResult.js"></script>
	</body>
</html>