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
<title>新增赛车视频页面</title>
</head>
<body>
<%@include file="header.jsp"%>
     <div class="banner">
         <ol class="breadcrumb">
             <li class="active">题库</li>
             <li><a href="<%=request.getContextPath()+"/view/raceList"%>">赛车视频</a></li>
             <li class="active">新增视频</li>
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
            <%if((request.getParameter("edit")!=null)&&(Integer.parseInt(request.getParameter("edit")) == 1)) {
                	Race race = (Race)session.getAttribute("race"); 
                	int race_id = race.getId();
            %>
                <form action="saveRace" method="post" enctype="multipart/form-data">
                <div class="race-create-wrap clearfix">
                <input type="hidden" value="<%=race_id%>" name="race.id">
                <input type="hidden" value="<%=race.getContent()%>" name="videoName">
                <input type="hidden" value="<%=race.getTxt()%>" name="AngleFileName">
                <fieldset>
                    <legend>视频</legend>
                        <div class="r-content clearfix">
                            <span class="title">视频名称：</span>
                            <span><input type="text" name="race.name" value="<%=race.getName()%>"></span>
                            <p style="margin-top:10px"><EMBED src="<%=basePath %>upload/<%=race.getContent() %>" autostart=false width="600" height="400"></EMBED></p>  
                            <p class="video">
                                <input type="button" value="重新上传视频" class="my-button">
                                <input type="file" name="upload" class="my-button file j-file-value"/> 
                                <span class="file-value"></span>
                            </p>
                            <p class="video">
                                <input type="button" value="重新上传角度" class="my-button">
                                <input type="file" name="uploadAngle" class="my-button file j-file-value"/> 
                                <span class="file-value"></span>
                            </p>
                        </div>
                    <div class="save-cancel">
                        <input type="submit" value="保存修改" class="my-button">
                        <a class="cancel" href="<%=request.getContextPath()+"/view/showRaceDetail?race_id="+race_id%>">取消</a>
                    </div>
                </fieldset>
                </div>
                </form>
            <%} else {%>
                <form action="addRace" method="post" enctype="multipart/form-data">
                <div class="race-create-wrap clearfix">
                
                <fieldset>
                    <legend>视频</legend>
                        <div class="r-content clearfix">
                            <span class="title">视频名称：</span>
                            <span><input type="text" name="race.name"></span>
                            <p class="video">
                                <input type="button" value="上传视频" class="my-button">
                                <input type="file" name="upload" class="my-button file j-file-value"/> 
                                <span class="file-value"></span>
                            </p>
                            <p class="video">
                                <input type="button" value="上传角度" class="my-button">
                                <input type="file" name="uploadAngle" class="my-button file j-file-value"/> 
                                <span class="file-value"></span>
                            </p>
                        </div>
                    <div class="save-cancel">
                        <input type="submit" value="保存" class="my-button">
                        <input type="reset" value="重置" class="my-button">
                    </div>
                </fieldset>
                </div>
                </form>
            <%} %>
            </div>
       </div>
<script type="text/javascript">
    $(function() {
    	$('.j-file-value').on('change', function() {
    		var fileString = $(this).val();
    		var arr=fileString.split("\\");
    		var fileName = arr.pop();
    		$(this).next('.file-value').html(fileName);
    	})
    });
</script>
</body>
</html>