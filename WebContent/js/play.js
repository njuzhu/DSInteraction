	window.onload = function(){
		cinema_fun();
		searchList();        
		
		$("#startPlay").unbind().bind('click',function(){
			fullscreen();
			if(playType == "选择题"){
				startTimer();
				loadQuestions();
			}else{
				loadRace();
			}
		});
		
	};
	
	//选择电影院
	function cinema_fun(){
		$("#cinema_ul li").unbind().bind('click',function(){
			var cinema_selected = $(this).children().text();
			var cinema_btn = $("#cinema_btn");
			var cname = encodeURIComponent(cinema_selected);
            
			$.get("/DSInteraction/view/findHalls",{cinemaName:cname},function(data){	
				$("#hall_ul").empty();
				$.each(JSON.parse(data),function(i,item){					
					$("#hall_ul").append("<li><a href='#'>" + item + "</a></li>");
				});
				
				cinema_btn.text(cinema_selected);				
				hall_fun();				
			});
			
		});
	}
	
	//选择电影厅
	function hall_fun(){
		$("#hall_ul li").unbind().bind('click',function(){
			
			var cname = encodeURIComponent($("#cinema_btn").text());
			var hall_selected = $(this).children().text();
			var hall_btn = $("#hall_btn");
			var hname = encodeURIComponent(hall_selected);
            
			$.get("/DSInteraction/view/findPeriods",{cinemaName:cname,hallName:hname},function(data){	
				$("#period_ul").empty();
				$.each(JSON.parse(data),function(i,item){					
					$("#period_ul").append("<li><a href='#'>" + item + "</a></li>");
				});
				
				hall_btn.text(hall_selected);
				period_fun();
			});
		});
	};

	//选择电影场次
	function period_fun(){
		$("#period_ul li").unbind().bind('click',function(){
			$("#period_btn").text($(this).children().text());
		});
	};
	
	//搜索播放单,同时初始化接下来要播放的数据
	var playData = [];
	var playType = "";
	function searchList(){
		$("#searchPlayList").unbind().bind('click',function(){
			if($.trim($("#cinema_btn").text()) == $.trim("选择电影院")){
				$("#cinema_btn").addClass("warning");
			}else if($.trim($("#hall_btn").text()) == $.trim("选择电影厅")){
				$("#hall_btn").addClass("warning");
			}else if($.trim($("#period_btn").text()) == $.trim("选择今日场次")){
				$("#period_btn").addClass("warning");
			}else{
				$("#cinema_btn").removeClass("warning");
				$("#hall_btn").removeClass("warning");
				$("#period_btn").removeClass("warning");
				
				var cname = encodeURIComponent($("#cinema_btn").text());
				var hname = encodeURIComponent($("#hall_btn").text());
				var pname = encodeURIComponent($("#period_btn").text());
	            
				$.get("/DSInteraction/view/findPlayList",{cinemaName:cname,hallName:hname,period:pname},function(data){
					$("#list_table").empty();
					playData = JSON.parse(data);
					console.debug(playData);
					$.each(JSON.parse(data),function(i,item){
						$("#list_table").append("<tr><td>" + item.number + "</td><td>" + item.type + "</td><td>" + item.keyword + "</td><td>" + item.formatDuration + "</td></tr>");
						playType = item.type;
					});
					$("#playList").show();
				});
					            
			}
			
        });
	}
	//***********************************************赛车视频部分***********************************************************
	//加载视频
	function loadRace(){
		$("body").empty();
		/*var ele = $("<div style='position:absolute;top:0px;left:0px;width:100%;height:100%;background-color:#EEE;z-index:1000'></div>"); */
		var ele = $("<div></div>");

		var left = $("<div id = 'left'><canvas id='tip' width='500' height='450'></canvas><div id = 'left2'><div class='rank_container'><div id='rank1' class='rank'><img class='top' src='../../DSInteraction/images/crown.png' /><img id='test' class='logo' src='../../DSInteraction/images/icon.jpg' /></div><div id='rank2' class='rank'><img class='rankNO' src='../../DSInteraction/images/2.png' /><img class='logo' src='../../DSInteraction/images/icon1.jpg' /></div><div id='rank3' class='rank'><img class='rankNO' src='../../DSInteraction/images/3.png' /><img class='logo' src='../../DSInteraction/images/icon2.jpg' /></div><div id='rank4' class='rank'><img class='rankNO' src='../../DSInteraction/images/4.png' /><img class='logo' src='../../DSInteraction/images/icon3.jpg' /></div><div id='rank5' class='rank'><img class='rankNO' src='../../DSInteraction/images/5.png' /><img class='logo' src='../../DSInteraction/images/icon4.jpg' /></div></div></div></div>");
		var right = $("<div id = 'right'><video id='raceVideo' src='../../DSInteraction/video/raceVideo.webm' autoplay='autoplay' height='100%'></video></div>") ;

		ele.append(left);
		ele.append(right);
		$("body").append(ele);

		initAngles();
    	setInterval(drawShape,100);
    	changeRank(1,"../../DSInteraction/images/icon1.jpg");

    	$("#raceVideo").unbind("onended").bind('ended',function(){
			exitFullscreen();
		});
	}
	
	//绘制矩形和全屏播放
	var angles = [];
	var index = 0;

	function drawShape(){
	    var canvas = document.getElementById('tip');
	 
	    if (canvas.getContext){
	 
	    	var ctx = canvas.getContext('2d');
	        ctx.beginPath();
	        ctx.translate(250,250);

	        ctx.clearRect(-250,-250,500,500);

	        var length = angles.length;
	        if(index < length){
	            ctx.rotate(angles[index] * Math.PI/180);
	            ++index;
	        }

	        ctx.fillStyle="#FF0000";       
	        ctx.fillRect(-150,-80,300,160);
	        ctx.clearRect(-140,-70,280,140);
	           
	        ctx.translate(-250,-250);
	    } else {
	        alert('You need Safari or Firefox 1.5+ to see this demo.');
	    }
	};

	function initAngles(){
		$.getJSON("../../DSInteraction/video/raceVideo_angles.json", function(data) {
			angles = data;
		});
	};

	function fullscreen(){  
	    elem=document.body;  
	    if(elem.webkitRequestFullScreen){  
	        elem.webkitRequestFullScreen(); 
	        $("body").css("width","100%")
	    }else if(elem.mozRequestFullScreen){  
	        elem.mozRequestFullScreen();  
	        $("body").css("width","100%")
	    }else if(elem.requestFullScreen){  
	        elem.requestFullscreen();  
	        $("body").css("width","100%")
	    }else{  
	        //浏览器不支持全屏API或已被禁用  
	    }  
	};  

	function exitFullscreen(){  
	    var elem=document;  
	    if(elem.webkitCancelFullScreen){  
	        elem.webkitCancelFullScreen();      
	    }else if(elem.mozCancelFullScreen){  
	        elem.mozCancelFullScreen();  
	    }else if(elem.cancelFullScreen){  
	        elem.cancelFullScreen();  
	    }else if(elem.exitFullscreen){  
	        elem.exitFullscreen();  
	    }else{  
	        //浏览器不支持全屏API或已被禁用  
	    }  
	};
	
	//*******************************************选择题部分***********************************************************
	var questions = ['which is baoma?','which is a'];
	var answers = [[['content','../../DSInteraction/images/icon.jpg'],['content','../../DSInteraction/images/icon.jpg'],['content','../../DSInteraction/images/icon.jpg'],['content','../../DSInteraction/images/icon.jpg']],[['content2','../../DSInteraction/images/icon4.jpg'],['content2b','../../DSInteraction/images/icon4.jpg'],['content2c','../../DSInteraction/images/icon4.jpg'],['content2d','../../DSInteraction/images/icon4.jpg']]];
	var right_answers = [3,0];    //A...B & 0...3
	var durations = [11,15];
	var current = 0;

	function loadQuestions(){
		$("body").empty();

		var question_div = "<div class='question'><h1>" + questions[current] + "</h1></div>";
		var ans_top_div = "<div class='answer clearfix'><div class='answer_l'><p><label>A.</label>" + isImage(answers[current][0][1]) + "</p><p class='ans_content a_img'>" + answers[current][0][0] + "</p><input class='tag' type='hidden' value='1' /></div><div class='answer_r'><p><label>B.</label>" + isImage(answers[current][1][1]) + "</p><p class='ans_content a_img'>" + answers[current][1][0] + "</p><input class='tag' type='hidden' value='2' /></div></div>";
		var ans_bottom_div = "<div class='answer clearfix mt'><div class='answer_l'><p><label>C.</label>" + isImage(answers[current][2][1]) + "</p><p class='ans_content a_img'>" + answers[current][2][0] + "</p><input class='tag' type='hidden' value='3' /></div><div class='answer_r'><p><label>D.</label>" + isImage(answers[current][3][1]) + "</p><p class='ans_content a_img'>" + answers[current][3][0] + "</p><input class='tag' type='hidden' value='4' /></div></div>";
		var timer_div = "<div class='timer'><span></span></div>";

		$("body").append(question_div);
		$("body").append(ans_top_div);
		$("body").append(ans_bottom_div);
		$("body").append(timer_div);
	}

	function isImage(value){

		if(value != null){
		 	return "<img class='ans_img' src='" + value + "'>";
		}else{
			return "";
		}
	}

	//清除上一题的正确答案,清除图片
	function clear(){
		$(".answer label").removeClass("true_ans");
		$(".ans_img").remove();
	}

	//显示正确答案,清楚上题痕迹，显示答案后切换至下一题
	function showTrueAnswer(){
		var true_ans_tag = right_answers[current];
		$(".tag:eq(" + true_ans_tag + ")").parent().children().find("label").addClass("true_ans");

		setTimeout("clear()",1000);
		setTimeout("updateQuestion()",1000);

	}

	//更改题目及选项
	function updateQuestion(){
		current++;

		var question_content = questions[current];
		var answers_content = answers[current];

		$(".question h1").html(question_content);
		$.each(answers_content,function(i,value){
			$(".ans_content:eq(" + i + ")").html(value[0]);
			if(value[1] != null){
				$(".ans_content:eq(" + i + ")").prev("p").append("<img src='" + value[1] + "' />");
			}		
		});

		//重定计时器
		startTimer();
	}

	var time = 0;
	var timer1;

	//定时器开启
	function startTimer(){
		setTimer();
		timer1 = setInterval("timer()",1000);
	}

	function setTimer(){
		time = durations[current];
	}

	function timer(){	
		if(time > 0){
			$(".timer span").html(time);
			time--;		
		}else{
			$(".timer span").html(0);
			clearInterval(timer1);
			showTrueAnswer();		
		}
	}
	
	//**************************************************排名部分***************************************************
	//现逻辑：更新排名时，逐个名次比较，该名次用户是否改变。若改变，原图片渐隐，更改图片后，新图片渐现。
	//每隔1秒，更新一次排名。
	var interval1, interval2; 
	var usr_rank;
	var new_usr_img;

	//新图片渐现
	function show()  
	{  
	    if(interval2) {  
	         //这里是为了当鼠标在Div渐隐的过程中移到Div上图片立即慢慢重现  
	        clearInterval(interval2);  
	    }  
	    i = $(usr_rank).css("opacity")*100;  
	    interval1 = setInterval("showRound()",20);  
	};  

	function showRound()  
	{  
	    i++;  
	    var usr_logo = $(usr_rank);  

	    if(usr_logo.css("opacity") != 1.0) {  
	        usr_logo.css("opacity",i/100);
	    } else {  
	        if(interval1) {  
	            clearInterval(interval1);  
	        }  
	    }  
	} 

	//原图片渐隐 
	function hide()  
	{  
	    if(interval1) {  
	         //这里是为了当鼠标在Div渐现的过程中从Div上移走图片立即慢慢消失  
	        clearInterval(interval1);  
	    }  
	    j = $(usr_rank).css("opacity")*100;  
	    interval2 = setInterval("hideRound()",10);  
	}; 

	function hideRound()  
	{  
	    j--; 
	    var usr_logo = $(usr_rank);  

	    if(usr_logo.css("opacity") != 0.0) {  
	        usr_logo.css("opacity",j/100);
	    } else {  
	        if(interval2) {  
	            clearInterval(interval2);  
	            $(usr_rank).attr("src",new_usr_img);
	            show();
	        }  
	    } 
	};  

	function readData(){

	}

	//排名修改，参数为名次，用户头像
	function changeRank(rank,user_img){
	    usr_rank = '#rank' + rank + ">.logo";
	    new_usr_img = user_img;
	    var usr_old = $(usr_rank).attr("src");

	    if(usr_old != user_img){
	        hide();
	        if(interval2){
	            
	        }        
	    }
	    //$("" + ele + ">.logo").attr("src",user_img);
	}