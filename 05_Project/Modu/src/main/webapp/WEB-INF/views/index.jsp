<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<link href="css/index.css" rel="stylesheet">
<title>모두의 식탁</title>
</head>
<style type="text/css">
#lock_ad {
	width: 100%;
}

.banner {
	width: 100%;
}

#navbarSupportedContent a {
	color: #937062 !important;
	width: 70px;
	font-weight: 700;
	font-size: 15px;
	text-transform: uppercase;
	letter-spacing: 1px;
}
</style>
<body>
	<div id="page" class="hfeed site">
		<!-- start page wrapper -->
		<%@ include file="/WEB-INF/views/common/menu.jsp"%>
		<div class="head-title py-4 m-0">
			<div class="container">
				<p class="page-title m-0 fs-2">INDEX</p>
			</div>
			<!-- end container -->
		</div>
		<!-- end head-title -->

		<div class="main d-flex justify-content-center">
			<div
				class="main-container row d-flex justify-content-center m-0 py-4">

				<div class="row">
					<div id="left-side" class="col-md-2"></div>
					<div id="primary" class="content-area col-md-8">

						<div class="recipes-section pt-3 pb-3">
							<div class="container p-0">
								<div
									class="section-title d-flex justify-content-between align-items-center">
									<h4 class="mb-0 py-3 ms-3">
										베스트 레시피
										</h3>
										<a href="/recipe/list" class="btn-more h-100">더보기</a>
								</div>
								<!-- end section-title -->
								<div class="row">
									<c:forEach var="recipe" items="${recipeList}">
										<div class="col-6 col-md-3">
											<div class="recipe-thumb">
												<img src="imgs/recipe/${recipe.foodPhoto}"
													alt="Recipe Image">
											</div>
											<div class="recipe-desc">
												<div class="recipe-title">
													<a href="/recipe/detail/${recipe.id}">${recipe.title}</a>
												</div>
												<figure class="profile">
													<img class="profile-img"
														src="pics/profile/${recipe.profileImg}" alt="작성자">
													<span><em>${recipe.MNickname}</em></span>
												</figure>
												<div class="recipe-icons d-flex justify-content-between">
													<span class="d-flex align-items-center"> <c:if
															test="${recipe.star} ne 0">
															<img class="stars" src="imgs/stars${recipe.star}.png">
															<span class="p-1 mt-1">${recipe.star}(500)</span>
														</c:if>
													</span> <span class="d-flex align-items-center"> <span
														class="p-1 mt-1"> 조회 <fmt:formatNumber
																type="number" maxFractionDigits="3"
																value="${recipe.hits}" />
													</span>
													</span>
												</div>
											</div>
											<!-- end recipe-desc -->
										</div>
										<!-- end col -->
									</c:forEach>
								</div>
								<!-- end row -->
							</div>
							<!-- end container -->
						</div>
						<!-- end recipes -->

						<div class="chef-section py-3 mb-3">
							<div class="container px-0">
								<div
									class="section-title d-flex justify-content-between align-items-center ms-3">
									<h4 class="mb-0 py-3">쉐프 랭킹</h4>
									<!-- <a href="#" class="btn-more h-100">더보기</a> -->
								</div>
								<!-- end section-title -->
								<div class="row">
									<c:forEach var="member" items="${rankList}">
										<c:choose>
											<c:when test="${member.point >= 54001}">
												<c:set var="rank" value="LV7 요리의신" />
											</c:when>
											<c:when test="${member.point >= 18001}">
												<c:set var="rank" value="LV6 요리마스터" />
											</c:when>
											<c:when test="${member.point >= 9001}">
												<c:set var="rank" value="LV5 고급요리사" />
											</c:when>
											<c:when test="${member.point >= 3001}">
												<c:set var="rank" value="LV4 중급요리사" />
											</c:when>
											<c:when test="${member.point >= 1001}">
												<c:set var="rank" value="LV3 초보요리사" />
											</c:when>
											<c:when test="${member.point >= 301}">
												<c:set var="rank" value="LV2 보조요리사" />
											</c:when>
											<c:otherwise>
												<c:set var="rank" value="LV1 수습요리사" />
											</c:otherwise>
										</c:choose>
										<div class="col-4 col-md-2 chef-list text-center">
											<figure class="mb-0">
												<img class="chef-pic"
													src="pics/profile/${member.profileImg}" alt="쉐프 사진">
											</figure>
											<div class="chef-info">
												<p class="mb-0">
													<small class="chef-rank"> <c:out value="${rank}" />
														<fmt:formatNumber type="number" maxFractionDigits="3"
															value="${member.point}" />P
													</small>
												</p>
												<a class="chef-name" href="#">${member.nickname}</a>
											</div>
										</div>
										<!-- end col -->
									</c:forEach>
								</div>
								<!-- end row -->
							</div>
							<!-- end 쉐프 container -->
						</div>
						<!-- end chef-section -->
					</div>
					<!-- end primary(주요 메인영역) 종료-->

					<!--사이드 영역(secondary) 시작-->
					<div id="secondary" class="col-md-2">
