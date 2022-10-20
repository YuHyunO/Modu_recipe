<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<link href="/css/mypage.css" rel="stylesheet">
<script src="/js/mypage.js"></script>
<title>모두의식탁 - 쉐프 레시피 보기</title>
<style type="text/css">

</style>
</head>
<body>
	<div id="page" class="hfeed site">
		<!-- start page wrapper -->
		<%@ include file="/WEB-INF/views/common/menu.jsp"%>
		<div class="head-title py-4 m-0">
			<div class="container">
				<p class="page-title m-0 fs-2">mypage</p>
			</div><!-- end container -->
		</div><!-- end head-title -->
		
		<div class="main d-flex justify-content-center">
			<div
				class="main-container row d-flex justify-content-center m-0 py-4">
				<div class="row">

					<div id="primary" class="content-area col-md-9">
						<div id="content" class="site-content">
							<!--프로필 영역 시작 -->
							<div class="profile-details rounded-3 text-center">
								<!-- <figure class="profile-ava"></figure> -->
								<div>
									<!-- 첨부파일이 없을 때 -->
									<c:if
										test="${ member.profileImg=='default_profile_img.png' || member.profileImg==null}">
										<img 
										id="mypage_defaultimg"
										src="/imgs/mypage/default_profile_img.png" 
										alt="default_profileimg" />
									</c:if>
									<!-- 첨부파일이 있을 때 미리보기 사진 보임-->
									<c:if
										test="${ member.profileImg!='default_profile_img.png' && member.profileImg!=null}">
											<img 
											src="<spring:url value = '/pics/profile/${ member.profileImg }'/>" 
											id="mypage_profileimg"
											alt="현재프로필사진"/>
									</c:if>
								</div><br/>
								<div class="profile-context">
									<div class="profile-name">
										<h1>${sessionScope.nickname}님의 마이페이지</h1>
										<h4>${sessionScope.email}</h4>
										<!-- <h4 class="archive-title text-center"> -->
									</div>
									<div class="profile-content py-3">
										마이페이지에서 추천 레시피를 소개받고,<br/>
										나만의 레시피를 작성해보거나,<br/>
										다양한 쉐프들과 친구를 맺어보세요!
									</div>

									<!-- 회원정보 수정 버튼-->
									<div class="py-3">
										<button
											class="btn btn-outline-secondary btn-md me-3 member-btn"
											type="button" onclick="location.href='/member/modifymyinfo'">
                      						<i class="bi-person-fill me-1"></i>
											내 정보 수정</button>
										<!--로그인 버튼-->
										<button class="btn btn-outline-secondary btn-md member-btn"
											type="button" onclick="location.href='/member/point'">
											<i class="bi-key-fill me-1"></i>
											포인트 조회</button>
							 		</div>
								</div>
								<!-- end profile-context -->
							</div>
							<!-- end profile-details, 프로필 영역 종료 -->

									<!-- 탭2 시작(나의 레시피)-->
									<div class="tab-pane fade" id="article" role="tabpane2"
										aria-labelledby="article-tab">
										<div id="tab2-content" class="site-content">
											<h3 class="archive-title text-center">나의 레시피</h3>
											<br />

											<!--공개/비공개버튼 2개-->
											<div class="text-end pb-3">
<!-- 												<button class="btn gold-btn me-3 p-2" id="openBtn" type="button">
										                            공개 레시피</button>
										          <button class="btn gold-btn p-2" id="closedBtn" type="button">
										                            비공개 레시피</button> -->
												<select class="gold-border p-1 filter-open">
													<option selected="selected">공개 레시피</option>
													<option>비공개 레시피</option>
												</select>
											</div>
											<div class="row">
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
											</div>
											<div class="page mt-5">
												<nav aria-label="Page navigation">
													<ul class="pagination justify-content-center">
														<li class="page-item"><a
															class="page-link page-previous" href="#">＜</a></li>
														<li class="page-item"><a
															class="page-link active page-number"
															href="javascript:void(0);" onclick="activePage(this)">1</a></li>
														<li class="page-item"><a
															class="page-link page-number" href="javascript:void(0);"
															onclick="activePage(this)">2</a></li>
														<li class="page-item"><a
															class="page-link page-number" href="javascript:void(0);"
															onclick="activePage(this)">3</a></li>
														<li class="page-item"><a
															class="page-link page-number" href="javascript:void(0);"
															onclick="activePage(this)">4</a></li>
														<li class="page-item"><a
															class="page-link page-number" href="javascript:void(0);"
															onclick="activePage(this)">5</a></li>
														<li class="page-item"><a class="page-link page-next"
															href="#">＞</a></li>
													</ul>
												</nav>
											</div>
											<!-- end Page -->
										</div>
										<!-- id="tab2-content" 종료-->
									</div>
									<!-- id="article" 종료, 탭2 전체 종료-->
						</div>
						<!-- end id="content", 프로필+ 탭전체영역 종료-->
					</div>
					<!-- end primary(주요 메인영역) 종료-->
					<!--사이드 영역(secondary) 시작-->
					<div id="secondary" class="col-md-3">
						<div class="widget post-type-widget">
							<div class="widget-title-outer">
								<h3 class="widget-title">최근 본 게시물</h3>
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
						</div><!-- end widget -->

						<!--새로운 태그들-->
						<div class="widget">
							<div class="widget-title-outer">
								<h3 class="widget-title">News Tags</h3>
							</div>
							<div class="tagcloud">
								<a href="#">#돼지갈비</a> <a href="#">#잔치국수</a> <a href="#">#돈까스</a>
								<a href="#">#마카롱</a>
							</div>
						</div><!-- end widget -->

						<!--광고 배너 이미지-->
						<div class="widget">
							<a href="#"> <img class="rounded-3 banner"
								src="/imgs/mypage/ad_bespoke.PNG" alt="Banner" />
							</a>
						</div>
					</div><!-- end #secondary, 사이드영역 끝 -->

				</div><!-- end row -->
			</div><!-- end container -->
		</div><!-- end main -->
		<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
	</div>
	<!-- end page wrapper -->
	<!-- end #page hfeed site -->							
</body>
</html>