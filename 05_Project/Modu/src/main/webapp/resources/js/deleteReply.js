
function deleteReply(e){
	let classDeleteReply =  $(e).attr('class');
	let words = classDeleteReply.split(' ' , 2);
	let replyClass = words[0];
	let replyID = replyClass.split('-')[2];
	console.log("-기준으로3개의배열,id뽑아내기->"+replyID);	
	/*let replyID2 = $(e).attr("data-id");
	console.log("replyID2: " + replyID2);*/
	$.ajax({
		url: "/recipe/del.json", 
		type: "POST",
		data: {"id": replyID},
		dataType:"json",
		success: function(data){
			console.log(data.msg);						
			addReplyArea(e);
		},
		error: function(error){
			alert("err"+error);
		}	
	});
}

function addReplyArea(e){    
	$("#comment-area").empty();
	console.log("test1성공");
	let html = "";
		html += '<c:forEach items="${detail.replyList}" var="li">';				
		html += '<li class="comment" value="${li.list}">';
		html +=	'<div class="comment-body">';
		html +=	'<div class="comment-meta d-flex justify-content-between align-items-center">';
		html +=	'<figure class="comment-author">';
		html +=	'<img src="${li.profileImg}" alt="작성자">';
		html +=	'</figure><b class="fn px-2">${li.nickname}</b>';
		html +=	'<span class="star-rate-block">';
		html +=	'<span class="px-2">${li.replyDate}</span>';
		html +=	'<img class="star-rate-img2" src="/imgs/stars4.png" alt="stars" style="width: 80px; height: 15px; margin-bottom: 5px;">';
		html +=	'</span>';
		html +=	'</span>';
		html +=	'<div>';
		html +=	'<button class="reply-135000-${li.id} reply-btn" data-id="${li.id}" onclick="deleteReply(this)">삭제</button>';
		html +=	'<button class="reply-135000-0001 reply-btn" onclick="addReplyForm(this)">답글</a>';
		html +=	'</div>';
		html += '</div>';
		html +=	'<div class="comment-content d-flex">';
		html +=	'<p class="p-2 m-0 col-9">${li.reply}</p> <!-- ???? -->';
		html += '<figure class="comment-image">';
		html +=	'<img class="rounded-3" src="/imgs/content/dessert-l.png" alt="comment-image">';
		html +=	'</figure>';
		html +=	'</div>';
		html +=	'</div>';
		html += '<c:if test="${li.nestedReply eq 1}">';
		html += '<div id="nested-add-${li.id}" class="row view-more py-2" value="1" style="max-width: 300px;">'; 
		html +=	'<a id="readd-${li.id}" href="javascript:void(0)" onclick="setUrl(this)">▼대댓글 보기</a>';
		html +=	'</div>';	
		html +=	'</c:if>';									
		html +=	'</li>';
		html +=	'<c:if test="${li.nestedReply eq 1}">';																																						
		html +=	'<ol class="re-comment px-0" id="recomment-area-${li.id}">';																			 											
		html +=	'</ol>';
		html +=	'<div id="nested-sub-add-${li.id}">';
		html +=	'</div>';																			
		html +=	'</c:if>';																							
		html +=	'</c:forEach>';
		html +=	'<br/>';
		html += '<tr class="border">';
		html += '<td class="id">'+item.id+'</td>';
		html += '<td class="title-td text-start text"><a href="detail?id='+item.id+'"> <span';
		html += 'class="title text">'+item.title+'</span> <span class="reply">['+item.reply+']</span>';
		html += '</a>';
		html += '</th>';
		html += '<td class="nickname text"><img class="profile-img" src="/pics/profile/'+item.profileImg+'" alt="img">'+item.mnickname+'</td>';
		html += '<td class="post-date">'+item.postDate+'</td>';
		html += '<td class="hits">'+item.hits+'</td>';
		html += '</tr>';
	$("#comment-area").append(html);	
	console.log("test2성공");
}
    	
  
    	
 
    	
    	
    	
    	
    	
    	
    	
    	

