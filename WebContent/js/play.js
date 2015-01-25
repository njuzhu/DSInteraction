	window.onload = function(){
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
	}  