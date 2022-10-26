<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<!-- 공통 부분 END -->
<link href="/css/board-list.css" rel="stylesheet">
<script src="/js/board-list.js"></script>

<title>모두의 식탁 - 자유게시판 목록</title>

</head>
<body>

	<div id="page" class="hfeed site">
		<!-- start page wrapper -->
		<%@ include file="/WEB-INF/views/common/menu.jsp"%>
		<div class="head-title py-4 m-0">
			<div class="container">
				<p class="page-title m-0 fs-2">free board</p>
			</div>
			<!-- end container -->
		</div>
		<!-- end head-title -->

		<div class="main">
			<div
				class="main-container row d-flex justify-content-center m-0 py-4">
				<div class="main-size col-md-8 row justify-content-center">

					<div id="board-list" class="my-3 row px-0">
						<div class="container p-0">
							<table class="board-table text-center m-0 w-100">
								<div id="board-search"
									class="mb-3 d-flex justify-content-between align-items-center">
									<div class="selects">
								<select id="period-selector"class="gold-border p-1 filter-period" onchange="setData()" style="display: none;">
									<option value="0">전체기간</option>
									<option value="1">1일</option>
									<option value="7">1주</option>
									<option value="30">1개월</option>
									<option value="180">6개월</option>
									<option value="365">1년</option>
								</select> 
								<select id="name-selector" class="gold-border p-1 filter-group">
									<option value="title">제목만</option>
									<option value="mNickname" >글작성자</option>
									<option style="display: none;" value=reply>댓글내용</option>
									<option style="display: none;" value="replyWriter"> 댓글작성자</option>
								</select>
									</div>
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
										<th scope="col" class="th-2 title-td">제목</th>
										<th scope="col" class="th-3 nickname">작성자</th>
										<th scope="col" class="th-4 post-date">작성일</th>
										<th scope="col" class="th-5 hits">조회</th>
									</tr>
								</thead>
								<tbody class="tbody" id='board-test'>
									<c:if test="${empty data}">
											<tr align='center' noshade colspan="5">데이터가 없습니다.</tr>
									</c:if>
									<c:forEach items="${data.boardList}" var="li">
										<tr class="border">
											<td class="id">${li.id}</td>
											<td class="title-td text-start"><a href="detail?id=${li.id}"> <span
													class="title">${li.title}</span> <span class="reply">[${li.reply}]</span>
											</a>
											</th>
											<td class="nickname">${li.MNickname}</td>
											<td class="post-date">${li.postDate}</td>
											<td class="hits">${li.hits}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="write text-end my-3">
								<c:if test="${sessionScope.email == null}">
									<a href="javascript:alert('로그인 후 이용하실 수 있습니다.'); location.href='/member/login';" class="nav-link"					
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
						</div>
					</div>
					<div></div>
					<!-- end main-size -->
				</div>
				<!-- end main-container -->
			</div>
			<!-- end main-size -->
		</div>
		<!-- end main -->
		<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
	</div>
	<!-- end #page hfeed site -->
</body>
</html>