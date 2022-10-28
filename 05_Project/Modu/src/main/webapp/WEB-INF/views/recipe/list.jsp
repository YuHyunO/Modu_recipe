<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<!-- 공통 부분 END. -->
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
									<li class="tag-li" onclick="searchTag(this)" value="1">메인반찬</li>
									<li class="tag-li" onclick="searchTag(this)" value="2">밑반찬</li>
									<li class="tag-li" onclick="searchTag(this)" value="3">국/찌개</li>
									<li class="tag-li" onclick="searchTag(this)" value="4">디저트</li>
									<li class="tag-li" onclick="searchTag(this)" value="5">면/만두</li>
									<li class="tag-li" onclick="searchTag(this)" value="6">밥/죽/떡</li>
									<li class="tag-li" onclick="searchTag(this)" value="7">빵</li>
									<li class="tag-li" onclick="searchTag(this)" value="8">스프</li>
									<li class="tag-li" onclick="searchTag(this)" value="9">야식/안주</li>
									<li class="tag-li" onclick="searchTag(this)" value="10">샐러드</li>
									<li class="tag-li" onclick="searchTag(this)" value="11">소고기</li>
									<li class="tag-li" onclick="searchTag(this)" value="12">닭고기</li>
									<li class="tag-li" onclick="searchTag(this)" value="13">양고기</li>
									<li class="tag-li" onclick="searchTag(this)" value="14">돼지고기</li>
									<li class="tag-li" onclick="searchTag(this)" value="15">오리고기</li>
									<li class="tag-li" onclick="searchTag(this)" value="16">채소류</li>
									<li class="tag-li" onclick="searchTag(this)" value="17">해물류</li>
									<li class="tag-li" onclick="searchTag(this)" value="18">유제품</li>
									<li class="tag-li" onclick="searchTag(this)" value="19">기타</li>
									<li class="tag-li random" onclick="searchTag(this)" value="20">랜덤추천★</li>
								</ul>
							</div>
							<!-- end tag-search -->
						</div>
						<div id="recipe-search"
							class="mb-3 d-flex justify-content-between align-items-center">
							<div class="selects">
								<select id="period-selector" class="gold-border p-1 filter-period" onchange="setData()">
									<option value="0">전체기간</option>
									<option value="1">1일</option>
									<option value="7">1주</option>
									<option value="30">1개월</option>
									<option value="180">6개월</option>
									<option value="365">1년</option>
								</select>
								<select id="name-selector" class="gold-border p-1 filter-group">
									<option value="recipe">레시피명</option>
									<option value="chef">쉐프명</option>
									<option value="ingredient">재료명</option>
								</select>
							</div>
							<div class="d-flex justify-content-end search-box">
								<input id="search" class="border px-2 gold-border" type="search"
									name="search" placeholder="검색어를 입력해주세요" value="" onsearch="setKeywordData()">
								<button type="submit" id="search-btn" class="search-btn border gold-border" onclick="setKeywordData()">검색</button>
								
								<select id="size-selector" class="gold-border page p-1 ms-2" onchange="setPageSize()">
								<c:choose>
									<c:when test="${data.pageSize == 8}">
									<option value="8" selected>8</option>
									<option value="16">16</option>
									<option value="24">24</option>
									</c:when>
									<c:when test="${data.pageSize == 16}">
									<option value="8">8</option>
									<option value="16" selected>16</option>
									<option value="24">24</option>
									</c:when>
									<c:when test="${data.pageSize == 24}">
									<option value="8">8</option>
									<option value="16">16</option>
									<option value="24"selected>24</option>
									</c:when>
									<c:otherwise>
									<option value="8">8</option>
									<option value="16">16</option>
									<option value="24">24</option>
									</c:otherwise>																											
								</c:choose>						
								</select>
								
							</div>
						</div>
						<!-- end recipe-search -->
						
						<div id="recipe-list" class="row">
						<!-- 반복문 작성 -->
						<c:forEach items="${data.recipeList}" var="li">
							<div id="recipe-item" class="col-6 col-md-3">
								<div class="recipe-thumb">
									<img src="/imgs/${li.foodPhoto}" alt="/imgs/content/thumb-1.png">
								</div>
								<div class="recipe-desc">
									<h2 class="recipe-title">
										<a href="javascript:void(0)" onclick="saveCookie(${li.id})">${li.title}</a>
									</h2>
									<figure class="profile">
						
										<img class="profile-img" src="/imgs/content/auth-00.png"
											alt="작성자">
										<span><em> ${li.MNickname}</em></span>
									</figure>
									<div class="recipe-icons d-flex justify-content-between">
										<span class="d-flex align-items-center"> 
										<img class="stars" src="/imgs/stars5.png"> 
										<span class="p-1 mt-1">${li.star}(${li.stars})</span>
										</span> <span class="d-flex align-items-center"> 
										<span class="p-1 mt-1">조회 ${li.hits}</span>
										</span>
									</div>
								</div>
								<!-- end recipe-desc -->
							</div>
							</c:forEach>						
							<!-- 반복문 작성 끝 -->
						</div>
						<!-- end row -->
					</div>
					<!-- end content -->
				</div>
				<!-- end primary -->

				<div class="page">
					<nav aria-label="Page navigation">
						<ul id="pagination-ul" class="pagination justify-content-center">
						
							<%-- 페이징 처리 --%>
							<div id="pagination-previous" class="pagination justify-content-center">
							<c:choose>
								<c:when test="${data.currentPage != 1}">
									<li class="page-item"><a class="page-link page-previous"
										href="javascript:void(0);" onclick="setPage(this)" id="pre">＜</a></li>
								</c:when>
								<c:when test="${data.currentPage == 1}">
									<li class="page-item"><a class="page-link page-previous"
										href="javascript:void(0);">＜</a></li>
								</c:when>								
							</c:choose>
							</div>						
														
							<%-- begin 페이지네이션 영역 --%>
							<div id="pagination-area" class="pagination justify-content-center">
							<c:choose>	
							<%-- begin comment : 총 페이지 수가 5개 이상인 경우 --%>												
							<c:when test="${data.totalPage > 5}">							
								<c:choose>
									<c:when test="${data.currentPage < 3}">
										<c:forEach begin="1" end="5" var="i">
											<c:choose>
												<c:when test="${i == data.currentPage}">
													<li class="page-item"><a class="page-link active page-number"
														href="javascript:void(0);" onclick="activePage(this);setPage(this);">${i}</a></li>
												</c:when>
												<c:otherwise>
													<li class="page-item"><a class="page-link page-number" 
														href="javascript:void(0);" onclick="activePage(this);setPage(this);">${i}</a></li>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<%-- begin comment : 현재 페이지가 3페이지 이상인 경우 : 현재 페이지를 가운데 고정 --%>
									<c:when test="${data.currentPage >= 3}">																				
										<c:choose>
											<c:when test="${data.totalPage-data.currentPage < 2}">												
												<c:forEach begin="${data.totalPage-4}" end="${data.totalPage}" var="i">
													<c:choose>
														<c:when test="${i == data.currentPage}">
															<li class="page-item"><a class="page-link active page-number"
																href="javascript:void(0);" onclick="activePage(this);setPage(this);">${i}</a></li>
														</c:when>
														<c:otherwise>
															<li class="page-item"><a class="page-link page-number" 
																href="javascript:void(0);" onclick="activePage(this);setPage(this);">${i}</a></li>
														</c:otherwise>
													</c:choose>														
												</c:forEach>												
											</c:when>																								
											<c:otherwise>
												<c:forEach begin="${data.currentPage-2}" end="${data.currentPage+2}" var="i">											
													<c:choose>
														<c:when test="${i == data.currentPage}">
															<li class="page-item"><a class="page-link active page-number"
																href="javascript:void(0);" onclick="activePage(this);setPage(this);">${i}</a></li>
														</c:when>
														<c:otherwise>
															<li class="page-item"><a class="page-link page-number" 
																href="javascript:void(0);" onclick="activePage(this);setPage(this);">${i}</a></li>
														</c:otherwise>
													</c:choose>	
												</c:forEach>											
											</c:otherwise>
										</c:choose>																															
									</c:when>
									<%-- end comment : 현재 페이지가 3페이지 이상인 경우 : 현재 페이지를 가운데 고정 --%>																						
								</c:choose>							
							</c:when>
							<%-- end comment : 총 페이지 수가 5개 이상인 경우 --%>
							
							<%-- begin comment : 총 페이지 수가 5개 이하인 경우 --%>
							<c:when test="${data.totalPage < 5}">
								<c:forEach begin="1" end="${data.totalPage}" var="i">							
									<c:choose>							
										<c:when test="${i == data.currentPage}">					
											<li class="page-item"><a class="page-link active page-number"
												href="javascript:void(0);" onclick="activePage(this);setPage(this);">${i}</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link page-number" 
												href="javascript:void(0);" onclick="activePage(this);setPage(this);">${i}</a></li>
										</c:otherwise>							
									</c:choose>																						
								</c:forEach>
							</c:when>
							<%-- end comment : 총 페이지 수가 5개 이하인 경우 --%>
							
							</c:choose>
							</div>						
							<%-- end 페이지네이션 영역 --%>
							
							<div id="pagination-next" class="pagination justify-content-center">
							<c:choose>
								<c:when test="${data.currentPage != data.totalPage}">
									<li class="page-item"><a class="page-link page-next"
										href="javascript:void(0);" onclick="setPage(this)" id="next">＞</a></li>
								</c:when>
								<c:when test="${data.currentPage == data.totalPage}">
									<li class="page-item"><a class="page-link page-next"
										href="javascript:void(0);">＞</a></li>
								</c:when>								
							</c:choose>
							</div>
							
							<%-- end 페이징 처리 --%>					
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
