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
    // ?????????, ????????? ????????? ?????? ???????????? ??????
	event.preventDefault();
    /*let commentID = $(e).attr('id');
    if(commentID === "reply-main-form"){
    	
    }

    let replyContent = $(e).find('textarea[name=reply]');
    let lines = replyContent.val().split("\n");
    replyContent.val(''); // ?????? ?????? ?????????
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
                        <img src="/imgs/content/auth-00.png" alt="?????????">\
                    </figure><!-- end comment-author vcard -->\
                    <b class="fn px-2">Jessica</b>\
                    <span class="px-2">' + dateText + '</span>\
                    <span>' + starHtml + '</span>\
                </span>\
                <span class="reply-btn px-2">\
                    <button class="reply-' + commentID + ' reply-btn" onclick="">??????</a>\
                    <button class="reply-' + commentID + ' reply-btn" onclick="addReplyForm(this)">??????</a>\
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
            <img src="/imgs/reply-arrow.png" alt="?????????">\
        </div>\
        <div class="comment-body col">\
            <div class="comment-meta d-flex justify-content-between align-items-center">\
                <span class="d-flex align-items-center">\
                    <figure class="comment-author">\
                        <img src="/imgs/content/auth-03.png" alt="?????????">\
                    </figure><!-- end comment-author vcard -->\
                    <b class="fn px-2">Dina Makulatuwa</b>\
                    <span class="px-2">' + dateText + '</span>\
                </span>\
                <span class="reply-btn px-2">\
                    <button class="reply-' + commentID + ' reply-btn" onclick="">??????</a>\
                    <button class="reply-' + commentID + ' reply-btn" onclick="">??????</a>\
                    <button class="reply-' + commentID + ' reply-btn" onclick="addReplyForm(this)">??????</a>\
                </span>\
            </div><!-- end comment-meta -->\
            <div class="comment-content">\
                <p class="p-2 m-0">'
                    + reviewText +'</p>\
            </div><!-- end comment-content -->\
        </div><!-- end comment-body -->\
    </li><!-- end comment -->';

    if(targetCommentID === "#comment-0-0"){
        // console.log("????????????");
        $(targetCommentID).prepend(htmlMain);
        console.log(targetCommentID);
        $(e).parents('.reply-write').html('');
    }else {
        // console.log("?????????");
        $(targetCommentID).append(htmlSub);
        console.log(targetCommentID);
        $(e).parents('.reply-write').html('');
    }*/
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
                        <img class="border" src="/imgs/pic_none.gif" alt="????????????" width="100" height="100" style="cursor:pointer;">\
                    </div>\
                    <div class="col px-0">\
                        <textarea id="contentsRreply" class="w-100 h-100 border comment-text"  name="reply" maxlength="300" placeholder="????????? ???????????????" required></textarea>\
                    </div>\
                    <div class="col px-0 comment-btn" style="min-width:120px; max-width:120px;">\
                        <button id= "insertNestedReply" class="btn w-100 h-100 border comment-submit" type="submit" style>??????</button>\
                    </div>\
                </div>\
            </form><!-- end comment-form -->\
        </div>\
    </div></li><!-- end ?????? ?????? -->'

    if(replyForm.length === 0) {
        targetComment.append(html);
    }
}

