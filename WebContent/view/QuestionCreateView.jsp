<%@page import="model.Question"%>
<%@page import="model.Answer"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*"%>
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
<link rel="stylesheet" type="text/css" href="../css/question.css">
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<title>选择题新建页面</title>
</head>
<body>
 <%@include file="header.jsp"%>
        <div class="banner">
            <ol class="breadcrumb">
                <li class="active">题库</li>
                <li><a href="<%=request.getContextPath()+"/view/questionList"%>">选择题</a></li>
                <li class="active">新建选择题</li>
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
                <div class="create_wrap clearfix">
                <%if((request.getParameter("edit")!=null)&&(Integer.parseInt(request.getParameter("edit")) == 1)) {
                	Question question = (Question)session.getAttribute("question"); 
                	List<Answer> answers = (List<Answer>)session.getAttribute("answers");
                	int question_id = question.getId();
                	request.setAttribute("uploadFileName", question.getUpload());
                %>
                <%//编辑页面 start %>
                <form action="saveQuestion" method="post" enctype="multipart/form-data">
                <input type="hidden" value="<%=question_id%>" name="question.id">
                <fieldset>
                    <legend>问题</legend>
                        <div class="q-content clearfix">
                            <div class="fl">
                                <span class="title">题目：</span>
                                <span>
                                    <textarea style="width: 509px;height:100px" name="question.content" required="required"><%=question.getContent()%>
                                    </textarea>
                                </span>
                            </div>
                            <div class="fr">
                                <p>
                                    <span class="title">题目名称：</span>
                                    <span><input type="text" name="question.keyword" required="required" value="<%=question.getKeyword()%>"/></span>
                                </p>
                                <p>
                                    <span class="title">播放时长：</span>
                                    <span><input type="text" class="my-text" name="question.duration" required="required" value="<%=question.getDuration()%>"/></span>
                                    <span>s</span>
                                </p>
                            </div>
                            <div class="file-box">
                                 <input type="button" value="上传图片" class="my-button photo">
                                 <input type="file" name="upload" class="file" onchange="viewQimg(this)"/> 
                                 <div id="sig_preview" class="img-preview">
                                 <%if(question.getUpload() != null){ %>
                                     <img id=imghead border=0 width=138 height=100 src="<%=basePath %>upload/<%=question.getUpload() %>">
                                     <%} %>
                                 </div>
                                    
                                 <!--  <input type="button" value="上传视频" class="my-button video" name="video">-->
                             </div>
                        </div>
                </fieldset>
                <fieldset style="margin-top:30px">
                    <legend>答案</legend>
                        <%for(int i=0; i<answers.size(); i++){   
                            Answer answer = answers.get(i); 
                            String choice = answer.getContent().substring(0,1);
                            int answer_id = answer.getId();
                            request.setAttribute("upload"+answer.getContent().substring(0,1)+"FileName", answer.getUpload());
                            %>
                        <input type="hidden" name="answer<%=choice %>.id" value="<%=answer_id %>">
                        <div class="each-answer">
                            <input type="text" name="answer<%=choice %>.content" readonly="true" value="<%=answer.getContent().substring(0,2) %>" class="choice"/>
                            <textarea style="width: 507px;height:60px" name="answer<%=choice %>.content"><%=answer.getContent().substring(2) %></textarea>
                            <div class="right-ans">
                                <input type="radio" name="radio" value="answer<%=choice %>" 
                                <%if(answer.getIsRight() == 1){ %>checked <%} %>>正确答案
                            </div>
                            <div class="picture">
                                <input type="button" value="上传图片" class="my-button">
                            </div>
                            <div class="file">
                                <input type="file" class="my-button j-anw-img" name="upload<%=choice %>"/> 
                            </div>
                            <div class="img-preview">
                                <%if(answer.getUpload() != null){ %>
                                    <img class="imghead" border=0 width=90 height=60 src="<%=basePath %>upload/<%=answer.getUpload() %>">
                                <%} %>
                            </div>
                        </div>
                        <%} %>
                </fieldset>
                <div class="save-cancel">
                    <input type="submit" value="保存修改" class="my-button">
                    <a class="cancel" href="<%=request.getContextPath()+"/view/questionList"%>">取消</a>
                </div>
                </form>
                <%//编辑页面 end %>
                <%} else {%>
                <form action="addQuestion" method="post" enctype="multipart/form-data">
                <fieldset>
                    <legend>问题</legend>
                        <div class="q-content clearfix">
                            <div class="fl">
                                <span class="title">题目：</span>
                                <span><textarea style="width: 509px;height:100px" name="question.content" required="required"></textarea></span>
                            </div>
                            <div class="fr">
                                <p>
                                    <span class="title">题目名称：</span>
                                    <span><input type="text" name="question.keyword" required="required"/></span>
                                </p>
                                <p>
                                    <span class="title">播放时长：</span>
                                    <span><input type="text" class="my-text" name="question.duration" required="required"/></span>
                                    <span>s</span>
                                </p>
                            </div>
                            <div class="file-box">
                                 <input type="button" value="上传图片" class="my-button photo">
                                 <input type="file" name="upload" class="file" onchange="viewQimg(this)" /> 
                                 <div id="sig_preview" class="img-preview">
                                     <p style="text-align:center">文件预览区</p>
                                 </div>
                                    
                                 <!--  <input type="button" value="上传视频" class="my-button video" name="video">-->
                             </div>
                        </div>
                </fieldset>
                <fieldset style="margin-top:30px">
                    <legend>答案</legend>
                        <div class="each-answer">
                            <input type="text" name="answerA.content" readonly="true" value="A：" class="choice"/>
                            <textarea style="width: 507px;height:60px" name="answerA.content"></textarea>
                            <div class="right-ans">
                                <input type="radio" name="radio" value="answerA" checked>正确答案
                            </div>
                            <div class="picture">
                                <input type="button" value="上传图片" class="my-button">
                            </div>
                            <div class="file">
                                <input type="file" class="my-button j-anw-img" name="uploadA"/> 
                            </div>
                            <div class="img-preview">
                                
                            </div>
                        </div>
                        <div class="each-answer">
                            <input type="text" name="answerB.content" readonly="true" value="B：" class="choice"/>
                            <textarea style="width: 507px;height:60px" name="answerB.content"></textarea>
                            <div class="right-ans">
                                <input type="radio" name="radio" value="answerB">正确答案
                            </div>
                            <div class="picture">
                                <input type="button" value="上传图片" class="my-button">
                            </div>
                            <div class="file">
                                <input type="file" class="my-button j-anw-img" name="uploadB"/> 
                            </div>
                            <div class="img-preview">
                                
                            </div>
                        </div>
                        <div class="each-answer">
                            <input type="text" name="answerC.content" readonly="true" value="C：" class="choice"/>
                            <textarea style="width: 507px;height:60px" name="answerC.content"></textarea>
                            <div class="right-ans">
                                <input type="radio" name="radio" value="answerC">正确答案
                            </div>
                            <div class="picture">
                                <input type="button" value="上传图片" class="my-button">
                            </div>
                            <div class="file">
                                <input type="file" class="my-button j-anw-img" name="uploadC"/> 
                            </div>
                            <div class="img-preview">
                                
                            </div>
                        </div>
                        <div class="each-answer">
                            <input type="text" name="answerD.content" readonly="true" value="D：" class="choice"/>
                            <textarea style="width: 507px;height:60px" name="answerD.content"></textarea>
                            <div class="right-ans">
                                <input type="radio" name="radio" value="answerD">正确答案
                            </div>
                            <div class="picture">
                                <input type="button" value="上传图片" class="my-button">
                            </div>
                            <div class="file">
                                <input type="file" class="my-button j-anw-img" name="uploadD"/> 
                            </div>
                            <div class="img-preview">
                                
                            </div>
                        </div>
                </fieldset>
                <div class="save-cancel">
                    <input type="submit" value="保存" class="my-button">
                    <input type="reset" value="重置" class="my-button">
                    <a href="<%=request.getContextPath()+"/view/questionList"%>" class="cancel">返回</a>
                </div>
                </form>
                <%} %>
                </div>
            </div>
        </div>
