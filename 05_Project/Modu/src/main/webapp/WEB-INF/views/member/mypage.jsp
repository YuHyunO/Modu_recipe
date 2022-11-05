<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<link href="/css/mypage.css" rel="stylesheet">
<script src="/js/mypage.js"></script>
<title>모두의식탁 - 마이페이지</title>
<style type="text/css">

</style>
</head>
<body>
	<input type="hidden" id="page-type" value="mypage"/>
	<div id="page" class="hfeed site">
		<!-- start page wrapper -->
		<%@ include file="/WEB-INF/views/common/menu.jsp"%>
		<div class="head-title py-4 m-0">
			<div class="container">
				<p class="page-title m-0 fs-2">mypage</p>
			</div><!-- end container -->
		</div><!-- end head-title -->

		<div class="main d-flex justify-content-center">
			<div class="main-container row d-flex justify-content-center m-0 py-4">
				
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
											<img src="/pics/profile/${ member.profileImg }"
												id="mypage_profileimg" alt="mypage_profileimg"/>
											<%-- 주소 불안정(인식 못할 대 있음 src="<spring:url value='/pics/profile/${ member.profileImg }'/>" --%>
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

							<div class="my-content">
								<!-- 탭갈피+탭내용 합침 -->
								<!-- 탭갈피 시작/ 아티클,아티클,북마크,레시피,북마크 순서 -->
								<ul class="nav nav-tabs" id="myTab" role="tablist">
									<li class="nav-item"><a class="nav-link tab-menu active"
										id="ingredient-tab" data-toggle="tab" href="#ingredient" onclick="setUrl(this)"
										role="tab" aria-controls="ingredient" aria-selected="false">냉장고 비우기</a></li>
									<li class="nav-item"><a class="nav-link tab-menu"
										id="article-tab" data-toggle="tab" href="#article" onclick="setUrl(this)"
										role="tab" aria-controls="article" aria-selected="false">나의 레시피</a></li>
										
									<li class="nav-item"><a class="nav-link tab-menu"
										id="bookmark-tab" data-toggle="tab" href="#bookmark" onclick="setUrl(this)"
										role="tab" aria-controls="bookmark" aria-selected="false">북마크 레시피</a></li>
											
									<li class="nav-item"><a class="nav-link tab-menu"
										id="mypost-tab" data-toggle="tab" href="#mypost" onclick="setUrl(this)" 
										role="tab" aria-controls="mypost" aria-selected="false">나의 게시글/댓글</a></li>
									<li class="nav-item"><a class="nav-link tab-menu"
										id="myfriend-tab" data-toggle="tab" href="#myfriend" onclick="setUrl(this)"
										role="tab" aria-controls="myfriend" aria-selected="false">친구 관리</a></li>
								</ul>

								<!--탭 컨텐츠 시작-->
								<div class="tab-content" id="myTabContent">

									<!-- 탭1 시작(냉장고 비우기)-->
									<div class="tab-pane fade show active" id="ingredient"
										role="tabpanel" aria-labelledby="ingredient-tab">
										<div id="tab1-content" class="pb-3"><!-- class명에서 site-content 지움 -->
											<h4 class="archive-title text-center">냉장고 속 재료를 조합하여 요리해보기</h4>
											<div class="backbox rounded-3 text-center py-3">
												지금 냉장고 속 재료를 체크해보세요.<br /> 맞춤형 레시피를 볼 수 있어요!
											</div>
											<br>
											<div class="article-list">
												<div class="row">
													<!-- 채소류 row -->
													<div class="col-md-2">
														<h5>채소류</h5>
													</div>
													<!--체크영역 시작-->
													<div class="col-md-6">
														<div class="vegetableSection row" id="vegetableSection">
															<!--js 제이쿼리 $(function(){});가 실행되는 부분-재료명 나열 -->
														</div>
													</div><!-- col-md-6 종료-->
													<!--야채 사진-->
													<div class="col-md-4">
														<figure>
															<img class="rounded-3 category-pic"
																src="/imgs/mypage/vegetables.jpg" alt="image_null" />
														</figure>
													</div>
												</div>
												<!-- 채소류 종료, end row -->

											</div>
											<!-- end article-list-->

											<div class="article-list">
												<div class="row">
													<!--육류 row -->
													<div class="col-md-2">
														<h5>고기류</h5>
													</div>
													<div class="col-md-6">
														<div class="meatSection row" id="meatSection"></div>
													</div>
													<!--사진-->
													<div class="col-md-4">
														<figure>
															<img class="rounded-3 category-pic"
																src="/imgs/mypage/meat.jpg" alt="image_null" />
														</figure>
													</div>
												</div>
												<!--육류 종료, end row-->
											</div>
											<!-- end article-list-->

											<div class="article-list">
												<div class="row">
													<!--해산물 row -->
													<div class="col-md-2">
														<h5>해산물</h5>
													</div>
													<div class="col-md-6">
														<div class="fishSection row" id="fishSection"></div>
													</div>
													<!--사진-->
													<div class="col-md-4">
														<figure>
															<img class="rounded-3 category-pic"
																src="/imgs/mypage/fishes.jpg" alt="image_null" />
														</figure>
													</div>
												</div>
												<!-- end row-->
											</div>
											<!-- end article-list-->

											<div class="article-list">
												<div class="row">
													<!--견과류 row -->
													<div class="col-md-2">
														<h5>가공품</h5>
													</div>
													<div class="col-md-6">
														<div class="processedSection row" id="processedSection">
														</div>
													</div>
													<!--사진-->
													<div class="col-md-4">
														<figure>
															<img class="rounded-3 category-pic"
																src="/imgs/mypage/processed.jpg" alt="image_null" />
														</figure>
													</div>
												</div>
												<!-- end row-->
											</div>
											<!-- end article-list-->

											<div class="article-list">
												<div class="row">
													<!-- 양념 row -->
													<div class="col-md-2">
														<h5>양념</h5>
													</div>
													<div class="col-md-6">
														<div class="seasoningSection row" id="seasoningSection">
														</div>
													</div>
													<!--사진-->
													<div class="col-md-4">
														<figure>
															<img class="rounded-3 category-pic"
																src="/imgs/mypage/seasoning.jpg" alt="image_null" />
														</figure>
													</div>
												</div>
												<!--양념 종료, end row-->
											</div>
											<!-- end article-list-->

											<div class="article-list">
												<div class="row">
													<!-- 기타 row -->
													<div class="col-md-2">
														<h5>기타</h5>
													</div>
													<div class="col-md-6">
														<div class="milkSection row" id="milkSection"></div>
													</div>
													<!--사진-->
													<div class="col-md-4">
														<figure>
															<img class="rounded-3 category-pic"
																src="/imgs/mypage/milk.png" alt="image_null" />
														</figure>
													</div>
												</div>
												<!--양념 종료, end row-->
											</div>
											<!-- end article-list-->
											<br />

											<div class="rounded-3" id="finalIngredient">
												<span>최종 선택된 재료 :</span> <span id='result'></span>
											</div>
											<br><br>

											<!--레시피 검색 버튼-->
											<div class="text-center">
												<button class="btn-search gold-btn" id="search-reset"
													onclick="location.reload()">다시 선택</button>
													<!-- 추후 reload 없이 초기화 기능 구현할 것 -->											
											</div>
											<br><br>
											<div id="recipe-list-1" class="row">
											</div>					
																												
											<div id="paging-area-1" class="page common-area">
											</div>																													
											<!-- end row -->											
										</div>
										<!-- id="tab1-content" 종료 -->
									</div>
									<!-- id="ingredient" 종료 -->

									<!-- 탭2 시작(나의 레시피)-->
									<div class="tab-pane fade" id="article" role="tabpane2"
										aria-labelledby="article-tab">
										<div id="tab2-content" class="site-content">
											<h3 class="archive-title text-center">나의 레시피</h3>
											<br>
											<!--공개/비공개버튼 2개-->
											<div class="text-end pb-3">
											  <!--<button class="btn gold-btn me-3 p-2" id="openBtn" type="button">
										                            공개 레시피</button>
										          <button class="btn gold-btn p-2" id="closedBtn" type="button">
										                            비공개 레시피</button> -->
												<select id="recipe-access-option" class="gold-border p-1 filter-open" onchange="setUrl(this)">
													<option selected="selected" value="0">공개 레시피</option>
													<option value="1">비공개 레시피</option>
													<option value="2">임시저장 레시피</option>
												</select>
											</div><br>

											<div id="recipe-list-2" class="row"></div>																	
											<div id="paging-area-2" class="page common-area"></div>												
												<!-- end col -->
											<!-- end Page -->
										</div>
										<!-- id="tab2-content" 종료-->
									</div>
									<!-- id="article" 종료, 탭2 전체 종료-->

									<!-- 탭3 (북마크 레시피) 시작 -->
									<div class="tab-pane fade" id="bookmark" role="tabpane3"
										aria-labelledby="bookmark-tab">
										<div id="tab3-content" class="site-content">
											<h3 class="archive-title text-center">북마크 레시피</h3>
										
											<div id="recipe-list-3" class="row"></div>					
											<div id="paging-area-3" class="page common-area"></div>
											<!-- end paging-area-3 -->
											
<!-- 											<div class="article-cover">
												<div class="article-list">
													<div class="row">
														<div class="col-md-1">
															<div class="entry-date">
																<time class="published" datetime="2019-12-22"
																	title="postdate">Dec<span class="date">22</span>2019
																</time>
															</div>
														</div>
														<div class="col-md-4">
															<figure>
																<img class="recipe-thumb" src="/imgs/content/list-01.png" alt="" />
															</figure>
														</div>
														<div class="col-md-7">
															<h4>
																<a href="#" target="_blank">훈제연어야채샐러드</a>
															</h4>
															<span class="recipe-info">
															훈제연어의 훈연한 그 맛은 감칠맛이 나서 잘 맞는 드레싱만 얹어주면 인기만점의
																샐러드예요. 야채를 듬뿍 곁들여 건강하게 준비한 훈제연어 샐러드 소개합니다.</span>
															<a href="" class="readmore" target="_blank"><em>더보기</em></a>
														</div>
													</div>
												</div>
												end class="article-list", 첫번째 레시피 종료
											</div> -->
											<!-- end article-cover -->

<!-- 											<div class="page mt-5">
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
											</div> -->
											<!-- end Page -->
											
										</div>
										<!-- id=tab3-content 종료-->
									</div>
									<!--탭3 종료, id="bookmark" 종료 -->
									
									
									<!-- 탭4 레시피 탭(내게시글) 시작-->
									<div class="tab-pane fade" id="mypost" role="tabpane4"
										aria-labelledby="mypost-tab">

										<div id="tab4-content" class="myPost_thumbnail">
											<h3 class="archive-title text-center">나의 게시글</h3>

								<div id="board-search"
									class="mb-3 d-flex justify-content-between" style="float:right;">
									<div class="d-flex justify-content-end search-box">
										<input id="search" class="border px-2" type="search"
											name="search" placeholder="검색어를 입력해주세요" value="" onsearch="setKeywordData()">
										<button type="submit" class="search-btn border"  onclick="setKeywordData()">검색</button>
										<select id="size-selector" class="gold-border p-1 ms-2" onchange="setPageSize()">
											<c:choose>
												<c:when test="${data.pgSize == 10}">
												<option value="10" selected>10</option>
												<option value="15">15</option>
												<option value="20">20</option>
												</c:when>
												<c:when test="${data.pgSize == 15}">
												<option value="10">10</option>
												<option value="15" selected>15</option>
												<option value="20">20</option>
												</c:when>
												<c:when test="${data.pgSize == 20}">
												<option value="10">10</option>
												<option value="15">15</option>
												<option value="20"selected>20</option>
												</c:when>
												<c:otherwise>
												<option value="10">10</option>
												<option value="15">15</option>
												<option value="20">20</option>
												</c:otherwise>																											
											</c:choose>						
										</select>
									</div>
								</div>
								<!-- end board-search -->
								
							<table class="board-table text-center m-0 w-100">
								<thead>
									<tr>
										<th scope="col" class="th-1 id">순번</th>
										<th scope="col" class="th-2 title-td">게시글 제목</th>
										<!-- <th scope="col" class="th-3 nickname">작성자</th> -->
										<th scope="col" class="th-4 post-date">작성일</th>
										<th scope="col" class="th-5 hits">조회수</th>
									</tr>
								</thead>
								<tbody class="tbody" id='board-test'>
<%-- 										<c:if test="${empty data}">
												<tr align='center' noshade colspan="5">작성한 게시글이 없습니다.</tr>
										</c:if> --%>
									<c:forEach items="${data.boardList}" var="li">
										<c:if test="${empty data.boardList}">
												<tr align='center' noshade colspan="5">작성한 게시글이 없습니다.</tr>
										</c:if>
										<c:if test="${!empty data}">
											<tr class="border">
												<td class="id">${li.id}</td>
												<td class="title-td text-start"><a href="detail?id=${li.id}"> <span
														class="title">${li.title}</span> <span class="reply">[${li.reply}]</span>
												</a>
												</th>
												<td class="nickname">
													<img class="profile-img" src="/pics/profile/${li.profileImg}" alt="img">
															${li.MNickname}</td>
												<td class="post-date">${li.postDate}</td>
												<td class="hits">${li.hits}</td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
							<div class="write text-end my-3">
								<c:if test="${sessionScope.email == null}">
									<a href="javascript:alert('로그인 후 이용하실 수 있습니다.'); location.href='/member/login';"					
											><button type="button" id="write-btn"
										class="gold-border gold-btn">글쓰기</button></a>
								</c:if>
								<c:if test="${sessionScope.email != null}">
									<button type="button" id="write-btn"
										class="gold-border gold-btn"
										onclick="location.href='/freeboard/write'">글쓰기</button>
								</c:if>
							</div>
							<div class="page">
								<nav aria-label="Page navigation">
									<ul id="pagination-ul" class="pagination justify-content-center">
										<%-- 페이징 처리 --%>
											<div id="pagination-previous" class="pagination justify-content-center">
											<c:choose>
												<c:when test="${data.curPage != 1}">
													<li class="page-item"><a class="page-link page-previous"
														href="javascript:void(0);" onclick="setPage(this)" id="pre">＜</a></li>
												</c:when>
												<c:when test="${data.curPage == 1}">
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
													<c:when test="${data.curPage < 3}">
														<c:forEach begin="1" end="5" var="i">
															<c:choose>
																<c:when test="${i == data.curPage}">
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
													<c:when test="${data.curPage >= 3}">																				
														<c:choose>
															<c:when test="${data.totalPage-data.curPage < 2}">												
																<c:forEach begin="${data.totalPage-4}" end="${data.totalPage}" var="i">
																	<c:choose>
																		<c:when test="${i == data.curPage}">
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
																<c:forEach begin="${data.curPage-2}" end="${data.curPage+2}" var="i">											
																	<c:choose>
																		<c:when test="${i == data.curPage}">
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
														<c:when test="${i == data.curPage}">					
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
												<c:when test="${data.curPage != data.totalPage}">
													<li class="page-item"><a class="page-link page-next"
														href="javascript:void(0);" onclick="setPage(this)" id="next">＞</a></li>
												</c:when>
												<c:when test="${data.curPage == data.totalPage}">
													<li class="page-item"><a class="page-link page-next"
														href="javascript:void(0);">＞</a></li>
												</c:when>								
											</c:choose>
											</div>
											
											<%-- end 페이징 처리 --%>
									</ul>
								</nav>
							</div>
		
											<h3 class="archive-title text-center">나의 댓글</h3><br>
	
							<table class="board-table text-center m-0 w-100">
								<div id="board-search"
									class="mb-3 d-flex justify-content-between" style="float:right;">
									<div class="d-flex justify-content-end search-box">
										<input id="search" class="border px-2" type="search"
											name="search" placeholder="검색어를 입력해주세요" value="" onsearch="setKeywordData()">
										<button type="submit" class="search-btn border"  onclick="setKeywordData()">검색</button>
										<select id="size-selector" class="gold-border p-1 ms-2" onchange="setPageSize()">
											<c:choose>
												<c:when test="${data.pgSize == 10}">
												<option value="10" selected>10</option>
												<option value="15">15</option>
												<option value="20">20</option>
												</c:when>
												<c:when test="${data.pgSize == 15}">
												<option value="10">10</option>
												<option value="15" selected>15</option>
												<option value="20">20</option>
												</c:when>
												<c:when test="${data.pgSize == 20}">
												<option value="10">10</option>
												<option value="15">15</option>
												<option value="20"selected>20</option>
												</c:when>
												<c:otherwise>
												<option value="10">10</option>
												<option value="15">15</option>
												<option value="20">20</option>
												</c:otherwise>																											
											</c:choose>						
										</select>
									</div>
								</div>
								<!-- end board-search -->
								
								<thead>
									<tr>
										<th scope="col" class="th-1 id">순번</th>
										<th scope="col" class="th-2 title-td">게시글 제목</th>
										<th scope="col" class="th-3 text">댓글내용</th>
										<th scope="col" class="th-4 post-date">작성일</th>
										<th scope="col" class="th-5 hits">조회수</th>
									</tr>
								</thead>
								<tbody class="tbody" id='board-test'>
<%-- 										<c:if test="${empty data}">
												<tr align='center' noshade colspan="5">작성한 게시글이 없습니다.</tr>
										</c:if> --%>
									<c:forEach items="${data.boardList}" var="li">
										<c:if test="${empty data.boardList}">
												<tr align='center' noshade colspan="5">작성한 게시글이 없습니다.</tr>
										</c:if>
										<c:if test="${!empty data}">
											<tr class="border">
												<td class="id">${li.id}</td>
												<td class="title-td text-start"><a href="detail?id=${li.id}"> <span
														class="title">${li.title}</span> <span class="reply">[${li.reply}]</span>
												</a>
												</th>
												<td class="nickname">
													<img class="profile-img" src="/pics/profile/${li.profileImg}" alt="img">
															${li.MNickname}</td>
												<td class="post-date">${li.postDate}</td>
												<td class="hits">${li.hits}</td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>

							<div class="page">
								<nav aria-label="Page navigation">
									<ul id="pagination-ul" class="pagination justify-content-center">
										<%-- 페이징 처리 --%>
											<div id="pagination-previous" class="pagination justify-content-center">
											<c:choose>
												<c:when test="${data.curPage != 1}">
													<li class="page-item"><a class="page-link page-previous"
														href="javascript:void(0);" onclick="setPage(this)" id="pre">＜</a></li>
												</c:when>
												<c:when test="${data.curPage == 1}">
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
													<c:when test="${data.curPage < 3}">
														<c:forEach begin="1" end="5" var="i">
															<c:choose>
																<c:when test="${i == data.curPage}">
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
													<c:when test="${data.curPage >= 3}">																				
														<c:choose>
															<c:when test="${data.totalPage-data.curPage < 2}">												
																<c:forEach begin="${data.totalPage-4}" end="${data.totalPage}" var="i">
																	<c:choose>
																		<c:when test="${i == data.curPage}">
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
																<c:forEach begin="${data.curPage-2}" end="${data.curPage+2}" var="i">											
																	<c:choose>
																		<c:when test="${i == data.curPage}">
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
														<c:when test="${i == data.curPage}">					
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
												<c:when test="${data.curPage != data.totalPage}">
													<li class="page-item"><a class="page-link page-next"
														href="javascript:void(0);" onclick="setPage(this)" id="next">＞</a></li>
												</c:when>
												<c:when test="${data.curPage == data.totalPage}">
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
										<!-- id="tab4-content" 종료-->
									</div>
									<!-- id="mypost" 종료, 탭4 전체 종료 -->

								<!-- 탭5 (친구관리) 시작-->
									<div class="tab-pane fade" id="myfriend" role="tabpane5" aria-labelledby="myfriend-tab">
										<div id="tab5-content" class="site-content">
											<h3 class="archive-title text-center">친구 관리</h3>
											<br/>
<!-- 											<div class="text-end pb-3">
												<select class="gold-border p-1 filter-open">
													<option selected="selected">나를 구독하는 친구</option>
													<option>내가 구독하는 친구</option>
												</select>
											</div> -->
											
												<!--팔로잉/팔로워 변경 버튼-->
												<nav id="tab5-button-nav" style="text-align: center;">
													<button class="tab5-button" data-tab-section="tab5-section-1" onclick="getState(this);" value="1">팔로잉 목록</button>
													<button class="tab5-button" data-tab-section="tab5-section-2" onclick="getState(this);" value="2">팔로워 목록</button>
												</nav>
												<p class="text-right">팔로잉: 내가 추가한 친구<br>팔로워: 나를 추가한 친구</p>
												
												<section id="tab5-section-1" class="tab5-section">
													<div id="following-list" class="row"></div>
													<div id="paging-area-5" class="page common-area"></div>	
												</section>
												
												<section id="tab5-section-2" class="tab5-section" hidden="true">
													<div id="follower-list" class="row">
													</div>
													<div id="paging-area-5-null" class="page common-area">
													</div>
											 	</section>
										
												<!--아래 <script> 부분은 이 위치에서만 작동, 위치를 header로 올리거나 내리면 오류-->
												<script>
							                        const $nav5 = document.querySelector('#tab5-button-nav')
							                        const $sections5 = document.querySelectorAll('.tab5-section');
							
							                        $nav5.addEventListener('click', (e) => {
							                          if (!e.target.classList.contains('tab5-button')) {
							                            return;
							                          }
							                          const focusedTabId = e.target.dataset.tabSection;
							                          $sections5.forEach(($section) => {
							                            if ($section.id === focusedTabId) {
							                              $section.removeAttribute('hidden');
							                            } else {
							                              $section.setAttribute('hidden', true);
							                            }
							                          });
							                        });
							                        
							                        function removeFollow(e) { //친구 구독끊기 버튼 클릭시
						                        		let followId = $('#followId').val();
						                        		console.log("#followId: ", followId);
							                        	
						                        		var result = confirm("정말 구독을 끊으시겠습니까?");
							                        	if (result == true) {
							                        		
							                        		$.ajax({
							                        			type : "POST",
							                        			url : "/mypage/unfollow",
							                        			data : {id: followId},
							                        			success : function(result) {
							                        				if(result=="success"){
										                        		console.log("친구 구독끊기 성공");
								                        				alert("정상적으로 구독 끊기 되었습니다.");
								                        				//location.reload(); //현재 접속중인 페이지를 새로고침
							                        					location.href("/member/mypage");
							                        				}

							                        			} // success 종료
							                        		}); // ajax 종료					
							                        	} else {
							                        		e.preventDefault();
							                        		alert("구독 끊기를 취소하셨습니다.");
							                        		return false;
							                        	}
							                        }
						                      </script>
										</div>
										<!-- id="tab5-content" 종료-->
									</div>
									<!-- id="myfriend" 탭5 종료 -->
								</div>
								<!-- end class="tab-content", 탭5개 전체 종료-->
							</div><!-- end my-content= 탭갈피+탭5개 종료-->
						</div><!-- end id="content", 프로필+ 탭전체영역 종료-->
					</div><!-- end primary(주요 메인영역) 종료-->

					<!-- 사이드 영역(secondary), 모듈화 -->
					<%@ include file="/WEB-INF/views/common/secondary.jsp"%>
			
				</div><!-- end row -->
			</div><!-- end main-container -->
		</div><!-- end main -->
		<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
	</div> <!-- end #page hfeed site -->
</body>
</html>