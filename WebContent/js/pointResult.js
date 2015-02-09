window.onload = function(){
	searchUsr();
	initPoint();
	minus();
	plus();
	exchange();
};

function searchUsr(){
	$(".search_usr").unbind().bind('click',function(){
		var keyword = $('input',$(this).parent()).val();
		$("#findUsers input").val(keyword);
		$("#findUsers").submit();
	});
};

function initPoint(){
	$(".point_val").each(function(i){
		var point_val = $('span',$(this).parent().prev()).text();
		$(this).val(point_val);
	});
	$('.plus').attr("disabled","true");
};

function minus(){
	$(".minus").unbind().bind('click',function(){
		var point = $(this).next().val();
		if(point > 0){
			--point;
			$(this).next().next().removeAttr("disabled");
		}else{
			$(this).attr("disabled","true");
		}		

		if(point == 0){
			$(this).attr("disabled","true");	
		}

		$(this).next().val(point);
	});
};

function plus(){
	$(".plus").unbind().bind('click',function(){
		var point = $(this).prev().val();
		if(point < $('span',$(this).parent().prev()).text()){
			++point;
			$(this).prev().prev().removeAttr("disabled");
		}else{
			$(this).attr("disabled","true");
		}

		if(point == $('span',$(this).parent().prev()).text()){
			$(this).attr("disabled","true");	
		}

		$(this).prev().val(point);
	});
};

function exchange(){
	$(".change_btn").unbind().bind('click',function(){
		var before = new Number($("span",$(this).parent().prev()).text());
		var change = $("input",$(this).parent()).val();
		var self = $(this);
		
		if(change <= before){
			var after = before - change;
			var id = $(this).next().val();
			
			$.get("/DSInteraction/view/updatePoint",{id:id,point:after},function(data){
				if(data){
					$("span",self.parent().prev()).text(after);
					$("input",self.parent()).val(after);
					$("#tip",self.parent().parent()).remove();
					self.parent().append("<span id='tip'>兑换成功！</span>");
				}
			});
		}else{
			$("#tip",$(this).parent().parent()).remove();
			$(this).parent().append("<span id='tip'>兑换值不能大于原积分！</span>");
		}
	});
};