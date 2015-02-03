<%@page import="model.Answer"%>
<%@page import="java.util.List"%>
<%@page import="model.Question"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/index.css">
<link rel="stylesheet" type="text/css" href="../css/question.css">
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<title>查看选择题详情页面</title>
</head>
<body>
<%@include file="header.jsp"%>
        <div class="banner">
            <ol class="breadcrumb">
                <li class="active">题库</li>
                <li><a href="<%=request.getContextPath()+"/view/questionList"%>">选择题</a></li>
                <li class="active">查看选择题</li>
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
        <div class="wrap question">
            <div class="list">
                <fieldset>
                    <legend>选择题</legend>
                    <%Question question = (Question)session.getAttribute("question"); 
                    int ques_id = question.getId();
                    String name = question.getKeyword();
                    String content = question.getContent();
                    %>
                    <div class="box">
                        <div class="title">
                            <p><span class="bold">题目名称：</span><span><%=name %></span></p>
                            <p><span class="bold">题目内容：</span>
                                <span> 
                                    <!--<img src="../images/logo.png">
                                     <EMBED src="vedio/Wildlife.wmv" autostart=true width="600" height="400"></EMBED> -->
                                     <%=content %>
                                </span>
                            </p>
                        </div>
                        <div class="answer clearfix">
                        <% List<Answer> answers = (List<Answer>)session.getAttribute("answers");
                        for(int i=0; i<answers.size(); i++){   
                        	Answer answer = answers.get(i);
                        %>
                            <p><span><%=answer.getContent() %></span></p>
                            <!--  <p><span class="choice">B:</span><span>我也不知道这是不是我也不知道这是不是我也不知道这是不是我也不知道这是不是我也不知道这是不是</span></p>
                            <p><span class="choice">C:</span><span>这不是我们网站的logo这不是我们网站的logo这不是我们网站的logo这不是我们网站的logo这不是我们网站的logo<img src="../images/logo.png"></span></p>
                            <p><span class="choice">D:</span><span>这是够乱七八糟的这是够乱七八糟的这是够乱七八糟的这是够乱七八糟的这是够乱七八糟的</span></p>-->
                        <%} %>
                            <div class="isRight">
                                <p><span>正确答案:A</span></p>
                            </div>
                        </div>
                    </div>
                    <div class="bottom clearfix">
                         <div class="edit">
                            <a href="" data-toggle="tooltip" data-placement="right" title="编辑"><span class="glyphicon glyphicon-pencil"></span></a>
                         </div>
                    </div>
                </fieldset>
            </div>
        </div>
</body>
</html>