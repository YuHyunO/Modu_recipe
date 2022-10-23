function clickScrap(e){
	/*
	 1. 로그인 세션이 있는지 없는지 확인
	 2. 로그인 세션이 없는 경우 -> 로그인 후 이용
	 3. 로그인 세션이 있는 경우
	   1) 이메일 변수 저장
	   2) 레시피 아이디 변수 저장
	   3) 레시피 아이디를 기준으로 스크랩 테이블 조회
	     a) 스크랩 테이블에 해당 이메일이 없을 경우 
	      -> 이메일 추가
	     b) 해당 이메일이 있을 경우 
	      -> 이메일 제거
	*/
	let recipeId = $('.recipe-id').val();
	let loginUser;
	let formData = new FormData();
	formData.append("id", recipeId);
	
	// 스크랩 추가
    if ($(e).hasClass('recipe-scrap')){
        $.ajax({
    		url: "/recipe/scrap.json",
    		type: "POST",
    		enctype: "multipart/form-data",
    		processData: false,
    		contentType: false,
    		data: formData,
    		success: function (response) {
    			if (loginUser === undefined){
    	        	alert(response.error);
    			} else {
	    			loginUser = response.user; // 이메일 변수 저장
	    			console.log("login User: " + loginUser);
	    			$(e).removeClass('recipe-scrap');
    		        $(e).addClass('recipe-scrap-clicked');
    		        console.log("스크랩이 추가 되었습니다.");
    			}
    		},
    		error: function (response) {
    			console.log("서버와 통신에 실패하였습니다.")
    		}
    	});
    } else { // 스크랩 해제
        $(e).removeClass('recipe-scrap-clicked');
        $(e).addClass('recipe-scrap');
        console.log("스크랩이 해제 되었습니다.");
    }
}