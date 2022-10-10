<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<!-- 공통 부분 END -->
<link href="/css/recipe-list.css" rel="stylesheet">
<script src="/js/recipe-list.js"></script>

<title>모두의 식탁 - 레시피 목록</title>

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

		<div id="main">
			<div class="container py-4 main-container">

				<div id="primary" class="content-area fullwidth py-3">
					<div id="content" class="site-content">
						<div class="tag-cover">
							<div class="tag-search d-flex justify-content-center">
								<ul
									class="tag-ul d-flex flex-wrap justify-content-center px-0 mb-5 mt-3">
									<li class="tag-li" onclick="searchTag(this)">메인반찬</li>
									<li class="tag-li" onclick="searchTag(this)">밑반찬</li>
									<li class="tag-li" onclick="searchTag(this)">국/찌개</li>
									<li class="tag-li" onclick="searchTag(this)">디저트</li>
									<li class="tag-li" onclick="searchTag(this)">면/만두</li>
									<li class="tag-li" onclick="searchTag(this)">밥/죽/떡</li>
									<li class="tag-li" onclick="searchTag(this)">빵</li>
									<li class="tag-li" onclick="searchTag(this)">스프</li>
									<li class="tag-li" onclick="searchTag(this)">야식/안주</li>
									<li class="tag-li" onclick="searchTag(this)">샐러드</li>
									<li class="tag-li" onclick="searchTag(this)">소고기</li>
									<li class="tag-li" onclick="searchTag(this)">닭고기</li>
									<li class="tag-li" onclick="searchTag(this)">양고기</li>
									<li class="tag-li" onclick="searchTag(this)">돼지고기</li>
									<li class="tag-li" onclick="searchTag(this)">오리고기</li>
									<li class="tag-li" onclick="searchTag(this)">채소류</li>
									<li class="tag-li" onclick="searchTag(this)">해물류</li>
									<li class="tag-li" onclick="searchTag(this)">유제품</li>
									<li class="tag-li" onclick="searchTag(this)">기타</li>
									<li class="tag-li random" onclick="searchTag(this)">랜덤추천★</li>
								</ul>
							</div>
							<!-- end tag-search -->
						</div>
						<div id="recipe-search"
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
									<option selected="selected">레시피명</option>
									<option>쉐프명</option>
									<option>재료명</option>
								</select>
							</div>
							<div class="d-flex justify-content-end search-box">
								<input id="search" class="border px-2 gold-border" type="search"
									name="search" placeholder="검색어를 입력해주세요" value="">
								<button type="submit" class="search-btn border gold-border">검색</button>
								<select class="gold-border page p-1 ms-2">
									<option selected="selected">8</option>
									<option>16</option>
									<option>24</option>
								</select>
							</div>
						</div>
						<!-- end recipe-search -->

						<div class="row">
							<div class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="/imgs/content/thumb-1.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<h2 class="recipe-title">
										<a href="#">전참시 유병재가 만든 찜닭! 꽈리고추닭볶음</a>
									</h2>
									<figure class="profile">
										<img class="profile-img" src="/imgs/content/auth-00.png"
											alt="작성자">
										<span><em>펭귄하늘을날다</em></span>
									</figure>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> <img
											class="stars" src="/imgs/stars5.png"> <span
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
									<img src="/imgs/content/thumb-2.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<h2 class="recipe-title">
										<a href="#">전참시 유병재가 만든 찜닭! 꽈리고추닭볶음</a>
									</h2>
									<figure class="profile">
										<img class="profile-img" src="/imgs/content/auth-00.png"
											alt="작성자">
										<span><em>펭귄하늘을날다</em></span>
									</figure>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> <img
											class="stars" src="/imgs/stars5.png"> <span
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
									<img src="/imgs/content/thumb-3.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<h2 class="recipe-title">
										<a href="#">전참시 유병재가 만든 찜닭! 꽈리고추닭볶음</a>
									</h2>
									<figure class="profile">
										<img class="profile-img" src="/imgs/content/auth-00.png"
											alt="작성자">
										<span><em>펭귄하늘을날다</em></span>
									</figure>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> <img
											class="stars" src="/imgs/stars5.png"> <span
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
									<img src="/imgs/content/thumb-4.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<h2 class="recipe-title">
										<a href="#">전참시 유병재가 만든 찜닭! 꽈리고추닭볶음</a>
									</h2>
									<figure class="profile">
										<img class="profile-img" src="/imgs/content/auth-00.png"
											alt="작성자">
										<span><em>펭귄하늘을날다</em></span>
									</figure>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> <img class="">
											<span class="p-1 mt-1"></span>
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
									<img src="/imgs/content/thumb-5.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<h2 class="recipe-title">
										<a href="#">전참시 유병재가 만든 찜닭! 꽈리고추닭볶음</a>
									</h2>
									<figure class="profile">
										<img class="profile-img" src="/imgs/content/auth-00.png"
											alt="작성자">
										<span><em>펭귄하늘을날다</em></span>
									</figure>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> <img
											class="stars" src="/imgs/stars1.png"> <span
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
									<img src="/imgs/content/thumb-6.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<h2 class="recipe-title">
										<a href="#">전참시 유병재가 만든 찜닭! 꽈리고추닭볶음</a>
									</h2>
									<figure class="profile">
										<img class="profile-img" src="/imgs/content/auth-00.png"
											alt="작성자">
										<span><em>펭귄하늘을날다</em></span>
									</figure>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> <img
											class="stars" src="/imgs/stars2.png"> <span
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
									<img src="/imgs/content/thumb-7.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<h2 class="recipe-title">
										<a href="#">전참시 유병재가 만든 찜닭! 꽈리고추닭볶음</a>
									</h2>
									<figure class="profile">
										<img class="profile-img" src="/imgs/content/auth-00.png"
											alt="작성자">
										<span><em>펭귄하늘을날다</em></span>
									</figure>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> <img
											class="stars" src="/imgs/stars3.png"> <span
											class="p-1 mt-1">4.6(500)</span>
										</span> <span class="d-flex align-items-center"> <span
											class="p-1 mt-1">조회 1</span>
										</span>
									</div>
								</div>
								<!-- end recipe-desc -->
							</div>
							<!-- end col -->

							<div class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="/imgs/content/thumb-8.png" alt="Recipe Image">
								</div>
								<div class="recipe-desc">
									<h2 class="recipe-title">
										<a href="#">전참시 유병재가 만든 찜닭! 꽈리고추닭볶음</a>
									</h2>
									<figure class="profile">
										<img class="profile-img" src="/imgs/content/auth-00.png"
											alt="작성자">
										<span><em>펭귄하늘을날다</em></span>
									</figure>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> <img
											class="stars" src="/imgs/stars4.png"> <span
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
					<!-- end content -->
				</div>
				<!-- end primary -->

				<div class="page">
					<nav aria-label="Page navigation">
						<ul class="pagination justify-content-center">
							<li class="page-item"><a class="page-link page-previous"
								href="#">＜</a></li>
							<li class="page-item"><a
								class="page-link active page-number" href="javascript:void(0);"
								onclick="activePage(this)">1</a></li>
							<li class="page-item"><a class="page-link page-number"
								href="javascript:void(0);" onclick="activePage(this)">2</a></li>
							<li class="page-item"><a class="page-link page-number"
								href="javascript:void(0);" onclick="activePage(this)">3</a></li>
							<li class="page-item"><a class="page-link page-number"
								href="javascript:void(0);" onclick="activePage(this)">4</a></li>
							<li class="page-item"><a class="page-link page-number"
								href="javascript:void(0);" onclick="activePage(this)">5</a></li>
							<li class="page-item"><a class="page-link page-next"
								href="#">＞</a></li>
						</ul>
					</nav>
				</div>

			</div>
			<!-- end container -->
		</div>
		<!-- end main -->
		<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
	</div>
	<!-- end #page hfeed site -->
</body>
</html>
