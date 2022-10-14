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
		<div class="main">
			<div class="container main-container">
				<div class="recipes-section pt-3 pb-3">
					<div class="container">
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
										<img src="imgs/recipe/${recipe.foodPhoto}" alt="Recipe Image">
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
											<span class="d-flex align-items-center"> 
											<c:if test="${recipe.star} ne 0">
												<img class="stars" src="imgs/stars${recipe.star}.png">
												<span class="p-1 mt-1">${recipe.star}(500)</span>
											</c:if>
											</span> 
											<span class="d-flex align-items-center"> 
												<span class="p-1 mt-1">
												조회 <fmt:formatNumber type="number" maxFractionDigits="3" value="${recipe.hits}" />
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
					<div class="container">
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
										<img class="chef-pic" src="pics/profile/${member.profileImg}"
											alt="쉐프 사진">
									</figure>
									<div class="chef-info">
										<p class="mb-0">
											<small class="chef-rank"> <c:out value="${rank}" /> <fmt:formatNumber
													type="number" maxFractionDigits="3" value="${member.point}" />P
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
					<!-- end container -->
				</div>
				<!-- end blog-section -->
			</div>
			<!-- end container -->
		</div>
		<!-- end main -->
		<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
	</div>
	<!-- end #page hfeed site -->
</body>

</html>