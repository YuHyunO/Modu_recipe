<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %> 

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<!-- 공통 부분 END -->
<link href="/css/board-list.css" rel="stylesheet">
<link href="/css/board-detail.css" rel="stylesheet">
<script src="/js/board-detail.js"></script>
<script type="text/javascript" language="javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>모두의 식탁 - 자유게시판</title>
<script>
		$(function(){
			$("#ajaxAddReply").on("click", function(){
				var para = document.location.href.split("=");
				var reply =  $("#replyArea").val();
				var bId0 = para[1];
				var bId = parseInt(bId0);
				var text = $('#replyArea').val();
				 if (text.length <= 10) {
						false;
				 }else{
					 $.ajax({
							url: "/freeboard/addReply", 
							type: "POST", 
							data: {"reply": reply, "bId": bId },
							success: function(data){
								addReply(this);
								$("#replyArea").val("");
							} ,
							error: function(eror){
							}
						});
				 }
			});			
		});
		
		function deleteCheck() {
			 if (confirm("정말 삭제하시겠습니까?") == true){
				 location.href='delete.do?id=${board.board.id}';
			 }else{
			     return false;
			 }
		}
</script>
<script>
		function count(){
				const resultElement = document.getElementById('plusResult');
				let number = resultElement.innerText;
				number = parseInt(number) + 6;
				resultElement.innerText = number;
				var para = document.location.href.split("=");
				var bId0 = para[1];
				var Id = parseInt(bId0);
				
				$.ajax({
					url: "../freeboard/moreViewReply", 
					type: "POST", 
					data: { "id": Id ,"endRow":number },
					 dataType : 'json',
					success: function(data){
						var html = "";
						let list = data.list;
						console.log("###"+data);
						console.log("###"+list);

						for(let item of list){
							html +=	"<div class='reply py-3' id='reply-1'>";
							html +=	"	<div class='reply-author d-flex justify-content-between'>";
							html +=	"		<div class='author-main d-flex align-items-center px-3 pt-2'>";
							html +=	"			<figure class='profile m-0'>";
							html +=	"				<img class='profile-img' src='/pics/profile/"+item.profileImg+"'";
							html +=	"					alt='img'>";
							html +=	"			</figure>";
							html +=	"			<span class='m-nickname ps-2'>"+item.mNickname+"</b> <span";
							html +=	"				class='post-date px-2'>"+item.replyDate+"</span>";
							html +=	"		</div>";
							html +=	"		<div class='author-items px-3'>";
							html +=	"			<button class='reply-1 reply-btn' onclick=''>삭제</button>";
							html +=	"			<button class='reply-1 reply-btn'";
							html +=	"				onclick='addReplyForm(this)'>댓글</button>";
							html +=	"		</div>";
							html +=	"	</div>";
							html +=	"	<!-- end author -->";
							html +=	"	<div class='reply-content'>";
							html +=	"		<p class='textarea p-3'>"+item.reply+"</p>";
							html +=	"	</div>";
							html +=	"</div>";
						}
					$('.reply-list').append(html);
					} 
					,
					error: function(eror){
						alert("fail");
					}
				});
		}
		function resultData(){
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
		}
		
		function removeReply(rId){
			console.log("#게시판 댓글삭제 removeReply 메소드 진입");
			let rIdNum = rId;
			let para = document.location.href.split("=");
			let bId = para[1]; //해당 게시글 Id
			//console.log("#삭제전- para:",para); //para: (2) ['http://127.0.0.1:8080/freeboard/detail?id', '354']
			//console.log("#삭제전- bId:",bId); //354
			
			const removeReply = document.getElementById('removeReply'); //무조건 상위댓글만 뽑힘
			let number = removeReply.innerText;
			console.log("#삭제전-삭제할 댓글 id(rIdNum):",rIdNum); //무조건 상위댓글 id만 뽑힘 16143
			
			if (confirm("정말 댓글을 삭제하시겠습니까?") == true){
				$.ajax({
					url: "../freeboard/removeReply", 
					type: "POST", 
					data: { "id" : rIdNum },
					success: function(data){
						console.log("#삭제 성공한 댓글 id:", number);
						location.href='detail?id='+bId+'';
						} ,
					error: function(eror){
						alert("fail"); }
				});
				
			 }else{
			     return false;
			 }
		}
		function LoginCheck(){
			alert('로그인 후 이용하실 수 있습니다.');
			location.href='/member/login';
		}
		function prevView(){
			const prevIp = ${board.boardDetailNextPrev.prevId}
			if(prevIp>0){
				location.href="detail?id=${board.boardDetailNextPrev.prevId}";
			}else{
				alert("처음 글 입니다.");
			}
			
		}
		function nextView(){
			const nextIp = ${board.boardDetailNextPrev.nextId}
			if(nextIp>0){
				location.href="detail?id=${board.boardDetailNextPrev.nextId}";
			}else{
				alert("마지막 글 입니다.");
			}
		}
