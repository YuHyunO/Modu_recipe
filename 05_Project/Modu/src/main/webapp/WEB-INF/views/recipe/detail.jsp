<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<link href="/css/recipe-detail.css" rel="stylesheet">
<script src="/js/recipe-detail.js"></script>
<script src="/js/recipe-detailSub.js"></script>
<link href="/css/common.css" rel="stylesheet">
<title>모두의 식탁 - 레시피</title>
</head>
<body>
	<div id="page" class="hfeed site">
		<!-- start page wrapper -->
		<%@ include file="/WEB-INF/views/common/menu.jsp"%>
		<div class="head-title py-4 m-0">
			<div class="container">
				<p class="page-title m-0 fs-2">RECIPE</p>
			</div>
			<!-- end container -->
		</div>
		<!-- end head-title -->
		<div class="main d-flex justify-content-center">
			<div class="main-container row d-flex justify-content-center m-0 py-4">
				
		<div class="row">
			<div id="main-content" class="content-area col-md-9">
		
			<!-- 사진 팝업(hidden) -->
			<div id="viewPicDivModal" class="modal" role="dialog"
				aria-hidden="true" style="display: none;">
				<div class="modal-dialog">
					<div class="modal-content" style="padding: 0; width: 600px;">
						<div class="modal-header">
							<h4 class="modal-title">사진</h4>
						</div>
						<div class="modal-body" style="padding: 5px; max-width: 600px;">
							<div id="viewPicModalCont"></div>
						</div>
					</div>
				</div>
<<<<<<< HEAD
			</div>
			<!-- end 사진 팝업 -->
			<div class="row py-3 px-5">
				<div class="container col-md-2">
					<input class="recipe-id" hidden="true" value="${detail.recipe.id}">
				</div>
				<div class="container col-sm-10 col-md-8 col-lg-6 col-xl-5">
					<div class="recipe-content m-0" id="primary">
=======
			</div><!-- end 사진 팝업 -->
			
			<!-- primary 본격 시작 -->
				<!-- <div class="container col-md-2"></div> -->
				<input class="recipe-id" hidden="true" value="${id}">
				<div class="col-md-9 col-lg-6 col-xl-9">
					<div id="primary" class="recipe-content m-0">
