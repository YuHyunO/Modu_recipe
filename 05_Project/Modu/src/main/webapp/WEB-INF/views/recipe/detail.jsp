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
	<input type="hidden" id="page-type" value="recipe-detail"/>
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
		
				<!-- 사진 클릭시 팝업(hidden), 사용 안함 -->
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
				</div><!-- end 사진 팝업 -->
			
			<!-- primary 본격 시작 -->
				<!-- <div class="container col-md-2"></div> -->
				<input class="recipe-id" hidden="true" value="${detail.recipe.id}">
				<div class="col-md-9 col-lg-9 col-xl-9">
					<div id="primary" class="recipe-content m-0">
						<div class="row py-3">
							<div class="col-md-6">
								<figure class="ratio ratio-4x3 p-3 m-0">
									<img class="rounded-3" 
										src="/pics/recipe/${detail.recipe.id}/${detail.recipe.foodPhoto}"
										alt="recipe_mainImage">
								</figure>
							</div>
							<!-- end col -->
							<div class="col-md-6 d-flex flex-column justify-content-between">
								<div class="detail-desc d-flex flex-column">
									<c:if test="${detail.recipe.star} neq 0">
										<div class="recipe-rating m-0 p-1 d-flex align-items-center">
											<img class="star-rate-img me-2" src="/imgs/stars3.png" alt="stars">
											<span>${detail.recipe.star}</span>
											<span class="ps-1">(${replyCount})</span>
										</div>
									</c:if>
									<div id="recipeTitle" class="fs-4 pt-3 pb-1 m-0">${detail.recipe.title}</div>
									<div class="recipeinfo m-0">${detail.recipe.info}</div>
									<c:if test="${sessionScope.email == detail.recipe.MEmail }">
										<div class="recipe-fix">
											<a href="/recipe/update?id=${detail.recipe.id}">
												<button id="recipeUpdateBtn2" type="button" class="gold-btn">레시피 수정</button>
											</a>
										</div>
									</c:if>
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
									<img src="/pics/profile/${detail.recipe.profileImg}" 
										alt="recipe author">
									<span>Posted by 
										<a href="#">${detail.recipe.MNickname}</a>
									</span>
								</span>
								<span>
									<!-- 내가 쓴 레시피에는 친구추가 버튼이 뜨지 않도록 함 -->
	 								<c:if test="${sessionScope.email == detail.recipe.MEmail }">
										<input type="hidden" class="btn btn-outline-success subscribe-btn me-2" 
										data-email="${detail.recipe.MEmail}" 
										onclick="clickSubscribe(this)">
									</c:if>
									<c:if test="${sessionScope.email != detail.recipe.MEmail }">
										<button class="btn btn-outline-success subscribe-btn me-2" 
										data-email="${detail.recipe.MEmail}" 
										onclick="clickSubscribe(this)">친구 추가</button>
									 </c:if>
								</span>
							</div>
							<!-- end recipe author -->
							<div class="recipe-option d-flex justify-content-end col">
								<div class="d-flex flex-column text-center">
									<!-- 내가 쓴 레시피에는 스크랩 버튼이 뜨지 않도록 함 -->
	 								<c:if test="${sessionScope.email == detail.recipe.MEmail }">
	 									<input type="hidden">
									</c:if>
									<c:if test="${sessionScope.email != detail.recipe.MEmail }">
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
										<span>북마크</span>
									 </c:if>
								</div>
								<div class="d-flex flex-column text-center">
									<input type="hidden" value="http://modu-table/recipe/detail/${detail.recipe.id}" id="recipe-link"/>
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
												<strong>STEP&nbsp;${li.step}</strong>
											</div>
											<div class="step-main">${li.direction}</div>
										</div>
										<div class="col-md-4 step-img">
											<figure class="ratio ratio-4x3">
												<a href="javascript:viewLargePic('/imgs/content/thumb-1.png')"> 
													<img class="rounded-3" src="/pics/recipe/${li.RId}/${li.saveFile}" alt="Recipe-STEP-Image">
												</a>
											</figure>
										</div><br>
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
					
					<div id="review-loc" class="row comment-section border-top py-3 justify-content-center">
						<div class="row comments pt-3">
							<h2 class="comments-title text-start mb-2">
								리뷰<span>(${replyCount})</span>
							</h2>
							<ol class="comment-list" id="comment-area">
								<c:forEach items="${detail.replyList}" var="li">	<!-- forEach문으로 detail의 replyList를 li에 담는다 -->						
									<li class="comment" value="${li.list}"> <!-- li에 담은 값에서 list를 하나씩 뽑아서 배치 -->
										<div class="comment-body">
											<div class="comment-meta d-flex justify-content-between align-items-center">
												<span class="d-flex align-items-center">
													<figure class="comment-author">
														<img src="${li.profileImg}" alt="작성자"> <!-- 담긴 Data(li)에서 profileImg만 뽑아서 보여준다(여기서는 댓글을 등록한 작성자 -->
													</figure><b class="fn px-2">${li.nickname}</b> <!-- 옆에 닉네임도 출력 -->
													<span class="star-rate-block"> 
														<span class="px-2">${li.replyDate}</span> <!-- li에 담긴 DATA를 뽑아서 보여줄때 작성한 그 날짜 -->
														<img class="star-rate-img2" src="/imgs/stars4.png" alt="stars" style="width: 80px; height: 15px; margin-bottom: 5px;">
													</span>
												</span>
												<div>
													<button class="reply-135000-0001 reply-btn" onclick="">삭제</a>
													<button class="reply-135000-0001 reply-btn" onclick="addReplyForm(this)">답글</a>
												</div>
											</div>
											<div class="comment-content d-flex">
												<p class="p-2 m-0 col-9">${li.reply}</p> <!-- ???? -->
												<figure class="comment-image">
													<img class="rounded-3" src="/imgs/content/dessert-l.png" alt="comment-image">
												</figure>
											</div>
										</div>
										<c:if test="${li.nestedReply eq 1}"> <!-- nestedRelpy의 type값이 1이라면 -->
										<div id="nested-add-${li.id}" class="row view-more py-2" value="1" style="max-width: 300px;"> <!-- 대댓글의 id값을 불러온다 -->
											<a id="readd-${li.id}" href="javascript:void(0)" onclick="setUrl(this)">▼대댓글 보기</a> <!-- ????? -->
										</div>	
										</c:if>									
									</li>
									<c:if test="${li.nestedReply eq 1}">																																						
										<ol class="re-comment px-0" id="recomment-area-${li.id}">										
													<!-- 대댓글 영역 -->									 											
										</ol>
										<div id="nested-sub-add-${li.id}">
											
										</div>																			
									</c:if>																							
								</c:forEach>
								<br/>
							</ol>							
						</div>
						<!-- end row comments pt-3, 리뷰 댓글list -->
						<c:if test="${replyCount >= 5 }">							
							<div id="comment-add" class="row view-more p-2" value="5">
							<button id="add-${detail.recipe.id}" class="btn w-100 h-100 border more-view-btn" onclick="setUrl(this)" value="1">더보기</button>
							</div>								
						</c:if>
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
									</div>
								</form>
							</div>						
						</div>
					</div>
				</div>				
			</div>
			
			<%@ include file="/WEB-INF/views/common/secondary.jsp"%>
			</div>
		  </div>
		</div>
		
			<div class="row related-recipe border py-3 px-5" style="display: none;">
				<div class="container">
					<div class="row justify-content-center">
						<button class="col previous-icon px-0"></button>
						<div class="row col-sm-10 col-md-8 col-lg-6 col-xl-5 px-0">
							<h3 class="py-4 mb-0 text-start">
								<!-- <span class="text-danger">베이컨</span>추천 레시피 -->
								<span class="text-danger">${detail.tag[0].tag}</span>&nbsp;추천 레시피
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