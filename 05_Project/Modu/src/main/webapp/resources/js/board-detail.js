
function addReplyForm(e){    
    let commentID = $(e).attr("class").split(' ')[0];
    let targetComment = $("#" + commentID);
    let replyNum = commentID.split('-')[1]; //게시판 댓글번호
    let replyForm = $('#sub-reply-' + replyNum);
    let html = 
    '<div>\
        <div class="row p-2 pt-4 review-write">\
            <form class="comment-form row" type="POST" id="sub-reply-'+ replyNum +'" onsubmit="addReply(this);">\
                <div class="col py-4 px-2 arrow">\
                    <img src="images/reply-arrow.png" alt="화살표">\
                </div>\
                <div class="row col">\
                    <div class="col px-0">\
                        <textarea class="w-100 h-100 border comment-text p-2" name="reply" maxlength="300" rows=3 placeholder="댓글을 남겨주세요" required></textarea>\
                    </div>\
                    <div class="col px-0 comment-btn">\
                        <button class="btn w-100 h-100 border comment-submit" type="submit">등록</button>\
                    </div>\
                </div>\
            </form><!-- end comment-form -->\
        </div>\
    </div>'

    if(replyForm.length === 0) { //대댓글 입력 폼이 하나도 없을 경우에만 추가
        targetComment.append(html);
        $('#main-reply').css('display', 'none');
    }
}