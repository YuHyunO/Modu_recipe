<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<!-- 공통 부분 END -->
<link href="/css/board-list.css" rel="stylesheet">
<link href="/css/board-detail.css" rel="stylesheet">
<script src="/js/board-detail.js"></script>
<title>모두의 식탁 - 공지사항</title>
</head>
<body>
	<div id="page" class="hfeed site">
		<!-- start page wrapper -->
		<%@ include file="/WEB-INF/views/common/menu.jsp"%>
		<div class="head-title py-4 m-0">
			<div class="container">
				<p class="page-title m-0 fs-2">notice</p>
			</div>
			<!-- end container -->
		</div>
		<!-- end head-title -->

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
									<div class="div title-text col-8 text-start p-2">[美친특가]
										르젠 플러스 IH 가열식 가습기 4세대 (2023년형 신형)</div>
									<div class="div title-items align-items-center text-end px-2">
										<span class="hits ms-2">조회: 300</span>
									</div>
								</div>
								<!-- board-title -->
								<div class="board-body p-2">
									<div class="board-head d-flex justify-content-between">
										<div class="author d-flex align-items-center px-2 pt-2">
											<figure class="profile">
												<img class="profile-img" src="/imgs/content/auth-00.png"
													alt="작성자">
											</figure>
											<span class="m-nickname ps-2">Jessica</b> <span
												class="post-date px-2">09-20 18:30</span>
										</div>
										<div class="">
											<button class="btn-fix">수정</button>
											<button class="btn-del">삭제</button>
										</div>
									</div>
									<!-- board-head -->
									<div class="py-0 border-top"></div>
									<div
										class="content-cover d-flex flex-column justify-content-between">
										<div class="content p-2">
											<p>규정을 위반한 사용자들을 신고하고 오리 실험 요청을 할 수 있는 공간이다. 규정에 따라 일반
												이용자가 답변 등 신고 처리에 불필요한 내용이 들어간 댓글을 작성하면 안 된다. 단, 피신고자가 반론 정도는
												할 수 있다. 편집 차단된 사용자가 신고 게시판에 신고를 하는 건 불가능하다. 규정을 위반한 사용자들을
												신고하고 오리 실험 요청을 할 수 있는 공간이다. 규정에 따라 일반 이용자가 답변 등 신고 처리에 불필요한
												내용이 들어간 댓글을 작성하면 안 된다. 단, 피신고자가 반론 정도는 할 수 있다. 편집 차단된 사용자가
												신고 게시판에 신고를 하는 건 불가능하다. 규정을 위반한 사용자들을 신고하고 오리 실험 요청을 할 수 있는
												공간이다. 규정에 따라 일반 이용자가 답변 등 신고 처리에 불필요한 내용이 들어간 댓글을 작성하면 안 된다.
												단, 피신고자가 반론 정도는 할 수 있다. 편집 차단된 사용자가 신고 게시판에 신고를 하는 건 불가능하다.
											</p>
										</div>
										<div
											class="div file d-flex justify-content-end align-items-center p-2">
											<a href="javascript:void(0)" class="me-2">첨부파일: 게시판
												스샷.png</a>
											<button class="down-btn">다운로드</button>
										</div>
									</div>
								</div>
								<!-- board-body -->
							</div>
							<!-- end board-main -->
							<div>
								<div class="board-menu py-2 px-3 float-end">
									<a href="javascript:void(0)">목록</a> <a
										href="javascript:void(0)">| 이전글 |</a> <a
										href="javascript:void(0)">다음글</a>
								</div>
							</div>
							<div class="py-3"></div>
							<div class="reply-container pb-3">
								<p class="mb-1 pb-3 px-2 gold-bottom" style="font-weight: bold;">댓글
									(1)</p>
								<div class="reply-list">
									<div class="reply py-3" id="reply-1">
										<div class="reply-author d-flex justify-content-between">
											<div class="author-main d-flex align-items-center px-3 pt-2">
												<figure class="profile m-0">
													<img class="profile-img" src="/imgs/content/auth-01.png"
														alt="작성자">
												</figure>
												<span class="m-nickname ps-2">Kein</b> <span
													class="post-date px-2">09-20 18:30</span>
											</div>
											<div class="author-items px-3">
												<button class="reply-1 reply-btn" onclick="">수정</button>
												<button class="reply-1 reply-btn" onclick="">삭제</button>
												<button class="reply-1 reply-btn"
													onclick="addReplyForm(this)">댓글</button>
											</div>
										</div>
										<!-- end author -->
										<div class="reply-content">
											<p class="p-3">전체 유통기한이 제조일로부터 6개월이며, 국내 반입 기준으로는 4~5개월임을
												구매시 참고 부탁드립니다^^ 위 상품은 10/31 까지의 임박재고이며 구매 시 참고 해주세요, 최상의 상태를
												위해서 빠른 섭취를 추천드립니다:)</p>
										</div>
									</div>
									<!-- end reply -->
								</div>
								<!-- end reply-list -->
								<div class="row p-2">
									<form class="comment-form" type="POST" id="main-reply"
										onsubmit="addReply(this);">
										<div class="row">
											<div class="col px-0">
												<textarea class="w-100 h-100 border comment-text p-2"
													name="reply" maxlength="300" rows=3 placeholder="댓글을 남겨주세요"
													required></textarea>
											</div>
											<div class="col px-0 comment-btn">
												<button class="btn w-100 h-100 border comment-submit"
													type="submit">등록</button>
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
		<!-- end main -->
		<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
	</div>
	<!-- end #page hfeed site -->
</body>
</html>