// 페이지 이벤트
function activePage(e){
    $('.page-number').removeClass('active');
    $(e).addClass('active')
}

// 윈도우 특정 크기 이상일때 서브메뉴창 자동 접기
$(window).resize(function(){
	let windowSize = $(window).width();
	let subMenuBar = $('#navbarSupportedContent');
	
	if (windowSize >= 992 && subMenuBar.hasClass("show")){
		console.log(windowSize);
		subMenuBar.removeClass("show");
	}
});

let recentRecipe = new Array();
function saveCookie(id){
	if(recentRecipe.length == 0){
		recentRecipe.unshift(id);
	}
	if(recentRecipe.includes(id) == false){	
			recentRecipe.unshift(id);
	}	
	if(recentRecipe.length>5){
		recentRecipe.length = 5;	
	}
	document.cookie = "recipe="+recentRecipe;		
	window.location.href="/recipe/detail?no="+id;
}