>>>>>>> 25fdba1eb0510b0ce3028c831f40902ed642b7f9
						<div class="row py-3">
							<div class="col-md-6">
								<figure class="ratio ratio-4x3 p-3 m-0">
									<img class="rounded-3" src="/imgs/content/image-recipe.png"
										alt="IMG 01">
								</figure>
							</div>
							<!-- end col -->
							<div class="col-md-6 d-flex flex-column justify-content-between">
								<div class="detail-desc d-flex flex-column">
									<div class="recipe-rating m-0 p-1 d-flex align-items-center">
										<img class="star-rate-img me-2" src="/imgs/stars3.png" alt="stars">
										<span>${detail.recipe.star}</span> <span class="ps-1">(${replyCount})</span>
									</div>
									<div class="fs-4 py-3 m-0">${detail.recipe.title}</div>
									<div class="m-0">${detail.recipe.info}</div>
								</div>
								<!-- recipe desc -->
								<div class="recipe-info py-3 d-flex justify-content-end">
									<span class="recipe-amount pt-5 px-2">${detail.recipe.serving}</span>
									<span class="recipe-time pt-5 px-2">${detail.recipe.cookTime}</span>
									<span class="recipe-person pt-5 px-2">${detail.recipe.difficultyLevel}</span>
								</div>

							</div>
							<!-- end col -->
						</div>
						<!-- end 요리 소개 -->
						<div
							class="row option-section border-top border-bottom py-3 d-flex justify-content-between">
							<div class="recipe-auth align-self-center col px-2">
								<span>
									<img src="/imgs/content/auth-03.png" alt="recipe author">
									<span>Posted by 
										<a href="#">${detail.recipe.MNickname}</a>
									</span>
								</span>
								<span>
									<button class="btn btn-outline-success subscribe-btn me-2" data-email="${detail.recipe.MEmail}" onclick="clickSubscribe(this)">친구 추가</button>
								</span>
							</div>
							<!-- end recipe author -->
							<div class="recipe-option d-flex justify-content-end col">
								<div class="d-flex flex-column text-center">
									<c:choose>
										<c:when test="${scrapState eq true}">
											<button class="recipe-scrap-clicked" onclick="clickScrap(this)">
											</button>
										</c:when>
										<c:when test="${scrapState eq false}">
											<button class="recipe-scrap" onclick="clickScrap(this)">
											</button>
										</c:when>
									</c:choose> 
									<span>스크랩</span>
								</div>
								<div class="d-flex flex-column text-center">
									<input type="hidden" value="http://modu-table/recipe/detail/${id}" id="recipe-link"/>
									<button class="recipe-share" onclick="shareLink(this)"></button>
									<span>공유</span>
								</div>
								<div class="d-flex flex-column text-center">
									<button class="recipe-reply-top" onclick="moveScroll(this)"></button>
									<span>댓글</span>
								</div>
							</div>
							<!-- end recipe option -->
						</div>
						<div class="row py-3">
							<div class="ingredient col-md-6">
								<p>
									<strong>[재료]</strong>
								</p>
								<ul>
									<c:forEach items="${detail.ingredient}" var="li">
										<c:if test="${li.ingredientType eq 0}">
											<li>${li.ingredient}&nbsp;${li.quantity}</li>
										</c:if>
									</c:forEach>
								</ul>
							</div>
							<!-- end col -->
							<div class="source col-md-6">
								<p>
									<strong>[양념]</strong>
								</p>
								<ul>
									<c:forEach items="${detail.ingredient}" var="li">
										<c:if test="${li.ingredientType eq 1}">
											<li>${li.ingredient}&nbsp;${li.quantity}</li>
										</c:if>
									</c:forEach>
								</ul>
							</div>
							<!-- end col -->
						</div>
						<!-- end 재료, 양념 -->
					</div>
					<!-- end recipe content -->
					<div class="row py-3 direction-row border-top border-bottom">
						<div class="direction">
							<p>
								<strong>[조리순서]</strong>
							</p>
							<div class="p-0">
								<div class="row d-flex step justify-content-center py-2">
									<c:forEach items="${detail.direction}" var="li">
										<div class="row col-md-8">
											<div class="step-title">
												<strong>STEP &nbsp; ${li.step}</strong>
											</div>
											<div class="step-main">${li.direction}</div>
										</div>
										<div class="col-md-4 step-img">
											<figure class="ratio ratio-4x3">
												<a
													href="javascript:viewLargePic('/imgs/content/thumb-1.png')">
													<img class="rounded-3" src="/imgs/content/thumb-1.png"
													alt="Recipe Image">
												</a>
											</figure>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<!-- end col -->
					</div>
					<!-- end 조리순서 -->
					
					<div class="row tag-section d-flex">
						<span class="tag-head ms-2 py-3"></span>
						<c:forEach items="${detail.tag}" var="li">
							<span class="tag-body py-3 w-75"><a href="#"><em>${li.tag}</em></a>								
						</c:forEach>
					</div>
					<!-- end 태그 -->
					
					<div
						class="row comment-section border-top py-3 justify-content-center">
						<div class="row comments pt-3">
							<h2 class="comments-title text-start mb-2">
								리뷰<span>(${replyCount})</span>
							</h2>
							<ol class="comment-list" id="comment-0-0">
								<c:forEach items="${detail.replyList}" var="li">
									<li class="comment" id="comment-135000-0001">
										<!-- start comment -->
										<div class="comment-body">
											<div
												class="comment-meta d-flex justify-content-between align-items-center">
												<span class="d-flex align-items-center">
													<figure class="comment-author">
														<img src="/imgs/content/auth-00.png" alt="작성자">
													</figure> <!-- end comment-author vcard --> <b class="fn px-2">${li.nickname}</b>
													<span class="star-rate-block"> <span class="px-2">${li.replyDate}</span>
														<img class="star-rate-img2" src="/imgs/stars4.png"
														alt="stars"
														style="width: 80px; height: 15px; margin-bottom: 5px;">
												</span>
												</span>
												<div>
													<button class="reply-135000-0001 reply-btn"
														onclick="addReplyForm(this)">
													수정</a>
													<button class="reply-135000-0001 reply-btn"
														onclick="location.href='/recipe/del.do?id=${li.id}'">
													삭제</a>
													<button class="reply-135000-0001 reply-btn"
														onclick="addReplyForm(this)">
													답글</a>
												</div>
											</div>
											<!-- end comment-meta -->
											<div class="comment-content d-flex">
												<p class="p-2 m-0 col-9">${li.reply}</p>
												<figure class="comment-image">
													<img class="rounded-3" src="/imgs/content/dessert-l.png"
														alt="comment-image">
												</figure>
												<!-- end comment-author vcard -->
											</div>
											<!-- end comment-content -->
										</div> <!-- end comment-body -->
									</li>
									<!-- end li -->
								</c:forEach>
							 <ol class="re-comment px-0" id="recomment-135000-0001">
							 </ol>
							 <li class="comment" id="comment-135000-0002">									
							</ol>
							<!-- end comment-list -->
						</div>
						<!-- end 리뷰 -->
						<c:if test="${replyCount != 0}">
							<div class="row view-more p-2">
								<button class="btn w-100 h-100 border more-view-btn"
									onclick="showMore(this)">더보기</button>
							</div>
						</c:if>
						<!-- end row comments pt-3, 리뷰 댓글list -->

						<!-- 별점+댓글창 -->
						<div class="comment-write p-2">
							<div class="row w-100 m-0">
								<div class="col py-1">
									<span class="star-title me-2 align-middle">별점</span> <span
										class="btn star-point" id="star1" onclick="clickStar(this)"></span>
									<span class="btn star-point" id="star2"
										onclick="clickStar(this)"></span> <span class="btn star-point"
										id="star3" onclick="clickStar(this)"></span> <span
										class="btn star-point" id="star4" onclick="clickStar(this)"></span>
									<span class="btn star-point" id="star5"
										onclick="clickStar(this)"></span>
								</div>
							</div>
							<!-- end star row -->
							
							<div class="row w-100 m-0 pb-2">
								<form class="comment-form" type="POST" id="reply-form-0-0"
									onSubmit="addReply(this)">
									<div class="row">
										<div>
											<input name="mEmail" type="hidden" value="admin@modu.com">
											<input name="rId" type="hidden" value="150"> <input
												name="mNickname" type="hidden" value="관리자"> <input
												name="profileImg" type="hidden"
												value="default_profile_img.png">
										</div>
										<div class="col px-0 comment-file"
											style="min-width: 100px; max-width: 100px;">
											<input type="file" hidden> <img class="border"
												src="/imgs/pic_none.gif" alt="파일첨부" width="100" height="100"
												style="cursor: pointer;">
										</div>
										<div class="col px-0">
											<textarea id=contentsReply
												class="w-100 h-100 border comment-text" name="reply"
												maxlength="300" placeholder="리뷰를 남겨주세요" required></textarea>
										</div>
										<div class="col px-0 comment-btn"
											style="min-width: 120px; max-width: 120px;">
											<button id="insertReply"
												class="btn w-100 h-100 border comment-submit" type="submit">등록</button>
										</div>
									</div><!-- end row -->
								</form><!-- end comment-form -->
							</div> <!-- end row w-100 pb-2 -->
							
						</div> <!-- end class="comment-write p-2", comment-write 리뷰 작성 -->
					</div><!-- end row comment-section, 리뷰 전체 -->
				</div>

			</div><!-- end primary(원래 main) -->
			
			<!-- 사이드 영역(secondary), 모듈화 -->
			<%@ include file="/WEB-INF/views/common/secondary.jsp"%>
			</div><!-- end row(primary+secondary)-->
		  </div><!-- end main-container -->
		</div><!-- end main -->
		
			<div class="row related-recipe border py-3 px-5">
				<div class="container">
					<div class="row justify-content-center">
						<button class="col previous-icon px-0"></button>
						<div class="row col-sm-10 col-md-8 col-lg-6 col-xl-5 px-0">
							<h3 class="py-4 mb-0 text-start">
								<span class="text-danger">베이컨</span> 추천 레시피
							</h3>
							<div class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="/imgs/content/thumb-2.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<h2 class="recipe-title p-0">
										<a href="#">제목</a>
									</h2>
									<figure class="profile mb-1">
										<img class="profile-img" src="/imgs/content/auth-00.png"
											alt="작성자">
										<span><em> 닉네임</em></span>
									</figure>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> 
											<img class="stars" src="/imgs/stars5.png"> 
											<span class="p-1 mt-1">#</span>
										</span>
									</div>
								</div>
								<!-- end recipe-desc -->
							</div><!-- end col -->
							<div class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="/imgs/content/thumb-2.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<h2 class="recipe-title p-0">
										<a href="#">제목</a>
									</h2>
									<figure class="profile mb-1">
										<img class="profile-img" src="/imgs/content/auth-00.png"
											alt="작성자">
										<span><em> 닉네임</em></span>
									</figure>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> 
											<img class="stars" src="/imgs/stars5.png"> 
											<span class="p-1 mt-1">#</span>
										</span>
									</div>
								</div><!-- end recipe-desc -->
							</div><!-- end col -->
							<div class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="/imgs/content/thumb-2.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<h2 class="recipe-title p-0">
										<a href="#">제목</a>
									</h2>
									<figure class="profile mb-1">
										<img class="profile-img" src="/imgs/content/auth-00.png"
											alt="작성자">
										<span><em> 닉네임</em></span>
									</figure>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> 
											<img class="stars" src="/imgs/stars5.png"> 
											<span class="p-1 mt-1">#</span>
										</span>
									</div>
								</div>
								<!-- end recipe-desc -->
							</div><!-- end col -->
							<div class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="/imgs/content/thumb-2.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<h2 class="recipe-title p-0">
										<a href="#">제목</a>
									</h2>
									<figure class="profile mb-1">
										<img class="profile-img" src="/imgs/content/auth-00.png"
											alt="작성자">
										<span><em> 닉네임</em></span>
									</figure>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> 
											<img class="stars" src="/imgs/stars5.png"> 
											<span class="p-1 mt-1">#</span>
										</span>
									</div>
								</div><!-- end recipe-desc -->
							</div><!-- end col -->
						</div><!-- end list -->
						
				<button class="col next-icon px-0"></button>
					</div><!-- end row -->
				</div><!-- end container -->
			</div><!-- end row related-recipe/추천 레시피 -->

		<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
	</div><!-- end #page hfeed site -->

</body>
</html>