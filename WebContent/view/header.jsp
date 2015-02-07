<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="header">
            <div class="left-logo">
                <img src="../images/logo.png">
            </div>
            <div class="right-nav">
                <ul id="nav"> 
                    <li><a href="RewardListView.jsp"><span class="glyphicon glyphicon-gift"></span>奖励</a> 
                    </li> 
                    <li><a><span class="glyphicon glyphicon-question-sign"></span>题库</a> 
                        <ul> 
                            <li><a href="<%=request.getContextPath()+"/view/questionList"%>">选择题</a></li>  
                            <li><a href="RacingListView.jsp">赛车视频</a></li> 
                        </ul> 
                    </li> 
                    <li><a><span class="glyphicon glyphicon-cog"></span>管理</a> 
                        <ul> 
                            <li><a href="PlayListView.jsp">播放列表管理</a></li> 
                            <li><a href="PointListView.jsp">积分管理</a></li> 
                            <li><a href="CinemaListView.jsp">电影院管理</a></li> 
                        </ul> 
                    </li> 
                    <li>
                    	<a href="<%=request.getContextPath()+"/view/findCinemas"%>"  class="no-border"><span class="glyphicon glyphicon-film"></span>播放</a>
                    </li> 
                </ul> 
                <div class="login">
                    <a href="login.jsp">退出</a>
                </div>
            </div>
</div>
