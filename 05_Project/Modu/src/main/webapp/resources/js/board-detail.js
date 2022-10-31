function addReply(e){
    //작성자, 작성자 사진은 세션이용해서 추가
    event.preventDefault();
    let targetCommentID = $(e).attr('id');
    let replyId; //댓글 넘버
    let html;
    let sessionNick = $(e).find('textarea[name=sessionNickname]');
    let sessionProfile = $(e).find('textarea[name=sessionProfile]');
    let sNick = sessionNick.val();
    let sProfile = sessionProfile.val();
    let text = $(e).find('textarea[name=reply]');
    if (text.val().length <= 10) {
        alert("10글자 이상 입력해주세요"); //댓글 10글자 이하일 경우 경고창 띄우기
    } else {
        let lines = text.val().split("\n");
        let reviewText = '';
        for (let i = 0; i < lines.length; i++) {
            if (lines[i].length === 0){} //비어있는 줄 제거
            else{
                reviewText += lines[i] + "<br />";
            }
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

        if(targetCommentID.split('-')[0] === "main"){ //메인댓글일 경우
            replyId = "";
            html = 
            '<div class="reply py-3" id="reply-"'+ replyId +'>\
                <div class="reply-author d-flex justify-content-between">\
                    <div class="author-main d-flex align-items-center px-3 pt-2">\
                        <figure class="profile m-0">\
                            <img class="profile-img" src="/imgs/mypage/profile/'+sProfile+'" alt="작성자">\
                        </figure>\
                        <span class="m-nickname ps-2">'+sNick+'</b>\
                        <span class="post-date px-2">'+ dateText +'</span>\
                    </div>\
                    <div class="author-items px-3">\
                        <button class="reply-'+ replyId +' reply-btn" onclick="">삭제</a>\
                        <button class="reply-'+ replyId +' reply-btn" onclick="addReplyForm(this)">답글</a>\
                    </div>\
                </div><!-- end author -->\
                <div class="reply-content">\
                    <p class="p-3">' + reviewText +'</p>\
                </div>\
            </div><!-- end reply -->';

            $('.reply-list').prepend(html);
        } else {  //대댓글일 경우
            replyId = targetCommentID.split('-')[2];
            console.log(replyId);
            html = 
            '<div class="reply py-3 d-flex">\
                <div class="col py-4 px-2 arrow">\
                    <img src="images/reply-arrow.png" alt="화살표">\
                </div>\
                <div class="col px-0">\
                    <div class="reply-author d-flex justify-content-between py-3">\
                        <div class="author-main d-flex align-items-center px-3 pt-2">\
                            <figure class="profile m-0">\
                                <img class="profile-img" src="images/content/auth-01.png" alt="작성자">\
                            </figure>\
                            <span class="m-nickname ps-2">'+sessionNickname+'</b>\
                            <span class="post-date px-2">'+ dateText +'</span>\
                        </div>\
                        <div class="author-items px-3">\
                            <button class="reply-'+ replyId +' reply-btn" onclick="">삭제</a>\
                            <button class="reply-'+ replyId +' reply-btn" onclick="addReplyForm(this)">댓글</a>\
                        </div>\
                    </div><!-- end author -->\
                    <div class="reply-content">\
                        <p class="p-3">' + reviewText +'</p>\
                    </div>\
                </div>\
            </div><!-- end reply -->';

            $('#reply-' + replyId).prepend(html);
            $('.review-write').parent().html('');
            $('#main-reply').css('display', 'block');
        }
    }
}

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