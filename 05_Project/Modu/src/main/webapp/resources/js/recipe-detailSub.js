function clickScrap(e){
	let recipeId = $('.recipe-id').val();
	let loginUser; // �̸��� ����
	let formData = new FormData();
	formData.append("id", recipeId);
	
    if ($(e).hasClass('recipe-scrap')){ // ��ũ�� �߰�
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
    				console.log("�α��� ������ �����ϴ�.");
    	        	alert(response.error);
    			} else {
	    			$(e).removeClass('recipe-scrap');
    		        $(e).addClass('recipe-scrap-clicked');
    		        alert(response.msg);
    			}
    		},
    		error: function (response) {
    			console.log("�������� ��ſ� �����Ͽ����ϴ�.")
    		}
    	});
    } else { // ��ũ�� ����
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
    			console.log("�������� ��ſ� �����Ͽ����ϴ�.")
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
	alert(link.val() + "\n��ũ �ּҰ� ����Ǿ����ϴ�.");
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

function clickSubscribe(e){
	//������ ��� email,
	//�� �̸��� email
	let targetEmail = $(e).attr("data-email");
	console.log(targetEmail);
	let userEmail; // �̸��� ����
	let formData = new FormData();
	formData.append("targetEmail", targetEmail);
	
	 if ($(e).hasClass('subscribe-btn')){ // ģ���߰�(�ȷο�)
        $.ajax({
    		url: "/recipe/follow/insert.json",
    		type: "POST",
    		enctype: "multipart/form-data",
    		processData: false,
    		contentType: false,
    		data: formData,
    		success: function (response) {
    			userEmail = response.user;
    			if (userEmail === undefined){
    				console.log("ģ�� �߰� �� ������ �α��� �� �����մϴ�.");
    	        	alert(response.error);
    			} else {
    				$(e).removeClass('subscribe-btn');
			        $(e).addClass('subscribe-btn-clicked');
			        $(".subscribe-btn-clicked").text("ģ�� ����");
    		        alert(response.msg);
    			}
    		},
    		error: function (response) {
    			console.log("�������� ��ſ� �����Ͽ����ϴ�.")
    		}
    	});
    } else { // ģ���߰� ���(�ȷο� ���)
        $.ajax({
    		url: "/recipe/follow/delete.json",
    		type: "POST",
    		enctype: "multipart/form-data",
    		processData: false,
    		contentType: false,
    		data: formData,
    		success: function (response) {
    			userEmail = response.user;
    			if (userEmail === undefined){
    				console.log("ģ�� �߰� �� ������ �α��� �� �����մϴ�.");
    	        	alert(response.error);
    			} else {
    				$(e).removeClass('subscribe-btn-clicked');
    		        $(e).addClass('subscribe-btn');
    		        $(".subscribe-btn").text("ģ�� �߰�");
    		        alert(response.msg);
    			}
    		},
    		error: function (response) {
    			console.log("�������� ��ſ� �����Ͽ����ϴ�.")
    		}
    	});
    }
}

$(function(){
	let targetEmail = $(".subscribe-btn").attr("data-email");
	let userEmail; // �̸��� ����
	let formData = new FormData();
	formData.append("targetEmail", targetEmail);
	
	$.ajax({
		url: "/recipe/follow/check.json",
		type: "POST",
		enctype: "multipart/form-data",
		processData: false,
		contentType: false,
		data: formData,
		success: function (response) {
			userEmail = response.user;
			if (userEmail === undefined){
			} else {
				if (response.state === "true"){
			        $(".subscribe-btn").addClass('subscribe-btn-clicked');
			        $(".subscribe-btn").removeClass('subscribe-btn');
			        $(".subscribe-btn-clicked").text("ģ�� ����");
				} else {}
			}
		},
		error: function (response) {
			console.log("������ ��ſ� �����Ͽ����ϴ�.")
		}
	});
});