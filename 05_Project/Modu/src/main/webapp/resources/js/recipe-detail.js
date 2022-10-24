function clickSubscribe(e){
    //let request = new XMLHttpRequest();
    if ($(e).hasClass('subscribe-btn')){
        $(e).removeClass('subscribe-btn');
        $(e).addClass('subscribe-btn-clicked');
        //request.open("GET", "?res_name={{name}}");
        //request.send();
    } else {
        $(e).removeClass('subscribe-btn-clicked');
        $(e).addClass('subscribe-btn');
        //request.open("GET", "?res_name={{name}}-rm");
        //request.send();
    }
}

/*function clickScrap(e){
    //let request = new XMLHttpRequest();
    if ($(e).hasClass('recipe-scrap')){
        $(e).removeClass('recipe-scrap');
        $(e).addClass('recipe-scrap-clicked');
        //request.open("GET", "?res_name={{name}}");
        //request.send();
    } else {
        $(e).removeClass('recipe-scrap-clicked');
        $(e).addClass('recipe-scrap');
        //request.open("GET", "?res_name={{name}}-rm");
        //request.send();
    }
}*/

let star_point = 0;

function clickStar(e){
    let current_star = $(e).attr('id');
    let star_num = parseInt(current_star.slice(-1, 5));
    let star1 = $('#star1');
    let star2 = $('#star2');
    let star3 = $('#star3');
    let star4 = $('#star4');
    let star5 = $('#star5');
    let star_list = [star1, star2, star3, star4, star5];
    let num;
    switch(star_num){
        case 1:
            if(star_point > 1){
                $(e).addClass('star-clicked');
                star_point = 1;
            } else {
                if($(e).hasClass('star-clicked')){
                    $(e).removeClass('star-clicked');
                    star_point = 0;
                } else {
                    $(e).addClass('star-clicked');
                    star_point = 1;
                }
            }     
            break;
        case 2:
            $('#star1').addClass('star-clicked');
            $(e).addClass('star-clicked');
            star_point = 2;
            break;
        case 3:
            $('#star1').addClass('star-clicked');
            $('#star2').addClass('star-clicked');
            $(e).addClass('star-clicked');
            star_point = 3;
            break;
        case 4:
            $('#star1').addClass('star-clicked');
            $('#star2').addClass('star-clicked');
            $('#star3').addClass('star-clicked');
            $(e).addClass('star-clicked');
            star_point = 4;
            break;
        case 5:
            $('#star1').addClass('star-clicked');
            $('#star2').addClass('star-clicked');
            $('#star3').addClass('star-clicked');
            $('#star4').addClass('star-clicked');
            $(e).addClass('star-clicked');
            star_point = 5;
            break;
    }

    for(num = star_num; num < 5; num++){
        star_list[num].removeClass('star-clicked');
    }
    //console.log(star_point);
}

function viewLargePic(url){
    let html = '<div class="text-center"> <img src="'+ url +'" style="width:100%;"> </div>';
    $("#viewPicModalCont").html(html);
    $("#viewPicDivModal").modal("show");
}
    
