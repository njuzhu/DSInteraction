	window.onload = function(){
		cinema_fun();
		searchList();        
		
		$("#startPlay").unbind().bind('click',function(){
			fullscreen();
			$("body").empty();
			/*var ele = $("<div style='position:absolute;top:0px;left:0px;width:100%;height:100%;background-color:#EEE;z-index:1000'></div>"); */
			var ele = $("<div></div>");

			var left = $("<div id = 'left'><canvas id='tip' width='250' height='250'></canvas><div id = 'left2'><div class='rank'><h1>实时排名</h1><ul class='th'><li>排名</li><li>用户名</li><li>得分</li></ul><ul><li>1</li><li>赵默笙</li><li>13</li></ul><ul><li>2</li><li>何以琛</li><li>12</li></ul><ul><li>3</li><li>萧筱</li><li>11</li></ul><ul><li>4</li><li>路行风</li><li>10</li></ul><ul><li>5</li><li>应晖</li><li>9</li></ul></div></div></div>");
			var right = $("<div id = 'right'><video id='raceVideo' src='../video/raceVideo.webm' autoplay='autoplay' height='100%'></video></div>") ;

			ele.append(left);
			ele.append(right);
			$("body").append(ele);

			initAngles();
	    	setInterval(drawShape,1000);

	    	$("#raceVideo").unbind("onended").bind('ended',function(){
				exitFullscreen();
			});
		});
		
	};
	
	//选择电影院
	function cinema_fun(){
		$("#cinema_ul li").unbind().bind('click',function(){
			var cinema_selected = $(this).children().text();
			var cinema_btn = $("#cinema_btn");
			var cname = encodeURIComponent(cinema_selected);
            
			$.get("/DSInteraction/dsinteraction/findHalls",{cinemaName:cname},function(data){	
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
            
			$.get("/DSInteraction/dsinteraction/findPeriods",{cinemaName:cname,hallName:hname},function(data){	
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
	
	//搜索播放单
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
	            
				$.get("/DSInteraction/dsinteraction/findPlayList",{cinemaName:cname,hallName:hname,period:pname},function(data){
					$("#list_table").empty();
					$.each(JSON.parse(data),function(i,item){
						$("#list_table").append("<tr><td>" + item.number + "</td><td>" + item.type + "</td><td>" + item.keyword + "</td><td>" + item.duration + "</td></tr>");
					});
					$("#playList").show();
				});
					            
			}
			
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
	        ctx.translate(125,125);

	        ctx.clearRect(-125,-125,250,250);

	        var length = angles.length;
	        if(index < length){
	            ctx.rotate(angles[index] * Math.PI/180);
	            ++index;
	        }

	        ctx.fillStyle="#FF0000";       
	        ctx.fillRect(-75,-40,150,80);
	        ctx.clearRect(-70,-35,140,70);
	           
	        ctx.translate(-125,-125);
	    } else {
	        alert('You need Safari or Firefox 1.5+ to see this demo.');
	    }
	};

	function initAngles(){
		$.getJSON("。。/../../video/raceVideo_angles.json", function(data) {
			angles = data;
		});
	};

	function fullscreen(){  
	    elem=document.body;  
	    if(elem.webkitRequestFullScreen){  
	        elem.webkitRequestFullScreen();     
	    }else if(elem.mozRequestFullScreen){  
	        elem.mozRequestFullScreen();  
	    }else if(elem.requestFullScreen){  
	        elem.requestFullscreen();  
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