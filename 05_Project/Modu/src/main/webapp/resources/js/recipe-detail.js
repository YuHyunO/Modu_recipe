/*function clickSubscribe(e){
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

function clickScrap(e){
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
    '<li class="comment" id="comment-135000-0001">\
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
                    <button class="reply-' + commentID + ' reply-btn" onclick="">삭제</a>\
                    <button class="reply-' + commentID + ' reply-btn" onclick="addReplyForm(this)">답글</a>\
                </span>\
            </div>\
            <div class="comment-content d-flex">\
                <p class="p-2 m-0 col-9">\
                    '+ reviewText + '</p>\
                <figure class="comment-image">\
                    <img class="rounded-3" src="/imgs/content/dessert-l.png" alt="comment-image">\
                </figure>\
            </div>\
        </div>\
    </li>\
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
        // console.log("대댓글");
        $(targetCommentID).append(htmlSub);
        console.log(targetCommentID);
        $(e).parents('.reply-write').html('');
    }
}

function addReplyForm(e){    
	console.log("e:" +e)
	
    let commentID = $(e).attr("class").split(' ')[0];
    console.log("commentId: "+commentID);
    let targetCommentID = "#comment-" + commentID.split('-')[1] + "-" + commentID.split('-')[2];
    let replyFormIDValue = "reply-form-" + commentID.split('-')[1] + "-" + commentID.split('-')[2];
    let replyFormID = "#reply-form-" + commentID.split('-')[1] + "-" + commentID.split('-')[2];
    let targetComment = $(targetCommentID);
    let replyForm = $(replyFormID);

    let html = 
    '<li><div class="row reply-write p-2">\
        <div class="row pb-2">\
            <form class="comment-form" type="POST" id="'+ replyFormIDValue +'" onSubmit="addReply(this)"> \
                <div class="row">\
                    <div class="col px-0 comment-file" style="min-width:100px; max-width:100px;">\
                        <img class="border" src="/imgs/pic_none.gif" alt="파일첨부" width="100" height="100" style="cursor:pointer;">\
                    </div>\
                    <div class="col px-0">\
                        <textarea id="contentsRreply" class="w-100 h-100 border comment-text"  name="reply" maxlength="300" placeholder="리뷰를 남겨주세요" required></textarea>\
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

$(function(){
	$("#insertReply").on("click", function(){
		let reply =  $("#contentsReply").val()
		let mainForm = $('#reply-form-0-0')
		let info = mainForm.serializeArray();		
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

let mode;
function setUrl(e){
	let val = $(e).val();
	if(val == ""){		
		val = "2";
	}
	let url;
	switch(val){
	case "1": url = "/recipe/recipe-reply";
			mode = 1; break;
	case "2": url = "/recipe/recipe-nested-reply";
		 	mode = 2;
	}
	setData(e, url);
}

let id;
function setData(e, url){
	let data;
	id = $(e).attr("id");		

	switch(mode){
	case 1: data = {rId: id,
					lastIndex: getLastIndex(id)};
			  break;
	case 2: data = {rrId: id,
					lastIndex: getLastIndex(id)};		 	 
	}	
	dataAgent(url, data);
}

function getLastIndex(id){
	let lastIndex;
	if (mode == 1){
		lastIndex = $("#comment-add").attr("value");
		console.log(lastIndex);
	}else if (mode == 2){
		lastIndex = $("#nested-add-"+id).attr("value");
		console.log(lastIndex);
	}
	return lastIndex;
}

function dataAgent(url, data){
	$.ajax({
		url: url,
		type: "GET",
		data: data,
		dataType: "JSON",
		tranditional: true,
		success: function(response){
			switch(mode){
			case 1: displayMoreReply(response);
					break;
			case 2: displayMoreNestedReply(response);	 	 
			}						
		},
		error: function(error){
			console.log("X");
		}
	});
}

function displayMoreReply(response){
	let html = '';
	let i = 1;
	for(let item of response){
		i++;
		html += '<li class="comment" value="'+item.list+'">';			
		html += '<div class="comment-body">';
		html += '<div class="comment-meta d-flex justify-content-between align-items-center">';
		html += '<span class="d-flex align-items-center">';
		html += '<figure class="comment-author">';
		html += '<img src="/imgs/content/'+item.profileImg+'" alt="작성자">';
		html += '</figure><b class="fn px-2">'+item.mNickname+'</b>';
		html += '<span class="star-rate-block">';
		html += '<span class="px-2">'+item.replyDate+'</span>';	
		html += '<img class="star-rate-img2" src="/imgs/stars4.png" alt="stars" style="width: 80px; height: 15px; margin-bottom: 5px;">';	
		html += '</span>';
		html += '</span>';
		html += '<div>';
		html += '<button class="reply-135000-0001 reply-btn" onclick="getDate('+item.id+')">삭제</a>';
		html += '<button class="reply-135000-0001 reply-btn" onclick="addReplyForm(this)">답글</a>';
		html += '</div>';
		html += '</div>';
		html += '<div class="comment-content d-flex">';
		html += '<p class="p-2 m-0 col-9">'+item.reply+'</p>';
		html += '<figure class="comment-image">';
		html += '<img class="rounded-3" src="/imgs/content/dessert-l.png" alt="comment-image">';
		html += '</figure>';
		html += '</div>';
		html += '</div> ';
		html += '</li>';
		
		$("#comment-area").append(html);				
	}
	let value = $("#comment-add").attr("value");
	let val = parseInt(value);
	let lastIndex = val + parseInt(i);
	$("#comment-add").attr("value", lastIndex);	
}

function displayMoreNestedReply(response){
	let html = '';
	let i = 0;
	for(let item of response){
		i++;
		html += '<li class="comment row" value="'+item.list+'">';
		html += '<div class="col" style="max-width: 40px;">';
		html += '<img src="/imgs/reply-arrow.png" alt="화살표">';
		html += '</div>';
		html += '<div class="comment-body col">';
		html += '<div class="comment-meta d-flex justify-content-between align-items-center">';
		html += '<span class="d-flex align-items-center">';
		html += '<figure class="comment-author">';
		html += '<img src="/imgs/content/'+item.profileImg+'" alt="작성자">';
		html += '</figure>';
		html += '<b class="fn ps-2">'+item.mNickname+'</b>';
		html += '<span class="px-2">'+item.replyDate+'</span>';
		html += '</span>';
		html += '<span class="reply-btn px-2">';
		html += '<button class="reply-135000-0001 reply-btn" onclick="getData('+item.id+'});">삭제</button>';
		html += '</span>';
		html += '</div>';
		html += '<div class="comment-content">';
		html += '<p class="p-2 m-0">'+item.reply+'</p>';
		html += '</div>';
		html += '</div>';
		html += '</li>';
				
		$("#recomment-area-"+id).append(html);		
	}
	let value = $("#nested-add-"+id).attr("value");
	let val = parseInt(value);
	let lastIndex = val + parseInt(i);
	$("#nested-add-"+id).attr("value", lastIndex)
	console.log("##nested"+$("#nested-add-"+id).attr("value"));
}