<script type="text/javascript"> 
  // 显示图片的js 
function viewQimg(file){ 
	var div = document.getElementById('sig_preview');
	if (file.files && file.files[0]) {
		div.innerHTML ='<img id=imghead border=0 width=138 height=100>';
        var img = document.getElementById('imghead');
        var reader = new FileReader();
        reader.readAsDataURL(file.files[0]);
        reader.onload = function(evt){
        	img.src = evt.target.result;
        	//alert(file.files[0].name+file.files[0].size+file.files[0].type);
        }
	}else {//兼容IE
        var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text;
        div.innerHTML = '<img id=imghead border=0 width=138 height=100>';
        var img = document.getElementById('imghead');
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
      }
  }
$(function() {
	var $anwImg = $('.j-anw-img');
	$anwImg.change(function() {
		var $imgPreview = $(this).parent().next();
		var $files = $(this).prop('files');
		if($files.lenght != 0 && $files[0]){
			$imgPreview.html('<img class="imghead" border=0 width=90 height=60>');
			var reader = new FileReader();
	        reader.onload = function(evt){$imgPreview.find('.imghead').attr('src',evt.target.result);}
	        reader.readAsDataURL($files[0]);
		}
	});
	function getRadioValue(){
		$('input:radio').each(function(){
			//alert($(this).val());
		});
	}
	
	function cancelImg() {
		$('input:reset').bind('click', function() {
			$('img').remove();
		});
	}
	
	function init() {
		getRadioValue();
		cancelImg();
	}
	init();
});
/*function viewAimg(file){ 
	var div = $('.img-preview');
	if (file.files && file.files[0]) {
		div.innerHTML ='<img id=imghead border=0 width=90 height=60>';
        var img = document.getElementById('imghead');
        var reader = new FileReader();
        reader.onload = function(evt){img.src = evt.target.result;}
        reader.readAsDataURL(file.files[0]);
        alert(file.files[0].name+file.files[0].size+file.files[0].type);
	}else {//兼容IE
        var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text;
        div.innerHTML = '<img id=imghead border=0 width=90 height=60>';
        var img = document.getElementById('imghead');
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
      }
  }*/
</script>
</body>
</html>
