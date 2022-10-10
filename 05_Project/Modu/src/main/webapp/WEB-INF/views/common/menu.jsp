<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header id="masthead"
	class="site-header navbar-fixed-top d-flex align-items-center p-0">
	<div class="header-navigation w-100">
		<div class="container-fluid">

			<div class="row head-row w-100 m-0 p-3">

				<div class="brand-col">
					<div class="site-branding navbar-brand">
						<a href="/"><img src="/imgs/logo.png" alt="모두의식탁" title="logo"
							class="logo"></a>
					</div>
					<!-- end logo -->
				</div>
				<!-- end col-md-3 -->

				<div class="menu-col">
					<nav class="site-navigation navbar navbar-expand-lg navbar-light">

						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse">
							<ul class="navbar-nav">
								<li class="nav-item active"><a class="nav-link" href="/">홈<span
										class="sr-only">(current)</span></a></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown1"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">레시피</a>
									<div class="dropdown-menu" aria-labelledby="navbarDropdown1">
										<a class="dropdown-item" href="/recipe/list">레시피 목록</a> <a
											class="dropdown-item" href="/recipe/detail">레시피 상세</a> <a
											class="dropdown-item" href="/recipe/write">레시피 등록</a>
									</div></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown3"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">게시판</a>
									<div class="dropdown-menu" aria-labelledby="navbarDropdown3">
										<a class="dropdown-item" href="/notice/list">공지사항</a> <a
											class="dropdown-item" href="/freeboard/list">자유게시판</a>
									</div></li>
								<li class="nav-item dropdown"><a class="nav-link"
									href="/member/mypage">마이페이지</a></li>
								<li class="nav-item"><a class="nav-link"
									href="/member/login">로그인</a></li>
								<li class="nav-item"><a class="nav-link"
									href="/member/signup">회원가입</a></li>
							</ul>
						</div>
						<!-- end navbar-collapse -->
					</nav>
					<!-- end site-navigation -->
				</div>
				<!-- end col-md-6 -->

				<div class="side-col">
					<nav class="social-navigation p-0">
						<div class="social-container py-0">
							<ul class="social-menu">
								<!-- <li><a href="#"><i class="fab fa-facebook-square"></i></a></li>
                                        <li><a href="#"><i class="fab fa-twitter"></i></a></li>
                                        <li><a href="#"><i class="fab fa-instagram"></i></a></li> -->
							</ul>
						</div>
						<!-- end social-container -->
						<div class="search-container py-1">
							<a href="/recipe/list"><img src="/imgs/search.svg" alt="검색버튼"
								title="search" class="nav-search"></a>
						</div>
						<!-- end search-container -->
					</nav>
				</div>
				<!-- end col-md-3 -->

			</div>
			<!-- end row -->

		</div>
		<!-- end container-fluid -->
	</div>
	<!-- end header-navigation -->
</header>
<!-- end #masthead -->
<div class="collapse navbar-collapse px-5 mb-3"
	id="navbarSupportedContent">
	<ul class="navbar-nav ms-3">
		<li class="nav-item active"><a class="nav-link" href="/">홈<span
				class="sr-only">(current)</span></a></li>
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="navbarDropdown1" role="button" data-toggle="dropdown"
			aria-haspopup="true" aria-expanded="false">레시피</a>
			<div class="dropdown-menu" aria-labelledby="navbarDropdown1">
				<a class="dropdown-item" href="/recipe/list">레시피 목록</a> <a
					class="dropdown-item" href="/recipe/detail">레시피 상세</a> <a
					class="dropdown-item" href="/recipe/write">레시피 등록</a>
			</div></li>
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="navbarDropdown3" role="button" data-toggle="dropdown"
			aria-haspopup="true" aria-expanded="false">게시판</a>
			<div class="dropdown-menu" aria-labelledby="navbarDropdown3">
				<a class="dropdown-item" href="/notice/list">공지사항</a> <a
					class="dropdown-item" href="/freeboard/list">자유게시판</a>
			</div></li>
		<li class="nav-item dropdown"><a class="nav-link"
			href="/member/mypage">마이페이지</a></li>
		<li class="nav-item"><a class="nav-link" href="/member/login">로그인</a></li>
		<li class="nav-item"><a class="nav-link" href="/member/signup">회원가입</a></li>
	</ul>
</div>
<!-- end navbar-collapse -->