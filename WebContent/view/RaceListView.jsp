<%@page import="model.Race"%>
<%@page import="java.util.List"%>
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
<title>赛车视频页面</title>
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
        <div class="wrap">
            <div class="list">
                <fieldset>
                    <legend>视频列表</legend>
                    <table class="table table-striped" id="j_questions">
                        <thead>
                            <tr>
                                <th>编号</th>
                                <th>视频名称</th>
                                <th>时间(s)</th>
                                <th>查看</th>
                                <th>编辑</th>
                                <th>删除</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%if(request.getParameter("search") == null) {%>
	                        <%List<Race> raceList = (List<Race>)session.getAttribute("races");
	                            if(raceList != null){
	                        %>
	                        <%
	                            for(int i=0; i<raceList.size();i++){
	                            	Race race = raceList.get(i);
	                            	int race_id = race.getId();
	                            	String name = race.getName();
	                            	int duration = race.getDuration();
	                            
	                        %>
	                            <tr>
	                                <td><%=race_id %></td>
	                                <td><%=name %></td>
	                                <td><%=duration %></td>
	                                <td><a href="<%=request.getContextPath()+"/view/showRaceDetail?race_id="+race_id%>"><span class="glyphicon glyphicon-eye-open"></span></a></td>
	                                <td><a href="<%=request.getContextPath()+"/view/editRace?race_id="+race_id%>"><span class="glyphicon glyphicon-pencil"></span></a></td>
	                                <td><a href="<%=request.getContextPath()+"/view/deleteRace?race_id="+race_id%>"><span class="glyphicon glyphicon-remove"></span></a></td>
	                            </tr>
	                         <%} } else {%>
	                            <p>赛车视频列表为空</p>
	                        <%} %>
	                        
	                        <%} else if(Integer.parseInt(request.getParameter("search")) == 1){%>
                            <%List<Race> raceSearchList = (List<Race>)session.getAttribute("raceSearchList");%>
	                        <%
	                            for(int i=0; i<raceSearchList.size();i++){
	                            	Race race = raceSearchList.get(i);
	                            	int race_id = race.getId();
	                            	String name = race.getName();
	                            	int duration = race.getDuration();
	                            
	                        %>
	                            <tr>
	                                <td><%=race_id %></td>
	                                <td><%=name %></td>
	                                <td><%=duration %></td>
	                                <td><a><span class="glyphicon glyphicon-eye-open"></span></a></td>
	                                <td><a><span class="glyphicon glyphicon-pencil"></span></a></td>
	                                <td><a><span class="glyphicon glyphicon-remove"></span></a></td>
	                            </tr>
	                        <%} %>
                        <%} %>
                        </tbody>
                    </table>
                    <div class="bottom clearfix" id="j_bottom">
                         <div class="add">
                            <a href="RaceCreateView.jsp" data-toggle="tooltip" data-placement="right" title="新增赛车视频"><span class="glyphicon glyphicon-plus-sign"></span></a>
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
        	    $('.add').before('<p style="padding-left:10px">很抱歉，没有相关视频</p>');
        	<%  } %>	
        }
        </script>
</body>
</html>