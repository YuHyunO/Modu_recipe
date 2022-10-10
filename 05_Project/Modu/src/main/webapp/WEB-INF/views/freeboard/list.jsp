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
										<select class="gold-border p-1 filter-period">
											<option selected="selected">전체기간</option>
											<option>1일</option>
											<option>1주</option>
											<option>1개월</option>
											<option>6개월</option>
											<option>1년</option>
										</select> <select class="gold-border p-1 filter-group">
											<option selected="selected">제목만</option>
											<option>글작성자</option>
											<option>댓글내용</option>
											<option>댓글작성자</option>
										</select>
									</div>
									<div class="d-flex justify-content-end search-box">
										<input id="search" class="border px-2" type="search"
											name="search" placeholder="검색어를 입력해주세요" value="">
										<button type="submit" class="search-btn border">검색</button>
										<select class="gold-border p-1 ms-2">
											<option selected="selected">10</option>
											<option>15</option>
											<option>20</option>
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
								<tbody class="tbody">
									<tr class="border">
										<td class="id">10</td>
										<td class="title-td text-start"><a href="detail/"> <span
												class="title">상세페이지</span> <span class="reply">[1]</span>
										</a>
										</th>
										<td class="nickname">닉네임</td>
										<td class="post-date">2022.09.27</td>
										<td class="hits">3000</td>
									</tr>
									<tr class="border">
										<td class="id">9</td>
										<td class="title-td text-start"><a href="#"> <span
												class="title">[美친특가] 10/31 유통기한 임박 LeTAO 르타오 프로마쥬 치즈
													케이크...</span> <span class="reply">[1]</span>
										</a>
										</th>
										<td class="nickname">닉네임</td>
										<td class="post-date">2022.09.27</td>
										<td class="hits">3000</td>
									</tr>
									<tr class="border">
										<td class="id">8</td>
										<td class="title-td text-start"><a href="#"> <span
												class="title">[美친특가] 10/31 유통기한 임박 LeTAO 르타오 프로마쥬 치즈
													케이크...</span> <span class="reply">[1]</span>
										</a>
										</th>
										<td class="nickname">닉네임</td>
										<td class="post-date">2022.09.27</td>
										<td class="hits">3000</td>
									</tr>
									<tr class="border">
										<td class="id">7</td>
										<td class="title-td text-start"><a href="#"> <span
												class="title">[美친특가] 10/31 유통기한 임박 LeTAO 르타오 프로마쥬 치즈
													케이크...</span> <span class="reply">[1]</span>
										</a></td>
										<td class="nickname">닉네임</td>
										<td class="post-date">2022.09.27</td>
										<td class="hits">3000</td>
									</tr>
									<tr class="border">
										<td class="id">6</td>
										<td class="title-td text-start"><a href="#"> <span
												class="title">[美친특가] 10/31 유통기한 임박 LeTAO 르타오 프로마쥬 치즈
													케이크...</span> <span class="reply">[1]</span>
										</a>
										</th>
										<td class="nickname">닉네임</td>
										<td class="post-date">2022.09.27</td>
										<td class="hits">3000</td>
									</tr>
									<tr class="border">
										<td class="id">5</td>
										<td class="title-td text-start"><a href="#"> <span
												class="title">[美친특가] 10/31 유통기한 임박 LeTAO 르타오 프로마쥬 치즈
													케이크...</span> <span class="reply">[1]</span>
										</a>
										</th>
										<td class="nickname">닉네임</td>
										<td class="post-date">2022.09.27</td>
										<td class="hits">3000</td>
									</tr>
									<tr class="border">
										<td class="id">4</td>
										<td class="title-td text-start"><a href="#"> <span
												class="title">[美친특가] 10/31 유통기한 임박 LeTAO 르...</span> <span
												class="reply">[1]</span>
										</a>
										</th>
										<td class="nickname">닉네임</td>
										<td class="post-date">2022.09.27</td>
										<td class="hits">3000</td>
									</tr>
									<tr class="border">
										<td class="id">3</td>
										<td class="title-td text-start"><a href="#"> <span
												class="title">주방미학 티타늄 반상기 2인세트(10pcs)</span> <span
												class="reply">[5]</span>
										</a>
										</th>
										<td class="nickname">닉네임</td>
										<td class="post-date">2022.09.27</td>
										<td class="hits">3000</td>
									</tr>
									<tr class="border">
										<td class="id">2</td>
										<td class="title-td text-start"><a href="#"> <span
												class="title">9/27일이후출고 [만개특가] 케어팟 가습기...</span> <span
												class="reply">[3]</span>
										</a>
										</th>
										<td class="nickname">닉네임</td>
										<td class="post-date">2022.09.28</td>
										<td class="hits">8400</td>
									</tr>
									<tr class="border">
										<td class="id">1</td>
										<td class="title-td text-start"><a href="#"> <span
												class="title">[美친특가] 10/31 유통기한 임박 LeTAO 르타오...</span> <span
												class="reply">[1]</span>
										</a>
										</th>
										<td class="nickname">닉네임</td>
										<td class="post-date">2022.09.27</td>
										<td class="hits">3000</td>
									</tr>
								</tbody>
							</table>
							<div class="write text-end my-3">
								<button type="button" id="write-btn"
									class="gold-border gold-btn"
									onclick="location.href='/freeboard/write'">글쓰기</button>
							</div>
							<div class="page">
								<nav aria-label="Page navigation">
									<ul class="pagination justify-content-center">
										<li class="page-item"><a class="page-link page-previous"
											href="#">＜</a></li>
										<li class="page-item"><a
											class="page-link active page-number"
											href="javascript:void(0);" onclick="activePage(this)">1</a></li>
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