<%@page import="model.Race"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/index.css">
<link rel="stylesheet" type="text/css" href="../css/race.css">
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<title>视频详情页面</title>
</head>
<body>
    <%@include file="header.jsp"%>
    <div class="banner">
        <ol class="breadcrumb">
            <li class="active">题库</li>
            <li class="active">赛车视频</li>
        </ol>
        <div class="search">
            <form action="raceSearchPro" method="post" >
             <div class="input-group">
                 <input type="text" class="form-control" placeholder="搜索" required="required" name="race.name">
                 <span class="input-group-btn">
                     <button class="btn btn-default" type="submit" style="height:34px"><span class="glyphicon glyphicon-search"></span></button>
                 </span>
             </div>
            </form>
        </div>
    </div>
    <div class="wrap race">
        <div class="list">
            <fieldset>
                <legend>视频</legend>
                <%Race race = (Race)session.getAttribute("race"); 
                int race_id = race.getId();
                String name = race.getName();
                String content = race.getContent();
                int duration = race.getDuration();
                %>
             <p><span class="title">视频名称：</span>
             <span><%=name %></span> </p>
             <p><span class="title">视频时长：</span>
             <span><%=duration %>s</span> </p>
             <p style="margin-top:10px">
             <section id="player">
				  <video id="media" width="320" height="240" controls> 
				    <source src="<%=basePath %>upload/<%=content %>">
				  </video> 
				</section>
                <!--  <EMBED src="<%=basePath %>upload/<%=content %>" autostart=true width=320" height="240"></EMBED>  --> 
             </p>
             <div class="bottom">
                 <div class="edit">
                     <a href="<%=request.getContextPath()+"/view/editRace?race_id="+race_id%>" data-toggle="tooltip" data-placement="right" title="编辑"><span class="glyphicon glyphicon-pencil"></span></a>
                 </div>
             </div>
             </fieldset>
        </div>
    </div>
    
</body>
</html>