</script>
</head>
<body>
	<div id="page" class="hfeed site">
		<!-- start page wrapper -->
		<%@ include file="/WEB-INF/views/common/menu.jsp"%>
		<div class="head-title py-4 m-0">
			<div class="container">
				<p class="page-title m-0 fs-2"><a href='list'>free board</a></p>
			</div>
			<!-- end container -->
		</div>
		<!-- end head-title -->
		<textarea id="sessionNickname" name="sessionNickname" style="display: none;">${sessionScope.nickname}</textarea>
		<textarea id="sessionProfile" name="sessionProfile" style="display: none;">${sessionScope.profileImg}</textarea>
		<div class="main">
			<div
				class="main-container row d-flex justify-content-center m-0 py-4">
				<div class="main-size col-md-8 row justify-content-center">
					<div id="board" class="my-3 row px-0">
						<div class="container p-0">
							<div class="board-search text-center m-0 w-100">
								<div id="board-search"
									class="mb-3 d-flex justify-content-between align-items-center">
									<div class="selects">
										<select class="gold-border p-1 filter-period">
											<option selected="selected">전체기간</option>
											<option>1일</option>
											<option>1주</option>
											<option>1개월</option>
											<option>6개월</option>
											<option>1년</option>
										</select> <select class="gold-border p-1 filter-group">
											<option selected="selected">제목만</option>
											<option>글작성자</option>
											<option>댓글내용</option>
											<option>댓글작성자</option>
										</select>
									</div>
									<div class="d-flex justify-content-end search-box">
										<input id="search" class="border px-2" type="search"
											name="search" placeholder="검색어를 입력해주세요" value="">
										<button type="submit" class="search-btn border">검색</button>
										<select class="gold-border p-1 ms-2">
											<option selected="selected">10</option>
											<option>15</option>
											<option>20</option>
										</select>
									</div>
								</div>
								<!-- end board-search -->
							</div>
							<div class="board-main gold-border rounded-1">
								<div
									class="board-title gold-bottom d-flex justify-content-between align-items-center p-2">
									<div class="div title-text col-8 text-start p-2">${board.board.title}</div>
									<div class="div title-items align-items-center text-end px-2">
										<span class="hits ms-2">조회: ${board.board.hits}</span>
									</div>
								</div>
								<!-- board-title -->
								<div class="board-body p-2">
									<div class="board-head d-flex justify-content-between">
										<div class="author d-flex align-items-center px-2 pt-2">
											<figure class="profile">
												<img class="profile-img" src="/pics/profile/${board.board.profileImg}"
													alt="img">
											</figure>
											<span class="m-nickname ps-2">${board.board.MNickname}</b> <span
												class="post-date px-2">${board.board.postDate}</span>
										</div>
										<c:if test="${sessionScope.email == board.board.MEmail}">
											<div class="">
												<a href="update.do?id=${board.board.id}"><button class="btn-fix">수정</button></a>
												<button class="btn-del" onclick="deleteCheck()">삭제</button>
											</div>
										</c:if>
										<c:if test="${sessionScope.email != board.board.MEmail}">
											<div class="" style="display: none;">
												<a href="update.do?id=${board.board.id}"><button class="btn-fix">수정</button></a>
												<button class="btn-del" onclick="deleteCheck()">삭제</button>
											</div>
										</c:if>
									</div>
									<!-- board-head -->
									<div class="py-0 border-top"></div>
									<div
										class="content-cover d-flex flex-column justify-content-between">
										<div class="content p-2">
											<p>${board.board.content}</p>
										</div>
										<div
											class="div file d-flex justify-content-end align-items-center p-2">
											<c:forEach var="file" items="${board.boardFile}">
												<div id='downSave' style='display: none;'>
												</div>
													<a href="download.do?saveFile=${file.saveFile}" class="me-2" id="me-2">첨부파일: ${file.originalFile}
											<button class="down-btn" onclick='downCheck()'>다운로드</button></a>
											</c:forEach>
										</div>
									</div>
								</div>
								<!-- board-body -->
							</div>
							<!-- end board-main -->
							<div>
								<div class="board-menu py-2 px-3 float-end">
									<a href="list">목록</a> <a
										href="javascript:void(0);" onclick="prevView();">| 이전글 |</a> <a
										href="javascript:void(0);" onclick="nextView();">다음글</a>
								</div>
							</div>
							<div class="py-3"></div>
							<div class="reply-container pb-3">
								<p class="mb-1 pb-3 px-2 gold-bottom" style="font-weight: bold;">댓글
									(${board.board.reply})</p>
								<div class="reply-list">
										<c:forEach items="${list.list}" var="i">
													<div class="reply py-3" id="reply-1">
														<div class="reply-author d-flex justify-content-between">
															<div class="author-main d-flex align-items-center px-3 pt-2">
																<figure class="profile m-0">
																	<img class="profile-img" src="/pics/profile/${i.profileImg}" alt="img">
																</figure>
																<span class="m-nickname ps-2">${i.MNickname}</b>
																<span class="post-date px-2">${i.replyDate}</span>
															</div>
															<div id='removeReply' style="display: none;" >${i.id}</div>
															<div class="author-items px-3">
																<c:if test="${sessionScope.email == i.MEmail}">
																	<button class="reply-1 reply-btn" onclick="removeReply(${i.id})">삭제</button>
																</c:if>
																<button style='display: none;' class="reply-1 reply-btn"
																	onclick="addReplyForm(this)">답글</button>
															</div>
														</div>
														<!-- end author -->
														<div class="reply-content">
															<p class="textarea p-3">${i.reply}</p>
														</div>
													</div>
											</c:forEach>
											<textarea name='sessionNickname' id='sessionNickname'  style='display:none;'>${sessionScope.MNickname}</textarea>
											<textarea name='sessionProfile' style='display:none;'>${sessionScope.profileImg}</textarea>
									<!-- end reply -->
								</div>
								<!-- end reply-list -->
								<div class="row p-2">
									<form class="comment-form" type="POST" id="main-reply"
										onsubmit="addReply(this);">
										<div class="row">
												<c:if test="${fn:length(list.list) > 5}">
														<input type='button' id="theBoGi" style="text-align: center; " onclick='count()' value='더보기'>
															<br></br>
														<textarea id='plusResult' style='display:none;'>6</textarea>
												</c:if>
												<c:if test="${fn:length(list.list) == 0}">
														<input type='button' id="theBoGi" style="text-align: center; display: none; " onclick='count()' value='더보기'>
															<br></br>
														<textarea id='plusResult' style='display:none;'>6</textarea>
												</c:if>
											<div class="col px-0">
												<textarea class="w-100 h-100 border comment-text p-2"
													name="reply" maxlength="300" rows=3 placeholder="댓글을 남겨주세요"
													required id="replyArea"></textarea>
											</div>
											<div class="col px-0 comment-btn">
												<c:if test="${sessionScope.email != null}">
													<button class="btn w-100 h-100 border comment-submit"
														type="submit" id="ajaxAddReply">등록</button>
												</c:if>
												<c:if test="${sessionScope.email == null}">
													<button class="btn w-100 h-100 border comment-submit"
														type="button" onclick='LoginCheck()'>등록</button>
												</c:if>
											</div>
										</div>
									</form>
									<!-- end comment-form -->
								</div>
							</div>
							<!-- end reply-container -->
						</div>
						<!-- end board container -->
					</div>
					<!-- end board -->
				</div>
				<!-- end main-size -->
			</div>
			<!-- end main-container -->
		</div>
		<!-- <textarea name="sessionPostDate" style="display: none;"></textarea> -->
		<!-- end main -->
		<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
	</div>
	<!-- end #page hfeed site -->
	<script>
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
	    let sessionNickname2 = document.getElementById("sessionNickname");
		let sN2 = sessionNickname2.value;
	    let text = $(e).find('textarea[name=reply]');
	    let sessionNick1 = $("#sessionNickname").val();
	    let sessionNick2 = $("#sessionNickname").text();
	    let sessionProfile1 = $("#sessionProfile").val();
	    let sessionProfile2 = $("#sessionProfile").text();
	    
	    console.log("#addReply 필드들")
	    console.log(sessionNickname2);
	    console.log(sessionNick1);
	    console.log(sessionProfile1); //undefined
	    console.log(sProfile); //undefined
	    
	    if (text.val().length <= 10) {
	        alert("10글자 이상 입력해주세요"); //댓글 10글자 이하일 경우 경고창 띄우기
	    } else {
	        let lines = text.val().split("\n");
	        let reviewText = ''; 
	        for (let i = 0; i < lines.length; i++) {
	            if (lines[i].length === 0){} //비어있는 줄 제거
	            else{
	                reviewText += lines[i] + "<br>";
	            }
	        }
	        //console.log("#reviewText: "+reviewText); //작성한 댓글 내용
	        
	        let time = new Date();
	        let month = String(time.getMonth() + 1);
	        let date = String(time.getDate());
	        let hours = String(time.getHours());
	        let minutes = String(time.getMinutes());
	        let seconds = String(time.getSeconds());
	        
	        text.val('');
	        console.log(text.val(''));

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
	            html = '<div class="reply py-3" id="reply-"'+ replyId +'>\
	                <div class="reply-author d-flex justify-content-between">\
	                    <div class="author-main d-flex align-items-center px-3 pt-2">\
	                        <figure class="profile m-0">\
	                            <img class="profile-img" src="/pics/profile/'+sessionProfile1+'" alt="img">\
	                        </figure>\
	                        <span class="m-nickname ps-2">'+sessionNick1+'</b>\
	                        <span class="post-date px-2">'+ dateText +'</span>\
	                    </div>\
	                    <div class="author-items px-3">\
	                        <button class="reply-'+ replyId +' reply-btn" onclick="removeReply(${i.id})">삭제</a>\
	                        <button style="display:none;"class="reply-'+ replyId +' reply-btn" onclick="addReplyForm(this)">답글</a>\
	                    </div>\
	                </div><!-- end author -->\
	                <div class="reply-content">\
	                    <p class="textarea p-3">' + reviewText +'</p>\
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
	                            <button class="reply-'+ replyId +' reply-btn" onclick="removeReply(${i.id})">삭제</a>\
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
	</script>
</body>
</html>