function addReply(e){
    // 작성자, 작성자 사진은 세션이용해서 추가
    event.preventDefault();
    let commentID = $(e).attr('id').split('-')[2] + "-" + $(e).attr('id').split('-')[3];
    let targetCommentID = "#comment-" + commentID;
    
    //let test = $("#nested-reply").val();
    //console.log(test);
    
    let text = $(e).find('textarea[name=reply]');
    let lines = text.val().split("\n");
    let reviewText = '';
    for (let i = 0; i < lines.length; i++) {
        reviewText += lines[i] + "<br />";
    }
    let time = new Date();
    let month = String(time.getMonth() + 1);
    let date = String(time.getDate());
    let hours = String(time.getHours());
    let minutes = String(time.getMinutes());
    let seconds = String(time.getSeconds());
    
    text.val('');

    if(month.length === 1){
        month = "0" + month;
    }
    if(date.length === 1){
        date = "0" + date;
    }
    if(hours.length === 1){
        hours = "0" + hours;
    }
    if(minutes.length === 1){
        minutes = "0" + minutes;
    }
    if(seconds.length === 1){
        seconds = "0" + seconds;
    }

    let dateText = month + "-" + date +
    " " + hours + ":" + minutes;

    let starHtml = '';

    if(star_point === 0){
    } else {
        starHtml = '<img class="star-rate-img2" src="/imgs/stars' 
        + star_point + '.png" alt="stars" style="width:80px; height:15px; margin-bottom:5px;">';
    }
    let htmlMain = 
    '<li class="comment" id="comment-135000-0001"><!-- start comment -->\
        <div class="comment-body">\
            <div class="comment-meta d-flex justify-content-between align-items-center">\
                <span class="d-flex align-items-center">\
                    <figure class="comment-author">\
                        <img src="/imgs/content/auth-00.png" alt="작성자">\
                    </figure><!-- end comment-author vcard -->\
                    <b class="fn px-2">Jessica</b>\
                    <span class="px-2">' + dateText + '</span>\
                    <span>' + starHtml + '</span>\
                </span>\
                <span class="reply-btn px-2">\
                <span class="reply-btn px-2">\
                    <button class="reply-' + commentID + ' reply-btn" onclick="">수정</a>\
                    <button class="reply-' + commentID + ' reply-btn" onclick="">삭제</a>\
                    <button class="reply-' + commentID + ' reply-btn" onclick="addReplyForm(this)">답글</a>\
            </span>\
                </span>\
            </div><!-- end comment-meta -->\
            <div class="comment-content d-flex">\
                <p class="p-2 m-0 col-9">\
                    '+ reviewText + '</p>\
                <figure class="comment-image">\
                    <img class="rounded-3" src="/imgs/content/dessert-l.png" alt="comment-image">\
                </figure><!-- end comment-author vcard -->\
            </div><!-- end comment-content -->\
        </div><!-- end comment-body -->\
    </li><!-- end li -->\
    <ol class="re-comment px-0" id="recomment-' + commentID + '"></ol>';

    let htmlSub = 
    '<li class="comment row">\
        <div class="col" style="max-width: 40px;">\
            <img src="/imgs/reply-arrow.png" alt="화살표">\
        </div>\
        <div class="comment-body col">\
            <div class="comment-meta d-flex justify-content-between align-items-center">\
                <span class="d-flex align-items-center">\
                    <figure class="comment-author">\
                        <img src="/imgs/content/auth-03.png" alt="작성자">\
                    </figure><!-- end comment-author vcard -->\
                    <b class="fn px-2">Dina Makulatuwa</b>\
                    <span class="px-2">' + dateText + '</span>\
                </span>\
                <span class="reply-btn px-2">\
                    <button class="reply-' + commentID + ' reply-btn" onclick="">수정</a>\
                    <button class="reply-' + commentID + ' reply-btn" onclick="">삭제</a>\
                    <button class="reply-' + commentID + ' reply-btn" onclick="addReplyForm(this)">답글</a>\
                </span>\
            </div><!-- end comment-meta -->\
            <div class="comment-content">\
                <p class="p-2 m-0">'
                    + reviewText +'</p>\
            </div><!-- end comment-content -->\
        </div><!-- end comment-body -->\
    </li><!-- end comment -->';
    
    if(targetCommentID === "#comment-0-0"){
        // console.log("메인댓글");
        $(targetCommentID).prepend(htmlMain);
        console.log(targetCommentID);
        $(e).parents('.reply-write').html('');
    }else {
        //console.log("대댓글");
        //console.log(targetCommentID);      
    	//let reply = $("#nested-reply").val();
    	let reply = document.getElementById("nested-reply");
		let rreplyForm = $('#reply-form-135000-0001')
		let info = rreplyForm.serializeArray();		
		console.log(reply);
    }
}

