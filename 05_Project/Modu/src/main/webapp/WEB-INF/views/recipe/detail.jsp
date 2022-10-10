<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<!-- 공통 부분 END -->
<link href="/css/recipe-detail.css" rel="stylesheet">
<script src="/js/recipe-detail.js"></script>

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

		<div class="main">
			<!-- 사진 팝업 -->
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
			</div>
			<!-- end 사진 팝업 -->

			<div class="row py-3 px-5">
				<div class="container col-md-2">
					<input hidden="true">
				</div>
				<div class="container col-md-8">
					<div class="recipe-content m-0" id="primary">
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
									<div class="recipe-rating m-0">
										<img class="star-rate-img" src="/imgs/stars3.png" alt="stars">
										<span>4.3</span> <span class="ps-1">(4)</span>
									</div>
									<div class="fs-4 py-3 m-0">10분요리) 양배추베이컨볶음!</div>
									<div class="m-0">양배추가 위에 좋답니다~ 저희집 식구들이 위가 좋지 않은편이라서 위에
										좋다는 양배추를 가지고 음식을 많이 해 먹는데요~ 보통은 샐러드나 양배추를 삶아서 쌈을 싸먹거나 짜장에 듬뿍넣어
										먹기도 하지요~ 오늘은 좀 간단 하면서도 아이가 좋아하는 방법으로 만들어 봅니다~~ 양배추 베이컨볶음 요거요거
										꼭 꼭 드셔보세요 아마 양배추를 사랑하게 되실거예요~~^^</div>
								</div>
								<!-- recipe desc -->
								<div class="recipe-info py-3 d-flex justify-content-end">
									<span class="recipe-amount pt-5 px-2">4인분</span> <span
										class="recipe-time pt-5 px-2">10분 이내</span> <span
										class="recipe-person pt-5 px-2">아무나</span>
								</div>

							</div>
							<!-- end col -->
						</div>
						<!-- end 요리 소개 -->

						<div
							class="row option-section border-top border-bottom py-3 d-flex justify-content-between">
							<div class="recipe-auth align-self-center w-50">
								<img src="/imgs/content/auth-03.png" alt="recipe author">
								<span>Posted by <a href="#">Dina Makulatuwa</a></span> <span><button
										class="btn btn-outline-success subscribe-btn"
										onclick="clickSubscribe(this)">소식받기</button></span>
							</div>
							<!-- end recipe author -->
							<div class="recipe-option d-flex justify-content-end w-50">
								<div class="d-flex flex-column text-center">
									<button class="recipe-scrap" onclick="clickScrap(this)">
									</button>
									<span>스크랩</span>
								</div>
								<div class="d-flex flex-column text-center">
									<button class="recipe-share"></button>
									<span>공유</span>
								</div>
								<div class="d-flex flex-column text-center">
									<button class="recipe-reply-top"></button>
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
									<li>3 pound sesame snaps powder gingerbread</li>
									<li>2 ounce lemon drops chupa chups bear</li>
									<li>1 (1.25 ounce) package cookie cotton candy</li>
									<li>1 (14.5 ounce) marshmallow liquorice</li>
									<li>1 cup crushed tortilla chips</li>
									<li>1/2 cup sour cream (optional)</li>
								</ul>
							</div>
							<!-- end col -->
							<div class="source col-md-6">
								<p>
									<strong>[양념]</strong>
								</p>
								<ul>
									<li>3 pound sesame snaps powder gingerbread</li>
									<li>2 ounce lemon drops chupa chups bear</li>
									<li>1 (1.25 ounce) package cookie cotton candy</li>
									<li>1 (14.5 ounce) marshmallow liquorice</li>
									<li>1 cup crushed tortilla chips</li>
									<li>1/2 cup sour cream (optional)</li>
									<li>1/4 cup chopped red onions</li>
									<li>1/2 cup sour cream (optional)</li>
									<li>1/4 cup chopped red onions</li>
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
									<div class="row col-md-8">
										<div class="step-title">
											<strong>STEP 1</strong>
										</div>
										<div class="step-main">Duist, vel illum dolore odio
											dignissim qui</div>
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
								</div>
								<div class="row d-flex step justify-content-center py-2">
									<div class="row col-md-8">
										<div class="step-title">
											<strong>STEP 2</strong>
										</div>
										<div class="step-main">Duist, vel illum dolore odio
											dignissim qui</div>
									</div>
									<div class="col-md-4 step-img">
										<figure class="ratio ratio-4x3">
											<a
												href="javascript:viewLargePic('/imgs/content/thumb-2.png')">
												<img class="rounded-3" src="/imgs/content/thumb-2.png"
												alt="Recipe Image">
											</a>
										</figure>
									</div>
								</div>
								<div class="row d-flex step justify-content-center py-2">
									<div class="row col-md-8">
										<div class="step-title">
											<strong>STEP 3</strong>
										</div>
										<div class="step-main">Duist, vel illum dolore odio
											dignissim qui</div>
									</div>
									<div class="col-md-4 step-img">
										<figure class="ratio ratio-4x3">
											<a
												href="javascript:viewLargePic('/imgs/content/thumb-3.png')">
												<img class="rounded-3" src="/imgs/content/thumb-3.png"
												alt="Recipe Image">
											</a>
										</figure>
									</div>
								</div>
								<div class="row d-flex step justify-content-center py-2">
									<div class="row col-md-8">
										<div class="step-title">
											<strong>STEP 4</strong>
										</div>
										<div class="step-main">Duist, vel illum dolore odio
											dignissim qui</div>
									</div>
									<div class="col-md-4 step-img">
										<figure class="ratio ratio-4x3">
											<a
												href="javascript:viewLargePic('/imgs/content/thumb-4.png')">
												<img class="rounded-3" src="/imgs/content/thumb-4.png"
												alt="Recipe Image">
											</a>
										</figure>
									</div>
								</div>
								<div class="row d-flex step justify-content-center py-2">
									<div class="row col-md-8">
										<div class="step-title">
											<strong>STEP 5</strong>
										</div>
										<div class="step-main">Duist, vel illum dolore odio
											dignissim qui</div>
									</div>
									<div class="col-md-4 step-img">
										<figure class="ratio ratio-4x3">
											<a
												href="javascript:viewLargePic('/imgs/content/thumb-5.png')">
												<img class="rounded-3" src="/imgs/content/thumb-5.png"
												alt="Recipe Image">
											</a>
										</figure>
									</div>
								</div>
								<div class="row d-flex step justify-content-center py-2">
									<div class="row col-md-8">
										<div class="step-title">
											<strong>STEP 6</strong>
										</div>
										<div class="step-main">Duist, vel illum dolore odio
											dignissim qui</div>
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
								</div>
								<div class="row d-flex step justify-content-center py-2">
									<div class="row col-md-8">
										<div class="step-title">
											<strong>STEP 7</strong>
										</div>
										<div class="step-main">Duist, vel illum dolore odio
											dignissim qui</div>
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
								</div>
							</div>
						</div>
						<!-- end col -->
					</div>
					<!-- end 조리순서 -->
					<div class="row tag-section d-flex">
						<span class="tag-head ms-2 py-3"> </span> <span
							class="tag-body py-3 w-75"> <a href="#"><em>#양배추</em></a><a
							href="#"><em>#베이컨</em></a><a href="#"><em>#고기</em></a><a href="#"><em>#볶음</em></a>
						</span>
					</div>
					<!-- end 태그 -->
					<div
						class="row comment-section border-top py-3 justify-content-center">
						<div class="row comments pt-3">
							<h2 class="comments-title text-start mb-2">
								리뷰<span>(4)</span>
							</h2>
							<ol class="comment-list" id="comment-0-0">
								<li class="comment" id="comment-135000-0001">
									<!-- start comment -->
									<div class="comment-body">
										<div
											class="comment-meta d-flex justify-content-between align-items-center">
											<span class="d-flex align-items-center">
												<figure class="comment-author">
													<img src="/imgs/content/auth-00.png" alt="작성자">
												</figure> <!-- end comment-author vcard --> <b class="fn ps-2">Jessica</b>
												<span class="star-rate-block"> <span class="px-2">09-20
														18:30</span> <img class="star-rate-img" src="/imgs/stars4.png"
													alt="stars"
													style="width: 80px; height: 15px; margin-bottom: 5px;">
											</span>
											</span>
											<div>
												<button class="reply-135000-0001 reply-btn" onclick="">
													수정</a>
													<button class="reply-135000-0001 reply-btn" onclick="">
														삭제</a>
														<button class="reply-135000-0001 reply-btn"
															onclick="addReplyForm(this)">
															답글</a>
											</div>
										</div>
										<!-- end comment-meta -->
										<div class="comment-content d-flex">
											<p class="p-2 m-0 col-9">Duis autem vel eum iriure dolor
												in hendrerit in vulputate velit esse molestie consequat, vel
												illum dolore eu feugiat nulla facilisis at vero eros et
												accumsan et iusto odio dignissim qui blandit praesent
												luptatum zzril delenit augue duis dolore te feugait nulla
												facilisi.</p>
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
								<ol class="re-comment px-0" id="recomment-135000-0001">
								</ol>
								<li class="comment" id="comment-135000-0002">
									<!-- start comment -->
									<div class="comment-body">
										<div
											class="comment-meta d-flex justify-content-between align-items-center">
											<span class="d-flex align-items-center">
												<figure class="comment-author">
													<img src="/imgs/content/auth-01.png" alt="작성자">
												</figure> <!-- end comment-author vcard --> <b class="fn ps-2">Paijo</b>
												<span class="star-rate-block"> <span class="px-2">09-20
														18:32</span> <img class="star-rate-img" src="/imgs/stars3.png"
													alt="stars"
													style="width: 80px; height: 15px; margin-bottom: 5px;">
											</span>
											</span>
											<div>
												<button class="reply-135000-0002 reply-btn" onclick="">
													수정</a>
													<button class="reply-135000-0002 reply-btn" onclick="">
														삭제</a>
														<button class="reply-135000-0002 reply-btn"
															onclick="addReplyForm(this)">
															답글</a>
											</div>
										</div>
										<!-- end comment-meta -->
										<div class="comment-content d-flex">
											<p class="p-2 m-0 col-9">Duis autem vel eum iriure dolor
												in hendrerit in vulputate velit esse molestie consequat, vel
												illum dolore eu feugiat nulla facilisis at vero eros et
												accumsan et iusto odio dignissim qui blandit praesent
												luptatum zzril delenit augue duis dolore te feugait nulla
												facilisi.</p>
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
								<ol class="re-comment px-0" id="recomment-135000-0002">
								</ol>
							</ol>
							<!-- end comment-list -->
						</div>
						<!-- end 리뷰 -->
						<div class="row view-more py-2" style="max-width: 630px;">
							<button class="btn w-100 h-100 border more-view-btn">더보기</button>
						</div>
						<div class="row comment-write p-2">
							<div class="row">
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
							<div class="row pb-2">
								<form class="comment-form" type="POST" id="reply-form-0-0"
									onsubmit="addReply(this);">
									<div class="row">
										<div class="col px-0 comment-file"
											style="min-width: 100px; max-width: 100px;">
											<input type="file" hidden> <img class="border"
												src="/imgs/pic_none.gif" alt="파일첨부" width="100" height="100"
												style="cursor: pointer;">
										</div>
										<div class="col px-0">
											<textarea class="w-100 h-100 border comment-text"
												name="reply" maxlength="300" placeholder="리뷰를 남겨주세요"
												required></textarea>
										</div>
										<div class="col px-0 comment-btn"
											style="min-width: 120px; max-width: 120px;">
											<button class="btn w-100 h-100 border comment-submit"
												type="submit">등록</button>
										</div>
									</div>
								</form>
								<!-- end comment-form -->
							</div>
						</div>
						<!-- end 리뷰 작성 -->
					</div>
					<!-- end 리뷰 -->
				</div>
				<!-- end 메인 컨테이너 -->
				<div class="container col-md-2" id="secondary">
					<div class="widget post-type-widget px-3">
						<div class="widget-title-outer">
							<h3 class="widget-title pb-2 mb-0">최근 본 레시피</h3>
						</div>
						<ul>
							<li class="d-flex flex-column"><span
								class="post-category pb-1 m-0"> <a href="#">Uncategorized</a>
							</span>
								<figure class="post-thumbnail w-100 h-100">
									<a href="#"><img src="/imgs/content/thumb-post-01.png"
										alt=""></a>
								</figure>
								<h2 class="post-title m-0">
									<a href="#">Lorem Ipsum Dolor Sit Amet, Consetetuer
										Adipiscing Elit</a>
								</h2></li>
							<li class="d-flex flex-column"><span
								class="post-category pb-1 m-0"> <a href="#">Cooking
										Tips</a>
							</span>
								<figure class="post-thumbnail w-100 h-100">
									<a href="#"><img src="/imgs/content/thumb-post-02.png"
										alt=""></a>
								</figure>
								<h2 class="post-title m-0">
									<a href="#">Euismod Tincidunt ut Laoreet Dolore Magna
										Aliquam</a>
								</h2></li>
							<li class="d-flex flex-column"><span
								class="post-category pb-1 m-0"> <a href="#">Kitchen</a>
							</span>
								<figure class="post-thumbnail w-100 h-100">
									<a href="#"><img src="/imgs/content/thumb-post-03.png"
										alt=""></a>
								</figure>
								<h2 class="post-title m-0">
									<a href="#">Ullamcorper Suscipit Lobortis Nisl ut Aliquip
										Commodo Consequat</a>
								</h2></li>
							<li class="d-flex flex-column"><span
								class="post-category pb-1 m-0"> <a href="#">Vegetable</a>
							</span>
								<figure class="post-thumbnail w-100 h-100">
									<a href="#"><img src="/imgs/content/thumb-post-04.png"
										alt=""></a>
								</figure>
								<h2 class="post-title m-0">
									<a href="#">Quod Mazim Placerat Facer Possim Assum</a>
								</h2></li>
						</ul>
					</div>
					<!-- end widget -->
				</div>
				<!-- end secondary -->
			</div>
			<div class="row related-recipe border py-3 px-5">
				<div class="container">
					<div class="row justify-content-center">
						<button class="col previous-icon px-0"></button>
						<div class="row col-md-11 col-lg-8 px-0">
							<h3 class="py-4 mb-0 text-start">
								<span class="text-danger">베이컨</span> 추천 레시피
							</h3>
							<div class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="/imgs/content/thumb-1.png" alt="Recipe Image">
									<a href="#" class="bookmarker"><i class="fas fa-bookmark"></i></a>
									<a href="#" class="view-recipe">VIEW RECIPE</a>
								</div>
								<div class="recipe-desc">
									<h2 class="recipe-title">
										<a href="#">Salad Nicoise</a>
									</h2>
									<p>
										<em>By Lina Sukowati</em>
									</p>
									<span><i class="fas fa-clock"></i>&nbsp;9 Minutes</span>
								</div>
								<!-- end recipe-desc -->
							</div>
							<!-- end col -->
							<div class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="/imgs/content/thumb-2.png" alt="Recipe Image">
									<a href="#" class="bookmarker"><i class="fas fa-bookmark"></i></a>
									<a href="#" class="view-recipe">VIEW RECIPE</a>
								</div>
								<div class="recipe-desc">
									<h2 class="recipe-title">
										<a href="#">Grilled Beef Steak</a>
									</h2>
									<p>
										<em>By Eka Nurwasilah</em>
									</p>
									<span><i class="fas fa-clock"></i>&nbsp;26 Minutes</span>
								</div>
								<!-- end recipe-desc -->
							</div>
							<!-- end col -->
							<div class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="/imgs/content/thumb-3.png" alt="Recipe Image">
									<a href="#" class="bookmarker"><i class="fas fa-bookmark"></i></a>
									<a href="#" class="view-recipe">VIEW RECIPE</a>
								</div>
								<div class="recipe-desc">
									<h2 class="recipe-title">
										<a href="#">Tiger Prawns Roasted</a>
									</h2>
									<p>
										<em>By Nurjanah</em>
									</p>
									<span><i class="fas fa-clock"></i>&nbsp;27 Minutes</span>
								</div>
								<!-- end recipe-desc -->
							</div>
							<!-- end col -->
							<div class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="/imgs/content/thumb-4.png" alt="Recipe Image">
									<a href="#" class="bookmarker"><i class="fas fa-bookmark"></i></a>
									<a href="#" class="view-recipe">VIEW RECIPE</a>
								</div>
								<div class="recipe-desc">
									<h2 class="recipe-title">
										<a href="#">Korean Soup</a>
									</h2>
									<p>
										<em>By Lina Sukowati</em>
									</p>
									<span><i class="fas fa-clock"></i>&nbsp;45 Minutes</span>
								</div>
								<!-- end recipe-desc -->
							</div>
							<!-- end col -->
						</div>
						<!-- end list -->
						<button class="col next-icon px-0"></button>
					</div>
					<!-- end row -->
				</div>
				<!-- end container -->
			</div>
			<!-- end 추천 레시피 -->
		</div>
		<!-- end main -->
		<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
	</div>
	<!-- end #page hfeed site -->
</body>
</html>