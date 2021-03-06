<%@page import="model.Answer"%>
<%@page import="java.util.List"%>
<%@page import="model.Question"%>
<%@page import="java.util.Hashtable"%>
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
                <form action="questionSearchPro" method="post" >
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="搜索" required="required" name="question.keyword">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="submit" style="height:34px"><span class="glyphicon glyphicon-search"></span></button>
                        </span>
                    </div>
                </form>
            </div>
        </div>
        <div class="wrap">
            <div class="list">
                <fieldset>
                    <legend>选择题列表</legend>
                    <table class="table table-striped" id="j_questions">
                        <thead>
                            <tr>
                                <th>编号</th>
                                <th>名称</th>
                                <th>时间(s)</th>
                                <th>查看</th>
                                <th>编辑</th>
                                <th>删除</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%if(request.getParameter("search") == null) {%>
	                        <%List<Question> questionList = (List<Question>)session.getAttribute("questions");
	                            if(questionList != null){
	                        %>
	                        <%
	                            for(int i=0; i<questionList.size();i++){
	                            	Question question = questionList.get(i);
	                            	int question_id = question.getId();
	                            	String name = question.getKeyword();
	                            	int duration = question.getDuration();
	                            
	                        %>
	                            <tr>
	                                <td><%=question_id %></td>
	                                <td><%=name %></td>
	                                <td><%=duration %></td>
	                                <td><a href="<%=request.getContextPath()+"/view/showQuestionDetail?question_id="+question_id%>"><span class="glyphicon glyphicon-eye-open"></span></a></td>
	                                <td><a href="<%=request.getContextPath()+"/view/editQuestion?question_id="+question_id%>"><span class="glyphicon glyphicon-pencil"></span></a></td>
	                                <td><a href="<%=request.getContextPath()+"/view/deleteQuestion?question_id="+question_id%>"><span class="glyphicon glyphicon-remove"></span></a></td>
	                            </tr>
	                        <%} } else {%>
	                            <p>问题列表为空</p>
	                        <%} %>
	                        
                        <%} else if(Integer.parseInt(request.getParameter("search")) == 1){%>
                            <%List<Question> questionSearchList = (List<Question>)session.getAttribute("questionSearchList");%>
	                        <%
	                            for(int i=0; i<questionSearchList.size();i++){
	                            	Question question = questionSearchList.get(i);
	                            	int question_id = question.getId();
	                            	String name = question.getKeyword();
	                            	int duration = question.getDuration();
	                            
	                        %>
	                            <tr>
	                                <td><%=question_id %></td>
	                                <td><%=name %></td>
	                                <td><%=duration %></td>
	                                <td><a href="<%=request.getContextPath()+"/view/showQuestionDetail?question_id="+question_id%>"><span class="glyphicon glyphicon-eye-open"></span></a></td>
	                                <td><a href="<%=request.getContextPath()+"/view/editQuestion?question_id="+question_id%>"><span class="glyphicon glyphicon-pencil"></span></a></td>
	                                <td><a href="<%=request.getContextPath()+"/view/deleteQuestion?question_id="+question_id%>"><span class="glyphicon glyphicon-remove"></span></a></td>
	                            </tr>
	                        <%} %>
                        <%} %>
                        </tbody>
                    </table>
                    <div class="bottom clearfix" id="j_bottom">
                         <div class="add">
                            <a href="QuestionCreateView.jsp" data-toggle="tooltip" data-placement="right" title="新建选择题"><span class="glyphicon glyphicon-plus-sign"></span></a>
                         </div>
                         <div class="page" id="pager">
                         <!--    <a href="">上一页</a>
                            <span>第2页，共3页</span>
                            <a href="">下一页</a>--> 
                         </div>
                    </div>
                </fieldset>
            </div>
        </div>
        <script>
        var questions = $('#j_questions tr:not(:first)');
        var PAGE_SIZE = 5;//每页个数
    	var totalNum = questions.length;//总个数
    	var pages = Math.ceil(totalNum/PAGE_SIZE);//总页数
        onload=function(){       
            var nowPage = 0;//当前页
            upPages(0);	
            deleteRemind();
            searchRemind();
        }
        function upPages(p){
        	var nowPage = p;
        	if(nowPage >= 0 && nowPage <= pages-1){
        		for(var i=0; i<totalNum; i++){
        			questions[i].style.display = "none";
        		}
        		for(var j=p*PAGE_SIZE;j<(p+1)*PAGE_SIZE;j++){
        			if(questions[j])
        				questions[j].style.display = "table-row";
        		}
        		strS = '<a href="###" onclick="upPages('+(nowPage-1)+')">上一页</a> ';
        		strE = '<a href="###" onclick="upPages('+(nowPage+1)+')">下一页</a> ';
        		strN = "<span>第"+(nowPage+1)+"页,共"+pages+"页</span>";
        		document.getElementById("pager").innerHTML=strS+strN+strE;
        	}
        }
        function deleteRemind(){
        	<%if(request.getParameter("delete") != null) {%>
        	    alert("删除成功！")
        	<%  } %>
        	
        }
        function searchRemind(){
        	<%if((request.getParameter("search")!=null)&&(Integer.parseInt(request.getParameter("search")) == 0)) {%>
        	    $('#j_questions').css('display','none');
        	    $('.add').before('<p style="padding-left:10px">很抱歉，没有相关问题</p>');
        	<%  } %>	
        }
        </script>
</body>
</html>