<<<<<<< HEAD
					
						<!--새로운 태그들-->
						<div class="widget">
							<div class="widget-title-outer">
								<h3 class="widget-title">News Recipe Tags</h3>
							</div>
							<div class="tagcloud">
								<a href="#">#나시고랭</a>
								<a href="#">#칵테일새우</a>
								<a href="#">#스테이크</a>
								<a href="#">#돈까스</a>
								<a href="#">#새우볶음밥</a>
							</div>
						</div><!-- end widget -->
						
=======
>>>>>>> cf32888c5b4dc99f2ecd65234b8d8b66add6a79c
						<div class="widget post-type-widget pt-3 pb-3">
							<!-- widget 최근 본 게시물 -->
							<div class="widget-title-outer">
								<h3 class="widget-title">새로운 게시물</h3>
							</div>
							<ul>
								<li><span class="post-category"> <a href="#">카테고리1-레시피</a>
								</span>
									<figure class="post-thumbnail">
										<a href="#"> <img class="rounded-3"
											src="/imgs/content/thumb-post-01.png" alt="" />
										</a>
									</figure>
									<h2 class="post-title">
										<a href="#">손쉬운 수제버거 레시피</a>
									</h2></li>
								<li><span class="post-category"> <a href="#">쉐프
											랭킹</a>
								</span>
									<figure class="post-thumbnail">
										<a href="#"> <img class="rounded-3"
											src="/imgs/content/thumb-post-02.png" alt="" />
										</a>
									</figure>
									<h2 class="post-title">
										<a href="#">미슐랭 쉐프의 요리 꿀팁</a>
									</h2></li>
								<li><span class="post-category"> <a href="#">자유게시판</a>
								</span>
									<figure class="post-thumbnail">
										<a href="#"> <img class="rounded-3"
											src="/imgs/content/thumb-post-03.png" alt="" />
										</a>
									</figure>
									<h2 class="post-title">
										<a href="#">가산역에 새로 생긴 돼지갈비집 맛있나요?</a>
									</h2></li>
								<li><span class="post-category"> <a href="#">Vegetable</a>
								</span>
									<figure class="post-thumbnail">
										<a href="#"> <img class="rounded-3"
											src="/imgs/content/thumb-post-04.png" alt="" />
										</a>
									</figure>
									<h2 class="post-title">
										<a href="#">토마토바나나쉐이크 만들기</a>
									</h2></li>
						</div>
						<!-- end widget 최근 본 게시물 -->
						<!--새로운 태그들-->
						<div class="widget">
							<div class="widget-title-outer">
								<h3 class="widget-title">새로 등록된 태그</h3>
							</div>
							<div class="tagcloud d-flex">
							<div class="row px-2">
								<a href="#" class="me-1 mb-1">#나시고랭</a>
								<a href="#" class="me-1 mb-1">#칵테일새우</a>
								<a href="#" class="me-1 mb-1">#스테이크</a>
								<a href="#" class="me-1 mb-1">#돈까스</a>
								<a href="#" class="me-1 mb-1">#새우볶음밥</a>
							</div>
								
								
							</div>
						</div>
						<!-- end widget -->

						<!--광고 배너 이미지-->
						<div class="widget">
							<a href="#"> <img id="lock_ad" class="rounded-3 banner"
								src="/imgs/index/lock_ad.gif" alt="lockandlock_ad.gif" />
							</a>
						</div>
						<!-- end 광고 배너 -->
					</div>
					<!-- end #secondary, 사이드영역 끝 -->
				</div>
				<!-- end row -->
			</div>
			<!-- end main=container -->
		</div>
		<!-- end main -->
		<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
	</div>
	<!-- end #page hfeed site -->
</body>
</html>