function addReplyForm(e){    
	console.log("e:" +e)
	
    let commentID = $(e).attr("class").split(' ')[0];
    console.log("commentId: "+commentID);
  
    
    let targetCommentID = "#comment-" + commentID.split('-')[1] + "-" + commentID.split('-')[2];
   
    let replyFormIDValue = "reply-form-" + commentID.split('-')[1] + "-" + commentID.split('-')[2];
    console.log("replyFormIDValue "+replyFormIDValue);
    
    
    
    let replyFormID = "#reply-form-" + commentID.split('-')[1] + "-" + commentID.split('-')[2];
    let targetComment = $(targetCommentID);
    let replyForm = $(replyFormID);

    
    let html = 
    '<li><div class="row reply-write p-2">\
        <div class="row pb-2">\
            <form class="comment-form" id="'+ replyFormIDValue +'" type="POST" onSubmit="test(this)">\
            <div class="row">\
            <div>\
	        	<input name="rrId" type="hidden" value="94"/>\
	        	<input name="MEmail" type="hidden" value="admin@modu.com"/>\
	        	<input name="MNickname" type="hidden" value="관리자"/>\
	        	<input name="profileImg" type="hidden" value="default_profile_img.png"/>\
            </div>\
                    <div class="col px-0 comment-file" style="min-width:100px; max-width:100px;">\
                        <img class="border" src="/imgs/pic_none.gif" alt="파일첨부" width="100" height="100" style="cursor:pointer;">\
                    </div>\
                    <div class="col px-0">\
                        <textarea name="reply" id="nested-reply" class="w-100 h-100 border comment-text"  maxlength="300" placeholder="리뷰를 남겨주세요" required></textarea>\
                    </div>\
                    <div class="col px-0 comment-btn" style="min-width:120px; max-width:120px;">\
                        <button id= "insertNestedReply" class="btn w-100 h-100 border comment-submit" type="submit" style>등록</button>\
                    </div>\
                </div>\
            </form><!-- end comment-form -->\
        </div>\
    </div></li><!-- end 리뷰 작성 -->'

    if(replyForm.length === 0) {
        targetComment.append(html);
    }
}

//댓글REPLY인서트
$(function(){
	$("#insertReply").on("click", function(){
		console.log("insertReply");
		let reply =  $("#contentsReply").val();
		let mainForm = $('#reply-form-0-0');
		let info = mainForm.serializeArray();
		console.log("info :"+info);
		alert("reply"+reply);
		$.ajax({
			url: "../recipe/insert.do", 
			type: "POST", 
			data: info,
			dataType:"text",
			success: function(data){
				if(!data){
					 alert("존재하지 않는 name");
					 return false;
				 }
				alert("#성공!"+data);
			},
			error: function(error){
				alert("err"+error);
			}
		
		});
	});
});

//대댓글NestedReply인서트
function NestedReply(e){
	event.preventDefault();
	console.log("NestedReply");
	console.log($(e).attr("class"));
	let data = $(e).serializeArray();
	console.log(data);
	$.ajax({
		url: "../recipe/insertNestedReply.do", 
		type: "POST", 
		data: data,
		dataType:"text",
		success: function(data){
			if(!data){
				 alert("존재하지 않는 name");
				 return false;
			 }
			alert("#성공!"+data);
			$(targetCommentID).append(htmlSub);
			$(e).parents('.reply-write').html('');
		},		
		error: function(error){
			alert("err"+error);
		}
	
	});
}





/*
 -> 파일drag&drop/미완,사진경로변경예정
$(function(e){
    let fileList = [];
    $('.comment-file').on("dragenter", function(e){
        e.preventDefault();
        e.stopPropagation();
    }).on("dragover", function(e){
        e.preventDefault();
        e.stopPropagation();
        $(e).css("border-color", "#FFD8D8");
    }).on("dragleave", function(e){
        e.preventDefault();
        e.stopPropagation();
        $(e).css("border-color", "#FFF");
    }).on("drop", function(e){
        e.preventDefault();
        let files = e.originalEvent.dataTransfer.files;
        console.log(files);
    })
});

*/
