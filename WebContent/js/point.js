window.onload = function(){
	searchUser();
};

function searchUser(){
	$(".search_usr").unbind().bind('click',function(){
		var keyword = $('input',$(this).parent()).val();
		$("#findUsers input").val(keyword);
		$("#findUsers").submit();
	});
}