$(function(){
	$("#insertReply").on("click", function(){
		event.preventDefault();
		let formData = new FormData();
		let hiddenFile = $('#hidden-file');
		let rId = $('input[name="rId"]').val();
		let reply= $('#contentsReply').val();
		
		formData.append("file", hiddenFile[0].files[0]);
		formData.append("rId", rId);
		formData.append("reply", reply);
		
		$.ajax({
			url: "/recipe/insert-reply.json", 
			type: "POST", 
			data: formData,
			enctype: "multipart/form-data",
			processData: false,
			contentType: false,
			dataType:"json",
			success: function(response){
				if(response.msg === "????????? ??????"){
					alert("????????? ????????? ??? ?????? ???????????????.");
				} else {
					let recipeCount = response.recipeCount; // ?????? ??????
					let starHtml = '';
					let starPoint = 0;
				    if(starPoint === 0){
				    } else {
				        starHtml = '<img class="star-rate-img2" src="/imgs/stars' 
				        + starPoint + '.png" alt="stars" style="width:80px; height:15px; margin-bottom:5px;">';
				    }
				    let img = "";
				    
				    if (response.replyPhoto !== undefined){
						img += '<figure class="comment-image"><img class="rounded-3" src="/pics/recipe/'
							+ response.reply.rid +'/' 
							+ response.replyPhoto.rrId + '/' 
							+ response.replyPhoto.saveFile + '" alt="comment-image"></figure>';
				    }
					let html = 
					    '<li class="comment" id="comment-'+ response.reply.id +'">\
					        <div class="comment-body">\
					            <div class="comment-meta d-flex justify-content-between align-items-center">\
					                <span class="d-flex align-items-center">\
					                    <figure class="comment-author">\
					                        <img src="/pics/profile/'+ response.reply.profileImg +'" alt="?????????">\
					                    </figure><!-- end comment-author vcard -->\
					                    <b class="fn px-2">'+ response.reply.mnickname +'</b>\
					                    <span class="px-2">' + response.reply.replyDate + '</span>\
					                    <span>' + starHtml + '</span>\
					                </span>\
					                <span class="reply-btn px-2">\
					                    <button class="reply-' + response.reply.id + ' reply-btn" onclick="deleteReply(this)">??????</a>\
					                </span>\
					            </div>\
					            <div class="comment-content d-flex">\
					                <p class="p-2 m-0 col-9">\
					                    '+ response.reply.reply + '</p>\
					                    '+ img + '\
					            </div>\
					        </div>\
					    </li>\
					    <ol class="re-comment px-0" id="recomment-' + response.reply.id + '"></ol>';
					$('.comment-list').prepend(html);
			        $('#contentsReply').val("");
			        
			        if(recipeCount === undefined){
						recipeCount = 0;
					}
			        $('.comments-title').find('span').text('('+recipeCount+')'); // ?????? ?????? ????????????
				}
			},
			error: function(response){
				alert("?????? ????????? ??????????????????.");
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
	id = id.split("-")[1];	
	console.log("id: "+id);
	console.log("mode: "+mode);
	
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
	}else if (mode == 2){
		lastIndex = $("#nested-add-"+id).attr("value");		
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

let rId;
function displayMoreReply(response){
	let i = 1;
	for(let item of response){
		rId = item.rid;	
		i++;
		let html = '';
		html += '<li class="comment" value="'+item.list+'">';			
		html += '<div class="comment-body">';
		html += '<div class="comment-meta d-flex justify-content-between align-items-center">';
		html += '<span class="d-flex align-items-center">';
		html += '<figure class="comment-author">';
		html += '<img src="/imgs/content/'+item.profileImg+'" alt="?????????">';
		html += '</figure><b class="fn px-2">'+item.mnickname+'</b>';
		html += '<span class="star-rate-block">';
		html += '<span class="px-2">'+item.replyDate+'</span>';	
		html += '<img class="star-rate-img2" src="/imgs/stars4.png" alt="stars" style="width: 80px; height: 15px; margin-bottom: 5px;">';	
		html += '</span>';
		html += '</span>';
		html += '<div>';
		html += '<button class="reply-135000-0001 reply-btn" onclick="getDate('+item.id+')">??????</a>';
		html += '<button class="reply-135000-0001 reply-btn" onclick="addReplyForm(this)">??????</a>';
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
	let location = (document.querySelector("#comment-add").offsetTop)-500;
	if(response.length < 5){
		$("#add-"+rId).text("????????????");
		$("#add-"+rId).attr("onclick", "scrollUpToReview("+rId+")");
		window.scrollTo({top:location, behavior:"smooth"});
	}else{
		let value = $("#comment-add").attr("value");
		let val = parseInt(value);
		let lastIndex = val + parseInt(i);
		$("#comment-add").attr("value", lastIndex);				
		window.scrollTo({top:location, behavior:"smooth"});
	}	
}

let rrId;
function displayMoreNestedReply(response){		
	let i = 1;	
	for(let item of response){
		i++;
		rrId = item.rrId;
		let html = '';
		html += '<li class="comment row" value="'+item.list+'">';
		html += '<div class="col" style="max-width: 40px;">';
		html += '<img src="/imgs/reply-arrow.png" alt="?????????">';
		html += '</div>';
		html += '<div class="comment-body col">';
		html += '<div class="comment-meta d-flex justify-content-between align-items-center">';
		html += '<span class="d-flex align-items-center">';
		html += '<figure class="comment-author">';
		html += '<img src="/imgs/content/'+item.profileImg+'" alt="?????????">';
		html += '</figure>';
		html += '<b class="fn ps-2">'+item.mnickname+'</b>';
		html += '<span class="px-2">'+item.replyDate+'</span>';
		html += '</span>';
		html += '<span class="reply-btn px-2">';
		html += '<button class="reply-135000-0001 reply-btn" onclick="getData('+item.id+'});">??????</button>';
		html += '</span>';
		html += '</div>';
		html += '<div class="comment-content">';
		html += '<p class="p-2 m-0">'+item.reply+'</p>';
		html += '</div>';
		html += '</div>';
		html += '</li>';		
		$("#recomment-area-"+id).append(html);		
	}
	$("#readd-"+rrId).text("???????????? ??????");
	$("#readd-"+rrId).attr("onclick", "foldReply("+rrId+")");	
	$("#nested-sub-add-"+rrId).html('<a id="subreadd-'+rrId+'" href="javascript:void(0)" onclick="setUrl(this)">???????????? ?????????</a>');
	
	if(response.length<5){
		$("#subreadd-"+rrId).text("???????????????");
		$("#subreadd-"+rrId).attr("onclick", "scrollUpToParent("+rrId+")");
	}
	let value = $("#nested-add-"+rrId).attr("value");
	let val = parseInt(value);
	let lastIndex = val + parseInt(i);
	$("#nested-add-"+id).attr("value", lastIndex);
	let location = (document.querySelector("#nested-sub-add-"+rrId).offsetTop)-600;		
	window.scrollTo({top:location, behavior:"smooth"});	
}

function scrollUpToReview(){
	let location = (document.querySelector("#review-loc").offsetTop);		
	window.scrollTo({top:location, behavior:"smooth"});	
}
function scrollUpToParent(){
	let location = (document.querySelector("#readd-"+rrId).offsetTop);		
	window.scrollTo({top:location, behavior:"smooth"});	
}

function foldReply(id){
	$("#recomment-area-"+id).empty();
	$("#readd-"+id).text("???????????? ??????");
	$("#nested-sub-add-"+id).empty();
	$("#readd-"+id).attr("onclick", "setUrl(this)");
	$("#nested-add-"+id).attr("value", 1);	
}

function fileUpButton(e) {
	let hiddenInput = $(e).parent().find('input');
	hiddenInput.click();
}

function imgUpload(e) {
	let files = $(e)[0].files;
	let filesArr = Array.prototype.slice.call(files);
	let regex = /(.*?)\/(jpg|jpeg|png|gif)$/;
	let formData = new FormData();
	formData.append("file", files[0]);

	filesArr.forEach(function (f) {
		if (!f.type.match(regex)) {
			alert("????????? ????????? ?????? ???????????????.");
			return;
		}
		sel_file = f;

		let reader = new FileReader();
		reader.onload = function (k) {
			$('.reply-img').attr("src", k.target.result);
		}
		reader.readAsDataURL(f);
	});
}

