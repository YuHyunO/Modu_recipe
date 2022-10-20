<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" charset="utf-8" src="/js/timerchk.js"></script>
<header id="masthead"
	class="site-header navbar-fixed-top d-flex align-items-center p-0">
	<div class="header-navigation w-100">
		<div class="container-fluid">
			<div class="row head-row w-100 m-0 p-3">

				<div class="brand-col">
					<div class="site-branding navbar-brand">
						<a href="/">
						<img src="/imgs/logo.png" alt="모두의식탁 logo" title="logo" class="logo"></a>
					</div><!-- end logo -->
				</div><!-- end col-md-3 -->
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
									<!--세션이 존재할 때-->
								<c:if test = "${ sessionScope.email != null }">
									<li class="nav-item">
										<a href='/mypage/main' class="nav-link">마이페이지</a>
									</li>
									<li class="nav-item">
						           		<a href='/member/logout' class="nav-link">로그아웃</a>
						           </li>
						           <li class="welcomelogin">${sessionScope.nickname}님, 반갑습니다.💕 </li>
						        	<li class="welcomelogin">
						        	  <span id="timer"></span><br/>
						        	  <a href="javascript:refreshTimer();">연장하기</a>
						        	</li>
								</c:if>
								<!--세션이 없을 때 = 미로그인시 -->
								<c:if test = "${ sessionScope.email == null }"> 
								 	<li class="nav-item">
										<a href="javascript:alert('로그인 후 이용하실 수 있습니다.'); location.href='/member/login';" class="nav-link"					
											>마이페이지</a>
									</li>
									<li class="nav-item">
										<a class="nav-link" href="/member/login">로그인</a>
									</li> <!--  모달창 사용시 id="loginBtn" or href="/member/login" -->
									<li class="nav-item">
										<a class="nav-link" href="/member/register">회원가입</a>
									</li>
								</c:if>
							</ul>
						</div><!-- end navbar-collapse -->
					</nav><!-- end site-navigation -->
				</div><!-- end col-md-6 -->

				<div class="side-col">
					<nav class="social-navigation p-0">
						<div class="social-container py-0">
							<ul class="social-menu">
							</ul>
						</div>
						<!-- end social-container -->
						<div class="search-container py-1">
							<a href="/recipe/list">
							<img src="/imgs/search.svg" alt="검색버튼"
								title="search" class="nav-search"></a>
						</div><!-- end search-container -->
					</nav>
				</div><!-- end col-md-3 -->
			</div><!-- end row -->
		</div><!-- end container-fluid -->
	</div><!-- end header-navigation -->
</header><!-- end #masthead -->

<div class="collapse navbar-collapse px-5 mb-3"
	id="navbarSupportedContent">
	<ul class="navbar-nav ms-3">
		<li class="nav-item active"><a class="nav-link" href="/">홈
			<span class="sr-only">(current)</span></a>
		</li>
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
			<!--세션이 존재할 때-->
		<c:if test = "${ sessionScope.email != null }">
			<li class="nav-item">
				<a href='/member/mypage' class="nav-link">마이페이지</a>
			</li>
			<li class="nav-item">
           		<a href='/member/logout' class="nav-link">로그아웃</a>
           </li>
           <li class="welcomelogin_dropdown">${sessionScope.nickname}님, 반갑습니다.💕 </li>
		</c:if>
		<!--세션이 없을 때(미로그인) -->
		<c:if test = "${ sessionScope.email == null }"> 
		 	<li class="nav-item">
				<a href="javascript:alert('로그인 후 이용하실 수 있습니다.'); location.href='/member/login';" class="nav-link"					
					>마이페이지</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/member/login">로그인</a>
			</li> <!--  모달창 사용시 id="loginBtn" or href="/member/login" -->
			<li class="nav-item">
				<a class="nav-link" href="/member/register">회원가입</a>
			</li>
		</c:if>
	</ul>
</div><!-- end navbar-collapse -->
