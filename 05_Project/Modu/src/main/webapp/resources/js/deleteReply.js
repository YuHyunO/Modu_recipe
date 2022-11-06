function deleteReply(e){
	let classDeleteReply =  $(e).attr('class');
	let words = classDeleteReply.split(' ' , 2);
	let replyClass = words[0];
	let replyID = replyClass.split('-')[1];
	//console.log("-기준으로3개의배열,id뽑아내기->"+replyID);	
	/*let replyID2 = $(e).attr("data-id");
	console.log("replyID2: " + replyID2);*/
	$.ajax({
		url: "/recipe/del.json", 
		type: "POST",
		data: {"id": replyID},
		dataType:"json",
		success: function(data){
			//console.log(data.msg);
			//addReplyArea(e);
			$("#comment-" + replyID).html(""); // 해당 html 비워줌
			$("#comment-" + replyID).css("display", "none");
			let recipeCount = data.recipeCount;
			if(recipeCount === undefined){
				recipeCount = 0;
			}
			$('.comments-title').find('span').text('('+recipeCount+')'); // 댓글 갯수 업데이트
		},
		error: function(error){
			alert("err"+error);
		}
	});
}