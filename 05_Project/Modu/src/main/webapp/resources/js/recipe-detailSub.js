function clickScrap(e){
	let recipeId = $('.recipe-id').val();
	let loginUser; // 이메일 정보
	let formData = new FormData();
	formData.append("id", recipeId);
	
    if ($(e).hasClass('recipe-scrap')){ // 스크랩 추가
        $.ajax({
    		url: "/recipe/scrap/insert.json",
    		type: "POST",
    		enctype: "multipart/form-data",
    		processData: false,
    		contentType: false,
    		data: formData,
    		success: function (response) {
    			loginUser = response.user;
    			if (loginUser === undefined){
    				console.log("로그인 정보가 없습니다.");
    	        	alert(response.error);
    			} else {
	    			$(e).removeClass('recipe-scrap');
    		        $(e).addClass('recipe-scrap-clicked');
    		        alert(response.msg);
    			}
    		},
    		error: function (response) {
    			console.log("서버와 통신에 실패하였습니다.")
    		}
    	});
    } else { // 스크랩 해제
    	$.ajax({
    		url: "/recipe/scrap/delete.json",
    		type: "POST",
    		enctype: "multipart/form-data",
    		processData: false,
    		contentType: false,
    		data: formData,
    		success: function (response) {
    			loginUser = response.user;
    			if (loginUser === undefined){
    	        	alert(response.error);
    			} else {
	    			$(e).removeClass('recipe-scrap-clicked');
	    	        $(e).addClass('recipe-scrap');
	    	        alert(response.msg);
    			}
    		},
    		error: function (response) {
    			console.log("서버와 통신에 실패하였습니다.")
    		}
    	}); 
    }
}

function shareLink(e){
	let link = $("#recipe-link");
	link.attr("type", "text");
	link.select();
	let copy = document.execCommand('copy');
	link.attr("type", "hidden");
	alert(link.val() + "\n링크 주소가 복사되었습니다.");
}

function moveScroll(e){
	let target;
	if ($(e).attr("class", "recipe-reply-top")){
		target = $(".tag-section");
	} else {
		target = $(e);
	}
	window.scrollTo({ top: target.offset().top - 50, behavior: "smooth" });
}