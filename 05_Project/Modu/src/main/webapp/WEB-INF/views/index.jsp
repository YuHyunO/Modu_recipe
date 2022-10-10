<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
								<a href="recipe-list.html" class="btn-more h-100">더보기</a>
						</div>
						<!-- end section-title -->
						<div class="row">
							<div class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="imgs/content/thumb-1.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<div class="recipe-title">
										<a href="#">전참시 유병재가 만든 찜닭! 꽈리고추닭볶음</a>
									</div>
									<figure class="profile">
										<img class="profile-img" src="imgs/content/auth-00.png"
											alt="작성자">
										<span><em>펭귄하늘을날다</em></span>
									</figure>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> <img
											class="stars" src="imgs/stars5.png"> <span
											class="p-1 mt-1">4.6(500)</span>
										</span> <span class="d-flex align-items-center"> <span
											class="p-1 mt-1">조회 300,000</span>
										</span>
									</div>
								</div>
								<!-- end recipe-desc -->
							</div>
							<!-- end col -->
							<div class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="imgs/content/thumb-2.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<div class="recipe-title">
										<a href="#">전참시 유병재가 만든 찜닭! 꽈리고추닭볶음</a>
									</div>
									<p>
										<em>펭귄하늘을날다</em>
									</p>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> <img
											class="stars" src="imgs/stars5.png"> <span
											class="p-1 mt-1">4.6(500)</span>
										</span> <span class="d-flex align-items-center"> <span
											class="p-1 mt-1">조회 300,000</span>
										</span>
									</div>
								</div>
								<!-- end recipe-desc -->
							</div>
							<!-- end col -->
							<div class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="imgs/content/thumb-3.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<div class="recipe-title">
										<a href="#">전참시 유병재가 만든 찜닭! 꽈리고추닭볶음</a>
									</div>
									<p>
										<em>펭귄하늘을날다</em>
									</p>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> <img
											class="stars" src="imgs/stars5.png"> <span
											class="p-1 mt-1">4.6(500)</span>
										</span> <span class="d-flex align-items-center"> <span
											class="p-1 mt-1">조회 300,000</span>
										</span>
									</div>
								</div>
								<!-- end recipe-desc -->
							</div>
							<!-- end col -->

							<div class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="imgs/content/thumb-4.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<div class="recipe-title">
										<a href="#">순두부찌개. 바지락, 고기 없이도 기가 막힌 순두부찌개 만드는 법 / 만들기 /
											순두부찌개 만드는 법</a>
									</div>
									<p>
										<em>펭귄하늘을날다</em>
									</p>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> <img
											class="stars" src="imgs/stars5.png"> <span
											class="p-1 mt-1">4.6(500)</span>
										</span> <span class="d-flex align-items-center"> <span
											class="p-1 mt-1">조회 300,000</span>
										</span>
									</div>
								</div>
								<!-- end recipe-desc -->
							</div>
							<!-- end col -->

							<div class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="imgs/content/thumb-5.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<div class="recipe-title">
										<a href="#">전참시 유병재가 만든 찜닭! 꽈리고추닭볶음</a>
									</div>
									<p>
										<em>펭귄하늘을날다</em>
									</p>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> <img
											class="stars" src="imgs/stars5.png"> <span
											class="p-1 mt-1">4.6(500)</span>
										</span> <span class="d-flex align-items-center"> <span
											class="p-1 mt-1">조회 300,000</span>
										</span>
									</div>
								</div>
								<!-- end recipe-desc -->
							</div>
							<!-- end col -->
							<div class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="imgs/content/thumb-6.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<div class="recipe-title">
										<a href="#">소고기무국 황금레시피, 재료는 간단! 맛은 최고!</a>
									</div>
									<p>
										<em>쑤이</em>
									</p>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> <img
											class="stars" src="imgs/stars5.png"> <span
											class="p-1 mt-1">4.6(500)</span>
										</span> <span class="d-flex align-items-center"> <span
											class="p-1 mt-1">조회 300,000</span>
										</span>
									</div>
								</div>
								<!-- end recipe-desc -->
							</div>
							<!-- end col -->
							<div class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="imgs/content/thumb-7.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<div class="recipe-title">
										<a href="#">제육볶음 만드는법 # 기사식당st 레시피</a>
									</div>
									<p>
										<em>트래블러버클로이</em>
									</p>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> <img
											class="stars" src="imgs/stars5.png"> <span
											class="p-1 mt-1">4.6(500)</span>
										</span> <span class="d-flex align-items-center"> <span
											class="p-1 mt-1">조회 300,000</span>
										</span>
									</div>
								</div>
								<!-- end recipe-desc -->
							</div>
							<!-- end col -->
							<div class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="imgs/content/thumb-8.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<div class="recipe-title">
										<a href="#">백종원레시피로 만든 콩나물무침으로 밥 한 끼 뚝딱 ~</a>
									</div>
									<p>
										<em>펭귄하늘을날다</em>
									</p>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> <img
											class="stars" src="imgs/stars5.png"> <span
											class="p-1 mt-1">4.6(500)</span>
										</span> <span class="d-flex align-items-center"> <span
											class="p-1 mt-1">조회 300,000</span>
										</span>
									</div>
								</div>
								<!-- end recipe-desc -->
							</div>
							<!-- end col -->
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
							<div class="col-4 col-md-2 chef-list text-center">
								<figure class="mb-0">
									<img class="chef-pic" src="imgs/content/ava-1.png" alt="쉐프 사진">
								</figure>
								<div class="chef-info">
									<p class="mb-0">
										<small class="chef-rank">LV7 요리의신</small>
									</p>
									<a class="chef-name" href="#">Mirum</a>
								</div>
							</div>
							<!-- end col -->
							<div class="col-4 col-md-2 chef-list text-center">
								<figure class="mb-0">
									<img class="chef-pic" src="imgs/content/ava-2.png" alt="쉐프 사진">
								</figure>
								<div class="chef-info">
									<p class="mb-0">
										<small class="chef-rank">LV6 요리마스터</small>
									</p>
									<a class="chef-name" href="#">nickname</a>
								</div>
							</div>
							<!-- end col -->
							<div class="col-4 col-md-2 chef-list text-center">
								<figure class="mb-0">
									<img class="chef-pic" src="imgs/content/auth-03.png"
										alt="쉐프 사진">
								</figure>
								<div class="chef-info">
									<p class="mb-0">
										<small class="chef-rank">LV5 고급요리사</small>
									</p>
									<a class="chef-name" href="#">Mirum</a>
								</div>
							</div>
							<!-- end col -->
							<div class="col-4 col-md-2 chef-list text-center">
								<figure class="mb-0">
									<img class="chef-pic" src="imgs/content/auth-00.png"
										alt="쉐프 사진">
								</figure>
								<div class="chef-info">
									<p class="mb-0">
										<small class="chef-rank">LV4 중급요리사</small>
									</p>
									<a class="chef-name" href="#">Mirum</a>
								</div>
							</div>
							<!-- end col -->
							<div class="col-4 col-md-2 chef-list text-center">
								<figure class="mb-0">
									<img class="chef-pic" src="imgs/content/auth-01.png"
										alt="쉐프 사진">
								</figure>
								<div class="chef-info">
									<p class="mb-0">
										<small class="chef-rank">LV3 초급요리사</small>
									</p>
									<a class="chef-name" href="#">Mirum</a>
								</div>
							</div>
							<!-- end col -->
							<div class="col-4 col-md-2 chef-list text-center">
								<figure class="mb-0">
									<img class="chef-pic" src="imgs/content/auth-02.png"
										alt="쉐프 사진">
								</figure>
								<div class="chef-info">
									<p class="mb-0">
										<small class="chef-rank">LV2 보조요리사</small>
									</p>
									<a class="chef-name" href="#">Mirum</a>
								</div>
							</div>
							<!-- end col -->

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